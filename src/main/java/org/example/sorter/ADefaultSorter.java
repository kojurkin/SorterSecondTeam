package org.example.sorter;

import org.example.student.Student;
import org.jspecify.annotations.NonNull;

import java.util.List;

public abstract class ADefaultSorter implements IPrioritySorter {
    private final ChainSorterFactory chainSorterFactory = new ChainSorterFactory();

    /**
     * Использует корневой цепной сортировщик, сортирующий по указанному приоритету сортировки.
     * Например, если указать поле 2, потом 3, а потом 1, то он сначала выведет все объекты отсортированные
     * по полю 2, а если среди значений поля 2 есть совпадения, то он отсортирует внутри этих
     * совпадений по полю 3 и так далее.
     *
     * @param studentList      массив студентов, который надо отсортировать
     * @param clientFieldNames приоритет на сортировку. Сначала отсортируется по первому указанному полю.
     * @return возвращает отсортированный массив
     */
    public List<Student> sort(@NonNull List<Student> studentList, @NonNull ClientFieldName... clientFieldNames) {
        return sort(studentList, chainSorterFactory.getSorter(clientFieldNames));
    }
}
