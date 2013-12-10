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
 * The persistent class for the Student database table.
 * 
 */

/*
 *   Table: STUDENT
 *   
 *   |  ID  |      NAME    |   NETID  |  GPA  |  WEBPAGE            |  YEAR     |
 *   |  1   | Steve Carell | sc332    | 3.1   | www.stevecarell.com | Senior    |
 *   |  2   | Jim Carrey   | jc299    | 2.9   | www.jimcarrey.com   | Ph. D     |
 *   |  3   | Bob Bobson   | bb22     | 3.9   |                     | Graduate  |
 *   |      |              |          |       |                     |           |
 *   
 */


/*
 * TODO:
 * Fix getters/setters to for the fields with tables
 * - http://en.wikibooks.org/wiki/Java_Persistence/OneToMany#Getters_and_Setters
 * Take another look at the constructor(s)
 */
@Entity(name = "STUDENT")
//@NamedQuery(name="Student.findAll", query="SELECT s FROM Student s")
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;
	
	// Persistent Fields
	@Id @Column(name = "ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "NAME", nullable = false, length = 75)
	private String name;
	@Column(name = "NETID", nullable = false, length = 10)
	private String netID;
	@Column(name = "GPA", nullable = false)
	private double gpa;
	@Column(name = "email", nullable = false)
	private String email;
	@Column(name = "YEAR")
	private Year year;
	@OneToOne (mappedBy = "student")
	private User user;
	@ManyToMany
	@JoinTable(
			name = "COLLEGES_TABLE",
			joinColumns = {@JoinColumn(name="STUD_ID", referencedColumnName="ID")},
			inverseJoinColumns = {@JoinColumn(name="COLLEGE_ID", referencedColumnName="ID")}
	)
	private List<College> colleges;
	@ManyToMany
	@JoinTable(
			name = "MAJORS_TABLE",
			joinColumns = {@JoinColumn(name="STUD_ID", referencedColumnName="ID")},
			inverseJoinColumns = {@JoinColumn(name="MAJOR_ID", referencedColumnName="ID")}
	)
	private List<Major> majors;
	@ManyToMany
	@JoinTable(
			name = "MINORS_TABLE",
			joinColumns = {@JoinColumn(name="STUD_ID", referencedColumnName="ID")},
			inverseJoinColumns = {@JoinColumn(name="MINOR_ID", referencedColumnName="ID")}
	)
	private List<Minor> minors;
	@ManyToMany
	@JoinTable(
			name = "SKILLS_TABLE",
			joinColumns = {@JoinColumn(name="STUD_ID", referencedColumnName="ID")},
			inverseJoinColumns = {@JoinColumn(name="SKILL_ID", referencedColumnName="ID")}
	)
	private List<Skill> skills;
	@ElementCollection
	@CollectionTable(name = "EXPERIENCES",
					 joinColumns = {@JoinColumn(name="STUD_ID")}
					)
	private List<Experience> priorExperience;
	@ManyToMany
	@JoinTable(
			name = "STUDENTS_INTERESTS_TABLE",
			joinColumns = {@JoinColumn(name="STUD_ID", referencedColumnName="ID")},
			inverseJoinColumns = {@JoinColumn(name="INTER_ID", referencedColumnName="ID")}
	)
	private List<Interest> interests;
	@ElementCollection  
	@CollectionTable (
			name = "CLASSES_TABLE",
			joinColumns = @JoinColumn(name = "OWNER_ID")
		)
	private List<Course> transcript;
	@OneToMany(mappedBy = "studentApplicant", cascade = CascadeType.ALL)
	private List<Application> applications;
	@OneToOne (mappedBy = "student", cascade = CascadeType.ALL)
	private StudentSettings settings;
	@ManyToMany (mappedBy = "hiddenStudents")
	private List<ResearcherSettings> hiddenByResearcher;

	// Constructors
	public Student() 
	{
		
	}
	
	/*
	 * Formal constructor: no initial applications or settings
	 */
	Student(String name, String netID, double gpa, String email,
			Year year, List<College> colleges, List<Major> majors,
			List<Minor> minors, List<Skill> skills,
			List<Experience> priorExperience, List<Interest> interests,
			List<Course> transcript, User user) {
		this.name = name;
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
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the netID
	 */
	public String getNetID() {
		return netID;
	}

	/**
	 * @return the gpa
	 */
	public double getGpa() {
		return gpa;
	}

	/**
	 * @return the webpage
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the year
	 */
	public Year getYear() {
		return year;
	}

	/**
	 * @return the serialversionuid
	 */
	static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @return the colleges
	 */
	public List<College> getColleges() {
		return colleges;
	}

	/**
	 * @return the majors
	 */
	public List<Major> getMajors() {
		return majors;
	}

	/**
	 * @return the minors
	 */
	public List<Minor> getMinors() {
		return minors;
	}

	/**
	 * @return the skills
	 */
	public List<Skill> getSkills() {
		return skills;
	}

	/**
	 * @return the priorExperience
	 */
	public List<Experience> getPriorExperience() {
		return priorExperience;
	}

	/**
	 * @return the interests
	 */
	public List<Interest> getInterests() {
		return interests;
	}

	/**
	 * @return the transcript
	 */
	public List<Course> getTranscript() {
		return transcript;
	}

	/**
	 * @return the applications
	 */
	public List<Application> getApplications() {
		return applications;
	}

	/**
	 * @return the settings
	 */
	public StudentSettings getSettings() {
		return settings;
	}
	
	/**
	 * @param id the id to set
	 */
	void setId(long id) {
		this.id = id;
	}

	/**
	 * @param name the name to set
	 */
	void setName(String name) {
		this.name = name;
	}

	/**
	 * @param netID the netID to set
	 */
	void setNetID(String netID) {
		this.netID = netID;
	}

	/**
	 * @param gpa the gpa to set
	 */
	void setGpa(double gpa) {
		this.gpa = gpa;
	}

	/**
	 * @param webpage the webpage to set
	 */
	void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param year the year to set
	 */
	void setYear(Year year) {
		this.year = year;
	}

	/**
	 * @param user the user to set
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

	
	
	void addCollege(College college) {
		if (!this.colleges.contains(college)) {
			this.colleges.add(college);
			if (!college.getStudents().contains(this)) {
				college.addStudent(this);
			}
		}
	}
	
	void removeCollege(College college) {
		if (this.colleges.remove(college)) {
			if (college.getStudents().contains(this)) {
				college.removeStudent(this);
			}
		}
	}
	
	void removeColleges() {
		for (College c : colleges) {
			c.getStudents().remove(this);
		}
	}
	
	void addMajor(Major major) {
		if (!this.majors.contains(major)) {
			this.majors.add(major);
			if (!major.getStudents().contains(this)) {
				major.addStudent(this);
			}
		}
	}
	
	void removeMajor(Major major) {
		if (this.majors.remove(major)) {
			if (major.getStudents().contains(this)) {
				major.removeStudent(this);
			}
		}
	}
	
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
	void removeMajors() {
		for (Major m : majors) {
			m.getStudents().remove(this);
		}
	}
	
	void addMinor(Minor minor) {
		if (!this.minors.contains(minor)) {
			this.minors.add(minor);
			if (!minor.getStudents().contains(this)) {
				minor.addStudent(this);
			}
		}
	}
	
	void removeMinor(Minor minor) {
		if (this.minors.remove(minor)) {
			if (minor.getStudents().contains(this)) {
				minor.removeStudent(this);
			}
		}
	}
	
	void removeMinors() {
		for (Minor m : minors) {
			m.getStudents().remove(this);
		}
	}
	
	void addSkill(Skill skill) {
		if (!this.skills.contains(skill)) {
			this.skills.add(skill);
			if (!skill.getStudents().contains(this)) {
				skill.addStudent(this);
			}
		}
	}
	
	void removeSkill(Skill skill) {
		if (this.skills.remove(skill)) {
			if (skill.getStudents().contains(this)) {
				skill.removeStudent(this);
			}
		}
	}
	
	void removeSkills() {
		for (Skill s : skills) {
			s.getStudents().remove(this);
		}
	}
	
	void addInterest(Interest interest) {
		if (!this.interests.contains(interest)) {
			this.interests.add(interest);
			if (!interest.getStudents().contains(this)) {
				interest.addStudent(this);
			}
		}
	}
	
	void removeInterest(Interest interest) {
		if (this.interests.remove(interest)) {
			if (interest.getStudents().contains(this)) {
				interest.removeStudent(this);
			}
		}
	}
	
	void removeInterests() {
		for (Interest i : interests) {
			i.getStudents().remove(this);
		}
	}
	
	void addExperience(Experience exp) {
		priorExperience.add(exp);
	}
	
	void removeExperience(Experience exp) {
		priorExperience.remove(exp);
	}
	
	void addCourse(Course c) {
		transcript.add(c);
	}
	
	void removeCourse(Course c) {
		transcript.remove(c);
	}
	
	void addApplication(Application app) {
		if (!this.applications.contains(app)) {
			this.applications.add(app);
			if (app.getStudentApplicant() != this) {
				app.setStudentApplicant(this);
			}
		}
	}
	
	void removeApplication(Application app) {
		if (this.applications.remove(app)) {
			if (app.getStudentApplicant() == this) {
				app.setStudentApplicant(null);
			}
		}
	}

	/**
	 * @param settings the settings to set
	 */
	void setSettings(StudentSettings settings) {
		this.settings = settings;
	}

	public List<ResearcherSettings> getHiddenByResearcher() {
		return hiddenByResearcher;
	}

	void addHiddenByResearcher(ResearcherSettings r) {
		if (!hiddenByResearcher.contains(r)) {
			hiddenByResearcher.add(r);
			if (!r.getHiddenStudents().contains(this)) {
				r.addStudent(this);
			}
		}
	}
	
	void removeHiddenByResearcher(ResearcherSettings r) {
		if (hiddenByResearcher.remove(r)) {
			if (r.getHiddenStudents().contains(this)) {
				r.removeStudent(this);
			}
		}
	}
	
	void removeHiddenByResearchers() {
		for (ResearcherSettings r : hiddenByResearcher) {
			r.getHiddenStudents().remove(this);
		}
	}
}