package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MajorController {
	

	
	public static Major createMajor(EntityManager em, String description) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		Major m = new Major(description);
		em.persist(m);
		
		tx.commit();
		return m;
	}

	public static void deleteMajor(EntityManager em, Major m) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		if (m != null) {
			m.removeStudents();
			em.remove(m);
		}
		
		tx.commit();
	}
	
	public static void renameMajor(EntityManager em, Major m, String description) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		m.setDescription(description);
		
		tx.commit();
	}
	
	public static void addStudent(EntityManager em, Major m, Student s) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		m.addStudent(s);
		
		tx.commit();
	}
	
	public static void removeStudent(EntityManager em, Major m, Student s) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		m.removeStudent(s);
		
		tx.commit();
	}

	public static String[] getMajors(EntityManager em) {
        String query = "select m from MAJOR m";
		@SuppressWarnings("unchecked")
		List<Major> majs = (List<Major>) em.createQuery(query).getResultList();
		List<String> majors = new LinkedList<String>();
		for (Major m : majs) {
			majors.add(m.getDescription());
		}
		
		return (String[]) majors.toArray();
	}
	
	public static List<Major> getMajorList(EntityManager em) {
       try {
        String query = "select m from MAJOR m";
		@SuppressWarnings("unchecked")
		List<Major> majs = (List<Major>) em.createQuery(query).getResultList();
		
		return majs;
       }
       catch (Exception e){
    	   return  new ArrayList<Major>();
       }
	}
	public static Major getMajorByDescription(EntityManager em, String description){
        String query = "select m from MAJOR m where m.description = \""+description+"\"";
		@SuppressWarnings("unchecked")
		List<Major> majs = (List<Major>) em.createQuery(query).getResultList();
		
		return majs.get(0);
	}
	public static List<Major> parseMajor(String majors){
		
		String [] majorArray = majors.split(",");
		List<Major> majorList = new ArrayList();
		for (String m : majorArray){
			if (m != "") majorList.add(new Major(m));
		}
		return majorList;
	}
	
	public static Major getMajor(EntityManager em, long id) {
        String query = "select m from MAJOR m where m.id = " + id;
        @SuppressWarnings("unchecked")
        List<Major> majs = (List<Major>) em.createQuery(query).getResultList();
        
        return majs.get(0);
	}
	public static JSONObject getMajorJson(EntityManager em) {
        String query = "select m from MAJOR m";
		@SuppressWarnings("unchecked")
		List<Major> majors = (List<Major>) em.createQuery(query).getResultList();
		JSONArray jsonArray = new JSONArray();
		for (Major m : majors){
			JSONObject jsonObject= new JSONObject();
			try {
				jsonObject.put("value", String.valueOf(m.getId()));
				jsonObject.put("name", m.getDescription());
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