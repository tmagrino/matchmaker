package model;

import java.io.Serializable;
import java.util.ArrayList;
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
	@Column(name = "DESCRIPTION", nullable = false)
	private String description;
	@ManyToMany(mappedBy = "projects")
	private List<Researcher> researchers;
	@OneToMany(mappedBy = "applicationProject")
	private List<Application> applications;
	@Column(name = "OPENINGS", nullable = false)
	private int openings;
	//private MinimumRequirements requirements;
	
	public Project() {
		
	}
	public Project(String name, String description, List<Researcher> res) {
		this.name = name;
		this.description = description;
		this.researchers = res;
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
		researchers.add(res);
	}
	
	void removeResearcher(Researcher res) {
		for (int i = 0; i < researchers.size(); i++) {
			Researcher r = researchers.get(i);
			if (r.equals(res)) {
				researchers.remove(i);
				break;
			}
		}
	} 
	
	void removeResearchers() {
		researchers = new ArrayList<Researcher>();
	}

	void addApplication(Application app) {
		applications.add(app);
	}
	
	void removeApplication(Application app) {
		applications.remove(app);
	}
	
	void removeApplications() {
		applications = new ArrayList<Application>();
	}
	
	public List<Application> getApplications() {
		return applications;
	}

	public int getOpenings() {
		return openings;
	}

	void setOpenings(int openings) {
		this.openings = openings;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}