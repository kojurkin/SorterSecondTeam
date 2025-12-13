package org.example.listfiller;

import org.example.student.Student;
import org.example.student.StudentBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class StrategyFile implements Strategy {
    @Override
    public List<Student> fill(Integer size) throws IOException {

        // проверка: необходима для тестов
        if(size <= 0){
            throw new IOException("Значение параметра должно быть больше ноля: " + size);
        }

        List<Student> list = Files.lines(Paths.get("file1.txt"))
                // игнорируем пустые строки
                .filter(line -> !line.isEmpty())
                .map(line -> {
                    String[] stringArray = line.split(",");

                    // удаляем пробелы у значений
                    for(int i = 0; i < stringArray.length; i++) {
                        stringArray[i] = stringArray[i].trim();
                    }

                    Student student = new StudentBuilder()
                            .setGroupNumber(Integer.valueOf(stringArray[0]))
                            .setAverageScore(Double.valueOf(stringArray[1]))
                            .setStudentBookNumber(Integer.valueOf(stringArray[2]))
                            .build();
                    return student;
                })
                // пропускает строки вначале списка
                // значение 0 отобразит весь список
                .limit(size)
                .collect(Collectors.toList());

        System.out.println("Мы получили из файла следующие данные:");
        checkList(list);

        return list;
    }

    public void checkList(List<Student> list) {
        for(int i = 0; i < list.size(); i++) {
            System.out.println("• Cтудент " + (i + 1) + ":" +
                    " группа " + list.get(i).getGroupNumber() +
                    ", средний балл - " + list.get(i).getAverageScore() +
                    ", зачётная книжка №" + list.get(i).getStudentBookNumber());
        }
    }
}
