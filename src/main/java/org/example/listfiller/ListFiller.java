package org.example.listfiller;

import org.example.student.Student;

import java.util.List;

public class ListFiller {
    Strategy strategy;

    public List<Student> fill(Integer size){
        return strategy.fill(size);
    }

}
