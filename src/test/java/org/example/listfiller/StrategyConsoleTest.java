package org.example.listfiller;

import org.example.student.Student;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StrategyConsoleTest {
    StrategyConsole strategyConsole = new StrategyConsole();

    @Test
    public void unexpectedParameter() throws Exception {
        assertThrows(Exception.class, () -> {
            strategyConsole.fill(-1);
        });
    }
}
