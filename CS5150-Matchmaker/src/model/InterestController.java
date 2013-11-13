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

public class InterestController {

	public Interest createInterest(String description) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("InterestController");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		Interest i = new Interest(description);
		em.persist(i);
		
		tx.commit();
		em.close();
		emf.close();
		return i;
	}

	public void deleteInterest(Interest i) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("InterestController");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		if (i != null) {
			i.removeStudents();
			em.remove(i);
		}
		
		tx.commit();
		em.close();
		emf.close();
	}
	
	public void renameInterest(Interest i, String description) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("InterestController");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		i.setDescription(description);
		
		tx.commit();
		em.close();
		emf.close();
	}
	
	public void addStudent(Interest i, Student s) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("InterestController");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		i.addStudent(s);
		
		tx.commit();
		em.close();
		emf.close();
	}
	
	public void removeStudent(Interest i, Student s) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("InterestController");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		i.removeStudent(s);
		
		tx.commit();
		em.close();
		emf.close();
	}
	
	public static String[] getInterests() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        EntityManager em = emf.createEntityManager();
      
        String query = "select i from INTEREST i";
		@SuppressWarnings("unchecked")
		List<Interest> ints = (List<Interest>) em.createQuery(query).getResultList();
		List<String> interests = new LinkedList<String>();
		for (Interest i : ints) {
			interests.add(i.getDescription());
		}
		
		return (String[]) interests.toArray();
	}
	public static List<Interest> getInterestList() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        EntityManager em = emf.createEntityManager();
        try{
	        String query = "select i from INTEREST i";
			@SuppressWarnings("unchecked")
			List<Interest> ints = (List<Interest>) em.createQuery(query).getResultList();
			return ints;
        }
        catch (Exception e){
        	return new ArrayList<Interest>(0);
        }
	}
	public static Interest getInterestByDescription(String description){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        EntityManager em = emf.createEntityManager();
      
        String query = "select i from INTEREST i where i.description = \""+description+"\"";
		@SuppressWarnings("unchecked")
		List<Interest> ints = (List<Interest>) em.createQuery(query).getResultList();
		
		return ints.get(0);
	}
    public static List<Interest> parseInterest(String interests){
		
		String [] interestArray = interests.split(",");
		List<Interest> interestList = new ArrayList();
		for (String i : interestArray){
			if (i != "") interestList.add(new Interest(i));
		}
		return interestList;
	}
    public static Interest getInterest(long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        EntityManager em = emf.createEntityManager();

        String query = "select i from INTEREST i where i.id = " + id;
        @SuppressWarnings("unchecked")
        List<Interest> ints = (List<Interest>) em.createQuery(query).getResultList();
        return ints.get(0);
    }
	public static JSONObject getInterestJson() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        EntityManager em = emf.createEntityManager();
      
        String query = "select i from INTEREST i";
		@SuppressWarnings("unchecked")
		List<Interest> interests = (List<Interest>) em.createQuery(query).getResultList();
		JSONArray jsonArray = new JSONArray();
		for (Interest i : interests){
			JSONObject jsonObject= new JSONObject();
			try {
				jsonObject.put("value", String.valueOf(i.getId()));
				jsonObject.put("name", i.getDescription());
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
