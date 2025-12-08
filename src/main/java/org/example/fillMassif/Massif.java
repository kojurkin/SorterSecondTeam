package org.example.fillMassif;

import org.example.student.Student;

import java.util.List;

public class Massif {
    FillStrategy fillStrategy;

    public List<Student> fill(Integer size){
        return fillStrategy.fill(size);
    }

}
