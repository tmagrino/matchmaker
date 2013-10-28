package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/*
 *  This table in the database will keep track of all
 *  possible majors a student can choose in their profile and
 *  which majors a professor can filter by in their searches
 *  
 *   Table: MAJOR
 *   
 *   |  ID  |    MAJOR               |
 *   |  1   | Africana_Studies       |
 *   |  2   | Agricultural_Sciences  |
 *   |  3   | American_Studies       |
 *   |  4   | Animal_Science         |
 *   |  5   | ...                    |
 *   |  6   |                        |
 *   |  7   |                        |
 *   
 *   
 *   Table: MAJORS_TABLE (maps students to their majors)
 *   
 *   | STUD_ID | MAJOR_ID |
 *   |    1    |     3    |
 *   |    1    |     16   |
 *   |    2    |     32   |
 *   |    3    |     21   |
 *   |    4    |     23   |
 *   |    5    |     31   |
 *   |    6    |     11   |
 *   |   ...   |    ...   |
 */

@Entity(name = "MAJOR")
public class Major {
	@Id @Column(name="ID")
	private long id;
	@Column(name="MAJOR")
	private String description;
	
	@ManyToMany(mappedBy = "majors")
	private List<Student> students;
	
}

/*

public enum Major {
	Africana_Studies,
	Agricultural_Sciences,
	American_Studies,
	Animal_Science,
	Anthropology,
	Applied_Economics_and_Management,
	Archaeology,
	Architecture,
	Asian_Studies,
	Astronomy,
	Atmospheric_Science,
	Biological_Engineering,
	Biological_Sciences,
	Biology_and_Society,
	Biometry_and_Statistics,
	Chemical_Engineering,
	Chemistry_and_Chemical_Biology,
	China_and_Asia_Pacific_Studies,
	Civil_Engineering,
	Classics,
	College_Scholar,
	Communication,
	Comparative_Literature,
	Computer_Science,
	Design_and_Environmental_Analysis,
	Development_Sociology,
	Economics,
	Electrical_and_Computer_Engineering,
	Engineering_Physics,
	English,
	Entomology,
	Environmental_Engineering,
	Feminist_Gender_and_Sexuality_Studies,
	Fiber_Science_and_Apparel_Design,
	Fine_Arts,
	Food_Science,
	French,
	German_Studies,
	Government,
	History,
	History_of_Architecture,
	History_of_Art,
	Hotel_Administration,
	Human_Biology_Health_and_Society,
	Human_Development,
	Independent_Major,
	Industrial_and_Labor_Relations,
	Information_Science,
	Information_Science_Systems_and_Technology,
	International_Agriculture_and_Rural_Development,
	Italian,
	Landscape_Architecture,
	Linguistics,
	Materical_Science_and_Engineering,
	Mathematics,
	Mechanical_Engineering,
	Music,
	Near_Eastern_Studies,
	Nutritional_Sciences,
	Operations_Research_and_Engineering,
	Performing_and_Media_Arts,
	Philosophy,
	Physics,
	Plant_Sciences,
	Policy_Analysis_and_Management,
	Psychology,
	Religious_Studies,
	Science_and_Technology_Studies,
	Environmental_Science_and_Sustainability,
	Sociology,
	Spanish,
	Statistical_Science,
	Urban_and_Regional_Studies,
	Viticulture_and_Enology,
}
*/