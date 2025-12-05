package org.example.FillMassif;

import org.example.FillMassif.FillStrategy;
import org.example.Student;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FillFromFileStrategy implements FillStrategy {
    @Override
    public ArrayList<Student> fill(Integer size) {
        if(size > 20 || size <= 0){
            System.out.println("В файле отсутствует заданное количество студентов!");
            return new ArrayList<>();
        }

        System.out.println("Заполнить массив данными из файла");
        ArrayList<Student> listStudents = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader("file1.txt"))){
            String line;
            for(int i = 0; i < size; i++) {
                if ((line = br.readLine()) != null) {
                    String[] parts = line.split(", ");
                    Student student = new Student(Integer.parseInt(parts[0]),
                            Double.parseDouble(parts[1]), Integer.parseInt(parts[2]));
                    listStudents.add(student);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return listStudents;
    }

}
