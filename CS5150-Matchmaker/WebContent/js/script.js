function initSideHeight()
{
	var sidebar = $(".sidebar #sidenav");
	if(sidebar.length == 0) {return; }
	if($(".main .content").height() > sidebar.height())
		sidebar.height($(".main .content").height());
	else
		$(".main .content").height(sidebar.height());
}

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
	var majorInput = $(".info input[name=major], #filter-list input[name=filter-major]");
	var minorInput = $(".info input[name=minor]");
	var collegeInput = $(".info input[name=college]");
	var skillsInput = $(".application-info input[name=skills], .info input[name=skills], #filter-list input[name=filter-skill]");
	var researchInput = $(".application-info input[name=research-area], .info input[name=research_interests], #filter-list input[name=filter-interest]");

	if(majorInput.length){
		majorInput.autoSuggest(majorData.items, {selectedItemProp: "name", searchObjProps: "name",  
			startText: "",asHtmlID: "major", preFill: prefillMajor.items});
	}
	if(minorInput.length){
		minorInput.autoSuggest(minorData.items, {selectedItemProp: "name", searchObjProps: "name", 
			startText: "",asHtmlID: "minor", preFill: prefillMinor.items});
	}
	if(collegeInput.length){
		collegeInput.autoSuggest(collegeData.items, {selectedItemProp: "name", searchObjProps: "name", 
			startText: "",asHtmlID: "college", preFill: prefillCollege.items});
	}
	if(skillsInput.length){
		skillsInput.autoSuggest(skillsData.items, {selectedItemProp: "name", searchObjProps: "name", 
			startText: "",asHtmlID: "skills", preFill: prefillSkills.items});
	}
	if(researchInput.length){
		researchInput.autoSuggest(interestData.items, {selectedItemProp: "name", searchObjProps: "name", 
			startText: "",asHtmlID: "research", preFill: prefillInterests.items});
	}

}

function EditField()
{
	var edit_btn = $("a.edit-btn");
	if(edit_btn.length == 0) { return; }
	edit_btn.click(function(){
		$(this).parent().fadeOut("slow", function(){
			$(this).next().fadeIn();
		});

		return false;
	});
}

function handleFilterText(){
	var formEl = $("#add-new-filters");
	var filters = $(".filters");
	if(formEl.length == 0) {return; }
	formEl.submit(function(){
		var inputFields = $("li input[type=text]", this);

		inputFields.each(function(index, el){
			if($(el).val().length != 0 && filters.length != 0){
				filters.append('<input type="checkbox" name="' + $(el).attr("name") + '">' + $(el).val());
				filters.last().click(handleSingleCheckbox);
				$(el).val('');
			}
		});
		return false;
	});
}

function handleFilterCheckboxes(){
	var checkboxes = $(".filters input[type=checkbox]");
	if(checkboxes.length == 0) {return;}
	checkboxes.each(function(index, el){
		$(el).click(handleSingleCheckbox);
	});
}

function handleSingleCheckbox(){
	var items = $(".project-list li");
	console.log("hiding " + items.length + " items");
	items.hide();
	var rand1 = Math.floor((Math.random()*500)+1);
	var rand2 = Math.floor((Math.random()*500)+rand1);
	console.log("rand1=" + rand1 + " rand2=" + rand2);
	items.slice(rand1, rand2).show();
	doPagination();
	initSideHeight();
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
				console.log($(el).parent().html());
				if($(el).val().length == 0){
					e.preventDefault();
					return false;
				}
			});
		}
		if(hiddenElReq.length){
			hiddenElReq.each(function(index, el){
				console.log($(el).parent().html());
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

function filterAll()
{
	var filterLink = $(".filters .filter-all");
	if(filterLink.length == 0){return;}
	var checkboxes = $(".filters input[type=checkbox]");
	filterLink.click(function(){
		checkboxes.each(function(idx, el){
			$(el).prop("checked", true);
		});
		handleSingleCheckbox();
	});
}

function sortTable()
{
	$(".project-list").tablesorter()
	.tablesorterPager({container: $("#pager"), positionFixed: false});
}

$(document).ready(function(){
	initAutosuggest();
	sortTable();
	EditField();
	handleAddCourse();
	validateFormSubmit();
	hideProject();
	filterAll();
	initSideHeight();
});