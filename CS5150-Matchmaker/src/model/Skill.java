package model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;

public class Skill {
	@Id @Column(name="ID")
	private long id;
	@Column(name="SKILL")
	private String description;
	
	@ManyToMany(mappedBy = "skills")
	private List<Student> students;
}
