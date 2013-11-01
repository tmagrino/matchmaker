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
	var skillsInput = $(".application-info input[name='skills']");
	var researchInput = $(".application-info input[name='research-interest']");
	
	if(skillsInput.length == 0 && researchInput.length == 0) {return;}
	var skillsData = {items: [
	                  		{value: "0", name: "C"},
	                		{value: "1", name: "C++"},
	                		{value: "2", name: "Java"},
	                		{value: "3", name: "Javascript"},
	                		{value: "4", name: "Python"}
	                	]};
	var researchData = {items: [
		                  		{value: "ml", name: "Machine Learning"},
		                		{value: "ai", name: "Artificial Intelligence"},
		                		{value: "nlp", name: "Natural Language Processing"}
		                	]};
	
	
	if(skillsInput.length){
		skillsInput.autoSuggest(skillsData.items, {selectedItemProp: "name", searchObjProps: "name", startText: ""});
	}
	if(researchInput.length){
		researchInput.autoSuggest(researchData.items, {selectedItemProp: "name", searchObjProps: "name", startText: ""});
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

$(document).ready(function(){
	initAutosuggest();
	doPagination();
	handleFilterText();
	handleFilterCheckboxes();
	initSideHeight();
});

