package com.ardakazanci.popularmovielist.model.detail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieDetailCastRoot {

    @SerializedName("cast")
    @Expose
    private List<MovieDetailCastResult> movieCastList;


    public MovieDetailCastRoot(List<MovieDetailCastResult> movieCastList) {
        this.movieCastList = movieCastList;
    }

    public List<MovieDetailCastResult> getMovieCastList() {
        return movieCastList;
    }

    public void setMovieCastList(List<MovieDetailCastResult> movieCastList) {
        this.movieCastList = movieCastList;
    }
}
