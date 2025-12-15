package org.example.listfiller;

import org.example.student.Student;
import org.example.utilites.ArrayListLogger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class StrategyFileNS implements Strategy {
    @Override
    public List<Student> fill(Integer size) {

        System.out.println("Заполнить массив данными из файла");
        List<Student> students = new ArrayListLogger<>();
        try(BufferedReader br = new BufferedReader(new FileReader("file1.txt"))){
            String line;
            for(int i = 0; i < size; i++) {
                if ((line = br.readLine()) != null) {
                    String[] parts = line.split(", ");
                    Student student = new Student(Integer.parseInt(parts[0]),
                            Double.parseDouble(parts[1]), Integer.parseInt(parts[2]));
                    students.add(student);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
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

}
