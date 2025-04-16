package org.apache.commons.io;

import java.io.File;
import java.io.IOException;

public class TestFileUtils {
    public static void main(String[] args) {
        testDeleteDirectory();   //测试删除目录功能
        testReadFileToString();  //测试文件读取功能
        testCopyFile();          //测试文件拷贝功能
    }

    //测试删除目录功能（包含注入的缺陷）
    private static void testDeleteDirectory() {
        try {
            // 测试不存在的目录
            File testDir = new File("src/test/resources/test-dir");
            testDir.mkdir(); //创建测试目录
            //测试删除存在的目录
            FileUtils.deleteDirectory(testDir);
            System.out.println("删除存在的目录: PASSED ");
            //测试注入的缺陷-删除不存在的目录
            File nonExistDir = new File("src/test/resources/test-dir/nonexistent-dir");
            FileUtils.deleteDirectory(nonExistDir);
            System.out.println("删除不存在的目录: PASSED (缺陷被触发)");

        } catch (IOException e) {
            System.err.println("测试失败： " + e.getMessage());
        }
    }

    //测试读取文件内容到字符串
    private static void testReadFileToString() {
        try {
            // 读取test/resources下的sample1.txt
            File file = new File("src/test/resources/test-files/sample1.txt");
            String content = FileUtils.readFileToString(file, "UTF-8");

            if ("Hello World".equals(content)) {
                System.out.println("读取文件内容: PASSED");
            } else {
                System.err.println("读取文件内容: FAILED");
            }
        } catch (IOException e) {
            System.err.println("读取文件内容出错: " + e.getMessage());
        }
    }

    // 测试文件拷贝功能
    private static void testCopyFile() {
        try {
            File src = new File("src/test/resources/test-files/sample1.txt");
            File dest = new File("src/test/resources/test-files/sample2.txt");

            FileUtils.copyFile(src, dest);

            if (dest.exists()) {
                System.out.println("文件拷贝: PASSED");
                dest.delete(); // 清理测试文件
            } else {
                System.err.println("文件拷贝: FAILED");
            }
        } catch (IOException e) {
            System.err.println("文件拷贝出错: " + e.getMessage());
        }
    }
}