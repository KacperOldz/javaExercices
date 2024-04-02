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
        List<Student> students = createData();
        Supplier<List<Student>> studentsSupplier = Main::createData;

        Consumer<String> printName = System.out::println;
        Consumer<String> printNameUppercase = Main::testMe;

        Predicate<Student> over18 = student -> student.getAge()>18;

        Function<Student, String> getStudentName = Student::getName;

        BiFunction<Student,String,Student> changeIndex = Student::changeIndexNumber;

        List<Student> studentsList = studentsSupplier.get();

        Double sumOfRandom = Stream.generate(Math::random).limit(10).reduce(0.0, (aDouble, aDouble2) -> aDouble + aDouble2);

        Optional<Integer> reduce = createDataStream().map(Student::getAge).reduce((ageA, ageB) -> ageA > ageB ? ageA : ageB);


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
