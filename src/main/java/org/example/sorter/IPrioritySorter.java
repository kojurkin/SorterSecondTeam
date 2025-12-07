package org.example.sorter;

import org.example.Student;
import org.jspecify.annotations.NonNull;

import java.util.List;

public interface IPrioritySorter {
    List<Student> sort(@NonNull List<Student> studentList, @NonNull ChainSorter chainSorter);
}
