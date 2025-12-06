package org.example.sorter;

import java.util.List;

interface ISorter<Data> {
    List<Data> sort(List<Data> data);
}
