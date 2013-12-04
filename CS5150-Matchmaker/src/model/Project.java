package model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the Project database table.
 * 
 */
@Entity

@NamedQuery(name="Project.findAll", query="SELECT p FROM Project p")
public class Project implements Serializable {
	private static final long serialVersionUID = 1L;

	// Persistent Fields
	@Id @Column(name = "ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "NAME", nullable = false, length = 75)
	private String name;
	@Column(name = "URL")
	private String url;
	@Column(name = "DESCRIPTION", nullable = false)
	private String description;
	@ManyToMany
	@JoinTable(
			name = "PROJECT_LEADERS",
			joinColumns = {@JoinColumn(name="PROJ_ID", referencedColumnName="ID")},
			inverseJoinColumns = {@JoinColumn(name="RES_ID", referencedColumnName="ID")}
	)
	private List<Researcher> researchers;
	@ManyToMany (cascade = CascadeType.ALL)
	@JoinTable(
			name = "PROJECT_AREA",
			joinColumns = {@JoinColumn(name="PROJ_ID", referencedColumnName="ID")},
			inverseJoinColumns = {@JoinColumn(name="AREA_ID", referencedColumnName="ID")}
	)
	private List<Interest> project_area;
	@OneToMany(mappedBy = "applicationProject" ,cascade = CascadeType.ALL)
	private List<Application> applications;
	
	@ManyToMany (mappedBy = "hiddenProjects")
	List<StudentSettings> settings;
	//private MinimumRequirements requirements;
	
	public Project() {
		System.out.println("Using constructor 1:");
	}
	public Project(String name, String description, String url, List<Researcher> res, List<Interest> area) {
		this.name = name;
		this.description = description;
		this.researchers = res;
		this.url = url;
		this.applications = new ArrayList<Application>();
		this.project_area = area;
		System.out.println("Using contstructor 2:");
	}
	public Project(String name, String description, String url, Researcher res,List<Interest> area) {
		this(name, description,  url, new ArrayList<Researcher>(Arrays.asList(res)), area);
	}
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Researcher> getResearchers() {
		return researchers;
	}
	
	void addResearcher(Researcher res) {
		if (!researchers.contains(res)) {
			researchers.add(res);
			if (!res.getProjects().contains(this)) {
				res.addProject(this);
			}
		}
	}
	
	void removeResearcher(Researcher res) {
		if (researchers.remove(res)) {
			if (res.getProjects().contains(this)) {
				res.removeProject(this);
			}
		}
	}
	
	void removeResearchers() {
		researchers = new ArrayList<Researcher>();
	}

	void addApplication(Application app) {
		if (applications == null) {
			applications = new ArrayList<Application>();
		}
		if (!this.applications.contains(app)) {
			this.applications.add(app);
			if (app.getApplicationProject() != this) {
				app.setApplicationProject(this);
			}
		}
	}
	
	void removeApplication(Application app) {
		if (this.applications.remove(app)) {
			if (app.getApplicationProject() == this) {
				app.setApplicationProject(null);
			}
		}
	}
	
	void removeApplications() {
		applications = new ArrayList<Application>();
	}
	
	public List<Application> getApplications() {
		return applications;
	}

	public String getURL(){
		return url;
	}
	void setURL(String url){
		this.url = url;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getResearchersString(){
		StringBuilder builder = new StringBuilder();
		for (Researcher r : researchers){
			builder.append(r.getName()+", ");
			
		}
		if (builder.length() > 2)
			builder.deleteCharAt(builder.length() - 2);
		return builder.toString();
	}
	public String getAreaString(){
		StringBuilder builder = new StringBuilder();
		for (Interest i : project_area){
			builder.append(i.getDescription()+", ");
			
		}
		if (builder.length() > 2)
			builder.deleteCharAt(builder.length() - 2);
		return builder.toString();
	}

}