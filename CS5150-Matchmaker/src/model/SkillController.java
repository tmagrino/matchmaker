package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.json.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SkillController {

	public static String[] getSkills() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        EntityManager em = emf.createEntityManager();
      
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
	public static Skill getSkillByDescription(String description){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        EntityManager em = emf.createEntityManager();
      
        String query = "select s from SKILL s where s.description = \""+description+"\"";
		@SuppressWarnings("unchecked")
		List<Skill> skls = (List<Skill>) em.createQuery(query).getResultList();
		
		return skls.get(0);
	}
	public static List<Skill> getSkillList() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        EntityManager em = emf.createEntityManager();
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
	
	public static JSONObject getSkillJson() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        EntityManager em = emf.createEntityManager();
      
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
	
	public static Skill getSkill(long id) {
         EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
         EntityManager em = emf.createEntityManager();

         String query = "select s from SKILL s where s.id = " + id;
         @SuppressWarnings("unchecked")
         List<Skill> skls = (List<Skill>) em.createQuery(query).getResultList();
         return skls.get(0);        
	 }
}

