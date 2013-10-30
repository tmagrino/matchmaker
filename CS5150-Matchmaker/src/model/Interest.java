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
public class Interest {
	@Id @Column(name="ID")
	private long id;
	@Column(name="INTEREST")
	private String description;
	
	@ManyToMany(mappedBy = "interests")
	private List<Student> students;
	
}