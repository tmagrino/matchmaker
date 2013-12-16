package model;

/**
 * This class is used to facilitate the usage of {@link FieldValue}s.
 * 
 * @author Leonardo Neves
 * @author Jan Cardenas
 *
 */

public class FieldFactory {
	
	public static final String SKILL = "skill";
	public static final String MAJOR = "major";
	public static final String MINOR = "minor";
	public static final String INTEREST = "interest";
	public static final String COLLEGE = "college";
	public static final String DEPARTMENT = "department";
	
	/**
	 * Creates a new {@link FieldValue}
	 * 
	 * @param type the String value of the {@link FieldValue} type to create
	 * @param name the name to give the new {@link FieldValue}
	 * @return a new {@FieldValue}
	 */
	public static FieldValue createField(String type, String name) {
		switch (type.toLowerCase()) {
			case MAJOR:
				return new Major(name);
			case MINOR:
				return new Minor(name);
			case SKILL:
				return new Skill(name);
			case INTEREST:
				return new Interest(name);
			case COLLEGE:
				return new College(name);
			case DEPARTMENT:
				return new Department(name);
			default:
				System.out.println("Error: No field type named "+type);	
				return null;
		}
	}
	
	/**
	 * 
	 * @param item the {@link FieldValue} used to determine its type
	 * @return a string of the type of {@link FieldValue} the argument is
	 */
	public static String getType(FieldValue item) {
		if (item instanceof Major) {
			return MAJOR;
		}
		else if (item instanceof Minor) {
			return MINOR;
		}
		else if (item instanceof Interest) {
			return INTEREST;
		}
		else if (item instanceof College) {
			return COLLEGE;
		}
		else if (item instanceof Skill) {
			return SKILL;
		}
		else if (item instanceof Department) {
			return DEPARTMENT;
		}
		return null;
	}
	
	/**
	 * Get the MySQL query command for a given {@link FieldValue}
	 * 
	 * @param item the {@link FieldValue} used to determine its query string
	 * @return MySQL query command for the given {@link FieldValue}
	 */
	public static String getQuery(FieldValue item) {
		String query = new String();
		if (item instanceof Major) {
			query = "select m from MAJOR m";
		}
		else if (item instanceof Minor) {
			query = "select m from MINOR m";
		}
		else if (item instanceof College) {
			query = "select m from COLLEGE m";
		}
		else if (item instanceof Skill) {
			query = "select m from SKILL m";
		}
		else if (item instanceof Interest) {
			query = "select m from INTEREST m";
		}
		else if (item instanceof Department) {
			query = "select m from DEPARTMENT m";
		}
		return query;
	}
	
	/**
	 * Get the MySQL query command for a given {@link FieldValue} string type
	 * 
	 * @param typ the {@link FieldValue} string type used to determine its query string
	 * @return MySQL query command for the given {@link FieldValue} string type
	 */
	public static String getQuery(String typ){
		String query = new String();
		String type = typ.toLowerCase();
		if (type.equals(MAJOR)){
			query = "select m from MAJOR m";
		}
		else if (type.equals(MINOR)) {
			query = "select m from MINOR m";
		}
		else if (type.equals(COLLEGE)) {
			query = "select m from COLLEGE m";
		}
		else if (type.equals(SKILL)) {
			query = "select m from SKILL m";
		}
		else if (type.equals(INTEREST)) {
			query = "select m from INTEREST m";
		}
		else if (type.equals(DEPARTMENT)){
			query = "select m from DEPARTMENT m";
		}
		return query;
	}
	
	/**
	 * Shortens a String over 40 characters into a String with 40 characters
	 * and appends "..." to that shortenedString
	 * 
	 * @param str the String to shorten
	 * @return a shortenedString
	 */
	public static String shortenString(String str){
		if (str.length() > 40) {
			return str.substring(0, 40) + "...";
		}
		else {
			return str;
		}
	}
}
