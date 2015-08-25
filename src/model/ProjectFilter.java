package model;
import java.util.List;

import model.Skill;


public class ProjectFilter {

	boolean project_name;
	boolean researcher_name;
	boolean description;
	boolean research_area;
	boolean required_skills;
	String filter;
	List<Skill> skills;
	
	public ProjectFilter() {
		
	}
}
