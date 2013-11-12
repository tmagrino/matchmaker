package model;

import javax.persistence.*;

public class User {

	String name;
	String email;
	String netid;
	boolean isAdmin;
	@OneToOne
	Student student;
	Researcher researcher;
	
	public User(String name, String email, String netid, boolean isAdmin,
			Student student, Researcher researcher) {
		this.name = name;
		this.email = email;
		this.netid = netid;
		this.isAdmin = isAdmin;
		this.student = student;
		this.researcher = researcher;
	}
}
