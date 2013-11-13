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

public class CollegeController {

	public static College createCollege(EntityManager em, String description) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		College c = new College(description);
		em.persist(c);
		
		tx.commit();
		return c;
	}

	public static void deleteCollege(EntityManager em, College c) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		if (c != null) {
			c.removeStudents();
			em.remove(c);
		}
		
		tx.commit();
	}
	
	public static void renameCollege(EntityManager em, College c, String description) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		c.setDescription(description);
		
		tx.commit();
	}
	
	public static void addStudent(EntityManager em, College c, Student s) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		c.addStudent(s);
		
		tx.commit();
	}
	
	public static void removeStudent(EntityManager em, College c, Student s) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		c.removeStudent(s);
		
		tx.commit();
	}

	public static College getCollege(EntityManager em, String description){
		String query = "select c from COLLEGE c where c.description = \""+description+"\"";
		@SuppressWarnings("unchecked")
		List<College> cols = (List<College>) em.createQuery(query).getResultList();
		try {
			return cols.get(0);
		}
		catch (Exception e) {
			return null;
		}
	}

	public static String[] getColleges(EntityManager em) {
		String query = "select c from COLLEGE c";
		@SuppressWarnings("unchecked")
		List<College> cols = (List<College>) em.createQuery(query).getResultList();
		List<String> colleges = new LinkedList<String>();
		for (College c : cols) {
			colleges.add(c.getDescription());
		}

		return (String[]) colleges.toArray();
	}
	
	public static List<College> getCollegeList(EntityManager em) {
		try{
			String query = "select c from COLLEGE c";
			@SuppressWarnings("unchecked")
			List<College> cols = (List<College>) em.createQuery(query).getResultList();
			return cols;
		}
		catch (Exception e){
			return new ArrayList<College>();
		}
	}

	public static List<College> parseCollege(String colleges){
		String [] collegeArray = colleges.split(",");
		List<College> collegeList = new ArrayList();
		for (String c : collegeArray){
			if (c != "") collegeList.add(new College(c));
		}
		return collegeList;
	}
	
	public static JSONObject getCollegeJson(EntityManager em) {
		String query = "select c from COLLEGE c";
		@SuppressWarnings("unchecked")
		List<College> colleges = (List<College>) em.createQuery(query).getResultList();
		JSONArray jsonArray = new JSONArray();
		for (College c : colleges){
			JSONObject jsonObject= new JSONObject();
			try {
				jsonObject.put("value", String.valueOf(c.getId()));
				jsonObject.put("name", c.getDescription());
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
