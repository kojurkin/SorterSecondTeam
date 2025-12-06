package org.example.sorter;

import org.example.Student;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class StudentListGenerator {
    private Random rnd = new Random();

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

    public List<Student> generateRandomStudentList() {
        List<Student> studentList = new LinkedList<>();
        for (int iStudent = 0; iStudent < 100; iStudent++) {
            studentList.add(new Student(rnd.nextInt(10), rnd.nextDouble(10), rnd.nextInt(10)));
        }
        return studentList;
    }
}
