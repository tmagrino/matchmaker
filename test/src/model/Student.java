package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Student database table.
 * 
 */
@Entity
@NamedQuery(name="Student.findAll", query="SELECT s FROM Student s")
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idstudent;

	private String college;

	private double gpa;

	private String major;

	private String minor;

	private String name;

	private String netid;

	private int year;

	//bi-directional many-to-one association to Project
	@OneToMany(mappedBy="studentBean")
	private List<Project> projects;

	public Student() {
	}
	
	public Student(int id, String name, double gpa){
		
		this.idstudent = id;
		this.name = name;
		this.gpa = gpa;
		
		
	}
	public int getIdstudent() {
		return this.idstudent;
	}

	public void setIdstudent(int idstudent) {
		this.idstudent = idstudent;
	}

	public String getCollege() {
		return this.college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public double getGpa() {
		return this.gpa;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	public String getMajor() {
		return this.major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getMinor() {
		return this.minor;
	}

	public void setMinor(String minor) {
		this.minor = minor;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNetid() {
		return this.netid;
	}

	public void setNetid(String netid) {
		this.netid = netid;
	}

	public int getYear() {
		return this.year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public List<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public Project addProject(Project project) {
		getProjects().add(project);
		project.setStudentBean(this);

		return project;
	}

	public Project removeProject(Project project) {
		getProjects().remove(project);
		project.setStudentBean(null);

		return project;
	}

}