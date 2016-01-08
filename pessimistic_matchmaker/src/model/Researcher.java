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
	private static final int MAX_NAME_CHARS = 75;

	// Persistent Fields
	@Id @Column(name = "ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "NAME", nullable = false, length = MAX_NAME_CHARS)
	private String name;
	@Column(name = "NETID", nullable = false, length = 10)
	private String netID;
	@Column(name = "EMAIL", nullable = false)
	private String email;
	@Column(name = "WEBPAGE", nullable = true)
	private String webpage;
	@ManyToMany
	@JoinTable(
			name = "RESEARCH_AREA_TABLE",
			joinColumns = {@JoinColumn(name="RES_ID", referencedColumnName="ID")},
			inverseJoinColumns = {@JoinColumn(name="AREA_ID", referencedColumnName="ID")}
	)
	private List<Interest> researchArea;
	@OneToOne (mappedBy = "researcher")
	private User user;
	@ManyToMany(mappedBy = "researchers")
	private List<Project> projects;
	@ManyToMany
	@JoinTable(
			name = "DEPARTMENTS_TABLE",
			joinColumns = {@JoinColumn(name="RES_ID", referencedColumnName="ID")},
			inverseJoinColumns = {@JoinColumn(name="DEPARTMENT_ID", referencedColumnName="ID")}
	)
	private List<Department> departments;
	@OneToOne (mappedBy = "researcher", cascade = CascadeType.ALL)
	private ResearcherSettings settings;
	@OneToOne
	@JoinColumn
	private ProfileImage photo;

	@Version
	long version = 0;
	
	public Researcher() {
		
	}
	
	public Researcher(String name,String netID,String email, 
			List<Department> departments,
			String webpage, List<Interest> researchArea) {
		if (name.length() >= MAX_NAME_CHARS) {
			this.name = name.substring(0, MAX_NAME_CHARS);
		}
		else {
			this.name = name;
		}
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
	
	public List<? extends FieldValue> getListAttribute(String attr) {
		switch (attr.toLowerCase()) {
			case FieldFactory.DEPARTMENT:
				return departments;
			case FieldFactory.INTEREST:
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
		String fixedName = name;
		if (name.length() >= MAX_NAME_CHARS) {
			fixedName = name.substring(0, MAX_NAME_CHARS);
		}
		this.name = fixedName;
		user.setName(fixedName);
	}

	public String getNetID() {
		return netID;
	}

	public void setNetID(String netID) {
		this.netID = netID;
		user.setNetid(netID);
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
		user.setEmail(email);
	}
	
	public String getWebpage() {
		return webpage;
	}

	public void setWebpage(String webpage) {
		this.webpage = webpage;
	}
	
	public List<Interest> getResearchArea() {
		return researchArea;
	}
	
	void setResearchArea(List<Interest> areas) {
		researchArea = areas;
	}
	
	void addResearchArea(Interest area){
		if (!researchArea.contains(area)) {
			researchArea.add(area);
			if (!area.getResearchers().contains((this))) {
				area.addResearcher(this);
			}
		}
	}
	
	void removeResearchArea(Interest area){
		if (this.researchArea.remove(area)) {
			if (area.getResearchers().contains(this)) {
				area.removeResearcher(this);
			}
		}
	}
	void removeResearchAreas() {
		for (Interest i : researchArea) {
			i.getResearchers().remove(this);
		}
		researchArea = new ArrayList<Interest>();
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
		else {
			this.user = user;
			if (user.getResearcher() != this) {
				user.setResearcher(this);
			}
		}
	}

	public List<Project> getProjects() {
		return projects;
	}

	void addProject(Project proj) {
		if (!projects.contains(proj)) {
			this.projects.add(proj);
			if (!proj.getResearchers().contains(this)) {
				proj.addResearcher(this);
			}
		}
	}
	
	void removeProject(Project proj) {
		if (projects.remove(proj)) {
			if (proj.getResearchers().contains(this)) {
				proj.removeResearcher(this);
			}
		}
	}
	List<Project> removeProjects() {
		List<Project> toDelete = new ArrayList<Project>();
		for (Project p : projects) {
			p.getResearchers().remove(this);
			toDelete.add(p);
		}
		projects = new ArrayList<Project>();
		return toDelete;
	}
	
	public List<Department> getDepartments() {
		return departments;
	}
	
	void addDepartment(Department d) {
		if (!departments.contains(d)) {
			departments.add(d);
			if (!d.getResearchers().contains(this)) {
				d.addResearcher(this);
			}
		}
	}
	
	void removeDepartment(Department d) {
		if(this.departments.remove(d)){
			if (d.getResearchers().contains(this)){
				d.removeResearcher(this);
			}
		}
	}
	
	void removeDepartments() {
		for (Department d : departments) {
			d.getResearchers().remove(this);
		}
		departments = new ArrayList<Department>();
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public ResearcherSettings getSettings() {
		return settings;
	}

	void setSettings(ResearcherSettings settings) {
		this.settings = settings;
	}

	public ProfileImage getPhoto() {
		return photo;
	}
	
	void setPhoto(ProfileImage img) {
		photo = img;
		if (img.getResearcher() != this) {
			img.setResearcher(this);
		}
	}
}
