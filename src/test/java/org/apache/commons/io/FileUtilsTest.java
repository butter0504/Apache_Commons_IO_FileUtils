package org.apache.commons.io;

import org.junit.jupiter.api.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

public class FileUtilsTest {
    // 测试读取文件内容
    @Test
    void testReadFileToString() throws IOException {
        File file = new File("src/test/resources/test-files/sample1.txt");
        String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        assertEquals("Hello World", content);
    }

    // 测试读取多行文件
    @Test
    void testReadLines() throws IOException {
        File file = new File("src/test/resources/test-files/sample2.txt");
        java.util.List<String> lines = FileUtils.readLines(file, StandardCharsets.UTF_8);
        assertEquals(3, lines.size());
        assertEquals("Line1", lines.get(0));
    }

    // 测试文件大小
    @Test
    void testSizeOf() throws IOException {
        File file = new File("src/test/resources/test-files/sample1.txt");
        long size = FileUtils.sizeOf(file);
        assertEquals(11, size); // "Hello World" 11个字符
    }

    // 测试空文件
    @Test
    void testEmptyFile() throws IOException {
        File file = new File("src/test/resources/test-files/empty.txt");
        String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        assertTrue(content.isEmpty());
    }

    // 测试注入的缺陷 - 不存在的目录
    @Test
    void testDeleteNonExistentDirectory() {
        File nonExistDir = new File("src/test/resources/nonexistent-dir");
        assertDoesNotThrow(() -> FileUtils.deleteDirectory(nonExistDir),
                "缺陷导致没有检查目录是否存在");
    }
}