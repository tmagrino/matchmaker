package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ResearcherController {

	public static Researcher createResearcher(EntityManager em, String name,
			String netID,String email, List<Department> departments,
			String webpage, String researchArea, User user) {
        EntityTransaction tx = em.getTransaction();
		tx.begin();
        
        Researcher r = new Researcher(name, netID, email, departments,
        				webpage, researchArea);
        ResearcherSettings settings = new ResearcherSettings();
        r.setSettings(settings);
		settings.setResearcher(r);
        user.setResearcher(r);
        em.persist(r);
        em.persist(settings);
        
		tx.commit();
		return r;
	}
	
	public static void deleteResearcher(Researcher r) {
		//TODO:
	}
	
	public static void addProject(EntityManager em, Project p) {
		
	}
	
	public static void addHiddenStudent(EntityManager em, Researcher r, Student s) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		r.getSettings().addStudent(s);
		
		tx.commit();
	}
	
	public static void removeHiddenStudent(EntityManager em, Researcher r, Student s) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		r.getSettings().removeStudent(s);
		
		tx.commit();
	}
	
	public static Researcher getResearcherByNetID(EntityManager em, String netid) {
        EntityTransaction tx = em.getTransaction();
        
        String query = "select r from Researcher r where r.netID = \"" + netid +"\"";
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
        
        String query = "select r from Researcher r where r.name = \"" + name +"\"";
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
		researcher.addDepartment((Department) ListController.getItemByDescription( em,  department, ItemFactory.DEPARTMENT));
		researcher.setResearchArea(researchArea);
		researcher.setWebpage(webpage);
		
        EntityTransaction tx = em.getTransaction();
        
        tx.begin();
        String deleteQuery = "delete from RESEARCHER where id = " + researcher.getId();
        em.createQuery(deleteQuery);
        em.persist(researcher);
        tx.commit();
	}
	public static void addProject(EntityManager em,Researcher r, Project p) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		r.addProject(p);
		
		tx.commit();
	}
}