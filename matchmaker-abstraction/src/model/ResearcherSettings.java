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
public class ResearcherSettings {	
	@Id @Column(name = "ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Researcher researcher;
	
	@ManyToMany (cascade = CascadeType.ALL)
	@JoinTable(
			name = "HIDDEN_STUDS",
			joinColumns = {@JoinColumn(name="SET_ID", referencedColumnName="ID")},
			inverseJoinColumns = {@JoinColumn(name="PROJ_ID", referencedColumnName="ID")}
	)

	@Version
	long version = 0;
	
	private List<Student> hiddenStudents;
	
	public ResearcherSettings() {
		hiddenStudents = new ArrayList<Student>();
	}
	
	public List<Student> getHiddenStudents() {
		return hiddenStudents;
	}
	
	void addStudent(Student s) {
		if (!hiddenStudents.contains(s)) {
			hiddenStudents.add(s);
			if (!s.getHiddenByResearcher().contains(this)) {
				s.addHiddenByResearcher(this);
			}
		}
	}
	
	void removeStudent(Student s) {
		if (hiddenStudents.remove(s)) {
			if (s.getHiddenByResearcher().contains(this)) {
				s.removeHiddenByResearcher(this);
			}
		}
	}
	
	void removeStudents() {
		for (Student s : hiddenStudents) {
			s.getHiddenByResearcher().remove(this);
		}
		hiddenStudents = new ArrayList<Student>();
	}
	
	public Researcher getResearcher() {
		return researcher;
	}
	
	void setResearcher(Researcher r) {
		researcher = r;
	}
}
