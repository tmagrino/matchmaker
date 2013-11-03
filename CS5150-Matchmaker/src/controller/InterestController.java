package controller;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Interest;

public class InterestController {

	public String[] getInterests() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        EntityManager em = emf.createEntityManager();
      
        String query = "select * from interest";
		@SuppressWarnings("unchecked")
		List<Interest> ints = (List<Interest>) em.createQuery(query).getResultList();
		List<String> interests = new LinkedList<String>();
		for (Interest i : ints) {
			interests.add(i.getDescription());
		}
		
		return (String[]) interests.toArray();
	}
	
}
