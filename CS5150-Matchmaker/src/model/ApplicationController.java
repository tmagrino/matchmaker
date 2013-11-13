package model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ApplicationController {

	public static Application createApplication(EntityManager em, Student s, Project p, 
			String studentResponse) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		Application a = new Application(s, p, studentResponse);
		s.addApplication(a);
		em.persist(a);
		
		tx.commit();
		return a;
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
}
