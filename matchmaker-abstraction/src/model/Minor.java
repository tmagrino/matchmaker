package model;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Persistant JPA Entity Class
 * <p>
 * A {@link FieldValue} representing a minor within the University
 * <p>
 * Follows the Model-View-Controller software pattern. Because of field and method
 * visibilities, only getter methods can be accessed externally. 
 * To create or alter instances of this class, use {@link FieldValueController}.
 * 
 * @author Jan Cardenas
 * @author Leonardo Neves
 *
 */

@Entity(name = "MINOR")
public class Minor extends FieldValue {
	// Persistent Fields
	// the id of this Minor in the MINOR table of the database
	@Id @Column(name="ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	// Name of this Minor
	@Column(name="MINOR")
	private String description;
	
	// List of Students in this Minor
	@ManyToMany(mappedBy = "minors")
	private List<Student> students;

	@Version
	long version = 0;
	
	Minor() {
		
	}
	
	/**
	 * Creates a Minor instance
	 * @param name the name of this Minor
	 */
	Minor(String name) {
		this.description = name;
	}
	
	/**
	 * @return the id of this Minor in the MINOR table of the database
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * @return the name of this Minor
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * @return the {@link Student}s in this Minor
	 */
	public List<Student> getStudents() {
		return students;
	}
	
	/**
	 * @param name the name to set to this Minor
	 */
	void setDescription(String name) {
		this.description = name;
	}
	
	/**
	 * Adds a {@link Student} to this Minor. Updates both sides of the Minor
	 * and Student relationship.
	 * 
	 * @param s the {@link Student} to add to this Minor
	 */
	void addStudent(Student s) {
		if (!students.contains(s)) {
			students.add(s);
			if (!s.getMinors().contains((this))) {
				s.addMinor(this);
			}
		}
	}
	
	/**
	 * Adds a {@link Student} to this Minor. Updates both sides of the Minor
	 * and Student relationship.
	 * 
	 * @param s the {@link Student} to add to this Minor
	 */
	void removeStudent(Student s) {
		if (this.students.remove(s)) {
			if (s.getMinors().contains(this)) {
				s.removeMinor(this);
			}
		}
	}
	
	/**
	 * Removes all {@link Student}s from this Minor
	 */
	void removeStudents() {
		for (Student s : students) {
			s.getMinors().remove(this);
		}
		students = new ArrayList<Student>();
	}
	
	/**
	 * Removes all {@link Student}s from this Minor
	 */
	void removeElements() {
		removeStudents();
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
	 * Not used in this class
	 */
	void addProject(Project p) {
		
	}
	
	/**
	 * Not used in this class
	 */
	void removeProject(Project p) {
		
	}

	/**
	 * Compares this Minor to another {@link FieldValue}
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
