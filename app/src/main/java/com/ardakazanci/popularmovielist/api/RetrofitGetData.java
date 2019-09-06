package com.ardakazanci.popularmovielist.api;

import com.ardakazanci.popularmovielist.model.main.MovieMainRoot;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitGetData {

    @GET("movie/popular")
    Call<MovieMainRoot> getPopularMovies(@Query("api_key") String api_key);


}
