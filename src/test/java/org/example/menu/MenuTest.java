package org.example.menu;

import org.example.student.Student;
import org.example.student.StudentBuilder;
import org.example.utilites.ArrayListLogger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {
    private Menu menu;
    private ByteArrayOutputStream outputStreamCaptor;
    private PrintStream originalOut;
    private InputStream originalIn;

    @BeforeEach
    void setUp() {
        outputStreamCaptor = new ByteArrayOutputStream();
        originalOut = System.out;
        originalIn = System.in;
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    private void setInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    private void setPrivateField(Object obj, String fieldName, Object value) throws Exception {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(obj, value);
    }

    private Object invokePrivateMethod(Object obj, String methodName, Class<?>[] paramTypes, Object... args) throws Exception {
        Method method = obj.getClass().getDeclaredMethod(methodName, paramTypes);
        method.setAccessible(true);
        return method.invoke(obj, args);
    }

    @Test
    void MainMenuWrongThenCorrectInput() throws Exception {
        setInput("invalid\n5\n-1\n\n\n1\n1\n0\n0\n");
        menu = new Menu();
        menu.mainMenu();
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Ошибка: Введены некорректные данные"));
        assertTrue(output.contains("Программа завершает работу."));
    }

    @Test
    void ReadIntInputWrongThenCorrectInput() throws Exception {
        setInput("invalid\n5\n-1\n\n\n3\n");
        menu = new Menu();
        // Используем рефлексию с правильными типами параметров
        int result = (int) invokePrivateMethod(menu, "readIntInput",
                new Class<?>[]{int.class}, 4);
        assertEquals(3, result);
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Ошибка: Введены некорректные данные"));
    }

    @Test
    void CreateArrayWrongThenCorrectInput() throws Exception {
        setInput("invalid\n-1\n0\n\n\n5\n0\n");
        menu = new Menu();
        menu.createArray();
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Ошибка: Введены некорректные данные"));
    }

    @Test
    void WhichSortWrongThenCorrectInput() throws Exception {
        setInput("invalid\n3\n\n\n2\n1\n0\n");
        menu = new Menu();
        List<Student> studentsList = new ArrayListLogger<>();
        studentsList.add(new StudentBuilder()
                .setGroupNumber(100)
                .setAverageScore(4.0)
                .setStudentBookNumber(10000000)
                .build());
        setPrivateField(menu, "students", studentsList);
        menu.whichSort();
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Ошибка: Введены некорректные данные"));
        assertTrue(output.contains("Сортировка четных значений одного из полей"));
    }

    @Test
    void OccurrencesCountWrongThenCorrectInput() throws Exception {
        setInput("invalid\n4.0\n10000000\n100\ninvalid\n10000000\n100\n4.0\ninvalid\n100\n4.0\n10000000\n0\n");
        menu = new Menu();
        List<Student> studentsList = new ArrayListLogger<>();
        studentsList.add(new StudentBuilder()
                .setGroupNumber(100)
                .setAverageScore(4.0)
                .setStudentBookNumber(10000000)
                .build());
        setPrivateField(menu, "students", studentsList);
        menu.occurrencesCount();
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Ошибка: Введены некорректные данные."));
        assertTrue(output.contains("Количество вхождений студента: 1"));
    }

    @Test
    void PrintStudentsWithData() throws Exception {
        menu = new Menu();
        List<Student> studentsList = new ArrayListLogger<>();
        studentsList.add(new StudentBuilder()
                .setGroupNumber(100)
                .setAverageScore(4.0)
                .setStudentBookNumber(10000000)
                .build());
        setPrivateField(menu, "students", studentsList);
        // Используем рефлексию без параметров
        invokePrivateMethod(menu, "printStudents", new Class<?>[0]);
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Текущий массив студентов:"));
    }
}