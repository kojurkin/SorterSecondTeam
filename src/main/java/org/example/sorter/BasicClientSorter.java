package org.example.sorter;

import org.example.Student;
import org.jspecify.annotations.NonNull;

import java.util.*;

public class BasicClientSorter extends ADefaultSorter {
    private final DataValidator dataValidator = new DataValidator();

    /**
     * Валидирует входные данные и сортирует согласно поведению цепного сортировщика
     *
     * @param studentList список, который надо отсортировать
     * @param chainSorter корневой цепной сортировщик
     * @return возвращает отсортированный список
     */
    public List<Student> sort(@NonNull List<Student> studentList, @NonNull ChainSorter chainSorter) {
        if (dataValidator.isNotNeedToSort(studentList)) {
            return studentList;
        }
        dataValidator.validateArrAndElementNotNull("StudentList:", studentList.toArray());
        return chainSorter.sort(studentList);
    }


}
