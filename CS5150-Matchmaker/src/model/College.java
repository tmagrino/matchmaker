package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

@Entity(name = "COLLEGE")
public class College {
	@Id @Column(name="ID")
	private long id;
	@Column(name="COLLEGE/SCHOOL")
	private String description;
	
	@ManyToMany(mappedBy = "colleges")
	private List<Student> students;
	
}


/*
public enum College {
	College_of_Agriculture_and_Life_Science,
	College_of_Architecture_Art_and_Planning,
	College_of_Arts_and_Sciences,
	College_of_Engineering,
	School_of_Hotel_Administration,
	College_of_Human_Ecology,
	School_of_Industrial_and_Labor_Relations,
}
*/