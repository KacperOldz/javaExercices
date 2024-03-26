package pl.clockworkjava;

import jakarta.persistence.*;
import pl.clockworkjava.entity.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {
        List<Student> students = createData();

        Predicate<Student> isKacper = student -> student.getName().equals("Kacper");
        Predicate<Student> isOver18 = student -> student.getAge()>=18;

        checkStudents(students, isKacper);
        checkStudents(students, isOver18);
    }

    private static List<Student> checkStudents(List<Student> students, Predicate<Student> predicate) {
        List<Student> result = new ArrayList<>();

        for (Student s : students) {
            if (predicate.test(s)) {
                result.add(s);
            }
        }
        return result;
    }


    private static List<Student> createData() {
        List<Student> result = new ArrayList<>();

        result.add(new Student("Kacper", 17, "01U"));
        result.add(new Student("Maciek", 31, "02U"));
        result.add(new Student("Wojtek", 21, "03U"));

        return result;

    }
}
