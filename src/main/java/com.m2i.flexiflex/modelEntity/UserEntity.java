package com.m2i.flexiflex.modelEntity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "user", schema = "flexiflex", catalog = "")
public class UserEntity {
    private int id;
    private String email;
    private Timestamp birthdate;
    private String password;
    private Timestamp registerDate;
    private Timestamp registerValidationDate;

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
    @Column(name = "birthdate")
    public Timestamp getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Timestamp birthdate) {
        this.birthdate = birthdate;
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
    @Column(name = "register_date")
    public Timestamp getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Timestamp registerDate) {
        this.registerDate = registerDate;
    }

    @Basic
    @Column(name = "register_validation_date")
    public Timestamp getRegisterValidationDate() {
        return registerValidationDate;
    }

    public void setRegisterValidationDate(Timestamp registerValidationDate) {
        this.registerValidationDate = registerValidationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (id != that.id) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (birthdate != null ? !birthdate.equals(that.birthdate) : that.birthdate != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (registerDate != null ? !registerDate.equals(that.registerDate) : that.registerDate != null) return false;
        if (registerValidationDate != null ? !registerValidationDate.equals(that.registerValidationDate) : that.registerValidationDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (birthdate != null ? birthdate.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (registerDate != null ? registerDate.hashCode() : 0);
        result = 31 * result + (registerValidationDate != null ? registerValidationDate.hashCode() : 0);
        return result;
    }
}
