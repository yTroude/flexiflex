package com.m2i.flexiflex.entity.properties;

public enum UserProperties {
    EMAIL("email"),
    PASSWORD("password"),
    INSCRIPTION_DATE("inscriptionDate"),
    VALIDATION_TOKEN("validationToken"),
    EMAIL_VALIDE("emailValide"),
    UUID("uuid");

    String label;

    UserProperties(String label) {
        this.label = label;
    }

    public String get() {
        return label;
    }
}
