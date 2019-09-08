package com.ardakazanci.popularmovielist.model.main;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieMainResults {

    @SerializedName("id")
    @Expose
    private double id;
    @SerializedName("poster_path")
    @Expose
    private String poster_path;
    @SerializedName("original_title")
    @Expose
    private String original_title;
    @SerializedName("vote_average")
    @Expose
    private float vote_average;
    @SerializedName("release_date")
    @Expose
    private String release_date;

    @SerializedName("backdrop_path")
    @Expose
    private String backdrop_path;


    public MovieMainResults(double id, String poster_path, String original_title, float vote_average, String release_date, String backdrop_path) {
        this.id = id;
        this.poster_path = poster_path;
        this.original_title = original_title;
        this.vote_average = vote_average;
        this.release_date = release_date;
        this.backdrop_path = backdrop_path;
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public float getVote_average() {
        return vote_average;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }
}
