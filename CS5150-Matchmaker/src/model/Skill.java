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
public class Skill {
	@Id @Column(name="ID")
	private long id;
	@Column(name="SKILL")
	private String description;
	
	@ManyToMany(mappedBy = "skills")
	private List<Student> students;
}