package org.example.sorter;

import org.example.Student;

import java.util.Comparator;

public class SorterStudentBookNumber extends ASorter{
    @Override
    public Comparator<Student> getComparator() {
        return Comparator.comparingInt(student -> student.studentBookNumber);
    }
}
