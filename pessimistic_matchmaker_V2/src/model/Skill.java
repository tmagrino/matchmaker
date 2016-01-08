package model;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Persistant JPA Entity Class
 * <p>
 * A {@link FieldValue} representing a skill
 * <p>
 * Follows the Model-View-Controller software pattern. Because of field and method
 * visibilities, only getter methods can be accessed externally. 
 * To create or alter instances of this class, use {@link FieldValueController}.
 * 
 * @author Jan Cardenas
 * @author Leonardo Neves
 *
 */

@Entity(name = "SKILL")
public class Skill extends FieldValue {
	// Persistent Fields
	// the id of this Skill in the SKILL table of the database
	@Id @Column(name="ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	// The name of this Skill
	@Column(name="SKILL")
	private String description;
	
	// List of Students with this Skill
	@ManyToMany(mappedBy = "skills")
	private List<Student> students;
	
	// List of Projects requiring this Skill
	@ManyToMany (mappedBy = "requiredSkills")
	private List<Project> projects;

	@Version
	long version = 0;
	
	Skill() {
		
	}
	
	/**
	 * Creates a Skill instance
	 * @param name the name of this Skill
	 */
	Skill(String name) {
		this.description = name;
	}
	
	/**
	 * @return the id of this Skill in the SKILL table of the database
	 */
	public long getId(){
		return id;
	}
	
	/**
	 * @return the name of this Skill
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * @return the {@link Student}s with this Skill
	 */
	public List<Student> getStudents() {
		return students;
	}
	
	/**
	 * @param name the name to set to this Skill
	 */
	void setDescription(String name) {
		this.description = name;
	}
	
	/**
	 * Adds a {@link Student} to this Skill. Updates both sides of the Skill
	 * and Student relationship.
	 * 
	 * @param s the {@link Student} to add to this Skill
	 */
	void addStudent(Student s) {
		if (!students.contains(s)) {
			students.add(s);
			if (!s.getSkills().contains((this))) {
				s.addSkill(this);
			}
		}
	}
	
	/**
	 * Removes a {@link Student} from this Skill. Updates both sides of the Skill
	 * and Student relationship.
	 * 
	 * @param s the {@link Student} to remove from this Skill
	 */
	void removeStudent(Student s) {
		if (this.students.remove(s)) {
			if (s.getSkills().contains(this)) {
				s.removeSkill(this);
			}
		}
	}

	/**
	 * Removes all {@link Student}s from this Skill
	 */
	void removeStudents() {
		for (Student s : students) {
			s.getSkills().remove(this);
		}
		students = new ArrayList<Student>();
	}
	
	/**
	 * 
	 * @return the {@link Project}s requiring this Skill
	 */
	public List<Project> getProjects() {
		return this.projects;
	}
	
	/**
	 * Adds a {@link Project} to this Skill. Updates both sides of the Project
	 * and Student relationship.
	 * 
	 * @param p the {@link Project} to add to this Skill
	 */
	void addProject(Project p) {
		if (!projects.contains(p)) {
			projects.add(p);
			if (!p.getRequiredSkills().contains(this)) {
				p.addRequiredSkill(this);
			}
		}
	}
	
	/**
	 * Removes a {@link Project} from this Skill. Updates both sides of the Project
	 * and Student relationship.
	 * 
	 * @param p the {@link Project} to remove from this Skill
	 */
	void removeProject(Project p) {
		if (projects.remove(p)) {
			if (p.getRequiredSkills().contains(this)) {
				p.removeRequiredSkill(this);
			}
		}
	}
	
	/**
	 * Removes all {@link Project}s requiring this Skill
	 */
	void removeProjects() {
		for (Project p : projects) {
			p.getRequiredSkills().remove(this);
		}
		projects = new ArrayList<Project>();
	}
	
	/**
	 * Not used in this class
	 */
	void addResearcher(Researcher r) {
		
	}
	
	/**
	 * Not used in this class
	 */
	void removeResearcher(Researcher r) {
		
	}
	
	/**
	 * Removes all {@link Student}s and {@link Project}s from this Skill
	 */
	void removeElements() {
		removeStudents();
		removeProjects();
	}
	
	/**
	 * Compares this Major to another {@link FieldValue}
	 * @param o the {@link FieldValue} to be compared to
	 */
	@Override
	public int compareTo(FieldValue o) {
		return getDescription().compareTo(o.getDescription());
	}

	/**
	 * Not used in this class
	 */
	@Override
	List<Researcher> getResearchers() {
		// TODO Auto-generated method stub
		return null;
	}
}
