package pl.clockworkjava.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

    private String name;

    @OneToMany
    private Set<Student> students;
    private University() {}

    public University(String name) {
        this.name = name;
        this.students = new HashSet<>();
    }
    public University(String name, Set<Student> students) {
        this.name = name;
        this.students = students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }
}
