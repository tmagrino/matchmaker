package model;


public class ItemFactory {
	
	public static final String SKILL = "skill";
	public static final String MAJOR = "major";
	public static final String MINOR = "minor";
	public static final String INTEREST = "interest";
	public static final String COLLEGE = "college";
	public static final String DEPARTMENT = "department";
	
	
	public static MultipleItem create(String type, String name){
		if (type == MAJOR){
			return new Major(name);
		}
		if (type == MINOR){
			return new Minor(name);
		}
		if (type == SKILL){
			return new Skill(name);
		}
		if (type == INTEREST){
			return new Interest(name);
		}
		if (type == COLLEGE){
			return new College(name);
		}
		if (type == DEPARTMENT) {
			return new Department(name);
		}
		
		return null;
	}
	public static String typeIdentifier(MultipleItem item){
		if (item instanceof Major){
			return MAJOR;
		}
		if (item instanceof Minor){
			return MINOR;
		}
		if (item instanceof Interest){
			return INTEREST;
		}
		if (item instanceof College){
			return COLLEGE;
		}
		if (item instanceof Skill){
			return SKILL;
		}
		if (item instanceof Department) {
			return DEPARTMENT;
		}
		return null;
	}
	public static String getQuery(MultipleItem item){
		String query = new String();
		if (item instanceof Major){
			query = "select m from MAJOR m";
		}
		if (item instanceof Minor){
			query = "select m from MINOR m";
		}
		if (item instanceof College){
			query = "select m from COLLEGE m";
		}
		if (item instanceof Skill){
			query = "select m from SKILL m";
		}
		if (item instanceof Interest){
			query = "select m from INTEREST m";
		}
		if (item instanceof Department) {
			query = "select m from DEPARTMENT m";
		}
		return query;
	}
	public static String getQuery(String type){
		String query = new String();
		if (type.toLowerCase() == MAJOR){
			query = "select m from MAJOR m";
		}
		if (type.toLowerCase() == MINOR){
			query = "select m from MINOR m";
		}
		if (type.toLowerCase() == COLLEGE){
			query = "select m from COLLEGE m";
		}
		if (type.toLowerCase() == SKILL){
			query = "select m from SKILL m";
		}
		if (type.toLowerCase() == INTEREST){
			query = "select m from INTEREST m";
		}
		if (type.toLowerCase() == DEPARTMENT){
			query = "select m from DEPARTMENT m";
		}
		return query;
	}
}
