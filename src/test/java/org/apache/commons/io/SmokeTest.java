package org.apache.commons.io;

import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SmokeTest {
    @Test
    void testBasicFileOperations() throws Exception {
        File tempFile = File.createTempFile("smoke", ".txt");
        try {
            FileUtils.writeStringToFile(tempFile, "smoke test", "UTF-8");
            String content = FileUtils.readFileToString(tempFile, "UTF-8");
            assertTrue(content.contains("smoke test"));
        } finally {
            tempFile.delete();
        }
    }
}