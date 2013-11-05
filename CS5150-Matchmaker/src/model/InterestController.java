package model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class InterestController {

	public static String[] getInterests() {
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
