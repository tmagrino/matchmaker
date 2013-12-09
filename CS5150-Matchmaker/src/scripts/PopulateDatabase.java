package scripts;

import java.awt.List;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
















import model.*;

public class PopulateDatabase {

	public static void main(String[] args) {
		Scanner in;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
		EntityManager em = emf.createEntityManager();
		// Majors
		if (ListController.getItensList(em, ItemFactory.MAJOR).size() == 0) {
			try {
				File myfile = new File("MajorsList");
				in = new Scanner(new FileReader(myfile));
				while (in.hasNextLine()) {
					String str = in.nextLine();
					System.out.println(str);
					ListController.createItem(em, str, ItemFactory.MAJOR);
				}
			}
			catch (Exception e) {
				System.out.println("Error: "+e);
			}
		}
		// Minors
		if (ListController.getItensList(em, ItemFactory.MINOR).size() == 0)  {
			try {
				File myfile = new File("MinorsList");
				in = new Scanner(new FileReader(myfile));
				while (in.hasNextLine()) {
					String str = in.nextLine();
					System.out.println(str);
					ListController.createItem(em, str, ItemFactory.MINOR);
				}
			}
			catch (Exception e) {
				System.out.println("Error: "+e);
			}
		}
		// Colleges
		if (ListController.getItensList(em, ItemFactory.COLLEGE).size() == 0){
			try {
				File myfile = new File("CollegesList");
				in = new Scanner(new FileReader(myfile));
				while (in.hasNextLine()) {
					String str = in.nextLine();
					System.out.println(str);
					ListController.createItem(em, str, ItemFactory.COLLEGE);
				}
			}
			catch (Exception e) {
				System.out.println("Error: "+e);
			}
		}
		// Interests
		if (ListController.getItensList(em, ItemFactory.INTEREST).size() == 0){
			try {
				File myfile = new File("InterestsList");
				in = new Scanner(new FileReader(myfile));
				while (in.hasNextLine()) {
					String str = in.nextLine();
					System.out.println(str);
					ListController.createItem(em, str, ItemFactory.INTEREST);
				}
			}
			catch (Exception e) {
				System.out.println("Error: "+e);
			}
		}
		// Skills
		if (ListController.getItensList(em, ItemFactory.SKILL).size() == 0){
			try {
				File myfile = new File("SkillsList");
				in = new Scanner(new FileReader(myfile));
				while (in.hasNextLine()) {
					String str = in.nextLine();
					System.out.println(str);
					ListController.createItem(em, str, ItemFactory.SKILL);
				}
			}
			catch (Exception e) {
				System.out.println("Error: "+e);
			}
		}
		// Departments
		if (ListController.getItensList(em, ItemFactory.DEPARTMENT).size() == 0){
			try {
				File myfile = new File("DepartmentsList");
				in = new Scanner(new FileReader(myfile));
				while (in.hasNextLine()) {
					String str = in.nextLine();
					System.out.println(str);
					ListController.createItem(em, str, ItemFactory.DEPARTMENT);
				}
			}
			catch (Exception e) {
				System.out.println("Error: "+e);
			}
		}
		// Students
		if (StudentController.getAllStudents(em).size()==0){
			try {
				File myfile = new File("StudentList");
				in = new Scanner(new FileReader(myfile));
				int i = 1;
				while (in.hasNextLine()) {
					String str = in.nextLine();
					System.out.println(str);
					String [] studentStrings = str.split(",");
					String name = studentStrings[0];
					String netid = studentStrings[1];
					Double gpa = Double.valueOf(studentStrings[2]);
					String email = studentStrings[3];
					Year year = YearController.getYear(studentStrings[4]);
					// Colleges
					String[] cols = studentStrings[5].split(";");
					LinkedList<College> colleges = new LinkedList<College>();
					for (String s : cols) {
						colleges.add((College) ListController.getItemByDescription(em, s, 
								ItemFactory.COLLEGE));
					}
					// Majors
					String[] majs = studentStrings[6].split(";");
					LinkedList<Major> majors = new LinkedList<Major>();
					for (String s : majs) {
						majors.add((Major) ListController.getItemByDescription(em, s, 
								ItemFactory.MAJOR));
					}
					// Minors
					String[] mins = studentStrings[7].split(";");
					LinkedList<Minor> minors = new LinkedList<Minor>();
					for (String s : mins) {
						minors.add((Minor) ListController.getItemByDescription(em, s, 
								ItemFactory.MINOR));
					}
					// Skills
					String[] skls = studentStrings[8].split(";");
					LinkedList<Skill> skills = new LinkedList<Skill>();
					for (String s : skls) {
						skills.add((Skill) ListController.getItemByDescription(em, s, 
								ItemFactory.SKILL));
					}
					// Interests
					String[] ints = studentStrings[9].split(";");
					LinkedList<Interest> interests = new LinkedList<Interest>();
					for (String s : ints) {
						interests.add((Interest) ListController.getItemByDescription(em, s, 
								ItemFactory.INTEREST));
					}
					// Prior Experience TODO:
					LinkedList<Experience> priorExperience= new LinkedList<Experience>();
					// Transcript TODO:
					LinkedList<Course> transcript = new LinkedList<Course>();
					
					User user = UserController.createUser(em, name, email, netid);
					Student s = StudentController.createStudent(em,name, netid, gpa, 
							email, year, colleges, majors, minors, skills, 
							priorExperience, interests, transcript, user);
				}
			}
			catch (Exception e) {
				System.out.println("Error: "+e);
			}
		}
		
		// Researchers
		if (ResearcherController.getResearcherList(em).size()==0){
			try {

				File myfile = new File("ResearcherList");
				in = new Scanner(new FileReader(myfile));
				while (in.hasNextLine()) {
					String str = in.nextLine();
					System.out.println(str);
					String [] researcherStrings = str.split(",");
					String name = researcherStrings[0];
					String netID = researcherStrings[1];
					String email = researcherStrings[2];
					String[] deps = researcherStrings[3].split(";");
					LinkedList<Department> departments = new LinkedList<Department>();
					for (String s : deps) {
						departments.add((Department) ListController.getItemByDescription(em, s, 
								ItemFactory.DEPARTMENT));
					}
					String webpage = researcherStrings[4];
					Interest researchArea = (Interest) ListController.getItemByDescription(em, "Machine Learning", ItemFactory.AREA);
					
					User user = UserController.createUser(em, name, email, netID);
					Researcher r = ResearcherController.createResearcher(em,name, netID, 
							email, departments, webpage, researchArea, user);
				}
			}
			catch (Exception e) {
				System.out.println("Error:  "+e);
			}
		}
		
		// Projects
		//String name, String description,
		//String url, Researcher researcher, List<Interest> area, List<Skill> skills
		if (ProjectController.getProjectList(em).size()==0){
			try {

				File myfile = new File("ProjectsList");
				in = new Scanner(new FileReader(myfile));
				while (in.hasNextLine()) {
					String str = in.nextLine();
					System.out.println(str);
					String [] projectStrings = str.split(",");
					String name = projectStrings[0];
					String desc = projectStrings[1];
					String url = projectStrings[2];
					String[] ress = projectStrings[3].split(";");
					LinkedList<Researcher> researchers = new LinkedList<Researcher>();
					for (String s : ress) {
						researchers.add((Researcher) ResearcherController.getResearcherByNetID(em, s));
					}
					
					String[] inters = projectStrings[4].split(";");
					LinkedList<Interest> interests = new LinkedList<Interest>();
					for (String s : inters) {
						interests.add((Interest) ListController.getItemByDescription(em, s, 
								ItemFactory.INTEREST));
					}
					
					String[] skls = projectStrings[5].split(";");
					LinkedList<Skill> skills = new LinkedList<Skill>();
					for (String s : skls) {
						skills.add((Skill) ListController.getItemByDescription(em, s, 
								ItemFactory.SKILL));
					}
					Project p = ProjectController.createProject(em, name, desc, url, researchers, interests, skills);
				}
			}
			catch (Exception e) {
				System.out.println("Error:  "+e);
			}
		}
		em.close();
		emf.close();
	}
}
