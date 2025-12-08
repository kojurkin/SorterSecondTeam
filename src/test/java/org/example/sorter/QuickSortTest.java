package org.example.sorter;

import org.example.student.Student;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.example.sorter.Logger.log;
import static org.junit.jupiter.api.Assertions.*;

public class QuickSortTest {
    private final StudentListGenerator studentListGenerator = new StudentListGenerator();

    @Test
    public void testSortAsNode() {
        Comparator<Student> comparator = Comparator.comparingInt(student -> student.groupNumber);
        QuickSort<Student> quickSort = new QuickSort<>(comparator);
        List<List<Student>> result = quickSort.sortAsNode(studentListGenerator.generateFixedStudentList(3));
        List<Student> flatResult = result.stream().flatMap(List::stream).toList();
        log("Sorted by groupNumber", flatResult);
        int maxGroupNumber = 0;
        for (Student student : flatResult) {
            if (maxGroupNumber < student.groupNumber) {
                maxGroupNumber = student.groupNumber;
            }
            if (maxGroupNumber > student.groupNumber) {
                fail();
            }
        }
        assertTrue(true);
    }
}