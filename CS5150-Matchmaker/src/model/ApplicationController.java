package model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ApplicationController {

	public static Application createApplication(Student s, Project p, 
			String studentResponse) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ApplicationController");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		Application a = new Application(s, p, studentResponse);
		s.addApplication(a);
		em.persist(a);
		
		tx.commit();
		em.close();
		emf.close();
		return a;
	}

	public static void deleteApplication(Application a) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ApplicationController");
		EntityManager em = emf.createEntityManager();
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
		em.close();
		emf.close();
	}
	
	public static void approveApplication(Application a) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ApplicationController");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		a.setStatus(ApplicationStatus.Accepted);
		
		tx.commit();
		em.close();
		emf.close();
	}
	
	public static void DeclineApplication(Application a) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ApplicationController");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		a.setStatus(ApplicationStatus.Declined);
		
		tx.commit();
		em.close();
		emf.close();
	}
	
	public static void editStudentResponse(Application a, String response) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ApplicationController");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		a.setStudentResponse(response);
		
		tx.commit();
		em.close();
		emf.close();
	}
	
}
