function doPagination() {
	var pag_holder = $("ul.holder");
	if(pag_holder.length == 0) {return;}
	pag_holder.jPages({
		containerID: "project-list-pagination",
		perPage: 50
	});
}

/*function pageChange(){
	if($("input[name=NewUser]").val() == ''){
		$(window).on('beforeunload', function(){ 
			return false;
		});
	}
	
}*/

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
				startText: "", emptyText: emptyTextOpt, asHtmlID: autocomplete_attr[index], preFill: prefill, neverSubmit: true});
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
	var editableFields = $(".editable");
	editableFields.each(function(idx, el){
		if(!$(el).hasClass("hidden") && $(".as-selections", $(this).parent()).length > 0){
			$(".view-suggestion", $(this).parent().next()).show();
		}
	});
	edit_btn.click(function(){
		$(this).parent().fadeOut("slow", function(){
			$(this).next().fadeIn();
			if($(".as-selections", $(this).parent()).length > 0){
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
	});	$(window).on('beforeunload', function(){ 
		if($("input[name=NewUser]").val() == ''){
			alert($("input[name=NewUser]").val());
			return false;
		}
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
				alert('Invalid email. Correct this in order to save changes.');
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
	var proj_list = $(".project-list, .additions_table");
	if(proj_list.length == 0){ return;}
	proj_list.tablesorter().tablesorterPager({container: $("#pager"), positionFixed: true});
	var defaultSort = $(".default-sort");
	if(defaultSort.length > 0){
		defaultSort.trigger("click");
		defaultSort.trigger("click");
	}
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
		var apply_form = $(".apply-form");
		var id = $(this).attr("id");	$(window).on('beforeunload', function(){ 
			if($("input[name=NewUser]").val() == ''){
				alert($("input[name=NewUser]").val());
				return false;
			}
		});
		if(id[0] == "a")
			$("input[name=app-id]", apply_form).val(id.substring(1));
		else
			$("input[name=id]", apply_form).val($(this).attr("id"));
		apply_form.dialog({
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
	var selector = null;
	var no_results = null;
	$("form#filter-list").submit(function(){
		return false;
	});
	$(searchbox).keyup(function(){
		if($(".project-list tbody tr").length > 0){
			selector = ".project-list tbody tr";
			no_results = ".project-list";
		}
		if($(".additions_table tbody tr").length > 0){
			selector = ".additions_table tbody tr";
			no_results = ".additions_table";
		}
		if(selector == null){ return; }
		var searchVals = new Array();
		var searchInput = $(this).val().trim().toLowerCase();
		if(!searchInput){
			$(selector).not(".hidden").show();
		}
		else{
			$(selector).hide();
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
			if(searchVals.length > 0)
				$.each(searchVals, function(idx, val){
					selector += "[class*="+val+"]";
				});
			$(selector+", " + no_results + " tr.no-results").not(".hidden").show();
		}
		var nResultsContainer = $(".search-container .num-results")
		if(nResultsContainer.length == 0) {return;}
		var nResults = $(selector).length;
		$(".search-container .num-results").html(nResults + " result(s) found");
	});
}

function initInvite()
{
	var inviteBtn = $("a.invite");
	if(inviteBtn.length == 0) {return;}
	inviteBtn.click(function(){
		var id = $(this).attr("id");
		var invite_form = $("#invite-form-"+id);
		$("input[name=stud-id]", invite_form).val(id);
		invite_form.dialog({
			  height: 200,
		      width: 500,
		      modal: true
		});
		return false;
	});
}

function initSelectRole(){
	var account_name = $("span.account-name");
	if(account_name.length == 0){ return;}
	$(account_name).click(function(){
		$("input[type=radio]", $(this).prev()).prop('checked', true);
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
	initInvite();
	initSelectRole();
	//pageChange();
});
