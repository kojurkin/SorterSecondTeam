package org.example.sorter;

import org.example.student.Student;

import java.util.Comparator;

class SorterStudentBookNumber extends ASorter {
    @Override
    public Comparator<Student> getComparator() {
        return Comparator.comparingInt(student -> student.studentBookNumber);
    }
}
