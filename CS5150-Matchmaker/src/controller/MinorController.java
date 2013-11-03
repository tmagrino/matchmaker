package controller;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Minor;

public class MinorController {

	public String[] getMinors() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        EntityManager em = emf.createEntityManager();
      
        String query = "select * from minor";
		@SuppressWarnings("unchecked")
		List<Minor> mins = (List<Minor>) em.createQuery(query).getResultList();
		List<String> Minors = new LinkedList<String>();
		for (Minor m : mins) {
			Minors.add(m.getDescription());
		}
		
		return (String[]) Minors.toArray();
	}
	
}
