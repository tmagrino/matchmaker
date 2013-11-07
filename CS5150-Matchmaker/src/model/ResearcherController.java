package model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ResearcherController {

	public static Researcher getResearcherByNetID(String netid) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        String query = "select s from Researcher s where s.netID = \"" + netid +"\"";
        List<Researcher> mylist = (List<Researcher>) em.createQuery(query).getResultList();
        try {
        	return mylist.get(0);
        }
        catch (Exception e) {
        	return null;
        }
	}

	public static Researcher getResearcherByName(String name) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        String query = "select s from RESEARCHER s where s.name = \"" + name +"\"";
        List<Researcher> mylist = (List<Researcher>) em.createQuery(query).getResultList();
        try {
        	return mylist.get(0);
        }
        catch (Exception e) {
        	return null;
        }
	}
	
	public static void updateResearcher(Researcher researcher, String name, String netID, String email,
			String department, String researchArea, String webpage) {
		researcher.setName(name);
		researcher.setNetID(netID);
		researcher.setEmail(email);
		researcher.setDepartment(department);
		researcher.setResearchArea(researchArea);
		researcher.setWebpage(webpage);
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        tx.begin();
        String deleteQuery = "delete from RESEARCHER where id = " + researcher.getId();
        em.createQuery(deleteQuery);
        em.persist(researcher);
        tx.commit();
	}


}
