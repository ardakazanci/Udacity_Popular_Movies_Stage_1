package com.ardakazanci.popularmovielist.model.detail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieDetailRoot {

    @SerializedName("genres")
    @Expose
    private String[] movieGenres;

    @SerializedName("overview")
    @Expose
    private String movieOverview;

    public MovieDetailRoot(String[] movieGenres, String movieOverview) {
        this.movieGenres = movieGenres;
        this.movieOverview = movieOverview;
    }

    public String[] getMovieGenres() {
        return movieGenres;
    }

    public void setMovieGenres(String[] movieGenres) {
        this.movieGenres = movieGenres;
    }

    public String getMovieOverview() {
        return movieOverview;
    }

    public void setMovieOverview(String movieOverview) {
        this.movieOverview = movieOverview;
    }
}
