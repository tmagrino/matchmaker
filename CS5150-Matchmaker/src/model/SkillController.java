package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.json.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class SkillController {

	public static Skill createSkill(EntityManager em, String description) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		Skill sk = new Skill(description);
		em.persist(sk);
		
		tx.commit();
		return sk;
	}

	public static void deleteSkill(EntityManager em, Skill sk) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		if (sk != null) {
			sk.removeStudents();
			em.remove(sk);
		}
		
		tx.commit();
	}
	
	public static void renameSkill(EntityManager em, Skill sk, String description) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		sk.setDescription(description);
		
		tx.commit();
	}
	
	public static void addStudent(EntityManager em, Skill sk, Student s) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		sk.addStudent(s);
		
		tx.commit();
	}
	
	public static void removeStudent(EntityManager em, Skill sk, Student s) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		sk.removeStudent(s);
		
		tx.commit();
	}
	
	public static String[] getSkills(EntityManager em) {
        String query = "select s from SKILL s";
		@SuppressWarnings("unchecked")
		List<Skill> skls = (List<Skill>) em.createQuery(query).getResultList();
		List<String> skills = new LinkedList<String>();
		for (Skill m : skls) {
			skills.add(m.getDescription());
		}
		Student s = new Student();
		return (String[]) skills.toArray();
		
	}
	public static Skill getSkillByDescription(EntityManager em, String description) {
        String query = "select s from SKILL s where s.description = \""+description+"\"";
		@SuppressWarnings("unchecked")
		List<Skill> skls = (List<Skill>) em.createQuery(query).getResultList();
		
		return skls.get(0);
	}
	public static List<Skill> getSkillList(EntityManager em) {
       try{
        String query = "select s from SKILL s";
		@SuppressWarnings("unchecked")
		List<Skill> skls = (List<Skill>) em.createQuery(query).getResultList();
		return skls;
       }
       catch (Exception e){
    	   return new ArrayList<Skill>();
       }
	}
	
	public static JSONObject getSkillJson(EntityManager em) {
        String query = "select s from SKILL s";
		@SuppressWarnings("unchecked")
		List<Skill> skls = (List<Skill>) em.createQuery(query).getResultList();
		JSONArray jsonArray = new JSONArray();
		for (Skill s : skls){
			JSONObject jsonObject= new JSONObject();
			try {
				jsonObject.put("value", String.valueOf(s.getId()));
				jsonObject.put("name", s.getDescription());
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
	
	public static List<Skill> parseSkill(String skills){
		
		String [] skillArray = skills.split(",");
		List<Skill> skillList = new ArrayList();
		for (String s : skillArray){
			if (s != "") skillList.add(new Skill(s));
		}
		return skillList;
	}
	
	public static Skill getSkill(EntityManager em, long id) {
         String query = "select s from SKILL s where s.id = " + id;
         @SuppressWarnings("unchecked")
         List<Skill> skls = (List<Skill>) em.createQuery(query).getResultList();
         return skls.get(0);        
	 }
}

