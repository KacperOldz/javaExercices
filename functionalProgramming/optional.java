package pl.clockworkjava;

import pl.clockworkjava.entity.Indeks;
import pl.clockworkjava.entity.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Main {

    public static void main(String[] args) {
        List<Student> students = createData();
        Supplier<List<Student>> studentsSupplier = Main::createData;

        Consumer<String> printName = System.out::println;
        Consumer<String> printNameUppercase = Main::testMe;

        Function<Student, String> getStudentName = Student::getName;

        BiFunction<Student,String,Student> changeIndex = Student::changeIndexNumber;

        consumeStudents(studentsSupplier, printNameUppercase.andThen(printName), getStudentName);
        Student student = createData().getFirst();
        Optional<Indeks> index = student.getIndex();

        if(index.isPresent()) {
            System.out.println(index.get().getNumber());
        }

        index.ifPresent(i -> {
            System.out.println(i.getNumber().equals("01U"));
        });

        //index.filter(i -> i.getNumber().equals("01U"));
        index.map(i -> i.getNumber()).filter(indexNumber-> indexNumber.equals("01U")).ifPresent(indexNumber -> System.out.println(indexNumber));
    }

    private static void consumeStudents(Supplier<List<Student>> supplier, Consumer<String> consumer, Function<Student, String> function) {
        for(Student s: supplier.get()) {
            consumer.accept(function.apply(s));
        }
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
