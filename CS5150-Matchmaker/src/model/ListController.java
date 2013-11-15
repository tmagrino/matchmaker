package model;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class ListController<T extends MultipleItem>{
	
	public  T createItem(EntityManager em, String description) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		T m = (T) T.create(description);
		em.persist(m);
		
		tx.commit();
		return m;
	}

}
