package com.github.microwww.dylog;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;

public class Log4jone implements ChangeLoggingLevel { // log4j 1.x.x
    private static final Logger log = Logger.getLogger(Log4jone.class);

    @Override
    public String getName() {
        return "log4j 1.x.x";
    }

    public void changeLevel(String logger, String level) throws UnsupportedOperationException, IllegalArgumentException {
        try {
            Class.forName("org.apache.log4j.Level");
            try {
                level = level.toUpperCase();
                Field lv = Level.class.getDeclaredField(level);
                if (lv.getType().equals(Level.class)) {
                    log.warn(String.format("Change logger level : [%s] => %s", logger, level));
                    Logger.getLogger(logger).setLevel((Level) lv.get(null));
                }
            } catch (Exception e) {
                Utils.tryMultiCatches(e, NoSuchFieldException.class, IllegalAccessException.class, IllegalArgumentException.class);
                String fields = Utils.joinFields(Level.class, Level.class);
                throw new IllegalArgumentException("Enable value : " + fields, e);
            }
        } catch (ClassNotFoundException e) {
            throw new UnsupportedOperationException();
        }
    }

}
