package org.example.fillmassif;

import org.example.student.Student;
import org.example.student.StudentBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FillConsoleStrategy implements FillStrategy {
    @Override
    public ArrayList<Student> fill(Integer size) {
        System.out.println("Заполнить массив данными из консоли");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            //System.out.print("Введите размер массива: ");
            //int size = Integer.parseInt(br.readLine()); // Считываем размер массива

            ArrayList<Student> students = new ArrayList<>();
            System.out.println("Введите элементы массива:");
            for (int i = 0; i < size; i++) {
                System.out.print("Студент " + (i + 1) + ": ");
                System.out.println("Введите номер группу: ");

                Integer numberGroup = Integer.parseInt(br.readLine());

                System.out.println("Введите средний балл: ");
                Double averageScore = Double.parseDouble(br.readLine());

                System.out.println("Введите номер зачетной книжки: ");
                Integer studentBookNumber = Integer.parseInt(br.readLine());

                if (numberGroup > 0 && averageScore > 0.5 && averageScore <= 5) {
                    Student student = new StudentBuilder()
                            .setGroupNumber(numberGroup)
                            .setAverageScore(averageScore)
                            .setStudentBookNumber(studentBookNumber)
                            .build();
                    students.add(student);
                } else {
                    System.out.println("Введенные значения не верны! Студент не может быть создан! \n" +
                            "======================================================================");
                }
            }
            System.out.println("Введённый массив:");
            for (Student num : students) {
                System.out.println(num);
            }
            return students;
        } catch (IOException e) {
            System.out.println("Ошибка ввода: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат числа: " + e.getMessage());
        }
        return new ArrayList<>();
    }

}