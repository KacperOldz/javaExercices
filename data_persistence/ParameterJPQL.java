package pl.clockworkjava;

import jakarta.persistence.*;
import org.hibernate.internal.build.AllowSysOut;
import pl.clockworkjava.entity.Student;
import pl.clockworkjava.entity.Indeks;
import pl.clockworkjava.entity.University;

import java.util.List;

public class Main {
    static EntityManagerFactory factory = Persistence.createEntityManagerFactory("ClockworkPersistence");
    static EntityManager manager = factory.createEntityManager();

    public static void main(String[] args) {
        createData();

        manager.createQuery("SELECT s FROM Students s").getResultList().forEach(S -> {
            System.out.println(S.toString());
        });

        manager.createQuery("SELECT s.indeks FROM Students s", Indeks.class).getResultList().forEach(S -> {
            System.out.println(S.toString());
        });

        TypedQuery<Indeks> query = manager.createQuery("SELECT s.indeks FROM Students s WHERE s.name LIKE ?1",Indeks.class);
        query.setParameter(1,"Piotr");
        query.getResultList().forEach(s -> {
            System.out.println(s.toString());
        });
    }


    private static void createData() {
        manager.getTransaction().begin();
        Student Kacper = manager.merge(new Student("Kacper", "01U"));
        Student Piotr = manager.merge(new Student("Piotr", "02U"));



        manager.getTransaction().commit();
    }
}
