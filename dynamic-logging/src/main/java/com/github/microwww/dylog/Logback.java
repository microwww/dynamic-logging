package com.github.microwww.dylog;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import org.slf4j.ILoggerFactory;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

public class Logback implements ChangeLoggingLevel {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(Logback.class);

    @Override
    public String getName() {
        return "Logback";
    }

    public void changeLevel(String logger, String level) throws UnsupportedOperationException, IllegalArgumentException {
        try {
            Class.forName("ch.qos.logback.classic.LoggerContext");
            ILoggerFactory factory = LoggerFactory.getILoggerFactory();
            if (factory instanceof LoggerContext) {
                LoggerContext context = (LoggerContext) factory;
                try {
                    Field lv = Level.class.getDeclaredField(level.toUpperCase());
                    if (lv.getType().equals(Level.class)) {
                        log.warn(String.format("Change logger level : [%s] => %s", logger, level));
                        context.getLogger(logger).setLevel((Level) lv.get(null));
                    }
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    String fields = Utils.joinFields(Level.class, Level.class);
                    throw new IllegalArgumentException("Enable value : " + fields, e);
                }
            } else {
                log.warn("SLF4J not config with demo");
                throw new UnsupportedOperationException();
            }
        } catch (ClassNotFoundException ex) {
            throw new UnsupportedOperationException();
        }
    }
}
