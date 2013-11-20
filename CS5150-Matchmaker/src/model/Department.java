package model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;

@Entity(name = "DEPARTMENT")
public class Department {
	@Id @Column(name="ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name="DEPARTMENT")
	private String description;
	
	@ManyToMany(mappedBy = "departments")
	private List<Researcher> researchers;
	
	public Department() {
		
	}
	
	Department(String name) {
		this.description = name;
	}
	public long getId(){
		return id;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public List<Researcher> getResearchers() {
		return researchers.subList(0, researchers.size());
	}
	
	void setDescription(String name) {
		this.description = name;
	}
	
	void addResearcher(Researcher r) {
		if (!researchers.contains(r)) {
			researchers.add(r);
			if (!r.getDepartments().contains((this))) {
				r.addDepartment(this);
			}
		}
	}
	
	void removeResearcher(Researcher r) {
		if (this.researchers.remove(r)) {
			if (r.getDepartments().contains(this)) {
				r.removeDepartment(this);
			}
		}
	}

	void removeResearchers() {
		for (Researcher r : researchers) {
			removeResearcher(r);
		}
	}

	public int compareTo(MultipleItem o) {
		return getDescription().compareTo(o.getDescription());
	}
}