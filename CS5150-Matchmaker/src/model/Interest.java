package model;

/*
 * Represents a possible student interest
 *
 *  This table in the database will keep track of all
 *  possible interests a student can choose in their 
 *  profile and which interests a professor can filter 
 *  by in their searches
 *  
 *   Table: INTEREST
 *   
 *   |  ID  |    INTEREST             |
 *   |  1   | Animal Healthcare       |
 *   |  2   | Computers               |
 *   |  3   | Sports Medicine         |
 *   |  4   | Zoo Management          |
 *   |  5   | ...                     |
 *   |  6   |                         |
 *   |  7   |                         |
 *   
 *   
 *   Table: STUDENTS<->INTERESTS_TABLE
 *   
 *   | STUD_ID | INTER_ID |
 *   |    1    |     3    |
 *   |    1    |     16   |
 *   |    1    |     32   |
 *   |    1    |     21   |
 *   |    2    |     23   |
 *   |    2    |     31   |
 *   |    5    |     11   |
 *   |   ...   |    ...   |
 */

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;

@Entity(name = "INTEREST")
public class Interest implements Comparable<Interest>{
	@Id @Column(name="ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name="INTEREST")
	private String description;
	
	@ManyToMany(mappedBy = "interests")
	private List<Student> students;
	
	public Interest() {
		
	}
	
	public Interest(String name) {
		this.description = name;
	}
	
	public long getId(){
		return id;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public List<Student> getStudents() {
		return students.subList(0, students.size());
	}
	
	void setDescription(String name) {
		this.description = name;
	}
	
	void addStudent(Student s) {
		if (!students.contains(s)) {
			students.add(s);
			if (!s.getInterests().contains((this))) {
				s.addInterest(this);
			}
		}
	}
	
	void removeStudent(Student s) {
		if (this.students.remove(s)) {
			if (s.getInterests().contains(this)) {
				s.removeInterest(this);
			}
		}
	}
	
	void removeStudents() {
		for (Student s : students) {
			removeStudent(s);
		}
	}

	@Override
	public int compareTo(Interest o) {
		return getDescription().compareTo(o.getDescription());
	}
}
