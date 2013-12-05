package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

/*
 * This class represent a student's settings for things such as:
 * - Email notifications
 * - Search filters
 */
@Entity
public class StudentSettings {
	
	@Id @Column(name = "ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Student student;
	
	@ManyToMany (cascade = CascadeType.ALL)
	@JoinTable(
			name = "HIDDEN_PROJ",
			joinColumns = {@JoinColumn(name="SET_ID", referencedColumnName="ID")},
			inverseJoinColumns = {@JoinColumn(name="PROJ_ID", referencedColumnName="ID")}
	)
	private List<Project> hiddenProjects;
	
	public StudentSettings(){
		hiddenProjects = new ArrayList<Project>();
	}
	
	public List<Project> getHiddenProjects() {
		return hiddenProjects;
	}
	
	public void addProject(Project p) {
		if (!hiddenProjects.contains(p)) {
			hiddenProjects.add(p);
		}
	}
	
	public void removeProject(Project p) {
		hiddenProjects.remove(p);
	}
	
	public Student getStudent() {
		return student;
	}
	
	void setStudent(Student s) {
		student = s;
	}
	
}
