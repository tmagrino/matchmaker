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

	public static Minor createMinor(String description) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MinorController");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		Minor m = new Minor(description);
		em.persist(m);
		
		tx.commit();
		em.close();
		emf.close();
		return m;
	}

	public static void deleteMinor(Minor m) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MinorController");
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
	
	public static void renameMinor(Minor m, String description) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MinorController");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		m.setDescription(description);
		
		tx.commit();
		em.close();
		emf.close();
	}
	
	public static void addStudent(Minor m, Student s) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MinorController");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		m.addStudent(s);
		
		tx.commit();
		em.close();
		emf.close();
	}
	
	public static void removeStudent(Minor m, Student s) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MinorController");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		m.removeStudent(s);
		
		tx.commit();
		em.close();
		emf.close();
	}
	
	public static String[] getMinors() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MinorController");
        EntityManager em = emf.createEntityManager();
      
        String query = "select m from MINOR m";
		@SuppressWarnings("unchecked")
		List<Minor> mins = (List<Minor>) em.createQuery(query).getResultList();
		List<String> Minors = new LinkedList<String>();
		for (Minor m : mins) {
			Minors.add(m.getDescription());
		}
		
		return (String[]) Minors.toArray();
	}
	public static List<Minor> getMinorList(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MinorController");
        EntityManager em = emf.createEntityManager();
        try{
        String query = "select m from MINOR m";
		@SuppressWarnings("unchecked")
		List<Minor> mins = (List<Minor>) em.createQuery(query).getResultList();
		return mins;
        }
        catch (Exception e){
        	return new ArrayList<Minor>();
        }
	}
	public static Minor getMinorByDescription(String description){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MinorController");
        EntityManager em = emf.createEntityManager();
      
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
	
    public static Minor getMinor(long id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MinorController");
        EntityManager em = emf.createEntityManager();

        String query = "select m from MINOR m where m.id = " + id;
        @SuppressWarnings("unchecked")
        List<Minor> mins = (List<Minor>) em.createQuery(query).getResultList();
        return mins.get(0);
    }
    
    public static JSONObject getMinorJson() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MinorController");
        EntityManager em = emf.createEntityManager();
      
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
