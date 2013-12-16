package model;

import javax.persistence.*;
import java.util.List;
/**
 * Persistant JPA Entity Class
 * <p>
 * Represents a department within the University
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
	 * @return the {@link Researcher
	 */
	public List<Researcher> getResearchers() {
		return researchers;
	}
	
	void setDescription(String name) {
		this.description = name;
	}
	
	void addResearcher(Researcher r) {
		if (!researchers.contains(r)) {
			researchers.add(r);
			if (!r.getDepartments().contains((this))) {
				r.addDepartment(this);
			}
		}
	}
	
	void removeResearcher(Researcher r) {
		if (this.researchers.remove(r)) {
			if (r.getDepartments().contains(this)) {
				r.removeDepartment(this);
			}
		}
	}

	void removeResearchers() {
		for (Researcher r : researchers) {
			r.getDepartments().remove(this);
		}
	}
	
	void removeElements() {
		removeResearchers();
	}
	
	void addStudent(Student s) {
		
	}
	
	void removeStudent(Student s) {
		
	}
	
	void addProject(Project p) {
		
	}
	
	void removeProject(Project p) {
		
	}
	
	public int compareTo(FieldValue o) {
		return getDescription().compareTo(o.getDescription());
	}

	@Override
	List<Student> getStudents() {
		// TODO Auto-generated method stub
		return null;
	}	
}