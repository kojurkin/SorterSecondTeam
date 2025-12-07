package org.example.sorter;

import org.example.Student;

import java.util.*;

public class BasicClientSorter {
    private final Map<ClientFieldName, ASorter> sorterList = new HashMap<>();

    public BasicClientSorter() {
        sorterList.put(ClientFieldName.GroupNumber, new SorterGroupNumber());
        sorterList.put(ClientFieldName.AverageScore, new SorterAverageScore());
        sorterList.put(ClientFieldName.StudentBookNumber, new SorterStudentBookNumber());
    }

    /**
     * Сортирует сначала по первому полю, потом по второму если есть одинаковые значения, и так далее.
     * Сортирует от меньшего к большему (по умолчанию)
     *
     * @param studentList     список, который надо отсортировать
     * @param sortingPriority Приоритет на сортировку. Сначала сортируется по первому указанному полю.
     * @return Возвращает отсортированный список
     */
    public List<Student> sort(List<Student> studentList, ClientFieldName... sortingPriority) {
        if (!isNeedToSort(studentList)) {
            return studentList;
        }
        validateInput(studentList, sortingPriority);
        return getSorter(sortingPriority).sort(studentList);
    }

    private ChainSorter getSorter(ClientFieldName... sortingPriority) {
        ChainSorterBuilder chainSorterBuilder = new ChainSorterBuilder();
        for (ClientFieldName fieldName : sortingPriority) {
            chainSorterBuilder.nextSorter(sorterList.get(fieldName));
        }
        return chainSorterBuilder.build();
    }

    private boolean isNeedToSort(List<Student> studentList) {
        return studentList != null && studentList.size() > 1;
    }

    private void validateInput(List<Student> studentList, ClientFieldName... sortingPriority) {
        if (sortingPriority == null) {
            throw new IllegalArgumentException("ClientFieldName list can't be null");
        }
        if (studentList == null) {
            throw new IllegalArgumentException("studentList can't be null");
        }
        studentList.forEach(
                student -> validateNotNull(student, "Student can't be null")
        );
        Arrays.stream(sortingPriority).forEach(
                fieldName -> {
                    validateNotNull(fieldName, "Field name can't be null.");
                    validatePresentInMap(fieldName);
                }
        );
    }

    private void validateNotNull(Object object, String errorMessage) {
        if (object == null) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private void validatePresentInMap(ClientFieldName fieldName) {
        if (!sorterList.containsKey(fieldName)) {
            throw new IllegalArgumentException("Can't find strategy for field name '" + fieldName + "'");
        }
    }

    public enum ClientFieldName {
        GroupNumber,
        AverageScore,
        StudentBookNumber
    }
}
