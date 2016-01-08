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
import javax.persistence.Version;

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

	@Version
	long version = 0;

	private List<Project> hiddenProjects;
	
	public StudentSettings(){
		hiddenProjects = new ArrayList<Project>();
	}
	
	public List<Project> getHiddenProjects() {
		return hiddenProjects;
	}
	
	void addProject(Project p) {
		if (!hiddenProjects.contains(p)) {
			hiddenProjects.add(p);
			if (p.getHiddenBy().contains(this)) {
				p.addHidden(this);
			}
		}
	}
	
	void removeProject(Project p) {
		if (hiddenProjects.remove(p)) {
			if (p.getHiddenBy().contains(this)) {
				p.removeHidden(this);
			}
		}
	}
	
	void removeProjects() {
		for (Project p : hiddenProjects) {
			p.getHiddenBy().remove(this);
		}
		hiddenProjects = new ArrayList<Project>();
	}
	
	public Student getStudent() {
		return student;
	}
	
	void setStudent(Student s) {
		student = s;
	}
	
}
