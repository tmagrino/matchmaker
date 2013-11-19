package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ResearcherController {

	public static Researcher createResearcher(EntityManager em, String name,String netID,String email, String department,
			String webpage, String researchArea, User user) {
        EntityTransaction tx = em.getTransaction();
		tx.begin();
        
        Researcher r = new Researcher(name, netID, email, department,
        				webpage, researchArea);
        user.setResearcher(r);
        em.persist(r);
        
		tx.commit();
		return r;
	}
	
	public static void deleteResearcher(Researcher r) {
		//TODO:
	}
	
	public static Researcher getResearcherByNetID(EntityManager em, String netid) {
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
	public static List<Researcher> getResearcherList(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        try {
        String query = "select r from Researcher r";
        List<Researcher> mylist = (List<Researcher>) em.createQuery(query).getResultList();
        
        	return mylist;
        }
        catch (Exception e) {
        	return new ArrayList<Researcher>();
        }
	}

	public static Researcher getResearcherByName(EntityManager em, String name) {
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
	
	public static void updateResearcher(EntityManager em, Researcher researcher, String name, String netID, String email,
			String department, String researchArea, String webpage) {
		researcher.setName(name);
		researcher.setNetID(netID);
		researcher.setEmail(email);
		researcher.setDepartment(department);
		researcher.setResearchArea(researchArea);
		researcher.setWebpage(webpage);
		
        EntityTransaction tx = em.getTransaction();
        
        tx.begin();
        String deleteQuery = "delete from RESEARCHER where id = " + researcher.getId();
        em.createQuery(deleteQuery);
        em.persist(researcher);
        tx.commit();
	}


}
