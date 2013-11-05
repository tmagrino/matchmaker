package model;

import java.util.LinkedList;
import java.util.List;

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
	public static List<Skill> getSkillList() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        EntityManager em = emf.createEntityManager();
      
        String query = "select s from SKILL s";
		@SuppressWarnings("unchecked")
		List<Skill> skls = (List<Skill>) em.createQuery(query).getResultList();
		return skls;

		
	}
}
