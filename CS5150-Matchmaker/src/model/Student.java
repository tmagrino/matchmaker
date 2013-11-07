package model;

import java.awt.image.BufferedImage;
import java.io.Serializable;

import javax.persistence.*;

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
	// COLLEGE
	@ManyToMany
	@JoinTable(
			name = "COLLEGES_TABLE",
			joinColumns = {@JoinColumn(name="STUD_ID", referencedColumnName="ID")},
			inverseJoinColumns = {@JoinColumn(name="COLLEGE_ID", referencedColumnName="ID")}
	)
	private List<College> colleges;
	// MAJORS
	@ManyToMany
	@JoinTable(
			name = "MAJORS_TABLE",
			joinColumns = {@JoinColumn(name="STUD_ID", referencedColumnName="ID")},
			inverseJoinColumns = {@JoinColumn(name="MAJOR_ID", referencedColumnName="ID")}
	)
	private List<Major> majors;
	// MINORS
	@ManyToMany
	@JoinTable(
			name = "MINORS_TABLE",
			joinColumns = {@JoinColumn(name="STUD_ID", referencedColumnName="ID")},
			inverseJoinColumns = {@JoinColumn(name="MINOR_ID", referencedColumnName="ID")}
	)
	private List<Minor> minors;
	// SKILLS
	@ManyToMany
	@JoinTable(
			name = "SKILLS_TABLE",
			joinColumns = {@JoinColumn(name="STUD_ID", referencedColumnName="ID")},
			inverseJoinColumns = {@JoinColumn(name="SKILL_ID", referencedColumnName="ID")}
	)
	private List<Skill> skills;
	// Prior Experience
	@ElementCollection  
	@CollectionTable (
			name = "EXPERIENCES_TABLE",
			joinColumns = @JoinColumn(
					name = "OWNER_ID")
			)
	private List<Experience> priorExperience;
	// Interests
	@ManyToMany
	@JoinTable(
			name = "STUDENTS_INTERESTS_TABLE",
			joinColumns = {@JoinColumn(name="STUD_ID", referencedColumnName="ID")},
			inverseJoinColumns = {@JoinColumn(name="INTER_ID", referencedColumnName="ID")}
	)
	private List<Interest> interests;
	// Transcript
	@ElementCollection  
	@CollectionTable (
			name = "CLASSES_TABLE",
			joinColumns = @JoinColumn(name = "OWNER_ID")
		)
	private List<Course> transcript;
	@OneToMany(mappedBy = "studentApplicant")
	private List<Application> applications;
	@Embedded
	private StudentSettings settings;
	//private BufferedImage profilePicture;
	@Version @Column(name = "VERSION")
	private long version;

	// Constructors:
	public Student() 
	{
		
	}
	
	public Student(String anything) {
		this.gpa = 2.0;
		this.name = "JOE BOB";
		this.netID = "JB20";
		this.version = 1;
		this.email = "billybob@cornell.edu";
		this.year = Year.Junior;
	}

	public Student(String name, String netID, double gpa, String email,
			Year year, List<College> colleges, List<Major> majors,
			List<Minor> minors, List<Skill> skills,
			List<Experience> priorExperience, List<Interest> interests,
			List<Course> transcript) {
		this.name = name;
		this.netID = netID;
		this.gpa = gpa;
		this.email = email;
		this.year = year;
		this.colleges = colleges;
		this.majors = majors;
		this.minors = minors;
		this.skills = skills;
		this.priorExperience = priorExperience;
		this.interests = interests;
		this.transcript = transcript;
		this.version = 1;
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
	 * @return the version
	 */
	public long getVersion() {
		return version;
	}
	/**
	 * @return a string with all skills separated by ','
	 */
	public String getSkillString(){
		if (skills.size() > 0){
		StringBuilder builder = new StringBuilder();
		for (Skill s : skills){
			builder.append(s.getDescription()+",");
		}
		builder.deleteCharAt(builder.length() -1);
		return builder.toString();
		}
		return "";
	}
	/**
	 * @return a string with all Interest separated by ','
	 */
	public String getInterestString(){
		if (interests.size() > 0){
		StringBuilder builder = new StringBuilder();
		for (Interest i : interests){
			builder.append(i.getDescription()+",");
		}
		builder.deleteCharAt(builder.length() -1);
		return builder.toString();
		}
		return "";
	}
	/**
	 * @return a string with all Majors separated by ','
	 */
	public String getMajorString(){
		if (majors.size() > 0){
		StringBuilder builder = new StringBuilder();
		for (Major m : majors){
			builder.append(m.getDescription()+",");
		}
		builder.deleteCharAt(builder.length() -1);
		return builder.toString();
		}
		return "";
	}
	
	/**
	 * @return a string with all Minors separated by ','
	 */
	public String getMinorString(){
		if (minors.size() > 0){
		StringBuilder builder = new StringBuilder();
		for (Minor m : minors){
			builder.append(m.getDescription()+",");
		}
		builder.deleteCharAt(builder.length() -1);
		return builder.toString();
		}
		return "";
	}
	/**
	 * @return a string with all Colleges separated by ','
	 */
	public String getCollegeString(){
		if (colleges.size() > 0){
		StringBuilder builder = new StringBuilder();
		for (College c : colleges){
			builder.append(c.getDescription()+",");
		}
		builder.deleteCharAt(builder.length() -1);
		return builder.toString();
		}
		return "";
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
	 * @param colleges the colleges to set
	 */
	void setColleges(List<College> colleges) {
		this.colleges = colleges;
		
	}

	/**
	 * @param majors the majors to set
	 */
	void setMajors(List<Major> majors) {
		this.majors = majors;
	}

	/**
	 * @param minors the minors to set
	 */
	void setMinors(List<Minor> minors) {
		this.minors = minors;
	}

	/**
	 * @param skills the skills to set
	 */
	void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	/**
	 * @param priorExperience the priorExperience to set
	 */
	void setPriorExperience(List<Experience> priorExperience) {
		this.priorExperience = priorExperience;
	}

	/**
	 * @param interests the interests to set
	 */
	void setInterests(List<Interest> interests) {
		this.interests = interests;
	}

	/**
	 * @param transcript the transcript to set
	 */
	void setTranscript(List<Course> transcript) {
		this.transcript = transcript;
	}

	/**
	 * @param applications the applications to set
	 */
	void setApplications(List<Application> applications) {
		this.applications = applications;
	}

	/**
	 * @param settings the settings to set
	 */
	void setSettings(StudentSettings settings) {
		this.settings = settings;
	}

	/**
	 * @param version the version to set
	 */
	void setVersion(long version) {
		this.version = version;
	}
	
	
	
}