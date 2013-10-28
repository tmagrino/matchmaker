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
 * TODO:
 * Fix getters/setters to for the fields with tables
 * - http://en.wikibooks.org/wiki/Java_Persistence/OneToMany#Getters_and_Setters
 * Take another look at the constructor(s)
 */
@Entity(name = "STUDENT")
@NamedQuery(name="Student.findAll", query="SELECT s FROM Student s")
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
	@Column(name = "WEBPAGE", nullable = true)
	private String webpage;
	@Enumerated(EnumType.STRING)
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
	@OneToMany  
	@CollectionTable (
			name = "EXPERIENCES_TABLE",
			joinColumns = @JoinColumn(
					name = "OWNER_ID")
			)
	private List<Experience> priorExperience;
	// Interests
	@ManyToMany
	@JoinTable(
			name = "STUDENTS<->INTERESTS_TABLE",
			joinColumns = {@JoinColumn(name="STUD_ID", referencedColumnName="ID")},
			inverseJoinColumns = {@JoinColumn(name="INTER_ID", referencedColumnName="ID")}
	)
	private List<Interest> interests;
	// Transcript
	@Embedded
	private Transcript transcript;
	@OneToMany(mappedBy = "studentBean")
	private List<Application> appliedProjects;
	@Embedded
	private StudentSettings settings;
	//private BufferedImage profilePicture;
	@Version @Column(name = "VERSION")
	private long version;

	// Constructors:
	public Student() 
	{
		
	}
	
	public Student(long id, String name, double gpa) {
		this.id = id;
		this.name = name;
		this.gpa = gpa;	
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNetID() {
		return netID;
	}

	public void setNetID(String netID) {
		this.netID = netID;
	}

	public Year getYear() {
		return year;
	}

	public void setYear(Year year) {
		this.year = year;
	}

	public List<College> getColleges() {
		return this.colleges;
	}

	public void setColleges(List<College> colleges) {
		this.colleges = colleges;
	}

	public double getGpa() {
		return gpa;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	public List<Major> getMajors() {
		return majors;
	}

	public void setMajors(List<Major> majors) {
		this.majors = majors;
	}

	public List<Minor> getMinors() {
		return minors;
	}

	public void setMinors(List<Minor> minors) {
		this.minors = minors;
	}

	public Transcript getTranscript() {
		return transcript;
	}

	public void setTranscript(Transcript transcript) {
		this.transcript = transcript;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public List<Experience> getPriorExperience() {
		return priorExperience;
	}

	public void setPriorExperience(List<Experience> priorExperience) {
		this.priorExperience = priorExperience;
	}

	public List<Application> getAppliedProjects() {
		return appliedProjects;
	}

	public void setAppliedProjects(List<Application> appliedProjects) {
		this.appliedProjects = appliedProjects;
	}

	public String getWebpage() {
		return webpage;
	}

	public void setWebpage(String webpage) {
		this.webpage = webpage;
	}

	public List<Interest> getInterests() {
		return interests;
	}

	public void setInterests(List<Interest> interests) {
		this.interests = interests;
	}

	public StudentSettings getSettings() {
		return settings;
	}

	public void setSettings(StudentSettings settings) {
		this.settings = settings;
	}	
}