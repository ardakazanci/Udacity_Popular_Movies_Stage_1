package com.ardakazanci.popularmovielist.model.detail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieDetailVideoResult {

   /* "id": "533ec654c3a36854480003eb",
            "iso_639_1": "en",
            "iso_3166_1": "US",
            "key": "SUXWAEX2jlg",
            "name": "Trailer 1",
            "site": "YouTube",
            "size": 720,
            "type": "Trailer"
*/

    @SerializedName("id")
    @Expose
    private String movieVideoId;
    @SerializedName("key")
    @Expose
    private String movieVideoKey;
    @SerializedName("name")
    @Expose
    private String movieVideoName;
    @SerializedName("site")
    @Expose
    private String movieVideoSite;
    @SerializedName("size")
    @Expose
    private int movieVideoSize;
    @SerializedName("type")
    @Expose
    private String movieVideoType;

    public MovieDetailVideoResult(String movieVideoId, String movieVideoKey, String movieVideoName, String movieVideoSite, int movieVideoSize, String movieVideoType) {
        this.movieVideoId = movieVideoId;
        this.movieVideoKey = movieVideoKey;
        this.movieVideoName = movieVideoName;
        this.movieVideoSite = movieVideoSite;
        this.movieVideoSize = movieVideoSize;
        this.movieVideoType = movieVideoType;
    }

    public String getMovieVideoId() {
        return movieVideoId;
    }

    public void setMovieVideoId(String movieVideoId) {
        this.movieVideoId = movieVideoId;
    }

    public String getMovieVideoKey() {
        return movieVideoKey;
    }

    public void setMovieVideoKey(String movieVideoKey) {
        this.movieVideoKey = movieVideoKey;
    }

    public String getMovieVideoName() {
        return movieVideoName;
    }

    public void setMovieVideoName(String movieVideoName) {
        this.movieVideoName = movieVideoName;
    }

    public String getMovieVideoSite() {
        return movieVideoSite;
    }

    public void setMovieVideoSite(String movieVideoSite) {
        this.movieVideoSite = movieVideoSite;
    }

    public int getMovieVideoSize() {
        return movieVideoSize;
    }

    public void setMovieVideoSize(int movieVideoSize) {
        this.movieVideoSize = movieVideoSize;
    }

    public String getMovieVideoType() {
        return movieVideoType;
    }

    public void setMovieVideoType(String movieVideoType) {
        this.movieVideoType = movieVideoType;
    }
}
