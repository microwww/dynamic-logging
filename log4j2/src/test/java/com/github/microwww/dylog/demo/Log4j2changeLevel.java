package com.github.microwww.dylog.demo;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log4j2changeLevel {

    @Test
    public void test() {
        Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.debug("debug not show");

        Configurator.setLevel(this.getClass().getName(), Level.DEBUG);

        logger.debug("debug show ...");
        logger.error("error show ...");

        Configurator.setLevel(this.getClass().getName(), Level.FATAL);

        logger.error("error not show");
    }
}
