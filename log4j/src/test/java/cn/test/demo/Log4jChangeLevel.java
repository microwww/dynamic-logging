package cn.test.demo;

import org.apache.log4j.Level;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log4jChangeLevel {

    @Test
    public void test() {
        Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.debug("debug not show");

        org.apache.log4j.Logger.getLogger(this.getClass()).setLevel(Level.DEBUG);

        logger.debug("debug show ...");
        logger.error("error show ...");

        org.apache.log4j.Logger.getLogger(this.getClass()).setLevel(Level.FATAL);

        logger.error("error not show");
    }
}
