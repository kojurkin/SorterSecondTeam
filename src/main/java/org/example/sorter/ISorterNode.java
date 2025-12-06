package org.example.sorter;

import java.util.List;

public interface ISorterNode<Data> {
    List<List<Data>> sortAsNode(List<Data> dataList);
}
