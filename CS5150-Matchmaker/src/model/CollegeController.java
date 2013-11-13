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

	public College createCollege(String description) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("CollegeController");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		College c = new College(description);
		em.persist(c);
		
		tx.commit();
		em.close();
		emf.close();

		return c;
	}

	public void removeCollege(College c) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("CollegeController");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		if (c != null) {
			c.removeStudents();
			em.remove(c);
		}
		
		tx.commit();
		em.close();
		emf.close();
	}
	
	public void renameCollege(College c, String description) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("CollegeController");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		c.setDescription(description);
		
		tx.commit();
		em.close();
		emf.close();
	}
	
	public void addStudent(College c, Student s) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("CollegeController");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		c.addStudent(s);
		
		tx.commit();
		em.close();
		emf.close();
	}
	
	public void removeStudent(College c, Student s) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("CollegeController");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		c.removeStudent(s);
		
		tx.commit();
		em.close();
		emf.close();
	}

	public static College getCollege(String description){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("CollegeController");
		EntityManager em = emf.createEntityManager();

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

	public static String[] getColleges() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("CollegeController");
		EntityManager em = emf.createEntityManager();

		String query = "select c from COLLEGE c";
		@SuppressWarnings("unchecked")
		List<College> cols = (List<College>) em.createQuery(query).getResultList();
		List<String> colleges = new LinkedList<String>();
		for (College c : cols) {
			colleges.add(c.getDescription());
		}

		return (String[]) colleges.toArray();
	}
	
	public static List<College> getCollegeList() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("CollegeController");
		EntityManager em = emf.createEntityManager();
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
	
	public static JSONObject getCollegeJson() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("CollegeController");
		EntityManager em = emf.createEntityManager();

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
