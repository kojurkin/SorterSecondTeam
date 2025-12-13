package org.example.oldlistfiller;

import org.example.student.Student;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileStrategyTest {

    @Test
    public void testFillNormal(){
        List<Student> fillFromFileStrategy = new FillFromFileStrategy().fill(5);

        assertEquals(5,fillFromFileStrategy.size());
    }

    @Test
    public void testFillBadValue(){
        List<Student> fillFromFileStrategy = new FillFromFileStrategy().fill(-1);

        assertEquals(0,fillFromFileStrategy.size());
    }
}
