package model;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Persistant JPA Entity Class
 * <p>
 * A {@link FieldValue} representing an interest for a {@link Student} or a 
 * research area for a {@link Researcher} 
 * <p>
 * Follows the Model-View-Controller software pattern. Because of field and method
 * visibilities, only getter methods can be accessed externally. 
 * To create or alter instances of this class, use {@link FieldValueController}.
 * 
 * @author Jan Cardenas
 * @author Leonardo Neves
 *
 */

@Entity(name = "INTEREST")
public class Interest extends FieldValue {
	// Persistent Fields
	// ID in the INTEREST table of the database
	@Id @Column(name="ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	// Name of this Interest
	@Column(name="INTEREST")
	private String description;
	
	// List of Students who have this Interest
	@ManyToMany(mappedBy = "interests")
	private List<Student> students;
	
	// List of Researchers who have this Interest as a research area
	@ManyToMany(mappedBy = "researchArea")
	private List<Researcher> researchers;
	
	// List of Projects who have this Interest as a research area
	@ManyToMany(mappedBy = "project_area")
	private List<Project> projects;

	@Version
	long version = 0;
	
	Interest() {
		
	}
	
	/**
	 * Creates an Interest
	 * 
	 * @param name the name of this Interest
	 */
	Interest(String name) {
		this.description = name;
	}
	
	/**
	 * @return the id of this Interest in the INTEREST table of the database
	 */
	public long getId(){
		return id;
	}
	
	/**
	 * @return the name of this Interest
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * @return the {@link Student}s who have this Interest
	 */
	public List<Student> getStudents() {
		return students.subList(0, students.size());
	}
	
	/**
	 * @return the {@link Researcher}s who have this Interest as a research area
	 */
	public List<Researcher> getResearchers() {
		return researchers.subList(0, researchers.size());
	}
	
	/**
	 * @param name the name to set to this Interest
	 */
	@Override
	void setDescription(String name) {
		this.description = name;
	}
	
	/**
	 * Adds a {@link Student} to this Interest. Updates both sides of the Student
	 * and Interest relationship.
	 * 
	 * @param s the {@link Student} to add to this Interest
	 */
	void addStudent(Student s) {
		if (!students.contains(s)) {
			students.add(s);
			if (!s.getInterests().contains((this))) {
				s.addInterest(this);
			}
		}
	}
	
	/**
	 * Removes a {@link Student} from this Interest. Updates both sides of the Student
	 * and Interest relationship.
	 * 
	 * @param s the {@link Student} to remove from to this Interest
	 */
	void removeStudent(Student s) {
		if (this.students.remove(s)) {
			if (s.getInterests().contains(this)) {
				s.removeInterest(this);
			}
		}
	}
	
	/**
	 * Removes all {@link Student}s from this Interest
	 */
	void removeStudents() {
		for (Student s : students) {
			s.getInterests().remove(this);
		}
		students = new ArrayList<Student>();
	}
	
	/**
	 * Adds a {@link Researcher} to this Interest. Updates both sides of the Researcher
	 * and Interest relationship.
	 * 
	 * @param r the {@link Researcher} to add to this Interest
	 */
	void addResearcher(Researcher r) {
		if (!researchers.contains(r)) {
			researchers.add(r);
			if (!r.getResearchArea().contains((this))) {
				r.addResearchArea(this);
			}
		}
	}
	
	/**
	 * Removes a {@link Researcher} from this Interest. Updates both sides of the Researcher
	 * and Interest relationship.
	 * 
	 * @param r the {@link Researcher} to remove from to this Interest
	 */
	void removeResearcher(Researcher r) {
		if (this.researchers.remove(r)) {
			if (r.getResearchArea().contains(this)) {
				r.removeResearchArea(this);
			}
		}
	}
	
	/**
	 * Removes all {@link Researcher}s from this Interest
	 */
	void removeResearchers() {
		for (Researcher r : researchers) {
			r.getResearchArea().remove(this);
		}
		researchers = new ArrayList<Researcher>();
	}
	
	/**
	 * 
	 * @return the {@link Project}s who have this Interest as a research area
	 */
	public List<Project> getProjects() {
		return projects;
	}
	
	/**
	 * Adds a {@link Project} to this Interest. Updates both sides of the Project
	 * and Interest relationship.
	 * 
	 * @param p the {@link Project} to add to this Interest
	 */
	void addProject(Project p) {
		if (!projects.contains(p)) {
			projects.add(p);
			if (!p.getProjectAreas().contains((this))) {
				p.addProjectArea(this);
			}
		}
	}
	
	/**
	 * Removes a {@link Project} from this Interest. Updates both sides of the Project
	 * and Interest relationship.
	 * 
	 * @param p the {@link Project} to remove from to this Interest
	 */
	void removeProject(Project p) {
		if (projects.remove(p)) {
			if (p.getProjectAreas().contains(this)) {
				p.removeProjectArea(this);
			}
		}
	}
	
	/** 
	 * Removes all {@link Project}s from this Interest
	 */
	void removeProjects() {
		for (Project p : projects) {
			p.getProjectAreas().remove(this);
		}
		projects = new ArrayList<Project>();
	}
	
	/**
	 * Removes all {@link Student}s, {@link Researcher}s, and {@link Project}s from this Interest
	 */
	void removeElements() {
		removeStudents();
		removeResearchers();
		removeProjects();
	}
	
	/**
	 * Compares this Interest to another {@link FieldValue}
	 * @param o the {@link FieldValue} to be compared to
	 */
	public int compareTo(FieldValue o) {
		return getDescription().compareTo(o.getDescription());
	}
}
