package com.m2i.flexiflex.modelEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "user_product", schema = "flexiflex", catalog = "")
public class UserProductEntity {
    private Timestamp dateOfUse;

    @Basic
    @Column(name = "date_of_use")
    public Timestamp getDateOfUse() {
        return dateOfUse;
    }

    public void setDateOfUse(Timestamp dateOfUse) {
        this.dateOfUse = dateOfUse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserProductEntity that = (UserProductEntity) o;

        if (dateOfUse != null ? !dateOfUse.equals(that.dateOfUse) : that.dateOfUse != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return dateOfUse != null ? dateOfUse.hashCode() : 0;
    }
}
