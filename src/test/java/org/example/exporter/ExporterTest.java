package org.example.exporter;

import org.example.student.Student;
import org.example.student.StudentBuilder;
import org.example.utilites.Exporter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ExporterTest {
    @TempDir
    Path tempDir;

    @Test
    void testExportToFileCreatesFileAndWritesContent() throws IOException {

        ArrayList<Student> students = new ArrayList<>();
        students.add(
                new StudentBuilder().setGroupNumber(101).setAverageScore(4.5).setStudentBookNumber(1001).build()
        );
        students.add(
                new StudentBuilder().setGroupNumber(102).setAverageScore(3.8).setStudentBookNumber(1002).build()
        );

        Path file = tempDir.resolve("students.txt");

        Exporter.exportStudentsListToTextFile(students, file.toString());

        assertTrue(Files.exists(file), "Файл должен быть создан");

        ArrayList<String> lines = (ArrayList<String>) Files.readAllLines(file);

        assertEquals(2, lines.size());
        assertEquals("Студент { Номер группы: 101, Средний балл: 4.5, Номер зачетной книжки: 1001 }", lines.get(0));
        assertEquals("Студент { Номер группы: 102, Средний балл: 3.8, Номер зачетной книжки: 1002 }", lines.get(1));
    }

    @Test
    void testExportEmptyList() throws IOException {

        ArrayList<Student> students = new ArrayList<>();
        Path file = tempDir.resolve("empty.txt");

        Exporter.exportStudentsListToTextFile(students, file.toString());

        assertTrue(Files.exists(file), "Файл должен быть создан");

        ArrayList<String> lines = (ArrayList<String>) Files.readAllLines(file);
        assertTrue(lines.isEmpty(), "Файл должен быть пустым");
    }

    @Test
    void testExporterThrowsExceptionOnInvalidPath() {
        ArrayList<Student> students = new ArrayList<>();
        students.add(
                new StudentBuilder().setGroupNumber(101).setAverageScore(4.5).setStudentBookNumber(1001).build()
        );

        assertThrows(RuntimeException.class, () ->
                Exporter.exportStudentsListToTextFile(students, "/invalid_path/bla_bla_bla/???/file.txt")
        );
    }
}
