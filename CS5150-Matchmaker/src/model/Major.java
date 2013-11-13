package model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;

/*
 *  This table in the database will keep track of all
 *  possible majors a student can choose in their profile and
 *  which majors a professor can filter by in their searches
 *  
 *   Table: MAJOR
 *   
 *   |  ID  |    MAJOR               |
 *   |  1   | Africana_Studies       |
 *   |  2   | Agricultural_Sciences  |
 *   |  3   | American_Studies       |
 *   |  4   | Animal_Science         |
 *   |  5   | ...                    |
 *   |  6   |                        |
 *   |  7   |                        |
 *   
 *   
 *   Table: MAJORS_TABLE (maps students to their majors)
 *   
 *   | STUD_ID | MAJOR_ID |
 *   |    1    |     3    |
 *   |    1    |     16   |
 *   |    2    |     32   |
 *   |    3    |     21   |
 *   |    4    |     23   |
 *   |    5    |     31   |
 *   |    6    |     11   |
 *   |   ...   |    ...   |
 */

@Entity(name = "MAJOR")
public class Major implements Comparable<Major>{
	@Id @Column(name="ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name="MAJOR")
	private String description;
	
	@ManyToMany(mappedBy = "majors")
	private List<Student> students;
	
	public Major() {
		
	}
	
	Major(String name) {
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
			if (!s.getMajors().contains((this))) {
				s.addMajor(this);
			}
		}
	}
	
	void removeStudent(Student s) {
		if (this.students.remove(s)) {
			if (s.getMajors().contains(this)) {
				s.removeMajor(this);
			}
		}
	}
	
	void removeStudents() {
		for (Student s : students) {
			removeStudent(s);
		}
	}

	@Override
	public int compareTo(Major o) {
		return getDescription().compareTo(o.getDescription());
	}
}