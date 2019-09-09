package com.ardakazanci.popularmovielist.api;

import com.ardakazanci.popularmovielist.model.detail.MovieDetailRoot;
import com.ardakazanci.popularmovielist.model.main.MovieMainRoot;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitGetData {

    @GET("movie/popular")
    Call<MovieMainRoot> getPopularMovies(@Query("api_key") String api_key);

    @GET("movie/top_rated")
    Call<MovieMainRoot> getTopRatedMovies(@Query("api_key") String api_key);

    @GET("movie/upcoming")
    Call<MovieMainRoot> getUpcomingMovies(@Query("api_key") String api_key);

    @GET("movie/{movie_id}")
    Call<MovieDetailRoot> getMovieIdDetail(@Path("movie_id") int movie_id, @Query("api_key") String api_key);






}
