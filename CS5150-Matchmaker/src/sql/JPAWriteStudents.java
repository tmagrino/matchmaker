package sql;

import model.Student;
import model.GetStudentInfo;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JPAWriteStudents {

    public static void main(String[] args) {
        List<Student> mylist = GetStudentInfo.get();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        for (Student st : mylist)
            em.persist(st);
        tx.commit();

        //after commit we have ids:
        for (Student st : mylist)
            System.out.println(st);
    }
}