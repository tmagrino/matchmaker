package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class InterestController {

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
      
        String query = "select i from INTEREST i";
		@SuppressWarnings("unchecked")
		List<Interest> ints = (List<Interest>) em.createQuery(query).getResultList();
		return ints;
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
	public static JSONArray getInterestJson() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        EntityManager em = emf.createEntityManager();
      
        String query = "select i from INTEREST";
		@SuppressWarnings("unchecked")
		List<Interest> interests = (List<Interest>) em.createQuery(query).getResultList();
		JSONArray jsonArray = new JSONArray();
		for (Interest i : interests){
			JSONObject jsonObject= new JSONObject();
			try {
				jsonObject.put(String.valueOf(i.getId()), i.getDescription());
				jsonArray.put(jsonObject);
			} catch (JSONException e) {
				
				e.printStackTrace();
			}
		}
		return jsonArray;
	}
}

