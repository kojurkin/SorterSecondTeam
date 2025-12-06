package org.example.sorter;

import org.example.Student;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.example.sorter.BasicClientSorter.*;
import static org.example.sorter.Logger.log;
import static org.junit.jupiter.api.Assertions.*;

class BasicClientSorterTest {
    private StudentListGenerator studentListGenerator = new StudentListGenerator();
    BasicClientSorter sorter = new BasicClientSorter();

    @Test
    void sortASG() {
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
    void testIsTheSameLength() {
        List<Student> studentList =  studentListGenerator.generateFixedStudentList(10);
        List<Student> sortedList = sorter.sort(studentList,
                ClientFieldName.AverageScore,
                ClientFieldName.StudentBookNumber,
                ClientFieldName.GroupNumber);
        log("sortedList.size()=" + sortedList.size() + ", studentList.size()=" + studentList.size());
        assertEquals(studentList.size(), sortedList.size());
    }


}