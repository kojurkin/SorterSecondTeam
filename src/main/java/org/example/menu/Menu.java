package org.example.menu;

import org.example.listfiller.ListFiller;
import org.example.listfiller.ListFillerConsole;
import org.example.listfiller.ListFillerFile;
import org.example.listfiller.ListFillerRandom;
import org.example.mthreadcounting.MThreadCounting;
import org.example.sorter.BasicClientSorter;
import org.example.sorter.ChainSorter;
import org.example.sorter.ChainSorterFactory;
import org.example.sorter.ClientFieldName;
import org.example.sorter.ClientSorterEven;
import org.example.student.Student;
import org.example.student.StudentBuilder;
import org.example.utilites.ArrayListLogger;
import org.example.utilites.Exporter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private Scanner scanner = new Scanner(System.in);
    private List<Student> students = new ArrayListLogger<>();
    private boolean isSorted = false;

    public void mainMenu() throws Exception {
        while (true) {
            System.out.println("--Главное меню--\n  1. Создать массив.\n  2. Выбрать режим сортировки.\n  3. Записать результат в файл.\n  4. Подсчитать количество вхождений.\n  0. Выход.");
            int input = readIntInput(4);
            if (input == 0) {
                System.out.println("Программа завершает работу.");
                scanner.close();
                break;
            }
            switch (input) {
                case 1 -> createArray();
                case 2 -> whichSort();
                case 3 -> printToFile();
                case 4 -> occurrencesCount();
            }
        }
    }

    private int readIntInput(int max) {
        while (true) {
            String inputStr = scanner.nextLine().trim();
            try {
                int input = Integer.parseInt(inputStr);
                if (input >= 0 && input <= max) {
                    return input;
                }
            } catch (NumberFormatException e) {
            }
            System.out.println("Ошибка: Введены некорректные данные, пожалуйста, введите цифру от " + 0 + " до " + max + ".");
        }
    }

    private int howMuchLinesInFile() {
        int lineCount = 0;
        try(BufferedReader br = new BufferedReader(new FileReader("file1.txt"))){
            while(br.readLine() != null) {
                lineCount++;
            }
        }catch (IOException e) {
            System.out.println("Ошибка: " + Arrays.toString(e.getStackTrace()));
        }
        return lineCount;
    }

    public void createArray() throws Exception {
        int size;
        while (true) {
            System.out.println("--Создание массива--\n  Введите желаемый размер массива.");
            String inputStr = scanner.nextLine().trim();
            try {
                size = Integer.parseInt(inputStr);
                if (size >= 1) {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Введены некорректные данные, пожалуйста, введите число больше 0.");
            }

        }
        while (true) {
            System.out.println("--Создание массива--\n  1. Заполнить массив из файла.\n  2. Заполнить массив случайными данными.\n  3. Заполнить массив вручную.\n  0. Выйти в главное меню.");
            int input = readIntInput(3);
            if (input == 0) {
                break;
            }
            ListFiller listFiller;
            switch (input) {
                case 1:
                    System.out.println("Заполнение массива из файла...");
                    int lineCount = howMuchLinesInFile();
                    System.out.println("В файле сейчас: " + lineCount);
                    while (size > lineCount || size < 1) {
                        System.out.println("Ошибка: Желаемый размер (" + size + ") не входит в диапозон (1, " + lineCount + "). Введите новый размер: ");
                        String newInputStr = scanner.nextLine().trim();
                        try {
                            size = Integer.parseInt(newInputStr);
                        } catch (NumberFormatException e) {
                            System.out.println("Ошибка: Введены некорректные данные.");
                        }
                    }
                    listFiller = new ListFillerFile();
                    students = listFiller.fill(size);
                    break;
                case 2:
                    System.out.println("Заполнение массива случайными данными...");
                    listFiller = new ListFillerRandom();
                    students = listFiller.fill(size);
                    break;
                case 3:
                    System.out.println("Заполнение массива вручную...");
                    listFiller = new ListFillerConsole();
                    students = listFiller.fill(size);
                    break;
                default:
                    continue;
            }
            printStudents();
        }
    }

    public void whichSort() {
        if (students.isEmpty()) {
            System.out.println("Ошибка: Массив студентов пуст. Сначала создайте массив.");
            return;
        }
        while (true) {
            System.out.println("--Выбор режима сортировки--\n  1. Отсортировать по всем трём полям.\n  2. Отсортировать четные значения одного из полей.\n  0. Выйти в главное меню.");
            int input = readIntInput(2);
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
                    int fieldInput = readIntInput(2);
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
                        students = evenSorter.sort(students,
                                ClientFieldName.GroupNumber,
                                ClientFieldName.AverageScore,
                                ClientFieldName.StudentBookNumber);
                        isSorted = true;
                        printStudents();
                    } catch (IllegalArgumentException e) {
                        System.out.println("Ошибка: " + e.getMessage() + ". Выберите другое поле.");
                    }
                    break;
            }
        }
    }

    public void printToFile() {
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

    public void occurrencesCount() {
        System.out.println("--Подсчет количества вхождений--");
        if (students.isEmpty()) {
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
        }
    }

    private void printStudents() {
        if (students.isEmpty()) {
            System.out.println("Массив пуст.");
            return;
        }
        System.out.println("Текущий массив студентов:");
        for (Student s : students) {
            System.out.println(s);
        }
    }

    /**
     * На данный момент неиспользуется, нужен для нестатичной реализации Меню
     * @throws Exception
     */
    /*public static void init() throws Exception {
        mainMenu();
    }*/
}