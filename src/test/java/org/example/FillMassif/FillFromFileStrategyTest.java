package org.example.FillMassif;

import org.example.student.Student;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FillFromFileStrategyTest {

    @Test
    public void testFillNormal(){
        ArrayList<Student> fillFromFileStrategy = new FillFromFileStrategy().fill(5);

        assertEquals(5,fillFromFileStrategy.size());
    }

    @Test
    public void testFillBadValue(){
        ArrayList<Student> fillFromFileStrategy = new FillFromFileStrategy().fill(-1);

        assertEquals(0,fillFromFileStrategy.size());
    }
}
