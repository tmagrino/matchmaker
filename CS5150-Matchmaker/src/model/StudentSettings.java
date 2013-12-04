package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
	private long id;
	
	@OneToOne(cascade = CascadeType.ALL)
	Student student;
	
	@ManyToMany
	@JoinTable(
			name = "HIDDEN_PROJ",
			joinColumns = {@JoinColumn(name="SET_ID", referencedColumnName="ID")},
			inverseJoinColumns = {@JoinColumn(name="PROJ_ID", referencedColumnName="ID")}
	)
	List<Project> hiddenProjects;
	
	public StudentSettings(){
		hiddenProjects = new ArrayList<Project>();
	}
	public void addProject(Project p){
		hiddenProjects.add(p);
	}
	
}
