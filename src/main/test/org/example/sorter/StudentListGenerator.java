package org.example.sorter;

import org.example.Student;

import java.util.LinkedList;
import java.util.List;

public class StudentListGenerator {
    public List<Student> generateFixedStudentList(int maxSizePerField) {
        List<Student> studentList = new LinkedList<>();

        for (int iGroup = 0; iGroup < maxSizePerField; iGroup++) {
            for (int iScore = 0; iScore < maxSizePerField; iScore++) {
                for (int iBookNumber = 0; iBookNumber < maxSizePerField; iBookNumber++) {
                    studentList.add(new Student(iGroup, (double) iScore, iBookNumber));
                }
            }
        }
        return studentList;
    }
}
