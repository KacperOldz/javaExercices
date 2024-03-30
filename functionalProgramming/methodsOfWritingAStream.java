package pl.clockworkjava;

import pl.clockworkjava.entity.Indeks;
import pl.clockworkjava.entity.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        // methods of writing a stream
        List<Student> students = createData();
        Supplier<List<Student>> studentsSupplier = Main::createData;

        Consumer<String> printName = System.out::println;
        Consumer<String> printNameUppercase = Main::testMe;

        Predicate<Student> over18 = student -> student.getAge()>18;

        Function<Student, String> getStudentName = Student::getName;

        BiFunction<Student,String,Student> changeIndex = Student::changeIndexNumber;

        List<Student> studentsList = studentsSupplier.get();

        studentsList.stream().filter(over18).map(getStudentName).forEach(printName);
        // Stream can be used only once
        // methods of writing a stream
        // 1.
        Stream.of("1,", "2,", "3").forEach(printName);

        // 2.
        List<Student> students2 = createData();
        Stream<Student> stream = students2.stream();


        // 3.
        Stream.generate(() -> Math.random()).limit(10).forEach(System.out::println);

        // 4.
        Stream.iterate(0,i -> i+2).limit(10).forEach(System.out::println);

        // 5.
        IntStream.rangeClosed(5,100).filter(i->i%2==0) .forEach(System.out::println);
        
        // 6.
        Stream<Student> studentStream = createDataStream();
    }

    private static void consumeStudents(Supplier<List<Student>> supplier, Consumer<String> consumer, Function<Student, String> function) {
        for(Student s: supplier.get()) {
            consumer.accept(function.apply(s));
        }
    }

    private static Stream<Student> createDataStream() {
        Student student1 = new Student("Kacper", 17, "01U");
        Student student2 = new Student("Maciek", 31, "02U");
        Student student3 = new Student("Wojtek", 21, "03U");

        return Stream.of(student1,student2,student3);
    }

    private static List<Student> createData() {
        List<Student> result = new ArrayList<>();

        result.add(new Student("Kacper", 17, "01U"));
        result.add(new Student("Maciek", 31, "02U"));
        result.add(new Student("Wojtek", 21, "03U"));

        return result;

    }

    private static void testMe(String x) {
        System.out.println(x.toUpperCase());
    }
}
