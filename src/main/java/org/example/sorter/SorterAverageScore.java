package org.example.sorter;

import org.example.Student;

import java.util.Comparator;

public class SorterAverageScore extends ASorter {
    @Override
    public Comparator<Student> getComparator() {
        return Comparator.comparingDouble(o -> o.averageScore);
    }
}
