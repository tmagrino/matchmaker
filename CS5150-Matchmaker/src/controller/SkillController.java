package controller;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Skill;

public class SkillController {

	public String[] getSkills() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        EntityManager em = emf.createEntityManager();
      
        String query = "select * from skill";
		@SuppressWarnings("unchecked")
		List<Skill> skls = (List<Skill>) em.createQuery(query).getResultList();
		List<String> skills = new LinkedList<String>();
		for (Skill m : skls) {
			skills.add(m.getDescription());
		}
		
		return (String[]) skills.toArray();
	}
}
