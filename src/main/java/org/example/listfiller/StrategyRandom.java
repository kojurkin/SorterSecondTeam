package org.example.listfiller;

import org.example.student.Student;
import org.example.student.StudentBuilder;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StrategyRandom implements Strategy {
    @Override
    public List<Student> fill(Integer size) throws Exception {
        // проверка: необходима для тестов
        if(size <= 0){
            throw new Exception("Значение параметра должно быть больше ноля: " + size);
        }

        /*
        // доп. возможность получить размер листа из консоли
        System.out.println("\nСейчас мы заполним лист случайными значениями");
        System.out.println("\nКакому числу студентов нужно сгенерировать значения?");
        Scanner scanner = new Scanner(System.in);
        int iterations = setIterations(scanner)
        ;*/

        List<Student> list = IntStream.range(0,size)
                .mapToObj(i -> {
                    Random random = new Random();
                    int groupNumber = random.nextInt(100, 400);
                    double averageScore = random.nextDouble(2.0, 5.0);
                    int studentBookNumber = random.nextInt(10000000, 99999999);

                    // оставляем два знака после запятой
                    averageScore = Math.ceil(averageScore * 100) / 100;

                    Student student = new StudentBuilder()
                            .setGroupNumber(groupNumber)
                            .setAverageScore(averageScore)
                            .setStudentBookNumber(studentBookNumber)
                            .build();

                    return student;
                }).collect(Collectors.toList());

        System.out.println("Готово!");
        System.out.println("-------");

        checkList(list);

        return list;
    }

    public void checkList(List<Student> list) {
        System.out.println("Мы сгенерировали следующие данные для студентов:");
        for(int i = 0; i < list.size(); i++) {
            System.out.println("• Cтудент " + (i + 1) + ":" +
                    " группа " + list.get(i).getGroupNumber() +
                    ", средний балл - " + list.get(i).getAverageScore() +
                    ", зачётная книжка №" + list.get(i).getStudentBookNumber());
        }
    }

    /*
    // доп. возможность получить размер листа из консоли
    public int setIterations(Scanner scanner) {
        int quantity = 0;

        while (true) {
            System.out.println("Введите количество студентов:");

            // проверка: пользователь должен ввести число, а не буквы
            if (scanner.hasNextInt()) {
                quantity = scanner.nextInt();
            } else {
                scanner.nextLine();
                System.out.println("<!> Нужно ввести число");
                System.out.println("----------------------");
                continue;
            }

            // проверка: введённое число должно быть > 0
            if (quantity <= 0) {
                scanner.nextLine();
                System.out.println("<!> Студентов должно быть больше");
                System.out.println("--------------------------------");
            } else {
                return quantity;
            }
        }
    }
     */
}
