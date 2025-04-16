package org.apache.commons.io;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("Apache Commons io 完整测试集")
@SelectClasses({
        FileUtilsTest.class,    // JUnit 5 测试类
        TestFileUtils.class,
        FileUtilsTestNG.class,        // TestNG 测试类
        // 其他测试类
})
public class AllTestsSuite {
    // 空类，仅作为测试套件容器
}