package com.github.microwww.dylog;

import org.junit.Assert;
import org.junit.Test;

public class ChangeLog4jTwoLevelTest {

    @Test
    public void changeLog4j2() {
        try {
            new Log4jtwo().changeLevel(ChangeLog4jTwoLevelTest.class.getName(), "X");
            Assert.fail();
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
        new Log4jtwo().changeLevel(ChangeLog4jTwoLevelTest.class.getName(), "Error");
        new Log4jtwo().changeLevel(ChangeLog4jTwoLevelTest.class.getName(), "erroR");
    }
}
