package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


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
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OWNER_ID")
	private Student studentApplicant;
	@ManyToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name = "OWNER_ID")
	private Project applicationProject;
	@Column(name = "STATUS")
	private ApplicationStatus status;
	@Column(name = "STUD_DATA")
	private String studentResponse;
	@Column(name = "SUBMITTED")
	private Date submissionDate;
	
	
	public Application() {
		
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


	public void setStudentApplicant(Student studentApplicant) {
		this.studentApplicant = studentApplicant;
	}


	public Project getApplicationProject() {
		return applicationProject;
	}


	public void setApplicationProject(Project applicationProject) {
		this.applicationProject = applicationProject;
	}


	public ApplicationStatus getStatus() {
		return status;
	}


	public void setStatus(ApplicationStatus status) {
		this.status = status;
	}


	public String getStudentResponse() {
		return studentResponse;
	}


	public void setStudentResponse(String studentResponse) {
		this.studentResponse = studentResponse;
	}


	public Date getSubmissionDate() {
		return submissionDate;
	}


	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	}
}
