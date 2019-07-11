package com.github.microwww.dylog;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import java.lang.reflect.Field;

public class Log4jtwo implements ChangeLoggingLevel {// log4j 2.x.x
    private static final Logger log = LogManager.getLogger(Log4jtwo.class);

    @Override
    public String getName() {
        return "log4j 2.x.x";
    }

    public void changeLevel(String logger, String level) throws UnsupportedOperationException, IllegalArgumentException {
        try {
            Class.forName("org.apache.logging.log4j.core.config.Configurator");
            try {
                Field lv = Level.class.getDeclaredField(level.toUpperCase());
                if (lv.getType().equals(Level.class)) {
                    log.warn(String.format("Change logger level : [%s] => %s", logger, level));
                    Configurator.setLevel(logger, (Level) lv.get(null));
                }
            } catch (Exception e) {
                Utils.tryMultiCatches(e, NoSuchFieldException.class, IllegalAccessException.class);
                String fields = Utils.joinFields(Level.class, Level.class);
                throw new IllegalArgumentException("Enable value : " + fields, e);
            }
        } catch (ClassNotFoundException ex) {
            throw new UnsupportedOperationException();
        }

    }
}
