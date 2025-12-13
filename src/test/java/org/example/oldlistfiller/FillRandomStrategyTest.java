package org.example.oldlistfiller;

import org.example.student.Student;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FillRandomStrategyTest {

    @Test
    public void testFillNormal(){
        List<Student> fillMassif = new FillRandomStrategy().fill(5);

        assertEquals(5,fillMassif.size());
    }

    @Test
    public void testFillBadValue(){
        List<Student> fillMassif = new FillRandomStrategy().fill(-1);

        assertEquals(0,fillMassif.size());
    }
}
