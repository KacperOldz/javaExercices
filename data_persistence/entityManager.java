package pl.clockworkjava;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.internal.build.AllowSysOut;
import pl.clockworkjava.entity.Student;

import java.util.List;

public class Main {
    static EntityManagerFactory factory = Persistence.createEntityManagerFactory("ClockworkPersistence");
    static EntityManager manager = factory.createEntityManager();

    public static void main(String[] args) {
        // CRUD
        createStudent(new Student(1, "Kacper", "123123123"));
        readStudents();
        updateStudent(1);
        readStudents();
        deleteStudent(1);
        readStudents();
    }

    private static void createStudent(Student stu) {
        manager.getTransaction().begin();
        manager.persist(stu);
        manager.getTransaction().commit();
    }

    private static void readStudents() {

        List students = manager.createQuery("from stu").getResultList();
        students.forEach(S -> {
            System.out.println(S.toString());
        });
    }

    private static void updateStudent(int pos) {
        manager.getTransaction().begin();

        Student student = manager.find(Student.class, pos);
        student.setName("Nowe imie");
        manager.merge(student);

        manager.getTransaction().commit();
    }

    private static void deleteStudent(int pos) {
        Student student = manager.find(Student.class, pos);

        manager.getTransaction().begin();
        manager.remove(student);
        manager.getTransaction().commit();
    }
}
