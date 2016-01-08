package model;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Persistant JPA Entity Class
 * <p>
 * A {@link FieldValue} representing a major within the University
 * <p>
 * Follows the Model-View-Controller software pattern. Because of field and method
 * visibilities, only getter methods can be accessed externally. 
 * To create or alter instances of this class, use {@link FieldValueController}.
 * 
 * @author Jan Cardenas
 * @author Leonardo Neves
 *
 */

@Entity(name = "MAJOR")
public class Major extends FieldValue {
	// Persistent Fields
	// ID of this major in the MAJOR table of the database
	@Id @Column(name="ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	// Name of this Major
	@Column(name="MAJOR")
	private String description;
	
	// List of Students in this Major
	@ManyToMany(mappedBy = "majors")
	private List<Student> students;

	@Version
	long version = 0;
	
	Major() {
		
	}
	
	/**
	 * Creates a Major instance
	 * @param name the name of this Major
	 */
	Major(String name) {
		this.description = name;
	}
	
	/**
	 * @return the id of this Major in the MAJOR table of the database
	 */
	public long getId(){
		return id;
	}
	
	/**
	 * @return the name of this Major
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * @return the {@link Student}s in this Major
	 */
	public List<Student> getStudents() {
		return students;
	}
	
	/**
	 * @param name the name to set to this Major
	 */
	void setDescription(String name) {
		this.description = name;
	}
	
	/**
	 * Adds a {@link Student} to this Major. Updates both sides of the Major
	 * and Student relationship.
	 * 
	 * @param s the {@link Student} to add to this Major
	 */
	void addStudent(Student s) {
		if (!students.contains(s)) {
			students.add(s);
			if (!s.getMajors().contains((this))) {
				s.addMajor(this);
			}
		}
	}
	
	/**
	 * Removes a {@link Student} from this Major. Updates both sides of the Major
	 * and Student relationship.
	 * 
	 * @param s the {@link Student} to remove from this Major
	 */
	void removeStudent(Student s) {
		if (this.students.remove(s)) {
			if (s.getMajors().contains(this)) {
				s.removeMajor(this);
			}
		}
	}
	
	/**
	 * Removes all {@link Student}s from this Major
	 */
	void removeStudents() {
		for (Student s : students) {
			s.getMajors().remove(this);
		}
		students = new ArrayList<Student>();
	}
	
	/**
	 * Removes all {@link Student}s from this Major
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
	 * Compares this Major to another {@link FieldValue}
	 * @param o the {@link FieldValue} to be compared to
	 */
	@Override
	public int compareTo(FieldValue o) {
		// TODO Auto-generated method stub
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