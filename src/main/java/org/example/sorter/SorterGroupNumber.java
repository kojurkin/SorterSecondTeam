package org.example.sorter;

import org.example.Student;

import java.util.Comparator;

class SorterGroupNumber extends ASorter {

    @Override
    public Comparator<Student> getComparator() {
        return Comparator.comparingInt(o -> o.groupNumber);
    }
}
