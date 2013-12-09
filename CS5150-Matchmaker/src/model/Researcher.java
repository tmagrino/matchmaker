package model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.ArrayList;
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
	@ManyToMany
	@JoinTable(
			name = "RESEARCH_AREA_TABLE",
			joinColumns = {@JoinColumn(name="RES_ID", referencedColumnName="ID")},
			inverseJoinColumns = {@JoinColumn(name="AREA_ID", referencedColumnName="ID")}
	)
	private List<Interest> researchArea;
	@Column(name = "WEBPAGE", nullable = true)
	private String webpage;
	@OneToOne (mappedBy = "researcher")
	private User user;
	
	@ManyToMany(mappedBy = "researchers")
	private List<Project> projects;
	@ManyToMany
	@JoinTable(
			name = "DEPARTMENTS_TABLE",
			joinColumns = {@JoinColumn(name="STUD_ID", referencedColumnName="ID")},
			inverseJoinColumns = {@JoinColumn(name="DEPARTMENT_ID", referencedColumnName="ID")}
	)
	private List<Department> departments;
	@OneToOne (mappedBy = "researcher", cascade = CascadeType.ALL)
	private ResearcherSettings settings;
	


	
	public Researcher() {
		
	}
	
	public Researcher(String name,String netID,String email, 
			List<Department> departments,
			String webpage, List<Interest> researchArea) {
		this.name = name;
		this.netID = netID;
		this.email = email;
		this.departments = departments;
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
				return departmentString();
			case "research area":
				return areaString();
			default:
				System.out.println("Invalid attribute");
				return null;
		}
	}
	
	public List<? extends MultipleItem> getListAttribute(String attr) {
		switch (attr.toLowerCase()) {
			case ItemFactory.DEPARTMENT:
				return departments;
			case ItemFactory.INTEREST:
				return researchArea;
			default:
				System.out.println("Invalid attribute " + attr);
				return null;
		}
	}
	
	public String departmentString() {
		StringBuffer res = new StringBuffer();
		for (Department d : departments) {
			res.append(d.getDescription());
			res.append(", ");
			
		}
		if (res.length() >= 2) {
			return res.substring(0, res.length() -2);
		}
		return res.toString();
	}
	
	public String areaString() {
		StringBuffer res = new StringBuffer();
		for (Interest a : researchArea) {
			res.append(a.getDescription());
			res.append(", ");
		}
		if (res.length() >= 2) {
			return res.substring(0, res.length() -2);
		}
		return res.toString();
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
	
	public List<Department> getDepartments() {
		return departments;
	}
	
	void addDepartment(Department d) {
		this.departments.add(d);
	}
	
	void removeDepartment(Department d) {
		this.departments.remove(d);
	}
	
	void removeDepartments() {
		this.departments = new ArrayList<Department>();
	}

	public List<Interest> getResearchArea() {
		return researchArea;
	}
	void addResearchArea(Interest area){
		this.researchArea.add(area);
	}
	public void setResearchArea(List<Interest> researchArea) {
		this.researchArea = researchArea;
	}
	void removeResearchArea(Interest area){
		this.researchArea.remove(area);
		}
	void removeResearchAreas(){
		this.researchArea = new ArrayList<Interest>();
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
	public void addProject(Project proj) {
		if (projects == null) {projects = new ArrayList<Project>();}
		if (!projects.contains(proj)) {
			this.projects.add(proj);
			if (!proj.getResearchers().contains(this)) {
				proj.addResearcher(this);
			}
		}
	}
	
	public void removeProject(Project proj) {
		if (projects.remove(proj)) {
			if (proj.getResearchers().contains(this)) {
				proj.removeResearcher(this);
			}
		}
	}
	public void removeProjects() {
		for (Project p : projects){
			p.removeResearcher(this);
		}
		projects = new ArrayList<Project>();
		
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
	
	
	public ResearcherSettings getSettings() {
		return settings;
	}

	void setSettings(ResearcherSettings settings) {
		this.settings = settings;
	}

	void setDepartments(List<Department> departments) {
		this.departments = departments;
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