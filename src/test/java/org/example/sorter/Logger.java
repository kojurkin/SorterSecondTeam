package org.example.sorter;

import org.example.Student;
import org.example.student.Student;

import java.util.List;

public class Logger {
    static void log(Object text) {
        System.out.println(text);
    }

    static void log(String prefix, List<Student> arr) {
        System.out.println(prefix);
        for (Object text : arr) {
            System.out.println(text);
        }
    }
}
