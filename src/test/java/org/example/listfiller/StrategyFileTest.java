package org.example.listfiller;

import org.example.student.Student;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StrategyFileTest {
    StrategyFile strategyFile = new StrategyFile();

    @Test
    public void unexpectedParameter() throws Exception {
        assertThrows(IOException.class, () -> {
            strategyFile.fill(-1);
        });
    }

    @Test
    public void tryParseFileWithEmptyLines() throws IOException {
        String path = "src/test/java/org/example/listfiller/FileWithEmptyLines.txt";
        List<Student> list = strategyFile.parseListFromFile(path,21);
        assertEquals(21, list.size());
    }

    @Test
    public void tryParseEmptyFile() throws IOException {
        String path = "src/test/java/org/example/listfiller/EmptyFile.txt";
        List<Student> list = strategyFile.parseListFromFile(path, 0);
        assertEquals(0, list.size());
    }
}
