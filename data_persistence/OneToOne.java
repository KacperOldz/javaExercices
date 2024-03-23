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


}
