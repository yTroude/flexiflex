package com.m2i.flexiflex.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "flexiflex")
public class UserEntity {
    private int id;
    private String email;
    private String password;
    private Date inscriptionDate;
    private String validationToken;
    private Integer emailValidation;
    private String uuid;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "inscriptionDate")
    public Date getInscriptionDate() {
        return inscriptionDate;
    }

    public void setInscriptionDate(Date inscriptionDate) {
        this.inscriptionDate = inscriptionDate;
    }

    @Basic
    @Column(name = "validationToken")
    public String getValidationToken() {
        return validationToken;
    }

    public void setValidationToken(String validationToken) {
        this.validationToken = validationToken;
    }

    @Basic
    @Column(name = "emailValidation")
    public Integer getEmailValidation() {
        return emailValidation;
    }

    public void setEmailValidation(Integer emailValidation) {
        this.emailValidation = emailValidation;
    }

    @Basic
    @Column(name = "UUID")
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return id == that.id &&
                Objects.equals(email, that.email) &&
                Objects.equals(password, that.password) &&
                Objects.equals(inscriptionDate, that.inscriptionDate) &&
                Objects.equals(validationToken, that.validationToken) &&
                Objects.equals(emailValidation, that.emailValidation) &&
                Objects.equals(uuid, that.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, inscriptionDate, validationToken, emailValidation, uuid);
    }
}
