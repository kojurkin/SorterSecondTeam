package org.example.utilites;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class LoggedCollectors {

    public static <T> Collector<T, ?, List<T>> toLoggedList() {
        return Collectors.toCollection(ArrayListLogger::new);
    }
}