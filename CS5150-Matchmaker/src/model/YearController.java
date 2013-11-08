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
			case "1":
				return Year.Freshman;
			case "2":
				return Year.Sophomore;
			case "3":
				return Year.Junior;
			case "4":
				return Year.Senior;
			case "5+":
				return Year.Graduate;
			default:
				return null;
		}
	}
	
	
}
