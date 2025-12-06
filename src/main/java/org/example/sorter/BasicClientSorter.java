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
     *
     * Сортирует сначала по первому полю, потом по второму если есть одинаковые значения, и так далее.
     * Сортирует от меньшего к большему (по умолчанию)
     * @param studentList список, который надо отсортировать
     * @param sortingPriority Приоритет на сортировку. Сначала сортируется по первому указанному полю.
     * @return Возвращает отсортированный список
     */
    public List<Student> sort(List<Student> studentList, ClientFieldName... sortingPriority) {
        validateNull(sortingPriority);
        validatePresentInMap(sortingPriority);
        ChainSorterBuilder chainSorterBuilder = new ChainSorterBuilder();
        for (ClientFieldName fieldName : sortingPriority) {
            chainSorterBuilder.nextSorter(sorterList.get(fieldName));
        }
        return chainSorterBuilder.build().sort(studentList);
    }

    private void validatePresentInMap(ClientFieldName... fieldNames) {
        for (ClientFieldName fieldName : fieldNames) {
            if (!sorterList.containsKey(fieldName)) {
                throw new IllegalArgumentException("Can't find strategy for field name '" + fieldName + "'");
            }
        }
    }

    private void validateNull(ClientFieldName... fieldNames) {
        if (fieldNames == null || Arrays.stream(fieldNames).anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("Field name can't be null. Field name list: " + Arrays.toString(fieldNames));
        }
    }

    public enum ClientFieldName {
        GroupNumber,
        AverageScore,
        StudentBookNumber
    }
}
