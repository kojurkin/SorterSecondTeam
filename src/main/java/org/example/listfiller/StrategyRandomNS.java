package org.example.listfiller;

import org.example.student.Student;
import org.example.student.StudentBuilder;
import org.example.utilites.ArrayListLogger;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class StrategyRandomNS implements Strategy {
    @Override
    public List<Student> fill(Integer size) {
        if(size <= 0){
            System.out.println("Введено не верное значение!");
            return new ArrayListLogger<>();
        }

        System.out.println("Заполняем массив рандомными данными");
        List<Student> students = new ArrayListLogger<>();
        for (int i = 0; i < size; i++){
            Student student = new StudentBuilder()
                    .setGroupNumber(randomGroupNumber())
                    .setAverageScore(randomAverageScore())
                    .setStudentBookNumber(randomStudentBookNumber())
                    .build();
            students.add(student);
        }
        System.out.println("Готово!\n-------\nМы ввели следующие данные:");
        for (int i = 0; i < students.size(); i++) {
            System.out.println("• Cтудент " + (i + 1) + ":" +
                    " группа " + students.get(i).getGroupNumber() +
                    ", средний балл - " + students.get(i).getAverageScore() +
                    ", зачётная книжка №" + students.get(i).getStudentBookNumber());
        }
        return students;
    }

    public Integer randomGroupNumber(){
        int minValue = 100;
        int maxValue = 400;
        return ThreadLocalRandom.current().nextInt(minValue,maxValue);
    }

    public Integer randomStudentBookNumber(){
        int minValue = 10000000;
        int maxValue = 99999999;
        return ThreadLocalRandom.current().nextInt(minValue,maxValue);
    }

    public Double randomAverageScore(){
        double minValue = 2.0;
        double maxValue = 5.0;

        double grade = ThreadLocalRandom.current().nextDouble(minValue,maxValue);
        BigDecimal bd = BigDecimal.valueOf(grade)
                .setScale(2, RoundingMode.CEILING);
        return bd.doubleValue();
    }
}
