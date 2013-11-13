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
	
	public static Major createMajor(String description) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MajorController");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		Major m = new Major(description);
		em.persist(m);
		
		tx.commit();
		em.close();
		emf.close();
		return m;
	}

	public static void deleteMajor(Major m) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MajorController");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		if (m != null) {
			m.removeStudents();
			em.remove(m);
		}
		
		tx.commit();
		em.close();
		emf.close();
	}
	
	public static void renameMajor(Major m, String description) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MajorController");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		m.setDescription(description);
		
		tx.commit();
		em.close();
		emf.close();
	}
	
	public static void addStudent(Major m, Student s) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MajorController");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		m.addStudent(s);
		
		tx.commit();
		em.close();
		emf.close();
	}
	
	public static void removeStudent(Major m, Student s) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MajorController");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		m.removeStudent(s);
		
		tx.commit();
		em.close();
		emf.close();
	}

	public static String[] getMajors() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MajorController");
        EntityManager em = emf.createEntityManager();
      
        String query = "select m from MAJOR m";
		@SuppressWarnings("unchecked")
		List<Major> majs = (List<Major>) em.createQuery(query).getResultList();
		List<String> majors = new LinkedList<String>();
		for (Major m : majs) {
			majors.add(m.getDescription());
		}
		
		return (String[]) majors.toArray();
	}
	
	public static List<Major> getMajorList() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MajorController");
        EntityManager em = emf.createEntityManager();
       try{
        String query = "select m from MAJOR m";
		@SuppressWarnings("unchecked")
		List<Major> majs = (List<Major>) em.createQuery(query).getResultList();
		
		return majs;
       }
       catch (Exception e){
    	   return  new ArrayList<Major>();
       }
	}
	public static Major getMajorByDescription(String description){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MajorController");
        EntityManager em = emf.createEntityManager();
      
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
	
	public static Major getMajor(long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MajorController");
        EntityManager em = emf.createEntityManager();

        String query = "select m from MAJOR m where m.id = " + id;
        @SuppressWarnings("unchecked")
        List<Major> majs = (List<Major>) em.createQuery(query).getResultList();
        
        return majs.get(0);
	}
	public static JSONObject getMajorJson() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MajorController");
        EntityManager em = emf.createEntityManager();
      
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