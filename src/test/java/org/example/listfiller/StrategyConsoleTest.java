package org.example.listfiller;

import org.junit.jupiter.api.Test;
import java.io.StringReader;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StrategyConsoleTest {
    StrategyConsole strategyConsole = new StrategyConsole();

    @Test
    public void unexpectedParameter() {
        assertThrows(Exception.class, () -> {
            strategyConsole.fill(-1);
        });
    }

    @Test
    public void tryParseStringToInt() {
        String input = "abc";
        Scanner scanner = new Scanner(new StringReader(input));
        assertEquals(0, strategyConsole.parseToInt(scanner));
    }

    @Test
    public void tryParseEmptyValueToInt() {
        String input = "";
        Scanner scanner = new Scanner(new StringReader(input));
        assertEquals(0, strategyConsole.parseToInt(scanner));
    }

    @Test
    public void tryParseIntToInt() {
        String input = "1";
        Scanner scanner = new Scanner(new StringReader(input));
        assertEquals(1, strategyConsole.parseToInt(scanner));
    }

    @Test
    public void tryParseDoubleToInt() {
        String input = "2.2";
        Scanner scanner = new Scanner(new StringReader(input));
        assertEquals(0, strategyConsole.parseToInt(scanner));
    }

    @Test
    public void tryParseNegativeValue() {
        String input = "-1";
        Scanner scanner = new Scanner(new StringReader(input));
        assertEquals(-1, strategyConsole.parseToInt(scanner));
    }

    @Test
    public void tryParseStringToDouble() {
        String input = "abc";
        assertEquals(0.0,strategyConsole.parseToDouble(input));
    }

    @Test
    public void tryParseEmptyValueToDouble() {
        String input = "";
        assertEquals(0.0, strategyConsole.parseToDouble(input));
    }

    @Test
    public void tryParseIntToDouble() {
        String input = "1";
        assertEquals(1.0, strategyConsole.parseToDouble(input));
    }

    @Test
    public void tryParseDoubleToDouble() {
        String input = "3,525216515661";
        assertEquals(3.53, strategyConsole.parseToDouble(input));
    }

    @Test
    public void tryParseDoubleWithCommaSeparator() {
        String input = "4,324";
        assertEquals(4.33, strategyConsole.parseToDouble(input));
    }

    @Test
    public void tryParseNegativeValueToDouble() {
        String input = "-2,23";
        assertEquals(-2.23, strategyConsole.parseToDouble(input));
    }
}
