package org.example.sorter;

import org.jspecify.annotations.NonNull;

import java.util.List;

interface ISorterNode<Data> {
    List<List<Data>> sortAsNode(@NonNull List<Data> dataList);
}
