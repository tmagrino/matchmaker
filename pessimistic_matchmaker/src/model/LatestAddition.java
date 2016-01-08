package model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * 
 * Persistant JPA Entity Class
 * <p>
 * An entity created in parallel with a {@link FieldValue} used to 
 * faciliate generating lists of recently added {@link FieldValue}s
 * <p>
 * Has no direct link with its associated {@link FieldValue}, but contains information
 * about its name and type to be able to search for the associated {@link FieldValue}
 * 
 * @author Jan Cardenas
 * @author Chris Song
 *
 */

@Entity (name="LATESTADDITION")
public class LatestAddition {
	// Persistent Fields
	// ID in the LATESTADDITION table of the database
	@Id @Column(name="ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	// The FieldValue type of the associated FieldValue
	@Column(name="TYPE")
	private String type;
	
	// The name of the associated FieldValue
	@Column(name="NAME")
	private String name;
	
	// The submission date of the associated FieldValue
	@Column(name="SUBMISSIONDATE", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private final Date submissionDate;

	@Version
	long version = 0;
	
	LatestAddition() {
		submissionDate = null;
	}
	
	/**
	 * Creates a LatestAddition instance
	 * @param type the {@link FieldValue} type of the associated {@link FieldValue}
	 * @param name the name of the associated {@link FieldValue}
	 */
	LatestAddition(String type, String name) {
		
		this.type = type;
		this.name = name;
		submissionDate = new Date();
	}

	/**
	 * 
	 * @return the id of this LatestAddition in the LATESTADDITION table of the database
	 */
	public long getId() {
		return id;
	}

	/**
	 * 
	 * @return the {@link FieldValue} type of the associated {@link FieldValue}
	 */
	public String getType() {
		return type;
	}

	/**
	 * 
	 * @return the name of the associated {@link FieldValue}
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @return the {@link Date} of the creation of the associated {@link FieldValue}
	 */
	public Date getSubmissionDate() {
		return submissionDate;
	}
}
