package com.m2i.flexiflex.modelEntity;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "product", schema = "flexiflex", catalog = "")
public class ProductEntity {
    private int id;
    private String url;
    private String title;
    private String synopsis;
    private int ageRequired;
    private Timestamp releaseDate;
    private Timestamp addDate;
    private Time duration;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "synopsis")
    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    @Basic
    @Column(name = "age_required")
    public int getAgeRequired() {
        return ageRequired;
    }

    public void setAgeRequired(int ageRequired) {
        this.ageRequired = ageRequired;
    }

    @Basic
    @Column(name = "release_date")
    public Timestamp getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Timestamp releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Basic
    @Column(name = "add_date")
    public Timestamp getAddDate() {
        return addDate;
    }

    public void setAddDate(Timestamp addDate) {
        this.addDate = addDate;
    }

    @Basic
    @Column(name = "duration")
    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductEntity that = (ProductEntity) o;

        if (id != that.id) return false;
        if (ageRequired != that.ageRequired) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (synopsis != null ? !synopsis.equals(that.synopsis) : that.synopsis != null) return false;
        if (releaseDate != null ? !releaseDate.equals(that.releaseDate) : that.releaseDate != null) return false;
        if (addDate != null ? !addDate.equals(that.addDate) : that.addDate != null) return false;
        if (duration != null ? !duration.equals(that.duration) : that.duration != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (synopsis != null ? synopsis.hashCode() : 0);
        result = 31 * result + ageRequired;
        result = 31 * result + (releaseDate != null ? releaseDate.hashCode() : 0);
        result = 31 * result + (addDate != null ? addDate.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        return result;
    }
}
