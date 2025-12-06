package org.example.sorter;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

class QuickSort<Data> implements ISorterNode<Data> {
    private final Comparator<Data> comparator;

    public QuickSort(Comparator<Data> comparator) {
        this.comparator = comparator;
    }

    /**
     * Сортирует от большего к меньшему (по умолчанию)
     * @param dataList массив, котоырй надо отсортировать
     */
    @Override
    public List<List<Data>> sortAsNode(List<Data> dataList) {
        validateNull(dataList);
        if (dataList.size() < 2) {
            return List.of(dataList);
        }
        Data pivot = getPivot(dataList);
        List<Data> left = new LinkedList<>();
        List<Data> center = new LinkedList<>();
        List<Data> right = new LinkedList<>();

        for (Data data : dataList) {
            if (comparator.compare(pivot, data) < 0) {
                right.add(data);
                continue;
            }
            if (comparator.compare(pivot, data) > 0) {
                left.add(data);
                continue;
            }
            center.add(data);
        }
        List<List<Data>> result = new LinkedList<>();
        if(left.size() > 0){
            result.addAll(sortAsNode(left));
        }
        result.add(center);
        if(right.size() > 0){
            result.addAll(sortAsNode(right));
        }
        return result;
    }

    private Data getPivot(List<Data> dataList) {
        return dataList.get(new Random().nextInt(dataList.size()));
    }

    private void validateNull(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Null can't be sorted");
        }
    }
}
