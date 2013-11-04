package model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class YearController {

	public String[] getYears() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        EntityManager em = emf.createEntityManager();
      
        String query = "select * from year";
		@SuppressWarnings("unchecked")
		List<Year> yrs = (List<Year>) em.createQuery(query).getResultList();
		List<String> years = new LinkedList<String>();
		for (Year y : yrs) {
			years.add(y.getDescription());
		}
		
		return (String[]) years.toArray();
		
	}
}
