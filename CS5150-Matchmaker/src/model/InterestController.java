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

	public static Interest createInterest(EntityManager em, String description) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		Interest i = new Interest(description);
		em.persist(i);
		
		tx.commit();
		return i;
	}

	public static void deleteInterest(EntityManager em, Interest i) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		if (i != null) {
			i.removeStudents();
			em.remove(i);
		}
		
		tx.commit();
	}
	
	public static void renameInterest(EntityManager em, Interest i, String description) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		i.setDescription(description);
		
		tx.commit();
	}
	
	public static void addStudent(EntityManager em, Interest i, Student s) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		i.addStudent(s);
		
		tx.commit();
	}
	
	public static void removeStudent(EntityManager em, Interest i, Student s) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		i.removeStudent(s);
		
		tx.commit();
	}
	
	public static String[] getInterests(EntityManager em) {
        String query = "select i from INTEREST i";
		@SuppressWarnings("unchecked")
		List<Interest> ints = (List<Interest>) em.createQuery(query).getResultList();
		List<String> interests = new LinkedList<String>();
		for (Interest i : ints) {
			interests.add(i.getDescription());
		}
		
		return (String[]) interests.toArray();
	}
	public static List<Interest> getInterestList(EntityManager em) {
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
	public static Interest getInterestByDescription(EntityManager em, String description) {
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
    public static Interest getInterest(EntityManager em, long id) {
        String query = "select i from INTEREST i where i.id = " + id;
        @SuppressWarnings("unchecked")
        List<Interest> ints = (List<Interest>) em.createQuery(query).getResultList();
        return ints.get(0);
    }
    
	public static JSONObject getInterestJson(EntityManager em) {
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
