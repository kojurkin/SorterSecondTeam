package org.example.sorter;

import org.jspecify.annotations.NonNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ChainSorterFactory {
    private final Map<ClientFieldName, ASorter> sorterMap;
    private final DataValidator dataValidator = new DataValidator();

    public ChainSorterFactory() {
        sorterMap = new HashMap<>();
        sorterMap.put(ClientFieldName.GroupNumber, new SorterGroupNumber());
        sorterMap.put(ClientFieldName.AverageScore, new SorterAverageScore());
        sorterMap.put(ClientFieldName.StudentBookNumber, new SorterStudentBookNumber());
    }

    /**
     * Возвращает корневой цепной сортировщик, сортирующий по указанному приоритету сортировки.
     * Например, если указать поле 2, потом 3, а потом 1, то он сначала выведет все объекты отсортированные
     * по полю 2, а если среди значений поля 2 есть совпадения, то он отсортирует внутри этих
     * совпадений по полю 3 и так далее.
     *
     * @param sortingPriority Приоритет на сортировку.
     */
    public ChainSorter getSorter(@NonNull ClientFieldName... sortingPriority) {
        dataValidator
                .validateArrAndElementNotNull("SortingProperty:", sortingPriority)
                .throwIfValid(() -> isAnyNotPresentInSorterMap(sortingPriority),
                        "Can't find strategy for field name in arr "
                                + Arrays.toString(sortingPriority));
        ChainSorterBuilder chainSorterBuilder = new ChainSorterBuilder();
        for (ClientFieldName fieldName : sortingPriority) {
            chainSorterBuilder.nextSorter(sorterMap.get(fieldName));
        }
        return chainSorterBuilder.build();
    }

    private boolean isAnyNotPresentInSorterMap(ClientFieldName... sortingPriority) {
        for (ClientFieldName fieldName : sortingPriority) {
            if (!sorterMap.containsKey(fieldName)) {
                return true;
            }
        }
        return false;
    }

}
