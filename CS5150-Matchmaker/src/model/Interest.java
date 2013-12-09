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
public class Interest extends MultipleItem{
	@Id @Column(name="ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name="INTEREST")
	private String description;
	
	@ManyToMany(mappedBy = "interests")
	private List<Student> students;
	
	@ManyToMany(mappedBy = "researchArea")
	private List<Researcher> researchers;
	
	public Interest() {
		
	}
	
	Interest(String name) {
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
	public List<Researcher> getResearchers() {
		return researchers.subList(0, researchers.size());
	}
	@Override
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
	@Override
	void removeStudents() {
		for (Student s : students) {
			s.getInterests().remove(this);
		}
	}

	@Override
	public int compareTo(MultipleItem o) {
		return getDescription().compareTo(o.getDescription());
	}
	
	void addResearcher(Researcher r) {
		if (!researchers.contains(r)) {
			researchers.add(r);
			if (!r.getResearchArea().contains((this))) {
				r.addResearchArea(this);
			}
		}
	}
	
	void removeResearcher(Researcher r) {
		if (this.researchers.remove(r)) {
			if (r.getResearchArea().contains(this)) {
				r.removeResearchArea(this);
			}
		}
	}
}
