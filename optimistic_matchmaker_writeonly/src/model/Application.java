package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import util.Sanitization;
import javax.persistence.LockModeType;
import javax.persistence.Version;


/**
 * Persistant JPA Entity Class
 * <p>
 * Represents a {@link Student}'s Application to a {@link Project}
 * <p>
 * Follows the Model-View-Controller software pattern. Because of field and method
 * visibilities, only getter methods can be accessed. To create or alter instances
 * of this class, use {@link ApplicationController}.
 * 
 * @author Jan Cardenas
 * @author Leonardo Neves
 * @author Tom Magrino
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
	
	// Student's message
	@Column(name = "STUD_DATA", length = MAX_RESPONSE_CHARS)
	private String studentResponse;
	
	// Date submitted
	@Column(name = "SUBMITTED")
	@Temporal(TemporalType.DATE)
	private Date submissionDate;

	@Version
	long version = 0;
	
	Application() {

	}

	/**
	 * Fully initializes an instance of an Application.
	 * Default {@link ApplicationStatus} is "Pending"
	 * 
	 * @param owner the {@link Student} creating this application
	 * @param project the {@link Project} being applied to
	 * @param studentResponse {@link Student}'s additional message with the application
	 */
	public Application(Student owner, Project project, 
			String studentResponse) {
		this.studentApplicant = owner;
		this.applicationProject = project;
		this.status = ApplicationStatus.Pending;
		if (studentResponse.length() >= MAX_RESPONSE_CHARS) {
			studentResponse = studentResponse.substring(0, MAX_RESPONSE_CHARS);
		}
		else {
			studentResponse = studentResponse;
		}
                this.studentResponse = Sanitization.sanitizeLongText(studentResponse);
		this.submissionDate = new Date();
	}
	
	/**
	 * 
	 * @return the Application's ID in the Application table in the database
	 */
	public long getId() {
		return id;
	}

	/**
	 * 
	 * @return the {@link Student} who owns this Application
	 */
	public Student getStudentApplicant() {
		return studentApplicant;
	}

	/**
	 * Sets both sides of the relationship between {@link Student} and the Application
	 * <p>
	 * If the argument given is null, both sides of the relationship are also set to null
	 * 
	 * @param studentApplicant the {@link Student} to be set as owner of this Application
	 */
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

	/**
	 * 
	 * @return the {@link Project} associated with this Application
	 */
	public Project getApplicationProject() {
		return applicationProject;
	}

	/**
	 * Sets both sides of the relationship between {@link Project} and the Application
	 * <p>
	 * If the argument given is null, both sides of the relationship are also set to null
	 * 
	 * @param applicationProject the {@link Project} to be set to this Application
	 */
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

	/**
	 * 
	 * @return the {@link ApplicationStatus} of this Application
	 */
	public ApplicationStatus getStatus() {
		return status;
	}

	/**
	 * 
	 * @param status the {@link ApplicationStatus} to be set to this Application
	 */
	void setStatus(ApplicationStatus status) {
		this.status = status;
	}

	/**
	 * 
	 * @return this Application's {@link Student}'s message with this Application
	 */
	public String getStudentResponse() {
		return studentResponse;
	}

	/**
	 * Sets the message associated with this Application. Message is truncated
	 * if too long.
	 * 
	 * @param studentResponse the message to be attached with this Application
	 */
	void setStudentResponse(String studentResponse) {
		if (studentResponse.length() >= MAX_RESPONSE_CHARS) {
			studentResponse = studentResponse.substring(0, MAX_RESPONSE_CHARS);
		}
		else {
			studentResponse = studentResponse;
		}
                this.studentResponse = Sanitization.sanitizeLongText(studentResponse);
	}

	/**
	 * 
	 * @return the {@link Date} this Application was created
	 */
	public Date getSubmissionDate() {
		return submissionDate;
	}
}
