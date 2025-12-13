package org.example.utilites;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ArrayListLogger<E> extends ArrayList<E> {

    private static final String LOG_FILE = "log.txt";

    private void log(String message) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, true))) {  // true — append mode
            writer.println(message);
        } catch (IOException e) {
            System.err.println("Ошибка логирования: " + e.getMessage());
        }
    }

    @Override
    public boolean add(E element) {
        log("add()");
        return super.add(element);
    }

    @Override
    public E get(int index) {
        log("get()");
        return super.get(index);
    }

    @Override
    public int size() {
        log("size()");
        return super.size();
    }

    @Override
    public boolean isEmpty() {
        log("isEmpty()");
        return super.isEmpty();
    }
}