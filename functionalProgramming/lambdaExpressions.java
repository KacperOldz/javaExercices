package pl.clockworkjava;

import jakarta.persistence.*;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Runnable r = new Runnable() {

            @Override
            public void run() {
                System.out.println("Standard runnable");
            }
        };

        Runnable ru = () -> System.out.println("Lambda");

        Comparable<String> c = new Comparable<String>() {
            @Override
            public int compareTo(String o) {
                return 0;
            }
        };

        Comparable<String> co = (String o) -> 0;

        test("Lambda expression can be passed like a variable", ru);
    }

    public static void test(String name, Runnable ru) {

    }
}
