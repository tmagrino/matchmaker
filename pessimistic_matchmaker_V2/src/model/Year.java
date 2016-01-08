package model;

/**
 * This class represents the year a student
 *  is currently in.
 * 
 * @author Leonardo Neves
 * @author Jan Cardenas
 *
 */

public enum Year {
	Freshman("1"),
	Sophomore("2"),
	Junior("3"),
	Senior("4"),
	Graduate("5+");
	private final String yearValue; 
	Year(String value){
		yearValue = value;
	}
	
	@Override
	public String toString(){
		return yearValue;
	}
}
