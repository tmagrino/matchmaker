package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.LockModeType;

public class ResearcherController {

	public static Researcher createResearcher(EntityManager em, String name,
			String netID,String email, List<Department> departments,
			String webpage, List<Interest> researchArea, User user) {
        // EntityTransaction tx = em.getTransaction();
        if (user == null) {
        	return null;
        }
        
		// tx.begin();
        
        Researcher r = new Researcher(name, netID, email, departments,
        				webpage, researchArea);
        ResearcherSettings settings = new ResearcherSettings();
        
        //em.lock(user, LockModeType.OPTIMISTIC);

        r.setSettings(settings);
		settings.setResearcher(r);
        user.setResearcher(r);
        em.persist(r);
        em.persist(settings);
        
		// tx.commit();
		return r;
	}
	public static Researcher createResearcher(EntityManager em, String name,
			String netID,String email, List<Department> departments,
			String webpage, Interest researchArea, User user) {

        List<Interest> areas = new ArrayList<Interest>();
        areas.add(researchArea);
		return createResearcher(em, name, netID, email, departments, webpage, 
				areas, user);
	}
	
	public static void deleteResearcher(EntityManager em, Researcher r) {
		// EntityTransaction tx = em.getTransaction();
		// tx.begin();
		if (r != null) {
			// Delete projects if sole project leader
			//em.lock(r, LockModeType.OPTIMISTIC);
			List<Project> toDelete = r.removeProjects();
			// tx.commit();
			for (Project p : toDelete) {
				if (p.getResearchers().size() == 0) {
					ProjectController.deleteProject(em, p);
				}
			}
			// tx.begin();
			//em.lock(r, LockModeType.OPTIMISTIC);
			// Removes incoming pointers to researcher

			/*
			 * Explicit variables used because they have to be locked.
			 */
			r.removeDepartments();
			r.removeResearchAreas();

			r.getUser().setResearcher(null);
			
			r.getSettings().removeStudents();
			r.getSettings().setResearcher(null);
			
			// Remove entities from database
			em.remove(r.getSettings());
			em.remove(r);
			System.out.println("Removed researcher");
		}
		else {
			System.out.println("Null researcher");
		}
		// tx.commit();
	}
	
	public static void addHiddenStudent(EntityManager em, Researcher r, Student s) {
		// EntityTransaction tx = em.getTransaction();
		if (r == null || s == null) {
			return;
		}
		// tx.begin();
		
		//em.lock(r, LockModeType.OPTIMISTIC);
		
		r.getSettings().addStudent(s);

		// tx.commit();
	}
	
	public static void removeHiddenStudent(EntityManager em, Researcher r, Student s) {
		// EntityTransaction tx = em.getTransaction();
		if (r == null || s == null) {
			
		}
		// tx.begin();
		
		//em.lock(r, LockModeType.OPTIMISTIC);
		r.getSettings().removeStudent(s);
		
		// tx.commit();
	}
	
	public static Researcher getResearcherByNetID(EntityManager em, String netid) {
        // EntityTransaction tx = em.getTransaction();
        // tx.begin();
        String query = "select r from Researcher r where r.netID = \"" + netid +"\"";
        List<Researcher> mylist = (List<Researcher>) em.createQuery(query).setLockMode(Locking.getReadLockType()).getResultList();
        try {
        	// tx.commit();
        	return mylist.get(0);
        }
        catch (Exception e) {
        	return null;
        }
	}
	public static List<Researcher> getResearcherList(EntityManager em) {
        // EntityTransaction tx = em.getTransaction();
        // tx.begin();
        try {
        String query = "select r from Researcher r";
        List<Researcher> mylist = (List<Researcher>) em.createQuery(query).setLockMode(Locking.getReadLockType()).getResultList();
        // tx.commit();
        	return mylist;
        }
        catch (Exception e) {
        	return new ArrayList<Researcher>();
        }
	}

	public static Researcher getResearcherByName(EntityManager em, String name) {
        // EntityTransaction tx = em.getTransaction();
        // tx.begin();
        String query = "select r from Researcher r where r.name = \"" + name +"\"";
        List<Researcher> mylist = (List<Researcher>) em.createQuery(query).setLockMode(Locking.getReadLockType()).getResultList();
        try {
        	// tx.commit();
        	return mylist.get(0);
        }
        catch (Exception e) {
        	return null;
        }
	}
	
	public static void addProject(EntityManager em,Researcher r, Project p) {
		// EntityTransaction tx = em.getTransaction();
		if (r == null || p == null) {
			return;
		}
		// tx.begin();
		
		//em.lock(r, LockModeType.OPTIMISTIC);
		r.addProject(p);
		
		// tx.commit();
	}
	
	public static void editName(EntityManager em, Researcher r, String name) {
		// EntityTransaction tx = em.getTransaction();
		if (r == null) {
			return;
		}
		// tx.begin();
		
		//em.lock(r, LockModeType.OPTIMISTIC);
		r.setName(name);
		
		// tx.commit();
	}
	public static void editEmail(EntityManager em, Researcher r, String email) {
		// EntityTransaction tx = em.getTransaction();
		if (r == null) {
			return;
		}
		// tx.begin();
		
		//em.lock(r, LockModeType.OPTIMISTIC);
		r.setEmail(email);
		
		// tx.commit();
	}
	public static void editWebpage(EntityManager em, Researcher r, String webpage) {
		// EntityTransaction tx = em.getTransaction();
		if (r == null) {
			return;
		}
		// tx.begin();
		
		//em.lock(r, LockModeType.OPTIMISTIC);
		r.setWebpage(webpage);
		
		// tx.commit();
	}
	public static void editDepartments(EntityManager em, Researcher r, String ids) throws InstantiationException, IllegalAccessException {
		if (r == null) {
			return;
		}
		// EntityTransaction tx = em.getTransaction();
		// tx.begin();
		//em.lock(r, LockModeType.OPTIMISTIC);
		r.removeDepartments();
		// tx.commit();
		System.out.println("Deleted all departments");
		System.out.println(r.getDepartments().size());
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
		// EntityTransaction tx = em.getTransaction();
		if (r == null) {
			return;
		}
		// tx.begin();
		//em.lock(r, LockModeType.OPTIMISTIC);
		r.addDepartment(dep);

		// tx.commit();
	}
		public static void editArea(EntityManager em, Researcher r, String ids) throws InstantiationException, IllegalAccessException {
			if (r == null) {
				return;
			}
			// EntityTransaction tx = em.getTransaction();
			// tx.begin();
			//em.lock(r, LockModeType.OPTIMISTIC);
			r.removeResearchAreas();
			// tx.commit();
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
			// EntityTransaction tx = em.getTransaction();
			if (r == null || a == null) {
				return;
			}
			// tx.begin();
			//em.lock(r, LockModeType.OPTIMISTIC);
			r.addResearchArea(a);
			// tx.commit();
		}
	
		
	
	
}
