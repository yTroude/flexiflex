package com.m2i.flexiflex.model;
import java.sql.Timestamp;

public class User extends Identity {

    private String email;
    private Timestamp birthdate;
    private String password;
    private Timestamp registerDate;
    private Timestamp registerValidationDate;

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getBirthDate() {
        return birthdate;
    }
    public void setBirthDate(Timestamp birthDate) {
        this.birthdate = birthDate;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getRegisterDate() {
        return registerDate;
    }
    public void setRegisterDate(Timestamp registerDate) {
        this.registerDate = registerDate;
    }

    public Timestamp getRegisterValidationDate() {
        return registerValidationDate;
    }
    public void setRegisterValidationDate(Timestamp registerValidationDate) {
        this.registerValidationDate = registerValidationDate;
    }

}