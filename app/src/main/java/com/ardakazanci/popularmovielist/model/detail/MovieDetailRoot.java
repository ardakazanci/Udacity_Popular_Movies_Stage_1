package com.ardakazanci.popularmovielist.model.detail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieDetailRoot {

    @SerializedName("backdrop_path")
    @Expose
    private String detail_backdrop_path;

    @SerializedName("genres")
    @Expose
    private List<MovieDetailGenresResult> movieDetailGenresResults;

    @SerializedName("overview")
    @Expose
    private String movieOverview;

    @SerializedName("tagline")
    @Expose
    private String tagline;

    public MovieDetailRoot(List<MovieDetailGenresResult> movieDetailGenresResults, String movieOverview, String tagline, String detail_backdrop_path) {
        this.movieDetailGenresResults = movieDetailGenresResults;
        this.movieOverview = movieOverview;
        this.tagline = tagline;
        this.detail_backdrop_path = detail_backdrop_path;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public List<MovieDetailGenresResult> getMovieDetailGenresResults() {
        return movieDetailGenresResults;
    }

    public void setMovieDetailGenresResults(List<MovieDetailGenresResult> movieDetailGenresResults) {
        this.movieDetailGenresResults = movieDetailGenresResults;
    }

    public String getMovieOverview() {
        return movieOverview;
    }

    public void setMovieOverview(String movieOverview) {
        this.movieOverview = movieOverview;
    }

    public String getDetail_backdrop_path() {
        return detail_backdrop_path;
    }

    public void setDetail_backdrop_path(String detail_backdrop_path) {
        this.detail_backdrop_path = detail_backdrop_path;
    }
}
