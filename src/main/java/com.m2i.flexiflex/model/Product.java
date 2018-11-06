package com.m2i.flexiflex.model;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.Duration;

public class Product {

    private int id;
    private String url;
    private String title;
    private String synopsis;
    private Timestamp ageRequired;
    private Timestamp releaseDate;
    private Timestamp addDate;
    private Time duration;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getSynopsis() {
        return synopsis;
    }
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Timestamp getAgeRequired() {
        return ageRequired;
    }
    public void setAgeRequired(Timestamp ageRequired) {
        this.ageRequired = ageRequired;
    }

    public Timestamp getReleaseDate() {
        return releaseDate;
    }
    public void setReleaseDate(Timestamp releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Timestamp getAddDate() {
        return addDate;
    }
    public void setAddDate(Timestamp addDate) {
        this.addDate = addDate;
    }

    public Time getDuration() {
        return duration;
    }
    public void setDuration(Time duration) {
        this.duration = duration;
    }
}