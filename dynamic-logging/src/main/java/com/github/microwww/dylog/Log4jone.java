package com.github.microwww.dylog;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;

public class Log4jone {
    private static final Logger log = Logger.getLogger(Log4jone.class);

    public static void changeLevel(String logger, String level) throws IllegalArgumentException {
        try {
            level = level.toUpperCase();
            Field lv = Level.class.getDeclaredField(level);
            if (lv.getType().equals(Level.class)) {
                log.warn(String.format("Change logger level : [%s] => %s", logger, level));
                Logger.getLogger(logger).setLevel((Level) lv.get(null));
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            String fields = Utils.joinFields(Level.class, Level.class);
            throw new IllegalArgumentException("Enable value : " + fields, e);
        }
    }

}
