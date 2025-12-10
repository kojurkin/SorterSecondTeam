package org.example;

import org.example.listfiller.ListFiller;
import org.example.listfiller.ListFillerRandom;
import org.example.oldlistfiller.FillMassifRandom;
import org.example.oldlistfiller.Massif;
import org.example.student.Student;

import java.util.List;

public class App {
    public static void main( String[] args ) {

        // Random
        ListFiller fillRandom = new ListFillerRandom();
        List<Student> list = fillRandom.fill(5);

        // Console
        //Massif fillMassif = new FillMassifConsole();
        //List<Student> list = fillMassif.fill(2);

        // File
        //Massif fillMassif = new FillMassifFromFile();
        //List<Student> list = fillMassif.fill(8);
        //for(int i = 0; i < list.size(); i++){
        //    System.out.println(list.get(i));
        //}

        // отобразить лист в консоли
        for(Student student : list) {
            System.out.println(student.toString());
        }

        System.out.println("тест");
    }
}
