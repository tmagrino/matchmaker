package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ProjectController {
	
	public static final String TITLE = "Title";
	public static final String AREA = "Research Area";
	public static final String SKILL = "Required Skills";
	public static final String URL = "Project URL";
	public static final String DESCRIPTION = "Project Description";

	public static Project createProject(EntityManager em , String name, String description,
			String url, Researcher researcher, List<Interest> area, List<Skill> skills){
		String newurl = url;
		
		if (description == null) {
			description = "";
		}
		if (url.startsWith("http://")) {
			newurl = url.substring(7);
		}
		else if (url.startsWith("https://")) {
			newurl = url.substring(8);
		}
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		ArrayList<Researcher> rlist = new ArrayList<Researcher>();
		rlist.add(researcher);

		Project p = new Project(name,description,newurl,rlist,area,skills);
		researcher.addProject(p);
		em.persist(p);
		
		tx.commit();
		return p;
	}
	
	public static Project createProject(EntityManager em , String name, String description,
			String url, List<Researcher> researcher, List<Interest> area, List<Skill> skills){
		String newurl = url;
		if (description == null) {
			description = "";
		}
		if (url.startsWith("http://")) {
			newurl = url.substring(7);
		}
		else if (url.startsWith("https://")) {
			newurl = url.substring(8);
		}
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		Project p = new Project(name,description,url,researcher,area,skills);
		
		em.persist(p);
		
		tx.commit();
		return p;
	}
	public static Project updateProject(EntityManager em ,Project p, String name, String description,
			String url, List<Researcher> researcher, List<Interest> area, List<Skill> skills){
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		p.updateProject(name,description,url,researcher,area,skills);
		
		em.persist(p);
		
		tx.commit();
		return p;
	}
	public static Project updateProject(EntityManager em ,Project p, String name, String description,
			String url, Researcher researcher, List<Interest> area, List<Skill> skills){
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		p.updateProject(name,description,url,researcher,area,skills);
		
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
        	
        	return null;
        }
	}
	public static List<Application> removeDeclinedApplications(List<Application> apps){
		List<Application> declined = new ArrayList<Application>();
		for (Application a : apps){
			if (a.getStatus() == ApplicationStatus.Declined){
				declined.add(a);
			}
		}
		apps.removeAll(declined);
		return apps;
	}
	public static String getAttribute(Project p, String type){
		if (type == TITLE){
			return p.getName();
		}
		if (type == AREA){
			return p.getAreaString();
		}
		if (type == SKILL){
			return p.getSkillString();
		}
		if (type == URL){
			return p.getURL();
		}
		if (type == DESCRIPTION){
			return p.getDescription();
		}
		return null;
	}
	public static JSONObject getObjectJson(List<? extends MultipleItem> collection) {
		if(collection.size() > 0){
			Collections.sort(collection);
		}
		JSONArray jsonArray = new JSONArray();
		for (MultipleItem t : collection){
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
