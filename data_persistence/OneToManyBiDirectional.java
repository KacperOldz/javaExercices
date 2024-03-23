package pl.clockworkjava.entity;

import jakarta.persistence.*;

@Entity(name="stu")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(name="imie", nullable = false)
    String name;

    @Column(name="numer_telefonu", unique = true)
    String telephone;

    @Embedded
    Address address;

    @OneToOne(cascade = CascadeType.ALL)
    Indeks indeks;

    @ManyToOne
    University university;

    private Student() {

    }

    public Student(String name) {
        this.name = name;
    }

    public Student(String name, String indexNum) {
        this.name = name;
        this.indeks = new Indeks(indexNum);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", telephone='" + telephone + '\'' +
                ", address=" + address +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIndeks(Indeks indeks) {
        this.indeks = indeks;
    }

    public void setUniversity(University university) { this.university = university; }
}




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

    @OneToMany(mappedBy = "university")
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

    @Override
    public String toString() {
        return "University{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", students=" + students +
                '}';
    }

    public void addStudent(Student student) {
        students.add(student);
    }
}

