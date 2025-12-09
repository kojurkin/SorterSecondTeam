package org.example.sorter;

import org.example.student.Student;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

import static org.example.sorter.Logger.log;
import static org.junit.jupiter.api.Assertions.*;

public class ClientSorterEvenTest {
    private final StudentListGenerator studentListGenerator = new StudentListGenerator();
    ClientSorterEven sorter = new ClientSorterEven(ClientFieldName.StudentBookNumber);

    @Test
    public void testSort() {
        int maxSizePerField = 2;
        List<Student> studentList = studentListGenerator.generateFixedStudentList(maxSizePerField);
        List<Student> sortedList = sorter.sort(studentList,
                ClientFieldName.AverageScore);
        log("ASG:", sortedList);
        assertTrue(isEqual(sortedList.get(0), new Student(0, 0.0, 0)));
        assertTrue(isEqual(sortedList.get(4), new Student(0, 1.0, 0)));
        assertTrue(isEqual(sortedList.get(7), new Student(1, 1.0, 1)));
    }

    private boolean isEqual(Student a, Student b) {
        return Objects.equals(a.groupNumber, b.groupNumber)
                && Objects.equals(a.studentBookNumber, b.studentBookNumber)
                && a.averageScore.equals(b.averageScore);
    }
}