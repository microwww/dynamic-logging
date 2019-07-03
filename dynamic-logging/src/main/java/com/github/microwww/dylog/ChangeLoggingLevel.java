package com.github.microwww.dylog;

public interface ChangeLoggingLevel {

    public String getName();

    public void changeLevel(String logger, String level) throws UnsupportedOperationException, IllegalArgumentException;
}
