package org.example.utilites;

import org.example.student.Student;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Exporter {
    public static String textFilePath = "result.txt";

    public static void exportStudentsListToTextFile(ArrayList<Student> students, String filePath) {
        if (filePath.isBlank()) {
            filePath = textFilePath;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Student student : students) {
                writer.write(student.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при сохранении файла: " + filePath, e);
        }
    }
}
