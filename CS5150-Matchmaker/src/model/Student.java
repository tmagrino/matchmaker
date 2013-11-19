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
	@OneToMany(mappedBy = "studentApplicant")
	private List<Application> applications;
	@Embedded
	private StudentSettings settings;

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
		this.colleges = colleges;
		this.majors = majors;
		this.minors = minors;
		this.skills = skills;
		this.priorExperience = priorExperience;
		this.interests = interests;
		this.transcript = transcript;
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
				return year.toString();
			case "college":
				return getString(getColleges());
			case "major":
				return getString(getMajors());
			case "minor":
				return getString(getMinors());
			case "skills":
				return getString(getSkills());
			case "research interests":
				return getString(getInterests());
			default:
				System.out.println("Invalid attribute");
				return null;
		}
	}
	
	public List<? extends MultipleItem> getListAttribute(String attr) {
		switch (attr.toLowerCase()) {
			case "college":
				return getColleges();
			case "major":
				return getMajors();
			case "minor":
				return getMinors();
			case "skill":
				return getSkills();
			case "interest":
				return getInterests();
			default:
				System.out.println("Invalid attribute");
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
	public String getString(List<? extends MultipleItem> collection) {
		if (collection.size() > 0) {
			Collections.sort(collection);
			StringBuilder builder = new StringBuilder();
			for (MultipleItem i : collection){
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
	public String getTruncatedString(List<? extends MultipleItem> collection){
		if (collection.size() > 0) {
			int subSequenceSize = 16;
			Collections.sort(collection);
			StringBuilder builder = new StringBuilder();
			for (MultipleItem i : collection){
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
	 * Return a JsonObject containing elements of a collection
	 */
	public JSONObject getObjectJson(List<? extends MultipleItem> collection) {
		if(collection.size() > 0){
			Collections.sort(collection);
		}
		JSONArray jsonArray = new JSONArray();
		for (MultipleItem t : collection){
			JSONObject jsonObject= new JSONObject();
			try {
				jsonObject.put("value", String.valueOf(t.getId()));
				jsonObject.put("name", t.getDescription());
				jsonArray.put(jsonObject);
			} catch (JSONException e) {
				
				e.printStackTrace();
			}
		}
		JSONObject items_obj = new JSONObject();
		try {
			items_obj.put("items", jsonArray);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return items_obj;
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
		colleges = new ArrayList<College>();
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
		if(type.toLowerCase() == ItemFactory.MAJOR){
			this.removeMajors();
		}
		if(type.toLowerCase() == ItemFactory.MINOR){
			this.removeMinors();
		}
		if (type.toLowerCase() == ItemFactory.COLLEGE){
			this.removeColleges();
		}
		if (type.toLowerCase() == ItemFactory.SKILL){
			this.removeSkills();
		}
		if (type.toLowerCase() == ItemFactory.INTEREST){
			this.removeInterests();
		}
		
		
	}
	void removeMajors() {
		majors = new ArrayList<Major>();
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
		minors = new ArrayList<Minor>();
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
		skills = new ArrayList<Skill>();
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
		interests = new ArrayList<Interest>();
	}
	
	void addExperience(Experience exp) {
		priorExperience.add(exp);
	}
	
	void removeExperience(Experience exp) {
		priorExperience.remove(exp);
	}
	
	void removeExperiences() {
		for (Experience e: priorExperience) {
			removeExperience(e);
		}
	}
	
	void addCourse(Course c) {
		transcript.add(c);
	}
	
	void removeCourse(Course c) {
		transcript.remove(c);
	}
	
	void removeCourses() {
		for (Course c : transcript) {
			removeCourse(c);
		}
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
	
	void removeApplications() {
		for (Application app : applications) {
			removeApplication(app);
		}
	}

	/**
	 * @param settings the settings to set
	 */
	void setSettings(StudentSettings settings) {
		this.settings = settings;
	}	
}