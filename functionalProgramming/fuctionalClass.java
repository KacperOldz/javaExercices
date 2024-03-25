package pl.clockworkjava.entity;

import java.util.Optional;

final public class Student {
    private String name;
    private int age;

    private Indeks index;

    public Student(String name, int age, String indeksNum) {
        this.name = name;
        this.age = age;
        this.index = new Indeks(indeksNum);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Optional<Indeks> getIndex() {
        return Optional.ofNullable(this.index);
    }

    public String getStudentInfo() {
        return this.name + " " + this.age;
    }

    public Student changeIndexNumber(String newNum){
        return new Student(this.name, this.age, newNum);
    }
}
