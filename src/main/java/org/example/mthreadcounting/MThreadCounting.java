package org.example.mthreadcounting;

import org.example.student.Student;

import java.util.List;
import java.util.concurrent.*;

public class MThreadCounting {
    private int coincidences = 0;

    public int getCounting(List<Student> list, Student studentForSearch) {

        ExecutorService executor = Executors.newFixedThreadPool(3);
        Future future = null;

        for (int i = 0; i < list.size(); i++) {
            int index = i;

//            проверка элемента коллекции на null, нужно для тестов
            if(list.get(index).getGroupNumber() == null
                    | list.get(index).getAverageScore() == null
                    | list.get(index).getStudentBookNumber() == null) {
                break;
            }

            future = executor.submit(() -> {
//                проверка работоспособности тредов
//                System.out.println("Выполняется задача " + index + " в потоке " + Thread.currentThread().getName());

                   if (list.get(index).equals(studentForSearch)) {
                       coincidences++;
                   }
            });
        }
            executor.shutdown();

//        необходим, чтобы main thread дождался выполнения всех остальных тредов
//        и не завершился раньше
        if(future != null) {
            try {
                future.get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Обнаружено совпадений: " + coincidences);
        return coincidences;
    }
}
