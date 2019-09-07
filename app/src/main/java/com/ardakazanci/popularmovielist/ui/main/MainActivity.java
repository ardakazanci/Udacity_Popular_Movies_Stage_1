package com.ardakazanci.popularmovielist.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;


import com.ardakazanci.popularmovielist.Interface.BottomSheetListener;
import com.ardakazanci.popularmovielist.R;
import com.ardakazanci.popularmovielist.adapter.MovieListAdapter;
import com.ardakazanci.popularmovielist.api.RetrofitClient;
import com.ardakazanci.popularmovielist.api.RetrofitGetData;
import com.ardakazanci.popularmovielist.common.Constants;
import com.ardakazanci.popularmovielist.model.main.MovieMainResults;
import com.ardakazanci.popularmovielist.model.main.MovieMainRoot;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements BottomSheetListener {

    private MovieListAdapter adapter;
    private RecyclerView recyclerviewLstMovie;
    private ProgressBar progressBar;
    private ImageButton menuButton;
    private LinearLayout error_dataTransfer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        error_dataTransfer = findViewById(R.id.error_dataTransfer);
        progressBar = findViewById(R.id.progressBar);
        menuButton = findViewById(R.id.imagebutton_check_list_type);

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuBottomSheetFragment menuBottomSheetFragment = new MenuBottomSheetFragment();
                menuBottomSheetFragment.show(getSupportFragmentManager(), "MenuBottomSheetMenu");
            }
        });


        recyclerviewLstMovie = findViewById(R.id.recyclerview_movies);

        adapter = new MovieListAdapter(new ArrayList<MovieMainResults>(0), getApplicationContext());

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2, RecyclerView.VERTICAL, false);

        recyclerviewLstMovie.setLayoutManager(layoutManager);

        recyclerviewLstMovie.setAdapter(adapter);

        recyclerviewLstMovie.setHasFixedSize(true);

        // Cache && Internet Connection Control - Offline Mode

        loadPopularMovieListData();


    }

    private void loadPopularMovieListData() {


        RetrofitGetData retrofitService = RetrofitClient.getRetrofitInstance().create(RetrofitGetData.class);

        Call<MovieMainRoot> call = retrofitService.getPopularMovies(Constants.API_KEY);

        call.enqueue(new Callback<MovieMainRoot>() {
            @Override
            public void onResponse(Call<MovieMainRoot> call, Response<MovieMainRoot> response) {
                // Bağlantı Başarılı olursa.
                if (response.isSuccessful()) {
                    progressBar.setVisibility(View.GONE);
                    recyclerviewLstMovie.setVisibility(View.VISIBLE);
                    adapter.updateMovieList(response.body().getResults());

                    // Cache
                    Hawk.put(Constants.CACHE_POPULAR, response.body().getResults());


                } else {
                    if (!connectionControl()) {
                        progressBar.setVisibility(View.GONE);
                        List<MovieMainResults> movieMainResults = Hawk.get(Constants.CACHE_POPULAR);
                        if (movieMainResults != null) {
                            error_dataTransfer.setVisibility(View.GONE);
                            adapter.updateMovieList(movieMainResults);
                        } else {
                            error_dataTransfer.setVisibility(View.VISIBLE);
                        }
                        int statusCode = response.code();
                        Log.e("MainActivity-Retrofit", "" + statusCode);
                    }

                }


            }

            // Bağlantı başarısız olursa
            @Override
            public void onFailure(Call<MovieMainRoot> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                error_dataTransfer.setVisibility(View.VISIBLE);
                Log.e("MainActivity", t.getMessage());
            }
        });


    }

    private void loadTopRatedMoviesListData() {


        RetrofitGetData retrofitService = RetrofitClient.getRetrofitInstance().create(RetrofitGetData.class);

        Call<MovieMainRoot> call = retrofitService.getTopRatedMovies(Constants.API_KEY);

        call.enqueue(new Callback<MovieMainRoot>() {
            @Override
            public void onResponse(Call<MovieMainRoot> call, Response<MovieMainRoot> response) {
                // Bağlantı Başarılı olursa.
                if (response.isSuccessful()) {
                    progressBar.setVisibility(View.GONE);
                    recyclerviewLstMovie.setVisibility(View.VISIBLE);
                    adapter.updateMovieList(response.body().getResults());

                    // Cache
                    Hawk.put(Constants.CACHE_TOP_RATED, response.body().getResults());


                } else {
                    if (!connectionControl()) {
                        progressBar.setVisibility(View.GONE);
                        List<MovieMainResults> movieMainResults = Hawk.get(Constants.CACHE_TOP_RATED);
                        if (movieMainResults != null) {
                            error_dataTransfer.setVisibility(View.GONE);
                            adapter.updateMovieList(movieMainResults);
                        } else {
                            error_dataTransfer.setVisibility(View.VISIBLE);
                        }
                        int statusCode = response.code();
                        Log.e("MainActivity-Retrofit", "" + statusCode);
                    }

                }


            }

            // Bağlantı başarısız olursa
            @Override
            public void onFailure(Call<MovieMainRoot> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                error_dataTransfer.setVisibility(View.VISIBLE);
                Log.e("MainActivity", t.getMessage());
            }
        });


    }

    private void loadUpcomingMoviesListData() {


        RetrofitGetData retrofitService = RetrofitClient.getRetrofitInstance().create(RetrofitGetData.class);

        Call<MovieMainRoot> call = retrofitService.getUpcomingMovies(Constants.API_KEY);

        call.enqueue(new Callback<MovieMainRoot>() {
            @Override
            public void onResponse(Call<MovieMainRoot> call, Response<MovieMainRoot> response) {
                // Bağlantı Başarılı olursa.
                if (response.isSuccessful()) {
                    progressBar.setVisibility(View.GONE);
                    recyclerviewLstMovie.setVisibility(View.VISIBLE);
                    adapter.updateMovieList(response.body().getResults());

                    // Cache
                    Hawk.put(Constants.CACHE_UPCOMING, response.body().getResults());

                } else {
                    if (!connectionControl()) {
                        progressBar.setVisibility(View.GONE);
                        List<MovieMainResults> movieMainResults = Hawk.get(Constants.CACHE_UPCOMING);
                        if (movieMainResults != null) {
                            error_dataTransfer.setVisibility(View.GONE);
                            adapter.updateMovieList(movieMainResults);
                        } else {
                            error_dataTransfer.setVisibility(View.VISIBLE);
                        }
                        int statusCode = response.code();
                        Log.e("MainActivity-Retrofit", "" + statusCode);
                    }


                }


            }

            // Bağlantı başarısız olursa
            @Override
            public void onFailure(Call<MovieMainRoot> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                error_dataTransfer.setVisibility(View.VISIBLE);
                Log.e("MainActivity", t.getMessage());
            }
        });


    }


    @Override
    public void onTextViewMenuClicked(String menuType, boolean isChecked) {


        if (menuType.equals(Constants.I_POPULAR) && isChecked) {


            loadPopularMovieListData();


        } else if (menuType.equals(Constants.I_TOP_RATED) && isChecked) {

            loadTopRatedMoviesListData();

        } else if (menuType.equals(Constants.I_UPCOMING) && isChecked) {

            loadUpcomingMoviesListData();

        } else {
            Log.e("MainActivity-MenuClick", "Tıklama İşleyici Hatası");
        }
    }

    private boolean connectionControl() {
        ConnectivityManager cm =
                (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        return isConnected;

    }
}
