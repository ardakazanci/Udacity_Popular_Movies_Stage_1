package com.ardakazanci.popularmovielist.model.main;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieMainRoot {

    @SerializedName("results")
    @Expose
    private List<MovieMainResults> results;

    public MovieMainRoot(List<MovieMainResults> results) {
        this.results = results;
    }

    public List<MovieMainResults> getResults() {
        return results;
    }

    public void setResults(List<MovieMainResults> results) {
        this.results = results;
    }
}
