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

public class MinorController {

	public static Minor createMinor(EntityManager em, String description) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		Minor m = new Minor(description);
		em.persist(m);
		
		tx.commit();
		return m;
	}

	public static void deleteMinor(EntityManager em, Minor m) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		if (m != null) {
			m.removeStudents();
			em.remove(m);
		}
		
		tx.commit();
	}
	
	public static void renameMinor(EntityManager em, Minor m, String description) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		m.setDescription(description);
		
		tx.commit();
	}
	
	public static void addStudent(EntityManager em, Minor m, Student s) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		m.addStudent(s);
		
		tx.commit();
	}
	
	public static void removeStudent(EntityManager em, Minor m, Student s) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		m.removeStudent(s);
		
		tx.commit();
	}
	
	public static String[] getMinors(EntityManager em) {
        String query = "select m from MINOR m";
		@SuppressWarnings("unchecked")
		List<Minor> mins = (List<Minor>) em.createQuery(query).getResultList();
		List<String> Minors = new LinkedList<String>();
		for (Minor m : mins) {
			Minors.add(m.getDescription());
		}
		
		return (String[]) Minors.toArray();
	}
	public static List<Minor> getMinorList(EntityManager em){
        try {
        String query = "select m from MINOR m";
		@SuppressWarnings("unchecked")
		List<Minor> mins = (List<Minor>) em.createQuery(query).getResultList();
		return mins;
        }
        catch (Exception e){
        	return new ArrayList<Minor>();
        }
	}
	public static Minor getMinorByDescription(EntityManager em, String description){
        String query = "select m from MINOR m where m.description = \""+description+"\"";
		@SuppressWarnings("unchecked")
		List<Minor> mins = (List<Minor>) em.createQuery(query).getResultList();
		
		return mins.get(0);
	}
	
	public static List<Minor> parseMinor(String minors){
		if (minors == "") return new ArrayList<Minor>();
		String [] minorArray = minors.split(",");
		List<Minor> minorList = new ArrayList<Minor>();
		for (String m : minorArray){
			if (m != "") minorList.add(new Minor(m));
		}
		return minorList;
	}
	
    public static Minor getMinor(EntityManager em,long id){
        String query = "select m from MINOR m where m.id = " + id;
        @SuppressWarnings("unchecked")
        List<Minor> mins = (List<Minor>) em.createQuery(query).getResultList();
        return mins.get(0);
    }
    
    public static JSONObject getMinorJson(EntityManager em) {
        String query = "select m from MINOR m";
		@SuppressWarnings("unchecked")
		List<Minor> minors = (List<Minor>) em.createQuery(query).getResultList();
		JSONArray jsonArray = new JSONArray();
		for (Minor m : minors){
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
