package pl.clockworkjava.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name="stu")
public class Student {
    @Id
    int id;
    @Column(name="imie", nullable = false)
    String name;

    @Column(name="numer_telefonu", unique = true)
    String telephone;

    @Embedded
    Address address;

    private Student() {

    }

    public Student(int id, String name, String telephone) {
        this.id = id;
        this.name = name;
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }
}
