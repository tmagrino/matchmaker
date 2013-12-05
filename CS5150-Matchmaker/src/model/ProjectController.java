package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class ProjectController {

	public static Project createProject(EntityManager em , String name, String description,
			String url, Researcher researcher, List<Interest> area, List<Skill> skills){
		if (description == null) description = "";
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		ArrayList<Researcher> rlist = new ArrayList<Researcher>();
		rlist.add(researcher);
		Project p = new Project(name,description,url,rlist,area,skills);
		researcher.addProject(p);
		em.persist(p);
		
		tx.commit();
		return p;
	}
	
	public static void deleteProject(EntityManager em, Project p) {
		for (Application a : p.getApplications())
			em.remove(a);
		p.removeApplications();
		p.removeResearchers();
		em.remove(p);
		
	}
	
	public static void editName(EntityManager em, Project p, String name) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		p.setName(name);
		
		tx.commit();
	}
	
	public static void editDescription(EntityManager em, Project p, String desc) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		p.setDescription(desc);
		
		tx.commit();
	}
	
	public static void addApplication(EntityManager em, Project p, Application a) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		p.addApplication(a);
		
		tx.commit();
	}
	
	public static void removeApplication(EntityManager em, Project p, Application a) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		p.removeApplication(a);
		
		tx.commit();
	}
	
	public static void addResearcher(EntityManager em, Project p, Researcher r) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		p.addResearcher(r);
		
		tx.commit();
	}
	
	public static void removeResearcher(EntityManager em, Project p, Researcher r) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		p.removeResearcher(r);
		
		tx.commit();
	}
	
	public static void editYear(EntityManager em, Student s, Year year) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		s.setYear(year);
		
		tx.commit();
	}
	
	public static List<Project> getProjectList(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        try {
        String query = "select r from Project r";
        List<Project> mylist = (List<Project>) em.createQuery(query).getResultList();
        
        	return mylist;
        }
        catch (Exception e) {
        	return new ArrayList<Project>();
        }
	}
	public static Project getProjectById(EntityManager em,String id) {
        EntityTransaction tx = em.getTransaction();
        try {
        String query = "select r from Project r where r.id = " + id;
        List<Project> mylist = (List<Project>) em.createQuery(query).getResultList();
        	
        	return mylist.get(0);
        }
        catch (Exception e) {
        	System.out.print(e);
        	return null;
        }
	}
}
