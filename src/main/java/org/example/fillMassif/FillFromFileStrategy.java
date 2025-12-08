package org.example.fillMassif;

import org.example.student.Student;
import org.example.student.StudentBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FillFromFileStrategy implements FillStrategy {
    @Override
    public ArrayList<Student> fill(Integer size) {
        if (size > 20 || size <= 0) {
            System.out.println("В файле отсутствует заданное количество студентов!");
            return new ArrayList<>();
        }

        System.out.println("Заполнить массив данными из файла");
        ArrayList<Student> listStudents = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("file1.txt"))) {
            String line;
            for (int i = 0; i < size; i++) {
                if ((line = br.readLine()) != null) {
                    String[] parts = line.split(", ");
                    Student student = new StudentBuilder()
                            .setGroupNumber(Integer.parseInt(parts[0]))
                            .setAverageScore(Double.parseDouble(parts[1]))
                            .setStudentBookNumber(Integer.parseInt(parts[2]))
                            .build();
                    listStudents.add(student);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listStudents;
    }

}
