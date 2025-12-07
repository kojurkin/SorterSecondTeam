package org.example.sorter;

import org.example.student.Student;

import java.util.Comparator;

class SorterGroupNumber extends ASorter {

    @Override
    public Comparator<Student> getComparator() {
        return Comparator.comparingInt(o -> o.groupNumber);
    }
}
