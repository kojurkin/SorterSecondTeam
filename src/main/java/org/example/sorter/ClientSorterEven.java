package org.example.sorter;

import org.example.Student;
import org.jspecify.annotations.NonNull;

import java.util.*;

public class ClientSorterEven extends ADefaultSorter {
    private final BasicClientSorter basicClientSorter = new BasicClientSorter();
    private final DataValidator dataValidator = new DataValidator();
    private final ClientFieldName filteredField;

    /**
     * Вариант реализации BasicClientSorter, однако меняет порядок только для элементов с четным полем
     *
     * @param filteredField какое поле смотреть для определения четности
     */
    public ClientSorterEven(ClientFieldName filteredField) {
        this.filteredField = filteredField;
    }

    /**
     * Вариант реализации BasicClientSorter, однако меняет порядок только для элементов с четным полем.
     * Сортирует указанный список.
     *
     * @param studentList список, который надо отсортировать
     * @return Возвращает отсортированный список
     */
    public List<Student> sort(@NonNull List<Student> studentList, @NonNull ChainSorter chainSorter) {
        if (dataValidator.isNotNeedToSort(studentList)) {
            return studentList;
        }
        dataValidator.validateArrAndElementNotNull("studentList: ", studentList.toArray());
        List<Student> evenList = studentList.stream().filter(this::isEven).toList();
        ArrayDeque<Student> sortedEvenQueue = new ArrayDeque<>(basicClientSorter.sort(evenList, chainSorter));
        List<Student> resultList = new LinkedList<>();
        Student currentStudent;
        for (Student student : studentList) {
            currentStudent = student;
            if (isEven(currentStudent)) {
                resultList.add(sortedEvenQueue.removeFirst());
            } else {
                resultList.add(student);
            }
        }
        return resultList;
    }

    private boolean isEven(Student student) {
        switch (filteredField) {
            case GroupNumber -> {
                return (student.groupNumber & 1) == 0;
            }
            case AverageScore -> throw new IllegalArgumentException("Четным числом может быть только целочисленное");
            case StudentBookNumber -> {
                return (student.studentBookNumber & 1) == 0;
            }
            default -> throw new IllegalArgumentException("Не найден обработчик для " + filteredField);
        }
    }


}
