package org.example.oldlistfiller;

import org.example.student.Student;

import java.util.List;

public interface FillStrategy {
    List<Student> fill(Integer size);
}
