package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.Student;

public class StudentController {
	
	public Student getStudentByNetID(String netid) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        String query = "select * from student s where s.netid = \"" + netid +"\"";
        List<Student> mylist = (List<Student>) em.createQuery(query).getResultList();
        try {
        	return mylist.get(0);
        }
        catch (Exception e) {
        	return null;
        }
	}
	
	public Student getStudentByName(String name) {
		//TODO:
		
		return null;
	}
	
	public void addMajor(Student stud, String maj) {
	
	}
	
	public void removeMajor(Student stud, String maj) {
		
	}
	
	public void addMinor(Student stud, String min) {
		
	}
	
	public void removeMinor(Student stud, String min) {
		
	}
	
	public void addCollege(Student stud, String col) {
		
	}
	
	public void RemoveCollege(Student stud, String col) {
		
	}
	
	public void addInterest(Student stud, String inte) {
		
	}
	
	public void RemoveInterest(Student stud, String inte) {
		
	}
	
	public void addSkill(Student stud, String skil) {
		
	}
	
	public void RemoveSkill(Student stud, String skil) {
		
	}
	
	
	
	
}
