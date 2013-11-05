package model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class YearController {

	public static Year getYear(String name) {
		String yr = name.toUpperCase();
		switch (yr) {
			case "FRESHMAN":
				return Year.Freshman;
			case "SOPHOMORE":
				return Year.Sophomore;
			case "JUNIOR":
				return Year.Junior;
			case "SENIOR":
				return Year.Senior;
			case "FIFTH YEAR UNDERGRAD":
				return Year.Fifth_Year_Undergrad;
			case "PHD":
				return Year.PhD;
			default:
				return null;
		}
	}
	
	public static Year getYear(long id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        EntityManager em = emf.createEntityManager();
      
        String query = "select s from Interest s where s.id = " + id;
		@SuppressWarnings("unchecked")
		List<Year> skls = (List<Year>) em.createQuery(query).getResultList();
		return skls.get(0);
	}
}
