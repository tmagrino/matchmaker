package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/*
 *  This table in the database will keep track of all
 *  possible majors a student can choose in their profile and
 *  which majors a professor can filter by in their searches
 *  
 *   Table: MINOR
 *   
 *   |  ID  |    MINOR                 |
 *   |  1   | Aerospace_Engineering    |
 *   |  2   | Africana_Studies         |
 *   |  3   | Agribusiness_Management  |
 *   |  4   | American_Indian_Studies  |
 *   |  5   | ...                      |
 *   |  6   |                          |
 *   |  7   |                          |
 *   
 *   Table: MINORS_TABLE (maps students to their majors)
 *   
 *   | STUD_ID | MINOR_ID |
 *   |    1    |     3    |
 *   |    1    |     16   |
 *   |    2    |     32   |
 *   |    3    |     21   |
 *   |    4    |     23   |
 *   |    5    |     31   |
 *   |    6    |     11   |
 *   |   ...   |    ...   |
 *   
 */

@Entity(name = "MINOR")
public class Minor {
	@Id @Column(name="ID")
	private long id;
	@Column(name="MINOR")
	private String description;
	
	@ManyToMany(mappedBy = "minors")
	private List<Student> students;
	
}


/*
public enum Minor {
	Aerospace_Engineering,
	Africana_Studies,
	Agribusiness_Management,
	American_Indian_Studies,
	Animal_Science,
	Anthropology,
	Applied_Economics,
	Applied_Exercise_Science,
	Applied_Mathematics,
	Archaeology,
	Architecture,
	Asian_American_Studies,
	Astronomy,
	Atmospheric_Science,
	Biological_Engineering,
	Biological_Sciences,
	Biomedical_Engineering,
	Biomedical_Sciences,
	Biometry_and_Statistics,
	Business,
	Business_for_Engineering_Students,
	China_and_Asia_Pacific_Studies,
	Civil_Infrastructure,
	Classics,
	Cognitive_Science,
	Communication,
	Computer_Science,
	Computing_in_the_Arts,
	Creative_Writing,
	Crop_Management,
	Dance,
	Design_and_Environmental_Analysis,
	Development_Sociology,
	East_Asia_Program,
	English,
	Education,
	Electrical_and_Computer_Engineering,
	Engineering_Management,
	Engineering_Statistics,
	Entomology,
	Environmental_and_Resource_Economics,
	Environmental_Engineering,
	European_Studies,
	Feminist_Gender_and_Sexuality_Studies,
	Fiber_Science,
	Film,
	Fine_Arts,
	Food_Science,
	French_Studies,
	Game_Design,
	German_Studies,
	Gerontology,
	Global_Health,
	Globalization_Ethnicity_and_Development,
	Health_Policy,
	History,
	History_of_Art,
	Human_Biology,
	Industrial_Systems_and_Information_Technology,
	Inequality_Studies,
	Information_Science,
	International_Relations,
	International_Development_Studies,
	International_Trade_and_Development,
	Italian,
	Jewish_Studies,
	Landscape_Studies,
	Latin_American_Studies,
	Latino_Studies,
	Law_and_Regulation,
	Law_and_Society,
	Lesbian_Gay_Bisexual_and_Transgender_Studies,
	Linguistics,
	Marine_Biology,
	Materials_Science_and_Engineering,
	Mathematics,
	Mechanical_Engineering,
	Medieval_Studies,
	Minority_Indigenous_and_Third_World_Studies,
	Music,
	Natural_Resources,
	Near_Eastern_Studies,
	Nutrition_and_Health,
	Operations_Research_and_Management_Science,
	Physics,
	Plant_Sciences,
	Policy_Analysis_and_Management,
	Portuguese_and_Brazilian_Studies,
	Real_Estate,
	Religious_Studies,
	Science_and_Technology_Studies,
	Science_of_Earth_Systems,
	Science_of_Natural_and_Environmental_Systems,
	Soil_Science,
	South_Asia_Studies,
	Southeast_Asia_Studies,
	Spanish,
	Sustainable_Energy_Systems,
	Theatre,
	University_Wide_Business_Minor,
	Urban_and_Regional_Studies,
	Visual_Studies,
	Viticulture_and_Enology
}*/