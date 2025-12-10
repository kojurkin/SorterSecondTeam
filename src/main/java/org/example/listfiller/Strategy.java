package org.example.listfiller;

import org.example.student.Student;

import java.util.ArrayList;
import java.util.List;

public interface Strategy {
    List<Student> fill(Integer size);
}
