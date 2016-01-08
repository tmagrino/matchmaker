package model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import util.Sanitization;

/**
 * The persistent class for the Project database table.
 * 
 */
@Entity

@NamedQuery(name="Project.findAll", query="SELECT p FROM Project p")
public class Project implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final int MAX_DESCRIPTION_CHARS = 2000;
	private static final int MAX_NAME_CHARS = 200;

	// Persistent Fields
	@Id @Column(name = "ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "NAME", nullable = false, length = MAX_NAME_CHARS)
	private String name;
	@Column(name = "URL")
	private String url;
	@Column(name = "DESCRIPTION", nullable = false, length = MAX_DESCRIPTION_CHARS)
	private String description;
	@ManyToMany
	@JoinTable(
			name = "PROJECT_LEADERS",
			joinColumns = {@JoinColumn(name="PROJ_ID", referencedColumnName="ID")},
			inverseJoinColumns = {@JoinColumn(name="RES_ID", referencedColumnName="ID")}
	)
	private List<Researcher> researchers;
	@ManyToMany (cascade = CascadeType.ALL)
	@JoinTable(
			name = "PROJECT_AREA",
			joinColumns = {@JoinColumn(name="PROJ_ID", referencedColumnName="ID")},
			inverseJoinColumns = {@JoinColumn(name="AREA_ID", referencedColumnName="ID")}
	)
	private List<Interest> project_area;
	
	@ManyToMany (cascade = CascadeType.ALL)
	@JoinTable(
			name = "REQUIRED_SKILLS",
			joinColumns = {@JoinColumn(name="PROJ_ID", referencedColumnName="ID")},
			inverseJoinColumns = {@JoinColumn(name="SKILL_ID", referencedColumnName="ID")}
	)
	private List<Skill> requiredSkills;
	
	@OneToMany(mappedBy = "applicationProject" ,cascade = CascadeType.ALL)
	private List<Application> applications;
	@ManyToMany (mappedBy = "hiddenProjects")
	private List<StudentSettings> hiddenBy;

	@Version
	long version = 0;
	
	public Project() {
		
	}
	public Project(String name, String description, String url, 
			List<Researcher> res, List<Interest> area
			, List<Skill> skills) {
		if (name.length() >= MAX_NAME_CHARS) {
			this.name = name.substring(0, MAX_NAME_CHARS);
		}
		else {
			this.name = name;
		}
		if (description.length() >= MAX_DESCRIPTION_CHARS) {
			description = description.substring(0, MAX_DESCRIPTION_CHARS);
		}
		else {
			description = description;
		}
                this.description = Sanitization.sanitizeLongText(description);
		if (res == null) {
			this.researchers = new ArrayList<Researcher>();
		}
		else {
			this.researchers = res;
		}
		this.url = url;
		this.applications = new ArrayList<Application>();
		if (area == null) {
			this.project_area = new ArrayList<Interest>();
		}
		else {
			this.project_area = area;
		}
		if (skills == null) {
			this.requiredSkills = new ArrayList<Skill>();
		}
		else {
			this.requiredSkills = skills;
		}
	}
	public Project(String name, String description, String url, Researcher res,List<Interest> area,
			List<Skill> skills) {
		this(name, description,  url, new ArrayList<Researcher>(Arrays.asList(res)), area,skills);
	}
	public void updateProject(String name, String description, String url, List<Researcher> res, List<Interest> area
			, List<Skill> skills){
		if (name != null && name != ""){
			if (name.length() >= MAX_NAME_CHARS) {
				this.name = name.substring(0, MAX_NAME_CHARS);
			}
			else {
				this.name = name;
			}
		}
		if (description != null && description != "") {
			if (description.length() >= MAX_DESCRIPTION_CHARS) {
				description = description.substring(0, MAX_DESCRIPTION_CHARS);
			}
			else {
				description = description;
			}
                        this.description = Sanitization.sanitizeLongText(description);
		}
		if (res != null && !res.isEmpty()){
			this.researchers = res;
		}
		if (url != null && url != ""){
			this.url = url;
		}
		if (area != null && !area.isEmpty()){
			this.project_area = area;
		}
		if (skills != null && !skills.isEmpty()){
			this.requiredSkills = skills;
		}
	}
	
	public void updateProject(String name, String description, String url, Researcher res,List<Interest> area,
            List<Skill> skills) {
		updateProject(name, description, url, new ArrayList<Researcher>(Arrays.asList(res)), area,skills);
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name.length() >= MAX_NAME_CHARS) {
			this.name = name.substring(0, MAX_NAME_CHARS);
		}
		else {
			this.name = name;
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		if (description.length() >= MAX_DESCRIPTION_CHARS) {
			description = description.substring(0, MAX_DESCRIPTION_CHARS);
		}
		else {
			description = description;
		}
                this.description = Sanitization.sanitizeLongText(description);
	}

	public List<Researcher> getResearchers() {
		return researchers;
	}
	
	void addResearcher(Researcher res) {
		if (!researchers.contains(res)) {
			researchers.add(res);
			if (!res.getProjects().contains(this)) {
				res.addProject(this);
			}
		}
	}
	
	void removeResearcher(Researcher res) {
		if (researchers.remove(res)) {
			if (res.getProjects().contains(this)) {
				res.removeProject(this);
			}
		}
	}
	
	void removeResearchers() {
		for (Researcher r : researchers) {
			r.getProjects().remove(this);
		}
		researchers = new ArrayList<Researcher>();
	}
	
	public List<Interest> getProjectAreas() {
		return project_area;
	}
	
	void addProjectArea(Interest area) {
		if (!project_area.contains(area)) {
			project_area.add(area);
			if (!area.getProjects().contains(this)) {
				area.addProject(this);
			}
		}
	}
	
	void removeProjectArea(Interest area) {
		if (project_area.remove(area)) {
			if (area.getProjects().contains(this)) {
				area.removeProject(this);
			}
		}
	}
	
	void removeProjectAreas() {
		for (Interest a : project_area) {
			a.getProjects().remove(this);
		}
		project_area = new ArrayList<Interest>();
	}
	
	public List<Skill> getRequiredSkills() {
		return requiredSkills;
	}
	
	void addRequiredSkill(Skill req) {
		if (!requiredSkills.contains(req)) {
			requiredSkills.add(req);
			if (!req.getProjects().contains(this)) {
				req.addProject(this);
			}
		}
	}
	
	void removeRequiredSkill(Skill req) {
		if (requiredSkills.remove(req)) {
			if (req.getProjects().contains(this)) {
				req.removeProject(this);
			}
		}
	}
	
	void removeRequiredSkills() {
		for (Skill s : requiredSkills) {
			s.getProjects().remove(this);
		}
		requiredSkills = new ArrayList<Skill>();
	}

	public List<Application> getApplications() {
		return applications;
	}
	
	void addApplication(Application app) {
		if (!this.applications.contains(app)) {
			this.applications.add(app);
			if (app.getApplicationProject() != this) {
				app.setApplicationProject(this);
			}
		}
	}
	
	void removeApplication(Application app) {
		if (this.applications.remove(app)) {
			if (app.getApplicationProject() == this) {
				app.getStudentApplicant().removeApplication(app);
				app.getApplicationProject().removeApplication(app);
				app.setApplicationProject(null);
			}
		}
	}
	
	List<Application> removeApplications() {
		List<Application> toDelete = new ArrayList<Application>();
		for (Application app : applications){
			toDelete.add(app);
		}
		for (Application app : toDelete) {
			app.setApplicationProject(null);
			app.getStudentApplicant().removeApplication(app);
		}
		applications = new ArrayList<Application>();
		return toDelete;
	}
	
	public List<StudentSettings> getHiddenBy() {
		return hiddenBy;
	}
	
	void addHidden(StudentSettings s) {
		if (!hiddenBy.contains(s)) {
			hiddenBy.add(s);
			if (!s.getHiddenProjects().contains(this)) {
				s.addProject(this);
			}
		}
	}
	
	void removeHidden(StudentSettings s) {
		if (hiddenBy.remove(s)) {
			if (s.getHiddenProjects().contains(this)) {
				s.removeProject(this);
			}
		}
	}
	
	void removeHiddenBys() {
		for (StudentSettings s : hiddenBy) {
			s.getHiddenProjects().remove(this);
		}
		hiddenBy = new ArrayList<StudentSettings>();
	}

	public String getURL(){
		return url;
	}

	void setURL(String url){
		this.url = url;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getResearchersString(){
		StringBuilder builder = new StringBuilder();
		for (Researcher r : researchers){
			builder.append(r.getName()+", ");
			
		}
		if (builder.length() > 2)
			builder.deleteCharAt(builder.length() - 2);
		return builder.toString();
	}
	public String getAreaString(){
		StringBuilder builder = new StringBuilder();
		for (Interest i : project_area){
			builder.append(i.getDescription()+", ");
			
		}
		if (builder.length() > 2)
			builder.deleteCharAt(builder.length() - 2);
		return builder.toString();
	}
	public String getSkillString(){
		StringBuilder builder = new StringBuilder();
		for (Skill s : requiredSkills){
			builder.append(s.getDescription()+", ");
			
		}
		if (builder.length() > 2)
			builder.deleteCharAt(builder.length() - 2);
		return builder.toString();
	}
}
