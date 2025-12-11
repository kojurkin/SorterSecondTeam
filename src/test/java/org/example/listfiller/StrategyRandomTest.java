package org.example.listfiller;

import org.example.student.Student;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StrategyRandomTest {
    StrategyRandom strategyRandom = new StrategyRandom();

    @Test
    public void normalBehavior() throws Exception {
        List<Student> list = strategyRandom.fill(1);

        assertEquals(1, list.size());
    }

    @Test
    public void unexpectedParameter() throws Exception {
        assertThrows(Exception.class, () -> {
            strategyRandom.fill(-1);
        });
    }
}
