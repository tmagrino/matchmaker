package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Persistant JPA Entity Class
 * <p>
 * A {@link FieldValue} representing a department within the University
 * <p>
 * Follows the Model-View-Controller software pattern. Because of field and method
 * visibilities, only getter methods can be accessed externally. 
 * To create or alter instances of this class, use {@link FieldValueController}.
 * 
 * @author Jan Cardenas
 * @author Leonardo Neves
 *
 */

@Entity(name = "DEPARTMENT")
public class Department extends FieldValue {
	// Persistent Fields
	// ID in the DEPARTMENT table of the database
	@Id @Column(name="ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	// Name of this Department
	@Column(name="DEPARTMENT")
	private String description;
	
	// List of the researchers within this Department
	@ManyToMany(mappedBy = "departments")
	private List<Researcher> researchers;

	@Version
	long version = 0;
	
	Department() {
		
	}
	
	/**
	 * Creates a Department
	 * 
	 * @param name the name of this Department
	 */
	Department(String name) {
		this.description = name;
	}
	
	/**
	 * 
	 * @return the id of this Department in the DEPARTMENT table of the database
	 */
	public long getId(){
		return id;
	}
	
	/**
	 * @return the name of this Department
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * @return the {@link Researcher}s in this Department
	 */
	public List<Researcher> getResearchers() {
		return researchers;
	}
	
	/**
	 * @param name the name to set to this Department
	 */
	void setDescription(String name) {
		this.description = name;
	}
	
	/**
	 * Adds a {@link Researcher} to this Department. Updates both sides of the Researcher
	 * and Department relationship.
	 * 
	 * @param r the {@link Researcher} to add to this Department
	 */
	void addResearcher(Researcher r) {
		if (!researchers.contains(r)) {
			researchers.add(r);
			if (!r.getDepartments().contains((this))) {
				r.addDepartment(this);
			}
		}
	}
	
	/**
	 * Removes a {@link Researcher} from this Department. Updates both sides of the Researcher
	 * and Department relationship.
	 * 
	 * @param r the {@link Researcher} to remove from this Department
	 */
	void removeResearcher(Researcher r) {
		if (this.researchers.remove(r)) {
			if (r.getDepartments().contains(this)) {
				r.removeDepartment(this);
			}
		}
	}

	/**
	 * Removes all {@link Researcher}s from this Department
	 */
	void removeResearchers() {
		for (Researcher r : researchers) {
			r.getDepartments().remove(this);
		}
		researchers = new ArrayList<Researcher>();
	}
	
	/**
	 * Removes all {@link Researcher}s from this Department
	 */
	void removeElements() {
		removeResearchers();
	}
	
	/**
	 * Not used for this class
	 */
	void addStudent(Student s) {
		
	}
	
	/**
	 * Not used for this class
	 */
	void removeStudent(Student s) {
		
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
	 * Compares this Department to another {@link FieldValue}
	 * @param o the {@link FieldValue} to be compared to
	 */
	public int compareTo(FieldValue o) {
		return getDescription().compareTo(o.getDescription());
	}

	/**
	 * Not used for this class
	 */
	@Override
	List<Student> getStudents() {
		// TODO Auto-generated method stub
		return null;
	}	
}
