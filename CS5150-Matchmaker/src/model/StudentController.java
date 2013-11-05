package model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class StudentController {
	
	
	public static Student getStudentByNetID(String netid) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        String query = "select s from STUDENT s where s.netID = \"" + netid +"\"";
        List<Student> mylist = (List<Student>) em.createQuery(query).getResultList();
        try {
        	return mylist.get(0);
        }
        catch (Exception e) {
        	return null;
        }
	}
	
	public static Student getStudentByName(String name) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        String query = "select s from student s where s.netid = \"" + name +"\"";
        List<Student> mylist = (List<Student>) em.createQuery(query).getResultList();
        try {
        	return mylist.get(0);
        }
        catch (Exception e) {
        	return null;
        }
	}
	
	public static void updateStudent(Student stud, String name, String netID, double gpa, String email,
			Year year, List<College> colleges, List<Major> majors,
			List<Minor> minors, List<Skill> skills,
			List<Experience> priorExperience, List<Interest> interests,
			List<Course> transcript) {
		stud.setName(name);
		stud.setNetID(netID);
		stud.setGpa(gpa);
		stud.setEmail(email);
		stud.setYear(year);
		stud.setColleges(colleges);
		stud.setMajors(majors);
		stud.setMinors(minors);
		stud.setSkills(skills);
		//stud.setPriorExperience(priorExperience);
		stud.setInterests(interests);
		//stud.setTranscript(transcript);
		stud.setVersion(stud.getVersion() + 1);
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        tx.begin();
        String deleteQuery = "delete from STUDENT where id = " + stud.getId();
        em.createQuery(deleteQuery);
        em.persist(stud);
        tx.commit();
	}
}
