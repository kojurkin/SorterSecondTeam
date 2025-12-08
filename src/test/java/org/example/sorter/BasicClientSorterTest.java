package org.example.sorter;

import org.example.student.Student;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.example.sorter.Logger.log;
import static org.junit.jupiter.api.Assertions.*;

public class BasicClientSorterTest {
    private final StudentListGenerator studentListGenerator = new StudentListGenerator();
    BasicClientSorter sorter = new BasicClientSorter();

    @Test
    public void testSortASG() {
        log("testSortASG");
        int maxSizePerField = 3;
        List<Student> studentList = studentListGenerator.generateFixedStudentList(maxSizePerField);
        List<Student> sortedList = sorter.sort(studentList,
                ClientFieldName.AverageScore,
                ClientFieldName.StudentBookNumber,
                ClientFieldName.GroupNumber);
        log("ASG:", sortedList);
        int studentIndex = 0;
        Student currentStudent;
        for (int iScore = 0; iScore < maxSizePerField; iScore++) {
            for (int iBNumber = 0; iBNumber < maxSizePerField; iBNumber++) {
                for (int iGroup = 0; iGroup < maxSizePerField; iGroup++) {
                    currentStudent = sortedList.get(studentIndex++);
                    assertEquals(currentStudent.averageScore, iScore);
                    assertEquals(currentStudent.studentBookNumber, iBNumber);
                    assertEquals(currentStudent.groupNumber, iGroup);
                }
            }
        }
    }

    @Test
    public void testTheSameLength() {
        log("testTheSameLength");
        List<Student> studentList = studentListGenerator.generateFixedStudentList(10);
        List<Student> sortedList = sorter.sort(studentList,
                ClientFieldName.AverageScore,
                ClientFieldName.StudentBookNumber,
                ClientFieldName.GroupNumber);
        log("sortedList.size()=" + sortedList.size() + ", studentList.size()=" + studentList.size());
        assertEquals(studentList.size(), sortedList.size());
    }

    @Test
    public void testInputNull() {
        log("testInputNull");
        List<Student> studentList = null;
        List<Student> sortedList = sorter.sort(studentList,
                ClientFieldName.AverageScore,
                ClientFieldName.StudentBookNumber,
                ClientFieldName.GroupNumber);
        assertNull(sortedList);
    }

    @Test
    public void testStudentNullInput() {
        log("testStudentNullInput");
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(1, 4.0, 2));
        studentList.add(null);
        studentList.add(new Student(3, 2.0, 1));

        assertThrowsExactly(IllegalArgumentException.class, () -> {
            try {
                List<Student> sortedList = sorter.sort(studentList,
                        ClientFieldName.AverageScore,
                        ClientFieldName.StudentBookNumber,
                        ClientFieldName.GroupNumber);
                assertNull(sortedList);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                throw e;
            }
        });
    }

    @Test
    public void testFieldNameNullInput() {
        log("testFieldNameNullInput");
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(1, 4.0, 2));
        studentList.add(new Student(3, 2.0, 1));

        assertThrowsExactly(IllegalArgumentException.class, () -> {
            try {
                List<Student> sortedList = sorter.sort(studentList,
                        ClientFieldName.AverageScore,
                        null,
                        ClientFieldName.GroupNumber);
                assertNull(sortedList);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                throw e;
            }
        });
    }

    @Test
    public void testFieldNameListNullInput() {
        log("testFieldNameListNullInput");
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(1, 4.0, 2));
        studentList.add(new Student(3, 2.0, 1));

        assertThrowsExactly(IllegalArgumentException.class, () -> {
            try {
                List<Student> sortedList = sorter.sort(studentList, (ClientFieldName[]) null);
                assertNull(sortedList);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                throw e;
            }
        });
    }
}