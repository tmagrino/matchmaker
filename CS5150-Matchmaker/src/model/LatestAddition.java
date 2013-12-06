package model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity (name="LATEST_ADDITION")
public class LatestAddition {
	@Id @Column(name="ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name="TYPE")
	private String type;
	@Column(name="NAME")
	private String name;
	@Column(name="SUBMISSIONDATE")
	private Date submissionDate;
	
	LatestAddition() {
		submissionDate = null;
	}
	
	LatestAddition(String type, String name) {
		this.type = type;
		this.name = name;
		submissionDate = new Date();
	}

	public long getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public Date getSubmissionDate() {
		return submissionDate;
	}
}
