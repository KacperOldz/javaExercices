package pl.clockworkjava.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Classes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    @ManyToMany(mappedBy = "classesSet")
    private Set<Student> studentSet;

    private Classes() {}

    public Classes(String name) {
        this.name = name;
        this.studentSet = new HashSet<>();
    }

    public void addStudent(Student stu) {
        studentSet.add(stu);
    }
}


@Entity(name="Students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name="imie", nullable = false)
    private String name;

    @Column(name="numer_telefonu", unique = true)
    private String telephone;

    @Embedded
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    private Indeks indeks;

    @ManyToOne
    private University university;

    @ManyToMany
    private Set<Classes> classesSet;

    private Student() {

    }

    public Student(String name) {
        this.name = name;
    }

    public Student(String name, String indexNum) {
        this.name = name;
        this.indeks = new Indeks(indexNum);
        this.classesSet = new HashSet<>();
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

    public void addClass(Classes classes) {
        classesSet.add(classes);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIndeks(Indeks indeks) {
        this.indeks = indeks;
    }

    public void setUniversity(University university) { this.university = university; }
}
