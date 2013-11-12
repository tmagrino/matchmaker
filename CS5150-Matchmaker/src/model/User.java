package model;

import javax.persistence.*;

public class User {

	// Persistent Fields
	@Id @Column(name = "ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private String email;
	private String netid;
	boolean isAdmin;
	@OneToOne
	@Column(nullable = true)
	private Student student;
	@OneToOne
	@Column(nullable = true)
	private Researcher researcher;
	
	public User(String name, String email, String netid) {
		this.name = name;
		this.email = email;
		this.netid = netid;
		this.isAdmin = false;
		this.student = null;
		this.researcher = null;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the netid
	 */
	public String getNetid() {
		return netid;
	}

	/**
	 * @return the isAdmin
	 */
	public boolean isAdmin() {
		return isAdmin;
	}

	/**
	 * @return the student
	 */
	public Student getStudent() {
		return student;
	}

	/**
	 * @return the researcher
	 */
	public Researcher getResearcher() {
		return researcher;
	}

	/**
	 * @param id the id to set
	 */
	void setId(long id) {
		this.id = id;
	}

	/**
	 * @param name the name to set
	 */
	void setName(String name) {
		this.name = name;
	}

	/**
	 * @param email the email to set
	 */
	void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param netid the netid to set
	 */
	void setNetid(String netid) {
		this.netid = netid;
	}

	/**
	 * @param isAdmin the isAdmin to set
	 */
	void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	/**
	 * @param student the student to set
	 */
	void setStudent(Student student) {
		this.student = student;
	}

	/**
	 * @param researcher the researcher to set
	 */
	void setResearcher(Researcher researcher) {
		this.researcher = researcher;
	}
	
	
	
	
	
}
