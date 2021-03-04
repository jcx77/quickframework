package com.framework.commons.log;

public enum LogType {
    ACTION("0"), LOGIN("1");

    private String value;

    private LogType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
