package model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/* 
 * A student's application to a Project
 */
@Entity(name = "APPLICATION")
public class Application implements Serializable {
	private static final long serialVersionUID = 1L;
	
	// Persistent Fields
	@Id @Column(name = "ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@ManyToOne
	@JoinColumn
	private Student studentApplicant;
	@ManyToOne
	@JoinColumn(name = "OWNER_ID")
	private Project applicationProject;
	@Column(name = "STATUS")
	private ApplicationStatus status;
	@Column(name = "STUD_DATA")
	private String studentResponse;
	@Column(name = "SUBMITTED")
	@Temporal(TemporalType.DATE)
	private Date submissionDate;
	@ManyToOne
	@JoinColumn(name = "project")
	private Project project;
	
	public Application() {

	}

	public Application(Student owner, Project project, 
			String studentResponse) {
		this.studentApplicant = owner;
		this.applicationProject = project;
		this.status = ApplicationStatus.Pending;
		this.studentResponse = studentResponse;
		this.submissionDate = new Date();
	}
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public Student getStudentApplicant() {
		return studentApplicant;
	}


	void setStudentApplicant(Student studentApplicant) {
		if (studentApplicant == null) {
			if (this.studentApplicant != null) {
				Student s = this.studentApplicant;
				this.studentApplicant = null;
				s.removeApplication(this);
			}
		}
		else {
			this.studentApplicant = studentApplicant;
			if (!studentApplicant.getApplications().contains(this)) {
				studentApplicant.addApplication(this);
			}
		}
	}

	public Project getApplicationProject() {
		return applicationProject;
	}


	void setApplicationProject(Project applicationProject) {
		this.applicationProject = applicationProject;
	}


	public ApplicationStatus getStatus() {
		return status;
	}


	void setStatus(ApplicationStatus status) {
		this.status = status;
	}


	public String getStudentResponse() {
		return studentResponse;
	}


	void setStudentResponse(String studentResponse) {
		this.studentResponse = studentResponse;
	}


	public Date getSubmissionDate() {
		return submissionDate;
	}


	void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
