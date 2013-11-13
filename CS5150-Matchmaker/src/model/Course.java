package model;

import javax.persistence.Embeddable;

@Embeddable
public class Course {
	private String coursenum;
	private String title;
	private String grade;
	private String semester;
	
	public Course(String coursenum, String title, String grade, String semester) {
		this.coursenum = coursenum;
		this.title = title;
		this.grade = grade;
		this.semester = semester;
	}

	public String getCoursenum(){
		return coursenum;
	}
	
	public String getTitle(){
		return title;
	}
	
	public String getGrade(){
		return grade;
	}
	
	public String getSemester(){
		return semester;
	}
	public void setCoursenum(String coursenum){
		this.coursenum = coursenum;
	}
	public void setTitle(String title){
		this.title = title;
	}
	public void setGrade(String grade){
		this.grade = grade;
	}
	public void setSemester(String semester){
		this.semester = semester;
	}
}
