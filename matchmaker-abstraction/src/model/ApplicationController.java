package model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;




public class ApplicationController {

	public static Application createApplication(EntityManager em, Student s, Project p, 
			String studentResponse) {
		// // EntityTransaction tx = em.getTransaction();
		if (s == null || p == null) {
			return null;
		}
		for (Application a : s.getApplications()) {
			if (a.getApplicationProject() == p) {
				return null;
			}
		}
		
		// // tx.begin();
		// em.lock(p, LockModeType.OPTIMISTIC);
		// em.lock(s, LockModeType.OPTIMISTIC);
		Application a = new Application(s, p, studentResponse);
		s.addApplication(a);
		p.addApplication(a);
		em.persist(a);

		// tx.commit();
		return a;
	}

	public static void deleteApplication(EntityManager em, Application a) {
		// // EntityTransaction tx = em.getTransaction();
		// tx.begin();
		
		if (a != null) {
			// em.lock(a, LockModeType.OPTIMISTIC);
			if (a.getApplicationProject() != null) {
				a.getApplicationProject().removeApplication(a);
			}
			if (a.getStudentApplicant() != null) {
				a.setStudentApplicant(null);
			}
			em.remove(a);
		}
		
		// tx.commit();
	}
	
	public static void approveApplication(EntityManager em, Application a) {
		// EntityTransaction tx = em.getTransaction();
		// tx.begin();
		if (a == null) {
			return;
		}
		
		// em.lock(a, LockModeType.OPTIMISTIC);
		a.setStatus(ApplicationStatus.Accepted);
		
		// tx.commit();
	}
	public static void inviteApplication(EntityManager em, Application a) {
		// EntityTransaction tx = em.getTransaction();
		if (a == null) {
			return;
		}
		// tx.begin();
		
		// em.lock(a, LockModeType.OPTIMISTIC);
		a.setStatus(ApplicationStatus.Invited);
		
		// tx.commit();
	}
	public static void acceptInvitation(EntityManager em, Application a, String response){
		// EntityTransaction tx = em.getTransaction();
		if (a == null) {
			return;
		}
		// tx.begin();
		
		// em.lock(a, LockModeType.OPTIMISTIC);
		a.setStatus(ApplicationStatus.Pending);
		a.setStudentResponse(response);
		
		// tx.commit();
	}
	
	public static void declineApplication(EntityManager em, Application a) {
		// EntityTransaction tx = em.getTransaction();
		if (a == null) {
			return;
		}
		// tx.begin();
		
		// em.lock(a, LockModeType.OPTIMISTIC);
		a.setStatus(ApplicationStatus.Declined);
		
		// tx.commit();
	}
	
	public static void editStudentResponse(EntityManager em, Application a, String response) {
		// EntityTransaction tx = em.getTransaction();
		if (a == null) {
			return;
		}
		// tx.begin();
		
		// em.lock(a, LockModeType.OPTIMISTIC);
		a.setStudentResponse(response);
		
		// tx.commit();
	}
	
	public static List<Application> getApplicationList (EntityManager em){
        // // EntityTransaction tx = em.getTransaction();
        try {
        // // tx.begin();
        String query = "select a from APPLICATION a";
        List<Application> mylist = (List<Application>) em.createQuery(query).setLockMode(Locking.getReadLockType()).getResultList();
        //List<Application> mylist = (List<Application>) em.createQuery(query).setLockMode(LockModeType.PESSIMISTIC_READ).getResultList();
        // // tx.commit();
        	return mylist;
        }
        catch (Exception e) {
        	System.out.println(e);
        	return new ArrayList<Application>();
        }
	}
	
	public static Application getApplicationById(EntityManager em,String id) {
        // EntityTransaction tx = em.getTransaction();
        try {
        // tx.begin();
        String query = "select a FROM APPLICATION a WHERE a.id = "+ id;
        List<Application> mylist = (List<Application>) em.createQuery(query).setLockMode(Locking.getReadLockType()).getResultList();
        //List<Application> mylist = (List<Application>) em.createQuery(query).setLockMode(LockModeType.PESSIMISTIC_READ).getResultList();
        // tx.commit();
        	return mylist.get(0);
        }
        catch (Exception e) {
        	System.out.print(e);
        	return null;
        }
	}
	
	public static Application getApplication(EntityManager em, Student s, Project p){
		List<Application> allApps = s.getApplications();
		if (s == null || p == null) {
			return null;
		}
		for (Application a : allApps){
			if(a.getStudentApplicant() == null || a.getApplicationProject() == null)
				return null;
			if (a.getApplicationProject().getId() == p.getId()){
				return a;
			}
		}
		
		return null;
	}	
}
