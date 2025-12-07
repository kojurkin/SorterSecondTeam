package org.example.sorter;

import org.jspecify.annotations.NonNull;

import java.util.List;

public interface ISorter<Data> {
    List<Data> sort(@NonNull List<Data> data);
}
