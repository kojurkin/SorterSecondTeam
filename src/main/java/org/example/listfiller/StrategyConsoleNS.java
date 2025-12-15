package org.example.listfiller;

import org.example.student.Student;
import org.example.student.StudentBuilder;
import org.example.utilites.ArrayListLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class StrategyConsoleNS implements Strategy {
    @Override
    public List<Student> fill(Integer size) {
        System.out.println("Заполнить массив данными из консоли");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            List<Student> students = new ArrayListLogger<>();

            while (students.size() < size) {
                try {
                    System.out.println("\nСтудент " + (students.size() + 1) + " из " + size + "\n--------------");

                    System.out.println("Введите номер группы (0 для выхода):");
                    String groupInput = br.readLine();
                    Integer numberGroup = Integer.parseInt(groupInput);

                    if (numberGroup == 0) {
                        System.out.println("Массив не был создан, программа завершает работу");
                        return new ArrayListLogger<>();
                    }

                    System.out.println("Введите средний балл:");
                    String scoreInput = br.readLine().replace(",", ".");
                    Double averageScore = Double.parseDouble(scoreInput);
                    averageScore = Math.ceil(averageScore * 100) / 100;

                    System.out.println("Введите номер зачетной книжки:");
                    String bookInput = br.readLine();
                    Integer studentBookNumber = Integer.parseInt(bookInput);

                    if (numberGroup >= 100 && numberGroup <= 400 &&
                            averageScore >= 2.0 && averageScore <= 5.0 &&
                            studentBookNumber >= 10000000 && studentBookNumber <= 99999999) {

                        Student student = new StudentBuilder()
                                .setGroupNumber(numberGroup)
                                .setAverageScore(averageScore)
                                .setStudentBookNumber(studentBookNumber)
                                .build();
                        students.add(student);
                    } else {
                        System.out.println("Введенные значения не верны! Студент не может быть создан!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("<!> Данные не соответствуют условию\n-----------------------------------\n  Введенный студент не был создан");
                }
            }

            System.out.println("Готово!\n-------\nМы ввели следующие данные:");
            for (int i = 0; i < students.size(); i++) {
                System.out.println("• Cтудент " + (i + 1) + ":" +
                        " группа " + students.get(i).getGroupNumber() +
                        ", средний балл - " + students.get(i).getAverageScore() +
                        ", зачётная книжка №" + students.get(i).getStudentBookNumber());
            }

            return students;
        } catch (IOException e) {
            System.out.println("Ошибка ввода: " + e.getMessage());
        }
        return new ArrayListLogger<>();
    }
}