package model;

public class FieldFactory {
	
	public static final String SKILL = "skill";
	public static final String MAJOR = "major";
	public static final String MINOR = "minor";
	public static final String INTEREST = "interest";
	public static final String COLLEGE = "college";
	public static final String DEPARTMENT = "department";
	
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
	
	public static String shortenString(String str){
		if (str.length() > 40) {
			return str.substring(0, 40) + "...";
		}
		else {
			return str;
		}
	}
}
