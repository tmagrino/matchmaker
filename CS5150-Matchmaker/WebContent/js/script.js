function doPagination() {
	var pag_holder = $("ul.holder");
	if(pag_holder.length == 0) {return;}
	pag_holder.jPages({
		containerID: "project-list-pagination",
		perPage: 50
	});
}

function initAutosuggest()
{
	if(typeof autocomplete_attr === 'undefined' || autocomplete_attr == null){return;}
	var autocomplete_els = Array();
	$.each(autocomplete_attr, function(index, value){
		autocomplete_els.push($(".info input[name=" + value + "], " +
				".project-list input[name=filter-" + value + "]"));
	});
	var emptyTextOpt = "No results found.  Press Tab to add new entry.";
	$.each(autocomplete_els, function(index, value){
		if(jsonArrStud.length == 0)
			prefill = null;
		else
			prefill = jsonArrStud[index].items;
		if(value.length){
			value.autoSuggest(jsonArrAll[index].items, {selectedItemProp: "name", searchObjProps: "name",  
				startText: "", emptyText: emptyTextOpt, asHtmlID: autocomplete_attr[index], preFill: prefill});
		}
	});
}

function initViewSuggestion()
{
	if(typeof autocomplete_attr === 'undefined' || autocomplete_attr == null){return;}
	var add_btn = $(".view-suggestion");
	if(add_btn.length == 0) {return; }
	$.each(autocomplete_attr, function(attrIdx, attr){
		var output = '';
		$.each(jsonArrAll[attrIdx].items, function(itemIdx, item){
			output += (item.name + "<br/>");
		});
		$("#all-"+attr).html(output);
	});
	add_btn.each(function(idx, el){
		$(el).click(function(){
			var name = $(".as-original input[type=text]", $(this).parent().prev()).attr("name");
			$("#all-"+name).dialog({
				modal: true
			});
		});
	});
}

function EditField()
{
	var edit_btn = $("a.edit-btn");
	if(edit_btn.length == 0) { return; }
	edit_btn.click(function(){
		$(this).parent().fadeOut("slow", function(){
			$(this).next().fadeIn();
			if($(".as-selections", $(this).parent()).length > 0 || $("select", $(this).parent()).length > 0){
				$(".view-suggestion", $(this).parent().next()).fadeIn();
			}
		});

		return false;
	});
}


function handleAddCourse(){
	var addbtn = $("#add-course");
	if(addbtn.length == 0) {return;}
	var courseTable = $("#profile-courses");
	var tableRowHtml = courseTable.find("tr").html();
	addbtn.click(function(){
		courseTable.append("<tr>"+tableRowHtml+"</tr>");
		initSideHeight();
	});
}

function IsEmail(email) {
	var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	return regex.test(email);
}

function validateFormSubmit()
{
	var formEl = $("form");
	if(formEl.length == 0){return;}
	formEl.submit(function(e){
		var emailEl = $("input[name=email]");
		if(emailEl.length){
			if(!IsEmail(emailEl.val())){
				e.preventDefault();
				return false;
			}
		}
		var textElReq = $(".required input[type=text]").not($(".as-selections input[type=text]"));
		var hiddenElReq = $(".required .as-selections input[type=hidden]");
		if(textElReq.length){
			textElReq.each(function(index, el){
				if($(el).val().length == 0){
					e.preventDefault();
					return false;
				}
			});
		}
		if(hiddenElReq.length){
			hiddenElReq.each(function(index, el){
				if($(el).val().length == 0){
					e.preventDefault();
					return false;
				}
			});
		}
	});
}

function hideProject()
{
	var delButton = $(".project-info .delete");
	if(delButton.length == 0) {return;}
	delButton.click(function(){
		$(this).parent().parent().animate({
			opacity: 0,
			height: 'toggle'
		}, 1000);
		doPagination();
		initSideHeight();
	});
}

function sortTable()
{
	var proj_list = $(".project-list");
	if(proj_list.length == 0){ return;}
	proj_list.tablesorter()
	.tablesorterPager({container: $("#pager"), positionFixed: false});
}

function initTabLinks(){
	var tabEl = $(".tabrow li");
	if(tabEl.length == 0) {return;}
	tabEl.click(function(){
		window.location = $("a", this).attr("href"); 
	});
}

function initApplyButton()
{
	var applyBtn = $("a.apply");
	applyBtn.click(function(){
		$(".apply-form", $(this.parent().prev())).dialog({
			  height: 200,
		      width: 500,
		      modal: true
		});
		return false;
	});
}

function initFilterType()
{
	var searchbox = $("form#filter-list .search-text");
	if(searchbox.length == 0){return;}
	$("form#filter-list").submit(function(){
		return false;
	});
	$(searchbox).keyup(function(){ 
		var searchVals = new Array();
		var searchInput = $(this).val().trim().toLowerCase();
		if(!searchInput){
			$(".project-list tbody tr").not(".hidden").show();
		}
		else{
			$(".project-list tbody tr").hide();
			var quotedInputArr = searchInput.match(/"(.*?)"/);
			if(quotedInputArr){
				$.each(quotedInputArr, function(idx, quotedInput){
					quotedInput = quotedInput.replace(/ /g, "_");
					searchVals.push(quotedInput);
				});
			}
			searchInput = searchInput.replace(/"(.*?)"?/g, "");
			var searchSplit = searchInput.split(" ");
			if(searchSplit.length > 0)
				$.each(searchSplit, function(idx, val){
					searchVals.push(val);
				});
			var selector = ".project-list tbody tr";
			if(searchVals.length > 0)
				$.each(searchVals, function(idx, val){
					selector += "[class*="+val+"]";
				});
			$(selector+", .project-list tr.no-results").not(".hidden").show();
		}
	});
}

$(document).ready(function(){
	initAutosuggest();
	initViewSuggestion();
	initApplyButton();
	sortTable();
	EditField();
	handleAddCourse();
	validateFormSubmit();
	initTabLinks();
	hideProject();
	initFilterType();
});
