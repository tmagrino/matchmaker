// Model for Student created in the code
package model;

import java.awt.image.BufferedImage;
import java.io.Serializable;

import javax.persistence.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * Persistant JPA Entity Class
 * <p>
 * Represents a student
 * <p>
 * Follows the Model-View-Controller software pattern. Because of field and method
 * visibilities, only getter methods can be accessed. To create or alter instances
 * of this class, use {@link StudentController}.
 * 
 * @author Jan Cardenas
 * @author Leonardo Neves
 *
 */

@Entity(name = "STUDENT")
//@NamedQuery(name="Student.findAll", query="SELECT s FROM Student s")
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final int MAX_NAME_CHARS = 75;
	
	// Persistent Fields
	// This Student's id in the STUDENT table of the database
	@Id @Column(name = "ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	// Name of this Student
	@Column(name = "NAME", nullable = false, length = MAX_NAME_CHARS)
	private String name;
	
	// NetID of this Student
	@Column(name = "NETID", nullable = false, length = 10)
	private String netID;
	
	// GPA of this Student
	@Column(name = "GPA", nullable = false)
	private double gpa;
	
	// Email of this Student
	@Column(name = "email", nullable = false)
	private String email;
	
	// This Student's Year in school
	@Column(name = "YEAR")
	private Year year;
	
	// User associated with this Student
	@OneToOne (mappedBy = "student")
	private User user;
	
	// This Student's colleges
	@ManyToMany
	@JoinTable(
			name = "COLLEGES_TABLE",
			joinColumns = {@JoinColumn(name="STUD_ID", referencedColumnName="ID")},
			inverseJoinColumns = {@JoinColumn(name="COLLEGE_ID", referencedColumnName="ID")}
	)
	private List<College> colleges;
	
	// This Student's Majors
	@ManyToMany
	@JoinTable(
			name = "MAJORS_TABLE",
			joinColumns = {@JoinColumn(name="STUD_ID", referencedColumnName="ID")},
			inverseJoinColumns = {@JoinColumn(name="MAJOR_ID", referencedColumnName="ID")}
	)
	private List<Major> majors;
	
	// This Student's Minors
	@ManyToMany
	@JoinTable(
			name = "MINORS_TABLE",
			joinColumns = {@JoinColumn(name="STUD_ID", referencedColumnName="ID")},
			inverseJoinColumns = {@JoinColumn(name="MINOR_ID", referencedColumnName="ID")}
	)
	private List<Minor> minors;
	
	// This Student's Skills
	@ManyToMany
	@JoinTable(
			name = "SKILLS_TABLE",
			joinColumns = {@JoinColumn(name="STUD_ID", referencedColumnName="ID")},
			inverseJoinColumns = {@JoinColumn(name="SKILL_ID", referencedColumnName="ID")}
	)
	private List<Skill> skills;
	
	// This Student's Experiences
	@ElementCollection
	@CollectionTable(name = "EXPERIENCES",
					 joinColumns = {@JoinColumn(name="STUD_ID")}
					)
	private List<Experience> priorExperience;
	
	// This Student's Interests
	@ManyToMany
	@JoinTable(
			name = "STUDENTS_INTERESTS_TABLE",
			joinColumns = {@JoinColumn(name="STUD_ID", referencedColumnName="ID")},
			inverseJoinColumns = {@JoinColumn(name="INTER_ID", referencedColumnName="ID")}
	)
	private List<Interest> interests;
	
	// This Student's completed classes 
	@ElementCollection  
	@CollectionTable (
			name = "CLASSES_TABLE",
			joinColumns = @JoinColumn(name = "OWNER_ID")
		)
	private List<Course> transcript;
	
	// This Student's applications to Projects
	@OneToMany(mappedBy = "studentApplicant", cascade = CascadeType.ALL)
	private List<Application> applications;
	
	// This Student's settings for the website
	@OneToOne (mappedBy = "student", cascade = CascadeType.ALL)
	private StudentSettings settings;
	
	// List of Researchers who have hidden this Student
	@ManyToMany (mappedBy = "hiddenStudents")
	private List<ResearcherSettings> hiddenByResearcher;

	@Version
	long version = 0;

	Student() 
	{
		
	}
	
	/*
	 * Formal constructor: no initial applications or settings
	 */
	/**
	 * Creates a Student instance
	 * @param name name of this Student
	 * @param netID netID of this Student
	 * @param gpa Grade Point Average
	 * @param email email address
	 * @param year {@link Year} in school
	 * @param colleges List of {@link College}s
	 * @param majors List of {@link Major}s
	 * @param minors List of {@link Minor}s 
	 * @param skills List of {@link Skill}s
	 * @param priorExperience List of Experiences
	 * @param interests List of {@link Interest}s
	 * @param transcript List of completed {@link Course}s
	 * @param user {@link User} associated with this Student
	 */
	Student(String name, String netID, double gpa, String email,
			Year year, List<College> colleges, List<Major> majors,
			List<Minor> minors, List<Skill> skills,
			List<Experience> priorExperience, List<Interest> interests,
			List<Course> transcript, User user) {
		if (name.length() >= MAX_NAME_CHARS) {
			this.name = name.substring(0, MAX_NAME_CHARS);
		}
		else {
			this.name = name;
		}
		this.netID = netID;
		this.gpa = gpa;
		this.email = email;
		this.year = year;
		
		if (colleges == null) {
			this.colleges = new ArrayList<College>();
		}
		else {
			this.colleges = colleges;
		}
		
		if (majors == null) {
			this.majors = new ArrayList<Major>();
		}
		else {
			this.majors = majors;
		}
		
		if (minors == null) {
			this.minors = new ArrayList<Minor>();
		}
		else {
			this.minors = minors;
		}
		
		if (skills == null) {
			this.skills = new ArrayList<Skill>();
		}
		else {
			this.skills = skills;
		}
		
		if (interests == null) {
			this.interests = new ArrayList<Interest>();
		}
		else {
			this.interests = interests;
		}
		
		if (priorExperience == null) {
			this.priorExperience = new ArrayList<Experience>();
		}
		else {
			this.priorExperience = priorExperience;
		}

		if (transcript == null) {
			this.transcript = new ArrayList<Course>();
		}
		else {
			this.transcript = transcript;
		}
		this.user = user;
	}
	
	/**
	 * Gets an attribute from this Student. Used in JSP pages to iterate through attributes
	 * @param attr String of one of this Student's attributes
	 * @return the field that matches the given string
	 */
	public String getAttribute(String attr) {
		switch (attr.toLowerCase()) {
			case "name":
				return name;
			case "netID":
				return netID;
			case "email":
				return email;
			case "gpa":
				return Double.toString(gpa);
			case "year":
				if(year != null) return year.toString();
				else return "1";
			case FieldFactory.COLLEGE:
				return getString(getColleges());
			case FieldFactory.MAJOR:
				return getString(getMajors());
			case FieldFactory.MINOR:
				return getString(getMinors());
			case FieldFactory.SKILL:
				return getString(getSkills());
			case "skills":
				return getString(getSkills());
			case FieldFactory.INTEREST:
				return getString(getInterests());
			case "research interests":
				return getString(getInterests());
			default:
				System.out.println("Invalid attribute " + attr);
				return null;
		}
	}
	
	/**
	 * 
	 * @param attr
	 * @return a List of one of this Student's attributes determined by the string given
	 */
	public List<? extends FieldValue> getListAttribute(String attr) {
		switch (attr.toLowerCase()) {
			case FieldFactory.COLLEGE:
				return getColleges();
			case FieldFactory.MAJOR:
				return getMajors();
			case FieldFactory.MINOR:
				return getMinors();
			case FieldFactory.SKILL:
				return getSkills();
			case FieldFactory.INTEREST:
				return getInterests();
			default:
				System.out.println("Invalid attribute " + attr);
				return null;
		}
	}
	
	/**
	 * @return the id of this Student in the STUDENT table of the database
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the name of this Student
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the netID of this Student
	 */
	public String getNetID() {
		return netID;
	}

	/**
	 * @return the gpa of this Student
	 */
	public double getGpa() {
		return gpa;
	}

	/**
	 * @return the email of this Student
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the {@link Year} of this Student
	 */
	public Year getYear() {
		return year;
	}

	/**
	 * @return the {@link User} associated with this Student
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @return the {@link College}s this Student is enrolled in
	 */
	public List<College> getColleges() {
		return colleges;
	}

	/**
	 * @return the {@link Major}s this Student is enrolled in
	 */
	public List<Major> getMajors() {
		return majors;
	}

	/**
	 * @return the {@link Minor}s this Student is enrolled in
	 */
	public List<Minor> getMinors() {
		return minors;
	}

	/**
	 * @return the {@link Skill}s this Student has
	 */
	public List<Skill> getSkills() {
		return skills;
	}

	/**
	 * @return the priorExperiences this Student has had
	 */
	public List<Experience> getPriorExperience() {
		return priorExperience;
	}

	/**
	 * @return the {@link Interest}s this Student has
	 */
	public List<Interest> getInterests() {
		return interests;
	}

	/**
	 * @return the {@link Course}s this Student has completed
	 */
	public List<Course> getTranscript() {
		return transcript;
	}

	/**
	 * @return the {@link Application}s this Student has submitted
	 */
	public List<Application> getApplications() {
		return applications;
	}

	/**
	 * @return the {@link StudentSettings} this Student has for the website
	 */
	public StudentSettings getSettings() {
		return settings;
	}

	/**
	 * Sets the name of the Student to the given name. Name is truncated if
	 * longer than the name limit. Also updates the associated {@link User}'s
	 * name.
	 * @param name the name to set to this Student
	 */
	void setName(String name) {
		String fixedName = name;
		if (name.length() >= MAX_NAME_CHARS) {
			fixedName = name.substring(0, MAX_NAME_CHARS);
		}
		this.name = fixedName;
		user.setName(fixedName);
	}

	/**
	 * Sets the netID of this Student to the given netID. Also updates the
	 * associated {@link User}'s netID.
	 * @param netID the netID to this Student
	 */
	void setNetID(String netID) {
		this.netID = netID;
		user.setNetid(netID);
	}

	/**
	 * @param gpa the gpa to set to this Student
	 */
	void setGpa(double gpa) {
		this.gpa = gpa;
	}

	/**
	 * Sets the email of this Student. Also updates the associated 
	 * {@link User}'s email.
	 * @param email the email to set to this Student
	 */
	void setEmail(String email) {
		this.email = email;
		user.setEmail(email);
	}

	/**
	 * @param year the {@link Year} to set to this Student
	 */
	void setYear(Year year) {
		this.year = year;
	}

	/**
	 * Sets the {@link User} associated with this Student. Updates both sides
	 * of the relationship.
	 * @param user the {@link User} to associate with this Student
	 */
	void setUser(User user) {
		if (user == null) {
			if (this.user != null) {
				if (this.user.getStudent() != null) {
					User u = this.user;
					this.user = null;
					u.setStudent(null);
				}
			}
		}
		else {
			this.user = user;
			if (user.getStudent() != this) {
				user.setStudent(this);
			}
		}
	}
	
	/**
	 * Return a String version of a collection with elements separated by commas
	 */
	public String getString(List<? extends FieldValue> collection) {
		if (collection.size() > 0) {
			Collections.sort(collection);
			StringBuilder builder = new StringBuilder();
			for (FieldValue i : collection){
				builder.append(i.getDescription() + ", ");
			}
			builder.deleteCharAt(builder.length() - 2);
			return builder.toString();
		}
		return "";
	}
	/**
	 * Return a truncated version of a collection string
	 */
	public String getTruncatedString(List<? extends FieldValue> collection){
		if (collection.size() > 0) {
			int subSequenceSize = 16;
			Collections.sort(collection);
			StringBuilder builder = new StringBuilder();
			for (FieldValue i : collection){
				builder.append(i.getDescription() + ", ");
				if (builder.length() > subSequenceSize+1){
					return builder.toString().subSequence(0, subSequenceSize) + "...";
				}
			}
			builder.deleteCharAt(builder.length() - 2);
			return builder.toString();
		}
		return "";
	}

	
	/**
	 * Adds a {@link College} to this Student. Updates both sides of the Student/College
	 * relationship.
	 * @param college the {@link College} to add
	 */
	void addCollege(College college) {
		if (!this.colleges.contains(college)) {
			this.colleges.add(college);
			if (!college.getStudents().contains(this)) {
				college.addStudent(this);
			}
		}
	}
	
	/**
	 * Removes a {@link College} from this Student. Updates both sides of the Student/College
	 * relationship.
	 * @param college the {@link College} to remove
	 */
	void removeCollege(College college) {
		if (this.colleges.remove(college)) {
			if (college.getStudents().contains(this)) {
				college.removeStudent(this);
			}
		}
	}
	
	/**
	 * Removes all {@link College}s from this Student
	 */
	void removeColleges() {
		for (College c : colleges) {
			c.getStudents().remove(this);
		}
		colleges = new ArrayList<College>();
	}
	
	/**
	 * Adds a {@link Major} to this Student. Updates both sides of the Student/Major
	 * relationship.
	 * @param major the {@link Major} to add
	 */
	void addMajor(Major major) {
		if (!this.majors.contains(major)) {
			this.majors.add(major);
			if (!major.getStudents().contains(this)) {
				major.addStudent(this);
			}
		}
	}
	
	/**
	 * Removes a {@link Major} from this Student. Updates both sides of the Student/Major
	 * relationship.
	 * @param major the {@link Major} to remove
	 */
	void removeMajor(Major major) {
		if (this.majors.remove(major)) {
			if (major.getStudents().contains(this)) {
				major.removeStudent(this);
			}
		}
	}
	
	/**
	 * Removes all of one type of {@link FieldValue} from this Student
	 * @param type the {@link FieldValue} type to remove
	 */
	void remove(String type){
		if(type.toLowerCase() == FieldFactory.MAJOR){
			this.removeMajors();
		}
		if(type.toLowerCase() == FieldFactory.MINOR){
			this.removeMinors();
		}
		if (type.toLowerCase() == FieldFactory.COLLEGE){
			this.removeColleges();
		}
		if (type.toLowerCase() == FieldFactory.SKILL){
			this.removeSkills();
		}
		if (type.toLowerCase() == FieldFactory.INTEREST){
			this.removeInterests();
		}
		
		
	}
	
	/**
	 * Removes all {@link Major}s from this Student
	 */
	void removeMajors() {
		for (Major m : majors) {
			m.getStudents().remove(this);
		}
		majors = new ArrayList<Major>();
		
	}
	
	/**
	 * Adds a {@link Minor} to this Student. Updates both sides of the Student/Minor
	 * relationship.
	 * @param minor the {@link Minor} to add
	 */
	void addMinor(Minor minor) {
		if (!this.minors.contains(minor)) {
			this.minors.add(minor);
			if (!minor.getStudents().contains(this)) {
				minor.addStudent(this);
			}
		}
	}
	
	/**
	 * Removes a {@link Major} from this Student. Updates both sides of the Student/Major
	 * relationship.
	 * @param minor the {@link Major} to remove
	 */
	void removeMinor(Minor minor) {
		if (this.minors.remove(minor)) {
			if (minor.getStudents().contains(this)) {
				minor.removeStudent(this);
			}
		}
	}
	
	/** 
	 * Removes all {@link Minor}s from this Student
	 */
	void removeMinors() {
		for (Minor m : minors) {
			m.getStudents().remove(this);
		}
		minors = new ArrayList<Minor>();
	}
	
	/**
	 * Adds a {@link Skill} to this Student. Updates both sides of the Student/Skill
	 * relationship.
	 * @param skill the {@link Skill} to add
	 */
	void addSkill(Skill skill) {
		if (!this.skills.contains(skill)) {
			this.skills.add(skill);
			if (!skill.getStudents().contains(this)) {
				skill.addStudent(this);
			}
		}
	}
	
	/**
	 * Removes a {@link Skill} from this Student. Updates both sides of the Student/Skill
	 * relationship.
	 * @param skill the {@link Skill} to remove
	 */
	void removeSkill(Skill skill) {
		if (this.skills.remove(skill)) {
			if (skill.getStudents().contains(this)) {
				skill.removeStudent(this);
			}
		}
	}
	
	/** 
	 * Removes all {@link Skill}s from this Student
	 */
	void removeSkills() {
		for (Skill s : skills) {
			s.getStudents().remove(this);
		}
		skills = new ArrayList<Skill>();
	}
	
	/**
	 * Adds an {@link Interest} to this Student. Updates both sides of the Student/Interest
	 * relationship.
	 * @param interest the {@link Interest} to add
	 */
	void addInterest(Interest interest) {
		if (!this.interests.contains(interest)) {
			this.interests.add(interest);
			if (!interest.getStudents().contains(this)) {
				interest.addStudent(this);
			}
		}
	}
	
	/**
	 * Removes a {@link Interest} from this Student. Updates both sides of the Student/Interest
	 * relationship.
	 * @param interest the {@link Interest} to remove
	 */
	void removeInterest(Interest interest) {
		if (this.interests.remove(interest)) {
			if (interest.getStudents().contains(this)) {
				interest.removeStudent(this);
			}
		}
	}
	
	/**
	 * Removes all {@link Interest}s from this Student.
	 */
	void removeInterests() {
		for (Interest i : interests) {
			i.getStudents().remove(this);
		}
		interests = new ArrayList<Interest>();
	}
	
	/**
	 * @param exp the Experience to add
	 */
	void addExperience(Experience exp) {
		priorExperience.add(exp);
	}
	
	/**
	 * 
	 * @param exp the Experience to remove
	 */
	void removeExperience(Experience exp) {
		priorExperience.remove(exp);
	}
	
	/**
	 * 
	 * @param c the {@link Course} to add
	 */
	void addCourse(Course c) {
		transcript.add(c);
	}
	
	/**
	 * 
	 * @param c the {@link Course} to remove
	 */
	void removeCourse(Course c) {
		transcript.remove(c);
	}
	
	/**
	 * Adds a {@link Application} to this Student. Updates both sides of the Student/Application
	 * relationship.
	 * @param app the {@link Application} to add
	 */
	void addApplication(Application app) {
		if (!this.applications.contains(app)) {
			this.applications.add(app);
			if (app.getStudentApplicant() != this) {
				app.setStudentApplicant(this);
			}
		}
	}
	
	/**
	 * Removes a {@link Application} from this Student. Updates both sides of the Student/Application
	 * relationship.
	 * @param app the {@link Application} to remove
	 */
	void removeApplication(Application app) {
		if (this.applications.remove(app)) {
			if (app.getStudentApplicant() == this) {
				app.setStudentApplicant(null);
			}
		}
	}
	
	/**
	 * Removes all {@link Application}s from this Student
	 * @return the {@link Application}s to be deleted by the Controller
	 */
	List<Application> removeApplications() {
		List<Application> toDelete = new ArrayList<Application>();
		for (Application a : applications) {
			if (a.getApplicationProject() != null) {
				a.setApplicationProject(null);
			}
			toDelete.add(a);
		}
		applications = new ArrayList<Application>();
		return toDelete;
	}

	/**
	 * @param settings the {@link StudentSettings} to set for this Student
	 */
	void setSettings(StudentSettings settings) {
		this.settings = settings;
	}

	/**
	 * 
	 * @return the {@link ResearcherSettings} which have hidden this Student
	 */
	public List<ResearcherSettings> getHiddenByResearcher() {
		return hiddenByResearcher;
	}

	/**
	 * Adds a {@link ResearcherSettings} to this Student which is hiding this Student. 
	 * Updates both sides of the Student/ResearcherSettings relationship.
	 * @param r the {@link ResearcherSettings} to add
	 */
	void addHiddenByResearcher(ResearcherSettings r) {
		if (!hiddenByResearcher.contains(r)) {
			hiddenByResearcher.add(r);
			if (!r.getHiddenStudents().contains(this)) {
				r.addStudent(this);
			}
		}
	}
	
	/**
	 * Removes a {@link ResearcherSettings} to this Student which is hiding this Student. 
	 * Updates both sides of the Student/ResearcherSettings relationship.
	 * @param r the {@link ResearcherSettings} to remove
	 */
	void removeHiddenByResearcher(ResearcherSettings r) {
		if (hiddenByResearcher.remove(r)) {
			if (r.getHiddenStudents().contains(this)) {
				r.removeStudent(this);
			}
		}
	}
	
	/**
	 * Removes all {@link ResearcherSettings} from this Student
	 */
	void removeHiddenByResearchers() {
		for (ResearcherSettings r : hiddenByResearcher) {
			r.getHiddenStudents().remove(this);
		}
		hiddenByResearcher = new ArrayList<ResearcherSettings>();
	}
}
