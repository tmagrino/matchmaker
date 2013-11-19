package model;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class ProjectController {

	public static Project createProject(EntityManager em , String name, String description, 
			Researcher researcher){
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		ArrayList<Researcher> rlist = new ArrayList<Researcher>();
		rlist.add(researcher);
		Project p = new Project(name,description,rlist);
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
	
}
