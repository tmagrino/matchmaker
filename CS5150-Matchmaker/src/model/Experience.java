package model;

import java.util.Date;
import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/*
 * This class represents a prior experience a student
 * has had
 * 
 * http://en.wikibooks.org/wiki/Java_Persistence/ElementCollection
 */
@Embeddable
public class Experience {
	private Date startDate;
	private Date endDate;
	private String jobTitle;
	private String location;
	private String description;
	
}
