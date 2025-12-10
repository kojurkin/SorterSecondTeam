package org.example.mthreadcounting;
import org.example.student.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MThreadCountingTest {
    org.example.student.Student student = new org.example.student.Student();
    List<Student> studentList = new ArrayList<Student>();

    MThreadCounting mThreadCounting;

    @BeforeEach
    public void setUp() {
      mThreadCounting = new MThreadCounting();
    }

    @Test
    public void normalBehavior() {
        student.setGroupNumber(240);
        student.setAverageScore(2.2);
        student.setStudentBookNumber(101);
        studentList.add(student);
        assertEquals(1,mThreadCounting.getCounting(studentList,student));
    }

    @Test
    public void nullParameters() {
        assertEquals(0,mThreadCounting.getCounting(studentList,student));
    }

    @Test
    public void firstParameterIsNull() {
        student.setGroupNumber(320);
        student.setAverageScore(2.7);
        student.setStudentBookNumber(102);
        assertEquals(0, mThreadCounting.getCounting(studentList,student));
    }

    @Test
    public void collectionElementFieldIsNull() {
        student.setGroupNumber(null);
        student.setAverageScore(3.5);
        student.setStudentBookNumber(103);
        studentList.add(student);
        assertEquals(0, mThreadCounting.getCounting(studentList,student));
    }

    @Test
    public void secondParameterFieldIsNull() {
        student.setStudentBookNumber(104);
        Student student2 = new Student(202, 3.7, 104);
        studentList.add(student);
        studentList.add(student2);
        assertEquals(0, mThreadCounting.getCounting(studentList,student));
    }
}
