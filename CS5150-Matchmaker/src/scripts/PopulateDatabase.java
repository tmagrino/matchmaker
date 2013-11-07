package scripts;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        EntityTransaction tx = em.getTransaction();
		
        // Majors
		try {
			File myfile = new File("MajorsList");
			in = new Scanner(new FileReader(myfile));
			tx.begin();
		    while (in.hasNextLine()) {
		    	String str = in.nextLine();
		    	System.out.println(str);
		    	Major maj = new Major(str);
		    	em.persist(maj);
		    }
			tx.commit();
		}
		catch (Exception e) {
			System.out.println("Error: "+e);
		}
		
		// Minors
		try {
			File myfile = new File("MinorsList");
			in = new Scanner(new FileReader(myfile));
			tx.begin();
		    while (in.hasNextLine()) {
		    	String str = in.nextLine();
		    	System.out.println(str);
		    	Minor min = new Minor(str);
		    	em.persist(min);
		    }
			tx.commit();
		}
		catch (Exception e) {
			System.out.println("Error: "+e);
		}
		
		// Colleges
		try {
			File myfile = new File("CollegesList");
			in = new Scanner(new FileReader(myfile));
			tx.begin();
		    while (in.hasNextLine()) {
		    	String str = in.nextLine();
		    	System.out.println(str);
		    	College col = new College(str);
		    	em.persist(col);
		    }
			tx.commit();
		}
		catch (Exception e) {
			System.out.println("Error: "+e);
		}
		
		// Interests
		try {
			File myfile = new File("InterestsList");
			in = new Scanner(new FileReader(myfile));
			tx.begin();
		    while (in.hasNextLine()) {
		    	String str = in.nextLine();
		    	System.out.println(str);
		    	Interest inte = new Interest(str);
		    	em.persist(inte);
		    }
			tx.commit();
		}
		catch (Exception e) {
			System.out.println("Error: "+e);
		}
		
		// Skills
		try {
			File myfile = new File("SkillsList");
			in = new Scanner(new FileReader(myfile));
			tx.begin();
		    while (in.hasNextLine()) {
		    	String str = in.nextLine();
		    	System.out.println(str);
		    	Skill skl = new Skill(str);
		    	em.persist(skl);
		    }
			tx.commit();
		}
		catch (Exception e) {
			System.out.println("Error: "+e);
		}
	
		try {
			File myfile = new File("StudentList");
			in = new Scanner(new FileReader(myfile));
			tx.begin();
		    while (in.hasNextLine()) {
		    	String str = in.nextLine();
		    	System.out.println(str);
		    	String [] studentStrings = str.split(",");
		    	Student s = new Student(studentStrings[0],studentStrings[1],
		    			Double.parseDouble(studentStrings[2]),studentStrings[3]);
		    	em.persist(s);
		    }
			tx.commit();
		}
		catch (Exception e) {
			System.out.println("Error: "+e);
		}
		try {
			File myfile = new File("ResearcherList");
			in = new Scanner(new FileReader(myfile));
			tx.begin();
		    while (in.hasNextLine()) {
		    	String str = in.nextLine();
		    	System.out.println(str);
		    	String [] researcherStrings = str.split(",");
		    	Researcher r = new Researcher(researcherStrings[0],researcherStrings[1],
		    			researcherStrings[2]);
		    	em.persist(r);
		    }
			tx.commit();
		}
		catch (Exception e) {
			System.out.println("Error: "+e);
		}
	}
}
