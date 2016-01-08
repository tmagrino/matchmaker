package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Persistant JPA Entity Class
 * <p>
 * A {@link FieldValue} representing a College within the University
 * <p>
 * Follows the Model-View-Controller software pattern. Because of field and method
 * visibilities, only getter methods can be accessed externally. 
 * To create or alter instances of this class, use {@link FieldValueController}.
 * 
 * @author Jan Cardenas
 * @author Leonardo Neves
 *
 */

@Entity(name = "COLLEGE")
public class College extends FieldValue {
	// Persistent Fields
	// ID in COLLEGE table of the database
	@Id @Column(name="ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	// Name of the College
	@Column(name="COLLEGE_SCHOOL")
	private String description;
	
	// List of the students within this College
	@ManyToMany(mappedBy = "colleges")
	private List<Student> students;

	@Version
	long version = 0;
	
	College() {
		
	}
	
	/**
	 * 
	 * Creates a College
	 * 
	 * @param name the name of the College
	 */
	College(String name) {
		this.description = name;
	}
	
	/**
	 * @return the ID of this instance in the College table of the database
	 */
	public long getId(){
		return id;
	}
	
	/**
	 * 
	 * @return the name of this College
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * 
	 * @return the {@link Student}s within this College
	 */
	public List<Student> getStudents() {
		return students;
	}
	
	/**
	 * 
	 * @param name the name to set to this College
	 */
	void setDescription(String name) {
		this.description = name;
	}
	
	/**
	 * Adds a {@link Student} to this College. Updates both sides of the Student
	 * and College relationship.
	 * 
	 * @param s the {@link Student} to add to this College
	 */
	void addStudent(Student s) {
		if (!students.contains(s)) {
			students.add(s);
			if (!s.getColleges().contains((this))) {
				s.addCollege(this);
			}
		}
	}
	
	/**
	 * Removes a {@link Student} from this College. Updates both sides of the Student
	 * and College relationship.
	 * 
	 * @param s the {@link Student} to remove from this College
	 */
	void removeStudent(Student s) {
		if (this.students.remove(s)) {
			if (s.getColleges().contains(this)) {
				s.removeCollege(this);
			}
		}
	}
	
	/**
	 * Removes all students from this College
	 */
	void removeStudents() {
		for (Student s : students) {
			s.getColleges().remove(this);
		}
		students = new ArrayList<>();
	}
	
	/**
	 * Not used for this class
	 */
	void addResearcher(Researcher r) {
		
	}
	
	/**
	 * Not used for this class
	 */
	void removeResearcher(Researcher r) {
		
	}
	
	/**
	 * Not used for this class
	 */
	void addProject(Project p) {
		
	}

	/**
	 * Not used for this class
	 */
	void removeProject(Project p) {
		
	}
	
	/**
	 * Removes all {@link Student}s from this College
	 */
	void removeElements() {
		removeStudents();
	}
	
	/**
	 * Compares this College to another {@link FieldValue}
	 * @param o the {@link FieldValue} to be compared to
	 */
	@Override
	public int compareTo(FieldValue o) {
		return getDescription().compareTo(o.getDescription());
	}

	/**
	 * Not used for this class
	 */
	@Override
	List<Researcher> getResearchers() {
		return null;
	}
}
