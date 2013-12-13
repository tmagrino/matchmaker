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
public class Skill extends FieldValue {
	@Id @Column(name="ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name="SKILL")
	private String description;
	
	@ManyToMany(mappedBy = "skills")
	private List<Student> students;
	@ManyToMany (mappedBy = "requiredSkills")
	private List<Project> projects;
	
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
		return students;
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
			s.getSkills().remove(this);
		}
	}
	
	public List<Project> getProjects() {
		return this.projects;
	}
	
	void addProject(Project p) {
		if (!projects.contains(p)) {
			projects.add(p);
			if (!p.getRequiredSkills().contains(this)) {
				p.addRequiredSkill(this);
			}
		}
	}
	
	void removeProject(Project p) {
		if (projects.remove(p)) {
			if (p.getRequiredSkills().contains(this)) {
				p.removeRequiredSkill(this);
			}
		}
	}
	
	void removeProjects() {
		for (Project p : projects) {
			p.getRequiredSkills().remove(this);
		}
	}
	
	void addResearcher(Researcher r) {
		
	}
	
	void removeResearcher(Researcher r) {
		
	}
	
	void removeElements() {
		removeStudents();
		removeProjects();
	}
	
	@Override
	public int compareTo(FieldValue o) {
		return getDescription().compareTo(o.getDescription());
	}

	@Override
	List<Researcher> getResearchers() {
		// TODO Auto-generated method stub
		return null;
	}
}
