package org.example.menu;

import org.example.fillMassif.FillMassifConsole;
import org.example.fillMassif.FillMassifFromFile;
import org.example.fillMassif.FillMassifRandom;
import org.example.fillMassif.Massif;
import org.example.sorter.BasicClientSorter;
import org.example.sorter.ChainSorter;
import org.example.sorter.ChainSorterFactory;
import org.example.sorter.ClientFieldName;
import org.example.sorter.ClientSorterEven;
import org.example.student.Student;
import org.example.student.StudentBuilder;
import org.example.utilites.Exporter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Student> students = new ArrayList<>();
    private static boolean isSorted = false;

    public static void mainMenu() {
        while (true) {
            System.out.println("--Главное меню--\n  1. Создать массив.\n  2. Выбрать режим сортировки.\n  3. Записать результат в файл.\n  4. Подсчитать количество вхождений.\n  0. Выход.");
            int input = readIntInput(0, 4);
            if (input == 0) {
                System.out.println("Программа завершает работу.");
                scanner.close();
                break;
            }
            switch (input) {
                case 1:
                    createArray();
                    break;
                case 2:
                    whichSort();
                    break;
                case 3:
                    printToFile();
                    break;
                case 4:
                    occurrencesCount();
                    break;
            }
        }
    }

    private static int readIntInput(int min, int max) {
        while (true) {
            String inputStr = scanner.nextLine().trim();
            try {
                int input = Integer.parseInt(inputStr);
                if (input >= min && input <= max) {
                    return input;
                }
            } catch (NumberFormatException e) {
            }
            System.out.println("Ошибка: Введены некорректные данные, пожалуйста, введите цифру от " + min + " до " + max + ".");
        }
    }

    private static int howMuchLinesInFile() {
        int lineCount = 0;
        try(BufferedReader br = new BufferedReader(new FileReader("file1.txt"))){
            while(br.readLine() != null) {
                lineCount++;
            }
        }catch (IOException e) {
            System.out.println("Ошибка: " + e.getStackTrace());
        }
        return lineCount;
    }

    public static void createArray() {
        int size;
        while (true) {
            System.out.println("--Создание массива--\n  Введите желаемый размер массива.");
            String inputStr = scanner.nextLine().trim();
            try {
                size = Integer.parseInt(inputStr);
                if (size >= 0) {
                    break;
                }
            } catch (NumberFormatException e) {
            }
            System.out.println("Ошибка: Введены некорректные данные, пожалуйста, введите неотрицательное число.");
        }
        while (true) {
            System.out.println("--Создание массива--\n  1. Заполнить массив из файла.\n  2. Заполнить массив случайными данными.\n  3. Заполнить массив вручную.\n  0. Выйти в главное меню.");
            int input = readIntInput(0, 3);
            if (input == 0) {
                break;
            }
            Massif fillMassif;
            switch (input) {
                case 1:
                    System.out.println("Заполнение массива из файла...");
                    int lineCount = howMuchLinesInFile();
                    System.out.println("В файле сейчас: " + lineCount);
                    while (size > lineCount) {
                        System.out.println("Ошибка: Желаемый размер (" + size + ") больше количества строк в файле (" + lineCount + "). Введите новый размер (не больше " + lineCount + "):");
                        String newInputStr = scanner.nextLine().trim();
                        try {
                            size = Integer.parseInt(newInputStr);
                            if (size < 0) {
                                System.out.println("Ошибка: Размер должен быть неотрицательным.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Ошибка: Введены некорректные данные, пожалуйста, введите неотрицательное число.");
                        }
                    }
                    fillMassif = new FillMassifFromFile();
                    students = fillMassif.fill(size);
                    break;
                case 2:
                    System.out.println("Заполнение массива случайными данными...");
                    fillMassif = new FillMassifRandom();
                    students = fillMassif.fill(size);
                    break;
                case 3:
                    System.out.println("Заполнение массива вручную...");
                    fillMassif = new FillMassifConsole();
                    students = fillMassif.fill(size);
                    break;
                default:
                    continue;
            }
            printStudents();
        }
    }

    public static void whichSort() {
        if (students.isEmpty()) {
            System.out.println("Ошибка: Массив студентов пуст. Сначала создайте массив.");
            return;
        }
        while (true) {
            System.out.println("--Выбор режима сортировки--\n  1. Отсортировать по всем трём полям.\n  2. Отсортировать четные значения одного из полей.\n  0. Выйти в главное меню.");
            int input = readIntInput(0, 2);
            if (input == 0) {
                break;
            }
            switch (input) {
                case 1:
                    System.out.println("Сортировка по всем трём полям (GroupNumber -> AverageScore -> StudentBookNumber)...");
                    BasicClientSorter sorter = new BasicClientSorter();
                    students = sorter.sort(students,
                            ClientFieldName.GroupNumber,
                            ClientFieldName.AverageScore,
                            ClientFieldName.StudentBookNumber);
                    isSorted = true;
                    printStudents();
                    break;
                case 2:
                    System.out.println("Сортировка четных значений одного из полей...");
                    System.out.println("Выберите поле для сортировки четных значений:\n  1. Номер группы\n  2. Номер зачетной книжки\n  0. Отмена");
                    int fieldInput = readIntInput(0, 2);
                    if (fieldInput == 0) {
                        continue;
                    }
                    ClientFieldName selectedField;
                    switch (fieldInput) {
                        case 1:
                            selectedField = ClientFieldName.GroupNumber;
                            break;
                        case 2:
                            selectedField = ClientFieldName.StudentBookNumber;
                            break;
                        default:
                            System.out.println("Ошибка: Введите цифру от 0 до 2.");
                            continue;
                    }
                    try {
                        ClientSorterEven evenSorter = new ClientSorterEven(selectedField);
                        ChainSorterFactory factory = new ChainSorterFactory();
                        ChainSorter chainSorter = factory.getSorter(
                                ClientFieldName.GroupNumber,
                                ClientFieldName.AverageScore,
                                ClientFieldName.StudentBookNumber
                        );
                        students = evenSorter.sort(students, chainSorter);
                        isSorted = true;
                        printStudents();
                    } catch (IllegalArgumentException e) {
                        System.out.println("Ошибка: " + e.getMessage() + ". Выберите другое поле.");
                    }
                    break;
            }
        }
    }

    public static void printToFile() {
        if(students.isEmpty()) {
            System.out.println("Ошибка: Список студентов не существует! Сначала создайте список студентов.");
            return;
        } else if(!isSorted) {
            System.out.println("Ошибка: Список студентов не отсортирован, для начала отсортируйте его.");
            return;
        }
        System.out.println("--Запись результата в файл--");
        Exporter.exportStudentsListToTextFile(students, "output.txt");
    }

    public static void occurrencesCount() {
        System.out.println("--Подсчет количества вхождений--");
/*        if (students.isEmpty()) {
            System.out.println("Ошибка: Список студентов не существует! Сначала создайте список студентов.");
            return;
        }
        while (true) {
            System.out.println("Введите данные искомого студента (или введите 0 для выхода)");
            System.out.print("Введите номер группы: ");
            String groupStr = scanner.nextLine().trim();
            if (groupStr.equals("0")) {
                break;
            }
            System.out.print("Введите средний балл: ");
            String scoreStr = scanner.nextLine().trim();
            System.out.print("Введите номер зачетной книжки: ");
            String bookStr = scanner.nextLine().trim();

            int numberGroup;
            double averageScore;
            int studentBookNumber;
            try {
                numberGroup = Integer.parseInt(groupStr);
                averageScore = Double.parseDouble(scoreStr);
                studentBookNumber = Integer.parseInt(bookStr);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Введены некорректные данные. Пожалуйста, введите числа.");
                continue;
            }

            if (numberGroup > 0 && averageScore > 0.5 && averageScore <= 5) {
                Student studentForSearch = new StudentBuilder()
                        .setGroupNumber(numberGroup)
                        .setAverageScore(averageScore)
                        .setStudentBookNumber(studentBookNumber)
                        .build();
                MThreadCounting mThreadCounting = new MThreadCounting();
                int count = mThreadCounting.getCounting(students, studentForSearch);
                System.out.println("Количество вхождений студента: " + count);
            } else {
                System.out.println("Введенные значения не верны! Студент не может быть создан!");
            }
        }*/
    }

    private static void printStudents() {
        if (students.isEmpty()) {
            System.out.println("Массив пуст.");
            return;
        }
        System.out.println("Текущий массив студентов:");
        for (Student s : students) {
            System.out.println(s);
        }
    }

    public static void init() {
        mainMenu();
    }
}