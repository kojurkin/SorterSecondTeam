package org.example.listfiller;

import org.example.student.Student;
import org.example.student.StudentBuilder;

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
                    System.out.println("\nСтудент " + (i + 1) + " из " + size +"\n--------------");

                    int groupNumber = setGroupNumber();
                    double averageScore = setAverageScore();
                    int studentBookNumber = setStudentBookNumber();

                    Student student = new StudentBuilder()
                            .setGroupNumber(groupNumber)
                            .setAverageScore(averageScore)
                            .setStudentBookNumber(studentBookNumber)
                            .build();
                    return student;
                }).collect(Collectors.toList());

        System.out.println("Готово!\n-------\nМы ввели следующие данные:");

        checkList(list);

        return new ArrayList<>();
    }

    // проверка: пользователь должен ввести целое числов, а не строку
    public int parseToInt(Scanner scanner) {
        return (scanner.hasNextInt()) ? scanner.nextInt() : 0;
    }

    public int setGroupNumber() {
        int groupNumber = 0;

        while(true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите номер группы:\n(целые числа от 100 до 400)");

            groupNumber = parseToInt(scanner);

            if (groupNumber >= 100 && groupNumber <= 400) {
                break;
            } else {
                System.out.println("<!> Данные не соответствуют условию\n-----------------------------------");
            }
        }
        return groupNumber;
    }

    // проверка: пользователь должен ввести число, а не строку
    public Double parseToDouble(String input) {
        if(input == null || input.trim().isEmpty()) return 0.0;
        try{
            // меняем знак разделителя дроби, чтобы не возникала ошибка
            // из-за знака, который не соответствует Locale сканнера
            double value = Double.parseDouble(input.replace(",","."));

            // оставляем два знака после запятой
            value = Math.ceil(value * 100) / 100;

            return value;
        } catch (NumberFormatException e){
            return 0.0;
        }
    }

    public double setAverageScore() {
        double averageScore = 0;

        while(true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите средний балл студента:\n(от 2,0 до 5,0)");

            String input = scanner.nextLine();
            averageScore = parseToDouble(input);

            if (averageScore >= 2 && averageScore <= 5) {
                break;
            } else {
                System.out.println("<!> Данные не соответствуют условию\n-----------------------------------");
            }
        }
        return averageScore;
    }

    public int setStudentBookNumber() {
        int studentBookNumber = 0;

        while(true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите номер зачётной книжки:\n(числа от 10000000 до 99999999)");

            studentBookNumber = parseToInt(scanner);

            if (studentBookNumber >= 10000000 && studentBookNumber <= 99999999) {
                break;
            } else {
                scanner.nextLine();
                System.out.println("<!> Данные не соответствуют условию\n-----------------------------------");
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