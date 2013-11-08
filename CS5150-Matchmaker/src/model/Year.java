package model;

/*
 *  This class represents the year a student
 *  is currently in.
 * 
 */

public enum Year {
	Freshman("1"),
	Sophomore("2"),
	Junior("3"),
	Senior("4"),
	Graduate("5");
	private final String yearValue; 
	private Year(String value){
		yearValue = value;
	}

}
