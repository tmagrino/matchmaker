function initSideHeight()
{
	var sidebar = $(".sidebar");
	if(sidebar.length == 0) {return; }
	sidebar.height($(".main .content").height());
}

function initAutosuggest()
{
	var skillsData = ['C', 'C++', 'Java', 'Javascript', 'Python'];
	var researchData = ['Machine Learning', 'Artificial Intelligence', 'Natural Language Processing'];
	
	var skillsInput = $("input[name='skills']");
	var researchInput = $("input[name='research-interest']");
	
	if(skillsInput.length == 0 && researchInput.length == 0) {return}
	if(skillsInput.length){
		skillsInput.autoSuggest(skilsData);
	}
	if(researchInput.length){
		researchInput.autoSuggest(researchData);
	}
}

$(document).ready(function(){
	initSideHeight();
	//initAutosuggest();
});