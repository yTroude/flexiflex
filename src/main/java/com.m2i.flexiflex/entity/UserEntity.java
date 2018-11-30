package com.m2i.flexiflex.entity;

import com.m2i.flexiflex.entity.properties.UserProperties;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.util.Objects;

@Entity(name = "user")
public class UserEntity {

    private int id;
    private String email;
    private String password;
    private Date inscriptionDate;
    private String validationToken;
    private Integer emailValidation;
    private String uuid;
    private String authToken;
    private String birthDate;
    private String firstName;
    private String lastName;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = UserProperties.EMAIL)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = UserProperties.PASSWORD)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = UserProperties.INSCRIPTION_DATE)
    public Date getInscriptionDate() {
        return inscriptionDate;
    }

    public void setInscriptionDate(Date inscriptionDate) {
        this.inscriptionDate = inscriptionDate;
    }

    @Basic
    @Column(name = UserProperties.VALIDATION_TOKEN)
    public String getValidationToken() {
        return validationToken;
    }

    public void setValidationToken(String validationToken) {
        this.validationToken = validationToken;
    }

    @Basic
    @Column(name = UserProperties.EMAIL_VALIDE)
    public Integer getEmailValidation() {
        return emailValidation;
    }

    public void setEmailValidation(Integer isValidated) {
        this.emailValidation = isValidated;
    }

    @Basic
    @Column(name = UserProperties.UUID)
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Basic
    @Column(name = UserProperties.AUTH_TOKEN)
    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    @Basic
    @Column(name = UserProperties.BIRTH_DATE)
    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @Basic
    @Column(name = UserProperties.LAST_NAME)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = UserProperties.FIRST_NAME)
    public String getLastName () {
        return lastName;
    }

    public void setLastName (String lastName) {
        this.lastName = lastName;
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
