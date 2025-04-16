package org.apache.commons.io;

import org.testng.annotations.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import static org.testng.Assert.*;

public class FileUtilsTestNG {
    // 定义常量代替参数变量
    private static final String RESOURCES_DIR = "src/test/resources/";
    private static final String SAMPLE_FILE_1 = "test-files/sample1.txt";
    private static final String SAMPLE_FILE_2 = "test-files/sample2.txt";
    private static final String EMPTY_FILE = "test-files/empty.txt";

    @DataProvider(name = "fileContentData")
    public Object[][] provideFileContentData() {
        return new Object[][] {
                {SAMPLE_FILE_1, "Hello World"},
                {SAMPLE_FILE_2, String.join(System.lineSeparator(), "Line1", "Line2", "Line3")},
                {EMPTY_FILE, ""}
        };
    }

    @Test(dataProvider = "fileContentData")
    public void testFileContent(String filePath, String expectedContent) throws IOException {
        File file = new File(RESOURCES_DIR + filePath);
        String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);

        // 标准化换行符
        String normalizedContent = content.replace("\r\n", "\n").replace("\r", "\n");
        String normalizedExpected = expectedContent.replace("\r\n", "\n").replace("\r", "\n");

        assertEquals(normalizedContent, normalizedExpected,
                "文件内容不匹配 - " + filePath);
    }

    @Test
    public void testCopyFile() throws IOException {
        File src = new File(RESOURCES_DIR + SAMPLE_FILE_1);
        File dest = new File(RESOURCES_DIR + "copy.tmp");

        try {
            FileUtils.copyFile(src, dest);
            assertTrue(dest.exists());
            assertEquals(
                    FileUtils.readFileToString(dest, StandardCharsets.UTF_8),
                    "Hello World"
            );
        } finally {
            if (dest.exists()) dest.delete();
        }
    }

    @Test
    public void testWriteStringToFile() throws IOException {
        File file = new File(RESOURCES_DIR + "write-test.tmp");
        try {
            FileUtils.writeStringToFile(file, "Test Content", StandardCharsets.UTF_8);
            assertEquals(
                    FileUtils.readFileToString(file, StandardCharsets.UTF_8),
                    "Test Content"
            );
        } finally {
            if (file.exists()) file.delete();
        }
    }

    @Test
    public void testDeleteDirectoryWithDefect() throws IOException {
        File nonExistDir = new File(RESOURCES_DIR + "nonexistent-dir");
        FileUtils.deleteDirectory(nonExistDir); // 测试注入的缺陷
    }
}