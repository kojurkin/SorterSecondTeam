package org.example.sorter;

import org.example.Student;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ChainSorter implements ISorter<Student> {
    private ChainSorter nextSorter;
    private final ISorterNode<Student> currentSorter;

    public ChainSorter(ISorterNode<Student> currentSorter) {
        this.currentSorter = currentSorter;
    }

    public ChainSorter setNextSorter(ISorterNode<Student> nextSorter){
        this.nextSorter = new ChainSorter(nextSorter);
        return this.nextSorter;
    }

    @Override
    public List<Student> sort(List<Student> studentList) {
        //Получаем отсортированный массив, где одинаковые значения сложены в массив
        List<List<Student>> nodeList = sortAsNode(studentList);
        if (nextSorter == null) {
            //Если нет следующего сортера, возвращаем List<Student>
            return nodeList.stream().flatMap(List::stream).toList();
        } else {
            //Если есть следующий сортер, сортируем одинаковые значения
            Queue<Student> result = new LinkedList<>();
            for (List<Student> node : nodeList) {
                result.addAll(nextSorter.sort(node));
            }
            return result.stream().toList();
        }
    }

    //Получаем массив, в котором найденные объекты с одинаковыми значениями (по критерию сортировки)
    // сложены в отдельный массив (нода)
    private List<List<Student>> sortAsNode(List<Student> studentList){
        return currentSorter.sortAsNode(studentList);
    }
}
