package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import javax.persistence.LockModeType;

public class ProjectController {
	
	public static final String TITLE = "Title";
	public static final String AREA = "Research Area";
	public static final String SKILL = "Required Skills";
	public static final String URL = "Project URL";
	public static final String DESCRIPTION = "Project Description";

	public static Project createProject(EntityManager em , String name, String description,
			String url, Researcher researcher, List<Interest> area, List<Skill> skills){
		if (description == null) {
			description = "";
		}

		if (researcher == null) {
			return null;
		}
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.lock(researcher, LockModeType.PESSIMISTIC_WRITE);
		ArrayList<Researcher> rlist = new ArrayList<Researcher>();
		rlist.add(researcher);

		Project p = new Project(name,description,url,rlist,area,skills);
		researcher.addProject(p);
		em.persist(p);
		
		tx.commit();
		return p;
	}
	
	public static Project createProject(EntityManager em , String name, String description,
			String url, List<Researcher> researcher, List<Interest> area, List<Skill> skills){
		if (description == null) {
			description = "";
		}

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		Project p = new Project(name,description,url,researcher,area,skills);
		
		em.persist(p);
		
		tx.commit();
		return p;
	}
	
	public static void deleteProject(EntityManager em, Project p) {
		EntityTransaction tx = em.getTransaction();
		if (p == null) {
			return;
		}
		tx.begin();
		em.lock(p, LockModeType.PESSIMISTIC_WRITE);

		// Delete applications
		List<Application> toDelete = p.removeApplications();
		tx.commit();
		for (Application a : toDelete) {
			ApplicationController.deleteApplication(em, a);
		}
		tx.begin();
		em.lock(p, LockModeType.PESSIMISTIC_WRITE);
		// Remove pointers to this project
		p.removeRequiredSkills();
		p.removeResearchers();
		p.removeProjectAreas();
		p.removeHiddenBys();
		
		em.remove(p);
		tx.commit();
	}

	public static Project updateProject(EntityManager em ,Project p, String name, String description,
			String url, List<Researcher> researcher, List<Interest> area, List<Skill> skills){
		EntityTransaction tx = em.getTransaction();
		if (p == null) {
			return null;
		}
		tx.begin();

		em.lock(p, LockModeType.PESSIMISTIC_WRITE);
		p.updateProject(name,description,url,researcher,area,skills);

		em.persist(p);

		tx.commit();
		return p;
	}
	public static Project updateProject(EntityManager em ,Project p, String name, String description,
			String url, Researcher researcher, List<Interest> area, List<Skill> skills){
		EntityTransaction tx = em.getTransaction();
		if (p == null || researcher == null) {
			return null;
		}
		tx.begin();

		em.lock(p, LockModeType.PESSIMISTIC_WRITE);
		p.updateProject(name,description,url,researcher,area,skills);

		em.persist(p);

		tx.commit();
		return p;
	}
	
	
	public static void editName(EntityManager em, Project p, String name) {
		EntityTransaction tx = em.getTransaction();
		if (p == null) {
			return;
		}
		tx.begin();
		
		em.lock(p, LockModeType.PESSIMISTIC_WRITE);
		p.setName(name);
		
		tx.commit();
	}
	
	public static void editDescription(EntityManager em, Project p, String desc) {
		EntityTransaction tx = em.getTransaction();
		if (p == null) {
			return;
		}
		tx.begin();
		
		em.lock(p, LockModeType.PESSIMISTIC_WRITE);
		p.setDescription(desc);
		
		tx.commit();
	}
	
	public static void editURL(EntityManager em, Project p, String url) {
		EntityTransaction tx = em.getTransaction();
		if (p == null) {
			return;
		}
		tx.begin();
		em.lock(p, LockModeType.PESSIMISTIC_WRITE);
		p.setURL(url);
		
		tx.commit();
	}
	
	public static void removeApplication(EntityManager em, Project p, Application a) {
		if (p == null || a == null) {
			return;
		}
		ApplicationController.deleteApplication(em, a);
	}
	
	public static void addResearcher(EntityManager em, Project p, Researcher r) {
		EntityTransaction tx = em.getTransaction();
		if (p == null || r == null) {
			return;
		}
		tx.begin();
		
		em.lock(p, LockModeType.PESSIMISTIC_WRITE);
		p.addResearcher(r);
		
		tx.commit();
	}
	
	public static void removeResearcher(EntityManager em, Project p, Researcher r) {
		EntityTransaction tx = em.getTransaction();
		if (p == null || r == null) {
			return;
		}
		tx.begin();
		
		em.lock(p, LockModeType.PESSIMISTIC_WRITE);
		p.removeResearcher(r);
		
		tx.commit();
	}
	
	public static boolean meetsRequirements(Project p, Student s) {
		if (s == null) {
			return false;
		}
		List<Skill> skills = p.getRequiredSkills();
		for (Skill skl : skills) {
			if (!s.getSkills().contains(skl)) {
				return false;
			}
		}
		
		return true;
	}
	
	public static List<Project> getProjectList(EntityManager em) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
        try {
        String query = "select r from Project r";
        List<Project> mylist = (List<Project>) em.createQuery(query).getResultList();
        tx.commit();
        	return mylist;
        }
        catch (Exception e) {
        	return new ArrayList<Project>();
        }
	}
	
	public static Project getProjectById(EntityManager em,String id) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
        String query = "select r from Project r where r.id = " + id;
        List<Project> mylist = (List<Project>) em.createQuery(query).getResultList();
        tx.commit();
        	return mylist.get(0);
        }
        catch (Exception e) {
        	
        	return null;
        }
	}
	
	public static void removeDeclinedApplications(EntityManager em,
			Project p) {
		EntityTransaction tx = em.getTransaction();
		if (p == null) {
			return;
		}
		tx.begin();
		em.lock(p, LockModeType.PESSIMISTIC_WRITE);
		List<Application> declined = new LinkedList<Application>();
		for (Application a : p.getApplications()) {
			if (a.getStatus() == ApplicationStatus.Declined) {
				declined.add(a);
			}
		}
		
		for (Application a : declined) {
			ApplicationController.deleteApplication(em, a);
		}
		
		tx.commit();
	}
	public static String getAttribute(Project p, String type) {
		if (p == null) {
			return null;
		}
		switch (type) {
			case TITLE:
				return p.getName();
			case AREA:
				return p.getAreaString();
			case SKILL:
				return p.getSkillString();
			case URL:
				return p.getURL();
			case DESCRIPTION:
				return p.getDescription();
			default:
				return null;
		}
	}
	
	public static JSONObject getObjectJson(List<? extends FieldValue> collection) {
		if(collection.size() > 0){
			Collections.sort(collection);
		}
		JSONArray jsonArray = new JSONArray();
		for (FieldValue t : collection){
			JSONObject jsonObject= new JSONObject();
			try {
				jsonObject.put("value", String.valueOf(t.getId()));
				jsonObject.put("name", t.getDescription());
				jsonArray.put(jsonObject);
			} catch (JSONException e) {
				
				e.printStackTrace();
			}
		}
		JSONObject items_obj = new JSONObject();
		try {
			items_obj.put("items", jsonArray);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return items_obj;
	}
}
