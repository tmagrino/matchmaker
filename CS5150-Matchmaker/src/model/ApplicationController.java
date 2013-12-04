package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ApplicationController {

	public static Application createApplication(EntityManager em, Student s, Project p, 
			String studentResponse) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		System.out.println("Creating...");
		Application a = new Application(s, p, studentResponse);
		
		System.out.println("App: " + a);
		System.out.println("Stud: " + s);
		System.out.println("Stud Name: " + s.getName());
		System.out.println("Stud NETID: " + s.getNetID());
		System.out.println("Proj: " + p);
		System.out.println("Proj Name: " + p.getName());
		System.out.println("Proj Description: " + p.getDescription());
		System.out.println("App's Proj: " + a.getApplicationProject());
		System.out.println("App's Stud: " + a.getStudentApplicant());
		System.out.println("Persisting...");
		em.persist(a);
		System.out.println("Persisted now");
		tx.commit();
		return a;
	}
	
	public static void updateApplication(EntityManager em, Student s, Project p,
			Application a) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		System.out.println("Updating...");
		s.addApplication(a);
		p.addApplication(a);
		System.out.println("Testing");
		System.out.println("App: " + a);
		System.out.println("Stud: " + s);
		System.out.println("Stud Name: " + s.getName());
		System.out.println("Stud NETID: " + s.getNetID());
		System.out.println("Proj: " + p);
		System.out.println("Proj Name: " + p.getName());
		System.out.println("Proj Description: " + p.getDescription());
		System.out.println("App's Proj: " + a.getApplicationProject());
		System.out.println("App's Stud: " + a.getStudentApplicant());
		tx.commit();
	}

	public static void deleteApplication(EntityManager em, Application a) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		if (a != null) {
			a.getStudentApplicant().removeApplication(a);
			a.setStudentApplicant(null);
			a.getApplicationProject().removeApplication(a);
			a.setApplicationProject(null);;
			em.remove(a);
		}
		
		tx.commit();
	}
	
	public static void approveApplication(EntityManager em, Application a) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		a.setStatus(ApplicationStatus.Accepted);
		
		tx.commit();
	}
	
	public static void DeclineApplication(EntityManager em, Application a) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		a.setStatus(ApplicationStatus.Declined);
		
		tx.commit();
	}
	
	public static void editStudentResponse(EntityManager em, Application a, String response) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		a.setStudentResponse(response);
		
		tx.commit();
	}
	
	public static List<Application> getApplicationList (EntityManager em){
        EntityTransaction tx = em.getTransaction();
        try {
        String query = "select r from Application r";
        List<Application> mylist = (List<Application>) em.createQuery(query).getResultList();
        
        	return mylist;
        }
        catch (Exception e) {
        	return new ArrayList<Application>();
        }
	}
}
