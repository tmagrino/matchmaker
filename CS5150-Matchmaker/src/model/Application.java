package model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
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


/**
 * Persistant JPA Entity Class
 * <p>
 * Represents a {@link Student}'s application to a {@link Project}
 * 
 * @author Jan Cardenas
 * @author Leonardo Neves
 *
 */
@Entity(name = "APPLICATION")
public class Application implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final int MAX_RESPONSE_CHARS = 2000;
	
	// Persistent Fields
	// ID in APPLICATION table
	@Id @Column(name = "ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	// Student associated with this application
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn(name = "STUD_ID")
	private Student studentApplicant;
	
	// Project associated with this application
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn(name = "PROJ_ID")
	private Project applicationProject;
	
	// Status of application
	@Column(name = "STATUS")
	private ApplicationStatus status;
	
	// Student's message to the project leaders
	@Column(name = "STUD_DATA", length = MAX_RESPONSE_CHARS)
	private String studentResponse;
	
	// Date submitted
	@Column(name = "SUBMITTED")
	@Temporal(TemporalType.DATE)
	private Date submissionDate;
	
	public Application() {

	}

	
	public Application(Student owner, Project project, 
			String studentResponse) {
		this.studentApplicant = owner;
		this.applicationProject = project;
		this.status = ApplicationStatus.Pending;
		if (studentResponse.length() >= MAX_RESPONSE_CHARS) {
			this.studentResponse = studentResponse.substring(0, MAX_RESPONSE_CHARS);
		}
		else {
			this.studentResponse = studentResponse;
		}
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
		if (applicationProject == null) {
			if (this.applicationProject != null) {
				Project p = this.applicationProject;
				this.applicationProject = null;
				p.removeApplication(this);
			}
		}
		else {
			this.applicationProject = applicationProject;
			if (!applicationProject.getApplications().contains(this)) {
				applicationProject.addApplication(this);
			}
		}
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
		if (studentResponse.length() >= MAX_RESPONSE_CHARS) {
			this.studentResponse = studentResponse.substring(0, MAX_RESPONSE_CHARS);
		}
		else {
			this.studentResponse = studentResponse;
		}
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