package org.example.listfiller;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class StrategyFileTest {
    StrategyFile strategyFile = new StrategyFile();

    @Test
    public void unexpectedParameter() throws Exception {
        assertThrows(IOException.class, () -> {
            strategyFile.fill(-1);
        });
    }
}
