package com.ardakazanci.popularmovielist.model.detail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieDetailGenresResult {

    @SerializedName("id")
    @Expose
    private int genresId;

    @SerializedName("name")
    @Expose
    private String genresName;

    public MovieDetailGenresResult(int genresId, String genresName) {
        this.genresId = genresId;
        this.genresName = genresName;
    }

    public int getGenresId() {
        return genresId;
    }

    public void setGenresId(int genresId) {
        this.genresId = genresId;
    }

    public String getGenresName() {
        return genresName;
    }

    public void setGenresName(String genresName) {
        this.genresName = genresName;
    }
}
