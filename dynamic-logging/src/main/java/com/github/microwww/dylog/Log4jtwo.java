package com.github.microwww.dylog;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import java.lang.reflect.Field;

public class Log4jtwo {
    private static final Logger log = LogManager.getLogger(Log4jtwo.class);

    public static void changeLevel(String logger, String level) throws IllegalArgumentException {
        try {
            Field lv = Level.class.getDeclaredField(level.toUpperCase());
            if (lv.getType().equals(Level.class)) {
                log.warn(String.format("Change logger level : [%s] => %s", logger, level));
                Configurator.setLevel(logger, (Level) lv.get(null));
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            String fields = Utils.joinFields(Level.class, Level.class);
            throw new IllegalArgumentException("Enable value : " + fields, e);
        }
    }
}
