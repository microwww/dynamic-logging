package com.github.microwww.dylog.demo;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import org.junit.Test;
import org.slf4j.ILoggerFactory;
import org.slf4j.LoggerFactory;

public class LogbackChangeLevel {

    @Test
    public void testLevel(){
        ILoggerFactory factory = LoggerFactory.getILoggerFactory();

        //if(factory instanceof LoggerContext){
        //    return;
        //}
        LoggerContext context = (LoggerContext) factory;
        {
            Logger logger = context.getLogger(this.getClass());
            logger.info("show info");
        }
        {
            Logger logger = context.getLogger(this.getClass());
            logger.setLevel(Level.WARN);
            logger.info("NOT show info");
        }
        {
            Logger logger = context.getLogger(this.getClass());
            logger.setLevel(Level.DEBUG);
            logger.debug("show debug");
        }
    }
}
