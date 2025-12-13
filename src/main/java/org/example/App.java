package org.example;

import org.example.listfiller.ListFiller;
import org.example.listfiller.ListFillerConsole;
import org.example.listfiller.ListFillerFile;
import org.example.listfiller.ListFillerRandom;
import org.example.menu.Menu;
import org.example.student.Student;

import java.util.List;

public class App {
    public static void main( String[] args ) throws Exception {

        // Random
//        ListFiller fillRandom = new ListFillerRandom();
//        List<Student> list = fillRandom.fill(3);

        // Console
//        ListFiller fillConsole = new ListFillerConsole();
//        List<Student> list = fillConsole.fill(2);

        // File
        //ListFiller fillFile = new ListFillerFile();
        // параметр – это количество пропущенных строк от начала списка
        // значение 0 отобразит весь список
        //List<Student> list = fillFile.fill(8);

        Menu.mainMenu();
    }
}
