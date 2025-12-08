package org.example.student;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {

    @Test
    public void testStudentBuilderSuccess() {
        Student student = new StudentBuilder()
                .setGroupNumber(101)
                .setAverageScore(4.5)
                .setStudentBookNumber(12345)
                .build();

        assertEquals(101, student.getGroupNumber());
        assertEquals(4.5, student.getAverageScore());
        assertEquals(12345, student.getStudentBookNumber());
    }

    @Test
    public void testInvalidGroupNumber() {
        assertThrows(IllegalArgumentException.class, () ->
                new StudentBuilder()
                        .setGroupNumber(0)
        );
    }

    @Test
    public void testInvalidAverageScoreTooLow() {
        assertThrows(IllegalArgumentException.class, () ->
                new StudentBuilder()
                        .setAverageScore(-1.0)
        );
    }

    @Test
    public void testInvalidAverageGradeTooHigh() {
        assertThrows(IllegalArgumentException.class, () ->
                new StudentBuilder()
                        .setAverageScore(6.0)
        );
    }

    @Test
    public void testInvalidRecordBookNumber() {
        assertThrows(IllegalArgumentException.class, () ->
                new StudentBuilder()
                        .setStudentBookNumber(0)
        );
    }

    @Test
    public void testBuildWithoutRequiredStudentBookNumber() {
        StudentBuilder builder = new StudentBuilder()
                .setGroupNumber(101)
                .setAverageScore(4.5);

        assertThrows(IllegalStateException.class, builder::build);
    }

    @Test
    public void testBuildWithoutRequiredGroupNumber() {
        StudentBuilder builder = new StudentBuilder()
                .setAverageScore(4.5)
                .setStudentBookNumber(12345);

        assertThrows(IllegalStateException.class, builder::build);
    }

    @Test
    public void testBuildWithoutRequiredAverageScore() {
        StudentBuilder builder = new StudentBuilder()
                .setGroupNumber(101)
                .setStudentBookNumber(12345);

        assertThrows(IllegalStateException.class, builder::build);
    }
}
