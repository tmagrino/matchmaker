package model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;

/*
 *  This table in the database will keep track of all
 *  possible majors a student can choose in their profile and
 *  which majors a professor can filter by in their searches
 *  
 *   Table: MINOR
 *   
 *   |  ID  |    MINOR                 |
 *   |  1   | Aerospace_Engineering    |
 *   |  2   | Africana_Studies         |
 *   |  3   | Agribusiness_Management  |
 *   |  4   | American_Indian_Studies  |
 *   |  5   | ...                      |
 *   |  6   |                          |
 *   |  7   |                          |
 *   
 *   Table: MINORS_TABLE (maps students to their majors)
 *   
 *   | STUD_ID | MINOR_ID |
 *   |    1    |     3    |
 *   |    1    |     16   |
 *   |    2    |     32   |
 *   |    3    |     21   |
 *   |    4    |     23   |
 *   |    5    |     31   |
 *   |    6    |     11   |
 *   |   ...   |    ...   |
 *   
 */

@Entity(name = "MINOR")
public class Minor {
	@Id @Column(name="ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name="MINOR")
	private String description;
	
	@ManyToMany(mappedBy = "minors")
	private List<Student> students;
	
	public Minor() {
		
	}
	
	public Minor(String name) {
		this.description = name;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	void setDescription(String name) {
		this.description = name;
	}
}