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
			String webpage, List<Interest> researchArea, User user) {
        EntityTransaction tx = em.getTransaction();
        if (user == null) {
        	return null;
        }
        
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
	public static Researcher createResearcher(EntityManager em, String name,
			String netID,String email, List<Department> departments,
			String webpage, Interest researchArea, User user) {
        EntityTransaction tx = em.getTransaction();
        if (user == null) {
        	return null;
        }
        List<Interest> areas = new ArrayList<Interest>();
        areas.add(researchArea);
		tx.begin();
        
        Researcher r = new Researcher(name, netID, email, departments,
        				webpage, areas);
        ResearcherSettings settings = new ResearcherSettings();
        r.setSettings(settings);
		settings.setResearcher(r);
        user.setResearcher(r);
        em.persist(r);
        em.persist(settings);
        
		tx.commit();
		return r;
	}
	
	public static void deleteResearcher(EntityManager em, Researcher r) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		if (r != null) {
			r.removeDepartments();
			r.removeResearchAreas();
			r.removeProjects();
			r.getUser().setResearcher(null);
			r.getSettings().removeStudents();
			r.getSettings().setResearcher(null);
			r.setSettings(null);
			em.remove(r.getSettings());
			em.remove(r);
		}
		tx.commit();
	}
	

	
	public static void addHiddenStudent(EntityManager em, Researcher r, Student s) {
		EntityTransaction tx = em.getTransaction();
		if (r == null || s == null) {
			return;
		}
		tx.begin();
		
		r.getSettings().addStudent(s);
		
		tx.commit();
	}
	
	public static void removeHiddenStudent(EntityManager em, Researcher r, Student s) {
		EntityTransaction tx = em.getTransaction();
		if (r == null || s == null) {
			
		}
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
			String departments, List<Interest> researchArea, String webpage) throws NumberFormatException, InstantiationException, IllegalAccessException {
		if (researcher == null) {
			return;
		}
		EntityTransaction tx = em.getTransaction();
        
        tx.begin();
		researcher.setName(name);
		researcher.setNetID(netID);
		researcher.setEmail(email);
		researcher.removeDepartments();
		String[] idList = departments.split(",");
		for (String id : idList) {
			Department dep = (Department) FieldValueController.getFieldValueById(em, Long.parseLong(id), FieldFactory.DEPARTMENT);
			if (dep == null) {
				System.out.println("wierd bug");
			}
			else {
				researcher.addDepartment(dep);
			}
		}
		researcher.setResearchArea(researchArea);
		
		researcher.setWebpage(webpage);
		
        
        String deleteQuery = "delete from RESEARCHER where id = " + researcher.getId();
        em.createQuery(deleteQuery);
        em.persist(researcher);
        tx.commit();
	}
	
	public static void addProject(EntityManager em,Researcher r, Project p) {
		EntityTransaction tx = em.getTransaction();
		if (r == null || p == null) {
			return;
		}
		tx.begin();
		
		r.addProject(p);
		
		tx.commit();
	}
	
	public static void editName(EntityManager em, Researcher r, String name) {
		EntityTransaction tx = em.getTransaction();
		if (r == null) {
			return;
		}
		tx.begin();
		
		r.setName(name);
		
		tx.commit();
	}
	public static void editEmail(EntityManager em, Researcher r, String email) {
		EntityTransaction tx = em.getTransaction();
		if (r == null) {
			return;
		}
		tx.begin();
		
		r.setEmail(email);
		
		tx.commit();
	}
	public static void editWebpage(EntityManager em, Researcher r, String webpage) {
		EntityTransaction tx = em.getTransaction();
		if (r == null) {
			return;
		}
		tx.begin();
		
		r.setWebpage(webpage);
		
		tx.commit();
	}
	public static void editDepartments(EntityManager em, Researcher r, String ids) throws InstantiationException, IllegalAccessException {
		if (r == null) {
			return;
		}
		r.removeDepartments();
		String[] idList = ids.split(",");

		for (String id : idList){
			if (id.length()>0){
				try{
			
					addDepartment(em,r,(Department) FieldValueController.getFieldValueById(em,Long.parseLong(id),FieldFactory.DEPARTMENT));
				}
				catch(NumberFormatException e){
					addDepartment(em,r,(Department) FieldValueController.createFieldValue(em, id, FieldFactory.DEPARTMENT));
				}
			}
		}
		
		
	}
	private static void addDepartment(EntityManager em, Researcher r, Department dep) {
		EntityTransaction tx = em.getTransaction();
		if (r == null) {
			return;
		}
		tx.begin();
		r.addDepartment(dep);
		tx.commit();
	}
		public static void editArea(EntityManager em, Researcher r, String ids) throws InstantiationException, IllegalAccessException {
			if (r == null) {
				return;
			}
			r.removeResearchAreas();
			String[] idList = ids.split(",");
			
			for (String id : idList){
				if (id.length()>0){
					try{
						addArea(em,r,(Interest) FieldValueController.getFieldValueById(em,Long.parseLong(id),FieldFactory.INTEREST));
					}
					catch(NumberFormatException e){

						addArea(em,r,(Interest) FieldValueController.createFieldValue(em, id, FieldFactory.INTEREST));
					}
				}
			}
		
			
		}
		private static void addArea(EntityManager em, Researcher r, Interest a){
			EntityTransaction tx = em.getTransaction();
			if (r == null || a == null) {
				return;
			}
			tx.begin();
			r.addResearchArea(a);
			tx.commit();
		}
	
		
	
	
}