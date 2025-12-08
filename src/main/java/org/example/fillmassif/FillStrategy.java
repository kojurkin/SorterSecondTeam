package org.example.fillmassif;

import org.example.student.Student;
import java.util.ArrayList;

public interface FillStrategy {
    ArrayList<Student> fill(Integer size);
}
