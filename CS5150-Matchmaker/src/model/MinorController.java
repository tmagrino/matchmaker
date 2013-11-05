package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MinorController {

	public static String[] getMinors() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        EntityManager em = emf.createEntityManager();
      
        String query = "select m from MINOR m";
		@SuppressWarnings("unchecked")
		List<Minor> mins = (List<Minor>) em.createQuery(query).getResultList();
		List<String> Minors = new LinkedList<String>();
		for (Minor m : mins) {
			Minors.add(m.getDescription());
		}
		
		return (String[]) Minors.toArray();
	}
	public static List<Minor> getMinorList(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        EntityManager em = emf.createEntityManager();
      
        String query = "select m from MINOR m";
		@SuppressWarnings("unchecked")
		List<Minor> mins = (List<Minor>) em.createQuery(query).getResultList();
		return mins;
	}
	
	public static List<Minor> parseMinor(String minors){
		
		String [] minorArray = minors.split(",");
		List<Minor> minorList = new ArrayList();
		for (String m : minorArray){
			if (m != "") minorList.add(new Minor(m));
		}
		return minorList;
	}
	
    public static Minor getMinor(long id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        EntityManager em = emf.createEntityManager();

        String query = "select m from MINOR m where m.id = " + id;
        @SuppressWarnings("unchecked")
        List<Minor> mins = (List<Minor>) em.createQuery(query).getResultList();
        return mins.get(0);
    }
}
