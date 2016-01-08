package model;

import java.util.Date;
import java.io.Serializable;

import javax.persistence.*;

import java.util.List;

import util.Sanitization;

/**
 * 
 * Represents an experience that a {@link Student} has experienced, such
 * as an intership or a job.
 * <p>
 * Not in use
 * 
 * @author Jan Cardenas
 * @author Leonardo Neves
 *
 */

@Embeddable
public class Experience {
	@Temporal(TemporalType.DATE)
	private Date startDate;
	@Temporal(TemporalType.DATE)
	private Date endDate;
	private String jobTitle;
	private String location;
	private String description;
	
	public Experience() {
		
	}
	
	Experience(Date startDate, Date endDate, String jobTitle, String location,
			String description) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.jobTitle = jobTitle;
		this.location = location;
		this.description = Sanitization.sanitizeLongText(description);
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public String getLocation() {
		return location;
	}

	public String getDescription() {
		return description;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setDescription(String description) {
		this.description = Sanitization.sanitizeLongText(description);
	}
}
