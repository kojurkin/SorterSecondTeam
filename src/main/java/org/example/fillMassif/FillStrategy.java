package org.example.fillMassif;

import org.example.student.Student;
import java.util.ArrayList;

public interface FillStrategy {
    ArrayList<Student> fill(Integer size);
}
