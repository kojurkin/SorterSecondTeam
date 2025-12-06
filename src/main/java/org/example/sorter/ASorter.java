package org.example.sorter;

import org.example.Student;

import java.util.Comparator;
import java.util.List;

abstract class ASorter implements ISorter<Student>, ISorterNode<Student> {
    private ISorterNode<Student> sorterNodeCache;

    @Override
    public List<Student> sort(List<Student> studentList) {
        return sortAsNode(studentList).stream().flatMap(List::stream).toList();
    }

    @Override
    public List<List<Student>> sortAsNode(List<Student> studentList) {
        if (sorterNodeCache == null) {
            sorterNodeCache = new QuickSort<>(getComparator());
        }
        return sorterNodeCache.sortAsNode(studentList);
    }

    public abstract Comparator<Student> getComparator();
}
