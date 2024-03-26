package pl.clockworkjava;

import jakarta.persistence.*;
import pl.clockworkjava.entity.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {
        List<Student> students = createData();

        Consumer<Student> printName = student -> System.out.println(student.getName());
        Consumer<Student> printNameUppercase = student -> System.out.println(student.getName().toUpperCase());

        consumeStudents(students, printNameUppercase.andThen(printName));
    }

    private static void consumeStudents(List<Student> students, Consumer<Student> consumer) {
        for(Student s: students) {
            consumer.accept(s);
        }
    }


    private static List<Student> createData() {
        List<Student> result = new ArrayList<>();

        result.add(new Student("Kacper", 17, "01U"));
        result.add(new Student("Maciek", 31, "02U"));
        result.add(new Student("Wojtek", 21, "03U"));

        return result;

    }
}
