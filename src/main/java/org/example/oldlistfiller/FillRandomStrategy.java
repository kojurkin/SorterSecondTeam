package org.example.oldlistfiller;

import org.example.student.Student;
import org.example.student.StudentBuilder;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class FillRandomStrategy implements FillStrategy {
    @Override
    public ArrayList<Student> fill(Integer size) {
        if(size <= 0){
            System.out.println("Введено не верное значение!");
            return new ArrayList<>();
        }

        System.out.println("Заполняем массив рандомными данными");
        ArrayList<Student> list = new ArrayList<>();
        for (int i = 0; i < size; i++){
            Student student = new StudentBuilder()
                    .setGroupNumber(randomGroupNumber())
                    .setAverageScore(randomAverageScore())
                    .setStudentBookNumber(randomStudentBookNumber())
                    .build();
            list.add(student);
        }
        return list;
    }


    ///////////////////////////////////////
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
