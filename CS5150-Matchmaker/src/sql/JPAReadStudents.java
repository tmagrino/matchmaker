package sql;

import model.Student;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAReadStudents {
	public static void main(String args[]) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
		EntityManager em = emf.createEntityManager();

		String query = "select * from STUDENT";
		@SuppressWarnings("unchecked")
		List<Student> mylist = (List<Student>) em.createQuery(query).getResultList();
		for (Student st : mylist) {
			System.out.println(st.getName());
		}
	}
}