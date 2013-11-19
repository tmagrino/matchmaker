package model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the Researcher database table.
 * 
 */
@Entity
@NamedQuery(name="Researcher.findAll", query="SELECT r FROM Researcher r")
public class Researcher implements Serializable {
	private static final long serialVersionUID = 1L;

	// Persistent Fields
	@Id @Column(name = "ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "NAME", nullable = false, length = 75)
	private String name;
	@Column(name = "NETID", nullable = false, length = 10)
	private String netID;
	@Column(name = "EMAIL", nullable = false)
	private String email;
	@Column(name = "DEPARTMENT", nullable = false)
	private String department;
	@Column(name = "AREA", nullable = false)
	private String researchArea;
	@Column(name = "WEBPAGE", nullable = true)
	private String webpage;
	@OneToOne (mappedBy = "researcher")
	private User user;
	@ManyToMany
	@JoinTable(
			name = "PROJECTS_MAPPING",
			joinColumns = {@JoinColumn(name="RESEARCHER_ID", referencedColumnName="ID")},
			inverseJoinColumns = {@JoinColumn(name="PROJ_ID", referencedColumnName="ID")}
	)
	private List<Project> projects;
	
	//private BufferedImage profilePicture;
	//private ResearcherSettings settings;

	
	public Researcher() {
		
	}
	
	public Researcher(String name,String netID,String email, String department,
			String webpage, String researchArea) {
		this.name = name;
		this.netID = netID;
		this.email = email;
		this.department = department;
		this.webpage = webpage;
		this.researchArea = researchArea;
	}
	
	public String getAttribute(String attr) {
		switch (attr.toLowerCase()) {
			case "name":
				return name;
			case "netID":
				return netID;
			case "email":
				return email;
			case "url":
				return webpage;
			case "department":
				return department;
			case "research area":
				return researchArea;
			default:
				System.out.println("Invalid attribute");
				return null;
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNetID() {
		return netID;
	}

	public void setNetID(String netID) {
		this.netID = netID;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getResearchArea() {
		return researchArea;
	}

	public void setResearchArea(String researchArea) {
		this.researchArea = researchArea;
	}

	public String getWebpage() {
		return webpage;
	}

	public void setWebpage(String webpage) {
		this.webpage = webpage;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	
	/**
	 * @param user the user to set
	 */
	void setUser(User user) {
		if (user == null) {
			if (this.user != null) {
				if (this.user.getResearcher() != null) {
					User u = this.user;
					this.user = null;
					u.setResearcher(null);
				}
			}
		}
		this.user = user;
		if (user.getResearcher() != this) {
			user.setResearcher(this);
		}
	}
}