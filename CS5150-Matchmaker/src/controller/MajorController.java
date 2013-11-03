package controller;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.Major;
import model.Student;

public class MajorController {

	public String[] getMajors() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        EntityManager em = emf.createEntityManager();
      
        String query = "select * from major";
		@SuppressWarnings("unchecked")
		List<Major> majs = (List<Major>) em.createQuery(query).getResultList();
		List<String> majors = new LinkedList<String>();
		for (Major m : majs) {
			majors.add(m.getDescription());
		}
		
		return (String[]) majors.toArray();
	}
}
