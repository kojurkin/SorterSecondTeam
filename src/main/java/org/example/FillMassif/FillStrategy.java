package org.example.FillMassif;

import org.example.Student;
import java.util.ArrayList;

public interface FillStrategy {
    ArrayList<Student> fill(Integer size);
}
