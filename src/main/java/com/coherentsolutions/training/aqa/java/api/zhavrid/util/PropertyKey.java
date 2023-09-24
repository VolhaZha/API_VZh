package com.coherentsolutions.training.aqa.java.api.zhavrid.util;

public enum PropertyKey {
    URI("uri"),
    URL("url"),

    DEFAULT_USER("user"),
    DEFAULT_PASS("password"),
    PORT("port");

    private final String key;

    PropertyKey (String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
