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
	var majorInput = $(".info input[name='major']");
	var minorInput = $(".info input[name='minor']");
	var collegeInput = $(".info input[name='school']");
	var skillsInput = $(".application-info input[name='skills']");
	var researchInput = $(".application-info input[name='research-interest']");
	
	if(skillsInput.length == 0 && researchInput.length == 0) {return;}
	
	var majorData = {items: [
		                  		{value: "0", name: "CS"},
		                		{value: "1", name: "Medice"},

		                	]};
	var minorData = {items: [
			                  		{value: "0", name: "Music"},
			                		{value: "1", name: "Statistics"},
			                		
			                	]};
	var collegeData = {items: [
		                  		{value: "0", name: "Arts & Sciences"},
		                		{value: "1", name: "College of Engineering"},
		                		
		                	]};
	var skillsData = {items: [
	                  		{value: "C", name: "C"},
	                		{value: "C++", name: "C++"},
	                		{value: "Java", name: "Java"},
	                		{value: "Javascript", name: "Javascript"},
	                		{value: "Python", name: "Python"}
	                	]};
	var researchData = {items: [
		                  		{value: "ml", name: "Machine Learning"},
		                		{value: "ai", name: "Artificial Intelligence"},
		                		{value: "nlp", name: "Natural Language Processing"}
		                	]};
	
	if(majorInput.length){
		majorInput.autoSuggest(majorData.items, {selectedItemProp: "name", searchObjProps: "name", startText: "",asHtmlID: "major"});
	}
	if(minorInput.length){
		minorInput.autoSuggest(minorData.items, {selectedItemProp: "name", searchObjProps: "name", startText: "",asHtmlID: "minor"});
	}
	if(collegeInput.length){
		collegeInput.autoSuggest(collegeData.items, {selectedItemProp: "name", searchObjProps: "name", startText: "",asHtmlID: "college"});
	}
	if(skillsInput.length){
		skillsInput.autoSuggest(skillsData.items, {selectedItemProp: "name", searchObjProps: "name", startText: "",asHtmlID: "skills"});
	}
	if(researchInput.length){
		researchInput.autoSuggest(researchData.items, {selectedItemProp: "name", searchObjProps: "name", startText: "",asHtmlID: "research"});
	}
	
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
				handleSingleCheckbox(filters.last());
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
		handleSingleCheckbox($(el));
	});
}

function handleSingleCheckbox(el){
	var items = $(".project-list li");
	el.click(function(){
		items.hide();
		var rand1 = Math.floor((Math.random()*500)+1);
		var rand2 = Math.floor((Math.random()*500)+rand1);
		items.slice(rand1, rand2).show();
		doPagination();
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

$(document).ready(function(){
	initAutosuggest();
	doPagination();
	handleFilterText();
	handleFilterCheckboxes();
	handleAddCourse();
	initSideHeight();
});

