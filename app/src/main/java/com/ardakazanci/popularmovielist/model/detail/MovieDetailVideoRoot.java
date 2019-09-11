package com.ardakazanci.popularmovielist.model.detail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieDetailVideoRoot {

    @SerializedName("id")
    @Expose
    private double movieId;

    @SerializedName("results")
    @Expose
    private List<MovieDetailVideoResult> movieDetailVideoResults;

    public MovieDetailVideoRoot(List<MovieDetailVideoResult> movieDetailVideoResults, double movieId) {
        this.movieDetailVideoResults = movieDetailVideoResults;
        this.movieId = movieId;
    }

    public double getMovieId() {
        return movieId;
    }

    public void setMovieId(double movieId) {
        this.movieId = movieId;
    }

    public List<MovieDetailVideoResult> getMovieDetailVideoResults() {
        return movieDetailVideoResults;
    }

    public void setMovieDetailVideoResults(List<MovieDetailVideoResult> movieDetailVideoResults) {
        this.movieDetailVideoResults = movieDetailVideoResults;
    }
}
