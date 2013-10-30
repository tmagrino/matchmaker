package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Project database table.
 * 
 */
@Entity
@NamedQuery(name="Project.findAll", query="SELECT p FROM Project p")
public class Project implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idProject;

	private String name;

	//bi-directional many-to-one association to Researcher
	@ManyToOne
	@JoinColumn(name="researcher")
	private Researcher researcherBean;

	//bi-directional many-to-one association to Student
	@ManyToOne
	@JoinColumn(name="student")
	private Student studentBean;

	public Project() {
	}

	public int getIdProject() {
		return this.idProject;
	}

	public void setIdProject(int idProject) {
		this.idProject = idProject;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Researcher getResearcherBean() {
		return this.researcherBean;
	}

	public void setResearcherBean(Researcher researcherBean) {
		this.researcherBean = researcherBean;
	}

	public Student getStudentBean() {
		return this.studentBean;
	}

	public void setStudentBean(Student studentBean) {
		this.studentBean = studentBean;
	}

}