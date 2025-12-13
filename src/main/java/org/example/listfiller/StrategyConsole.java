package org.example.listfiller;

import org.example.student.Student;
import org.example.student.StudentBuilder;
import org.example.utilites.LoggedCollectors;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StrategyConsole implements Strategy {
    @Override
    public List<Student> fill(Integer size) throws Exception {

        // проверка: необходима для тестов
        if(size <= 0){
            throw new Exception("Значение параметра должно быть больше ноля: " + size);
        }

        System.out.println("\nСейчас мы заполним данные студентов");

        List<Student> list = IntStream.range(0,size)
                .mapToObj(i -> {
                    System.out.println("\nСтудент " + (i + 1) + " из " + size);
                    System.out.println("--------------");

                    int groupNumber = setGroupNumber();
                    double averageScore = setAverageScore();
                    int studentBookNumber = setStudentBookNumber();

                    Student student = new StudentBuilder()
                            .setGroupNumber(groupNumber)
                            .setAverageScore(averageScore)
                            .setStudentBookNumber(studentBookNumber)
                            .build();
                    return student;
                }).collect(LoggedCollectors.toLoggedList());

        System.out.println("Готово!");
        System.out.println("-------");
        System.out.println("Мы заполнили следующие данные студентов:");

        checkList(list);

        return list;
    }

    public int setGroupNumber() {
        int groupNumber = 0;

        while(true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите номер группы:");
            System.out.println("(целые числа от 100 до 400)");

            // проверка: пользователь должен ввести число, а не буквы
            if (scanner.hasNextInt()) {
                groupNumber = scanner.nextInt();
            } else {
                System.out.println("<!> Введённые данные не соответствуют условию");
                System.out.println("---------------------------------------------");
            }

            if (groupNumber >= 100 && groupNumber <= 400){
                break;
            }
        }
        return groupNumber;
    }

    public double setAverageScore() {
        double averageScore = 0;

        while(true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите средний балл студента:");
            System.out.println("(от 2,0 до 5,0)");

            // проверка: пользователь должен ввести число, а не буквы
            if (scanner.hasNextDouble()) {
                // меняем знак разделителя дроби, чтобы не возникала ошибка
//              // из-зи знака, который не соответствует Locale сканнера
                String input = String.valueOf(scanner.nextDouble());
                averageScore = Double.parseDouble(input.replace(",","."));

                // оставляем два знака после запятой
                averageScore = Math.ceil(averageScore * 100) / 100;
            } else {
                scanner.nextLine();
                System.out.println("<!> Данные не соответствуют условию");
                System.out.println("-----------------------------------");
            }

            if (averageScore >= 2 && averageScore <= 5) {
                break;
            }
        }
        return averageScore;
    }

    public int setStudentBookNumber() {
        int studentBookNumber = 0;

        while(true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите номер зачётной книжки:");
            System.out.println("(числа от 10000000 до 99999999)");

            // проверка: пользователь должен ввести число, а не буквы
            if (scanner.hasNextInt()) {
                studentBookNumber = scanner.nextInt();
            } else {
                scanner.nextLine();
                System.out.println("<!> Введённые данные не соответствуют условию");
                System.out.println("---------------------------------------------");
            }

            if (studentBookNumber >= 10000000 && studentBookNumber <= 99999999) {
                break;
            }
        }
        return studentBookNumber;
    }

    public void checkList(List<Student> list) {
        for(int i = 0; i < list.size(); i++) {
            System.out.println("• Cтудент " + (i + 1) + ":" +
                    " группа " + list.get(i).getGroupNumber() +
                    ", средний балл - " + list.get(i).getAverageScore() +
                    ", зачётная книжка №" + list.get(i).getStudentBookNumber());
        }
    }
}