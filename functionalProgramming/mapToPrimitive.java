package pl.clockworkjava;

import pl.clockworkjava.entity.Indeks;
import pl.clockworkjava.entity.Student;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

public class Main {

    public static void main(String[] args) {

        IntStream intStream = createDataStream().map(Student::getAge).mapToInt(value -> value.intValue());
        intStream.sorted().forEach(System.out::println);
    }

    private static Stream<Student> createDataStream() {
        Student student1 = new Student("Kacper", 17, "01U");
        Student student2 = new Student("Maciek", 31, "02U");
        Student student3 = new Student("Wojtek", 67, "03U");
        Student student4 = new Student("Jacek", 23, "01U");

        return Stream.of(student1,student2,student3,student4);
    }
}
