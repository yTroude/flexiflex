package com.m2i.flexiflex.entity.properties;

public enum UserProperties {
    EMAIL("email"),
    PASSWORD("password");

    String label;

    UserProperties(String label) {
        this.label = label;
    }

    public String get() {
        return label;
    }
}
