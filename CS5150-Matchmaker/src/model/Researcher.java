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
	private String eMail;
	@Column(name = "AREA", nullable = false)
	private String researchArea;
	@Column(name = "WEBPAGE", nullable = true)
	private String webpage;
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
	
	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
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
	
	


}