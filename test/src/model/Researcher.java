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

	@Id
	private int idresearcher;

	private String name;

	private String netid;

	private String researcharea;

	private String webpage;

	//bi-directional many-to-one association to Project
	@OneToMany(mappedBy="researcherBean")
	private List<Project> projects;

	public Researcher() {
	}

	public int getIdresearcher() {
		return this.idresearcher;
	}

	public void setIdresearcher(int idresearcher) {
		this.idresearcher = idresearcher;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNetid() {
		return this.netid;
	}

	public void setNetid(String netid) {
		this.netid = netid;
	}

	public String getResearcharea() {
		return this.researcharea;
	}

	public void setResearcharea(String researcharea) {
		this.researcharea = researcharea;
	}

	public String getWebpage() {
		return this.webpage;
	}

	public void setWebpage(String webpage) {
		this.webpage = webpage;
	}

	public List<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public Project addProject(Project project) {
		getProjects().add(project);
		project.setResearcherBean(this);

		return project;
	}

	public Project removeProject(Project project) {
		getProjects().remove(project);
		project.setResearcherBean(null);

		return project;
	}

}