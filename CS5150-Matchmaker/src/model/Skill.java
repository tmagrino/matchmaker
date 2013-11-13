package model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;

/*
 *  This table in the database will keep track of all
 *  possible skills a student can choose in their profile and
 *  which skills a professor can filter by in their searches
 *  
 *   Table: SKILL
 *   
 *   |  ID  |    SKILL     |
 *   |  1   | Java         |
 *   |  2   | Welding      |
 *   |  3   | German       |
 *   |  4   | ...          |
 *   |  5   |              |
 *   |  6   |              |
 *   |  7   |              |
 * 
 *   Table: SKILLS_TABLE
 *   
 *   | STUD_ID | SKILL_ID |
 *   |    1    |     3    |
 *   |    1    |     16   |
 *   |    1    |     32   |
 *   |    1    |     21   |
 *   |    1    |     23   |
 *   |    1    |     31   |
 *   |    2    |     11   |
 *   |   ...   |    ...   |
 *   
 */
@Entity(name = "SKILL")
public class Skill implements Comparable<Skill>{
	@Id @Column(name="ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name="SKILL")
	private String description;
	
	@ManyToMany(mappedBy = "skills")
	private List<Student> students;
	
	public Skill() {
		
	}
	
	Skill(String name) {
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
			if (!s.getSkills().contains((this))) {
				s.addSkill(this);
			}
		}
	}
	
	void removeStudent(Student s) {
		if (this.students.remove(s)) {
			if (s.getSkills().contains(this)) {
				s.removeSkill(this);
			}
		}
	}
	
	void removeStudents() {
		for (Student s : students) {
			removeStudent(s);
		}
	}
	
	@Override
	public int compareTo(Skill o) {
		return getDescription().compareTo(o.getDescription());
	}
}
