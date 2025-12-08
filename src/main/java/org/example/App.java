package org.example;

import org.example.FillMassif.FillMassifRandom;
import org.example.FillMassif.Massif;
import org.example.mthreadcounting.MThreadCounting;
import org.example.student.Student;

import java.util.List;

public class App {
    public static void main( String[] args ) {
        //Заполнение массива через консоль
        //Massif fillMassif = new FillMassifConsole();
        //List<Student> list = fillMassif.fill(2);


        //Рандомное заполнение массива
        Massif fillMassif = new FillMassifRandom();
        List<Student> list = fillMassif.fill(5);
        for(int i = 0; i < list.size(); i++){
            System.out.println(list.get(i));
        }

        //Заполнение массива через файл
        //Massif fillMassif = new FillMassifFromFile();
        //List<Student> list = fillMassif.fill(8);
        //for(int i = 0; i < list.size(); i++){
        //    System.out.println(list.get(i));
        //}

        // MThreadCounting
        Student studentForSearch = new Student(341, 2.27, 70287439);
        MThreadCounting mThreadCounting = new MThreadCounting();
        mThreadCounting.getCounting(list, studentForSearch);
    }
}
