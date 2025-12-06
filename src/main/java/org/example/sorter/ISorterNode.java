package org.example.sorter;

import java.util.List;

interface ISorterNode<Data> {
    List<List<Data>> sortAsNode(List<Data> dataList);
}
