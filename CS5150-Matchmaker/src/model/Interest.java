package model;

/*
 * Represents a possible student interest
 */

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

public class Interest {
	@Id @Column(name="ID")
	private long id;
	@Column(name="INTEREST")
	private String description;
	
	@ManyToMany(mappedBy = "interests")
	private List<Student> students;
	
}
