package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/*
 *  This class represents the year a student
 *  is currently in.
 * 
 */

@Embeddable
public enum Year {
	Freshman,
	Sophomore,
	Junior,
	Senior,
	Fifth_Year_Undergrad,
	Graduate_Student,
	PhD,
}
