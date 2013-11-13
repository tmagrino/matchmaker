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
		if (MajorController.getMajorList(em).size() == 0) {
			try {
				File myfile = new File("MajorsList");
				in = new Scanner(new FileReader(myfile));
				while (in.hasNextLine()) {
					String str = in.nextLine();
					System.out.println(str);
					MajorController.createMajor(em, str);
				}
			}
			catch (Exception e) {
				System.out.println("Error: "+e);
			}
		}
		// Minors
		if (MinorController.getMinorList(em).size() == 0) {
			try {
				File myfile = new File("MinorsList");
				in = new Scanner(new FileReader(myfile));
				while (in.hasNextLine()) {
					String str = in.nextLine();
					System.out.println(str);
					MinorController.createMinor(em, str);
				}
			}
			catch (Exception e) {
				System.out.println("Error: "+e);
			}
		}
		// Colleges
		if (CollegeController.getCollegeList(em).size()==0){
			try {
				File myfile = new File("CollegesList");
				in = new Scanner(new FileReader(myfile));
				while (in.hasNextLine()) {
					String str = in.nextLine();
					System.out.println(str);
					CollegeController.createCollege(em, str);
				}
			}
			catch (Exception e) {
				System.out.println("Error: "+e);
			}
		}
		// Interests
		if (InterestController.getInterestList(em).size() == 0){
			try {
				File myfile = new File("InterestsList");
				in = new Scanner(new FileReader(myfile));
				while (in.hasNextLine()) {
					String str = in.nextLine();
					System.out.println(str);
					InterestController.createInterest(em, str);
				}
			}
			catch (Exception e) {
				System.out.println("Error: "+e);
			}
		}
		// Skills
		if (SkillController.getSkillList(em).size() == 0) {
			try {
				File myfile = new File("SkillsList");
				in = new Scanner(new FileReader(myfile));
				while (in.hasNextLine()) {
					String str = in.nextLine();
					System.out.println(str);
					SkillController.createSkill(em,str);
				}
			}
			catch (Exception e) {
				System.out.println("Error: "+e);
			}
		}
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
						colleges.add(CollegeController.getCollege(em,s));
					}
					// Majors
					String[] majs = studentStrings[6].split(";");
					LinkedList<Major> majors = new LinkedList<Major>();
					for (String s : majs) {
						majors.add(MajorController.getMajorByDescription(em,s));
					}
					// Minors
					String[] mins = studentStrings[7].split(";");
					LinkedList<Minor> minors = new LinkedList<Minor>();
					for (String s : mins) {
						minors.add(MinorController.getMinorByDescription(em,s));
					}
					// Skills
					String[] skls = studentStrings[8].split(";");
					LinkedList<Skill> skills = new LinkedList<Skill>();
					for (String s : skls) {
						skills.add(SkillController.getSkillByDescription(em,s));
					}
					// Interests
					String[] ints = studentStrings[9].split(";");
					LinkedList<Interest> interests = new LinkedList<Interest>();
					for (String s : ints) {
						interests.add(InterestController.getInterestByDescription(em,s));
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
					String department = researcherStrings[3];
					String webpage = researcherStrings[4];
					String researchArea = researcherStrings[5];
					
					User user = UserController.createUser(em, name, email, netID);
					Researcher r = ResearcherController.createResearcher(em,name, netID, 
							email, department, webpage, researchArea, user);
				}
			}
			catch (Exception e) {
				System.out.println("Error: "+e);
			}
		}
		em.close();
		emf.close();
	}
}
