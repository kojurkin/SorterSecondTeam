package org.example.utilites;

import org.example.student.Student;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class Exporter {
    public static String textFilePath = "result.txt";

    public static void exportStudentsListToTextFile(List<Student> students, String filePath) {
        if (filePath.isBlank()) {
            filePath = textFilePath;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Student student : students) {
                writer.write(student.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при сохранении файла: " + filePath, e);
        }
    }

    public static int clearFile() {
        String inputFilePath = "file1.txt";
        List<String> correctLines = new ArrayListLogger<>();
//        List<String> wrongLines = new ArrayListLogger<>();
        try(BufferedReader br = new BufferedReader(new FileReader(inputFilePath))){
            String line;
            while((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    try {
                        int groupNumber = Integer.parseInt(parts[0].trim());
                        double averageScore = Double.parseDouble(parts[1].trim().replace(",", "."));
                        int studentBookNumber = Integer.parseInt(parts[2].trim());

                        if (groupNumber >= 100 && groupNumber <= 400 &&
                                averageScore >= 2.0 && averageScore <= 5.0 &&
                                studentBookNumber >= 10000000 && studentBookNumber <= 99999999) {

                            correctLines.add(line);
                        } //else {
//                            wrongLines.add(line);
//                        }
                    } catch (NumberFormatException e) {
                    //    wrongLines.add(line);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка: " + Arrays.toString(e.getStackTrace()));
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("file1.txt"))) {
            for (String correctLine : correctLines) {
                bw.write(correctLine);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + Arrays.toString(e.getStackTrace()));
        }
        System.out.println("--Файл был очищен--");
/*        System.out.println("- WrongLines in file -");
        for(int i = 0; i < wrongLines.size(); i++){
            System.out.println(wrongLines.get(i));
        }*/
        return correctLines.size();
    }
}
