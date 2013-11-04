package model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class YearController {

	public Year getYear(String name) {
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
}
