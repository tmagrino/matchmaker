package model;

/**
 * This class is used to facilitate dealing with {@link Year}
 * 
 * @author Leonardo Neves
 *
 */

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
