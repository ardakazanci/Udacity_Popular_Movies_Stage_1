package com.ardakazanci.popularmovielist.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.ardakazanci.popularmovielist.Interface.BottomSheetListener;
import com.ardakazanci.popularmovielist.Interface.OnItemClickListener;
import com.ardakazanci.popularmovielist.R;
import com.ardakazanci.popularmovielist.adapter.MovieListAdapter;
import com.ardakazanci.popularmovielist.api.RetrofitClient;
import com.ardakazanci.popularmovielist.api.RetrofitGetData;
import com.ardakazanci.popularmovielist.common.Constants;
import com.ardakazanci.popularmovielist.model.main.MovieMainResults;
import com.ardakazanci.popularmovielist.model.main.MovieMainRoot;
import com.ardakazanci.popularmovielist.ui.detail.DetailActivity;
import com.jaeger.library.StatusBarUtil;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements BottomSheetListener, OnItemClickListener {

    private MovieListAdapter adapter;
    private RecyclerView recyclerviewLstMovie;
    private ProgressBar progressBar;
    private ImageButton menuButton;
    private LinearLayout error_dataTransfer;
    private List<MovieMainResults> movieMainRoots;
    private double movie_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // UI Component Intiliaze
        initViews();

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuBottomSheetFragment menuBottomSheetFragment = new MenuBottomSheetFragment();
                menuBottomSheetFragment.show(getSupportFragmentManager(), "MenuBottomSheetMenu");
            }
        });


        adapter = new MovieListAdapter(new ArrayList<MovieMainResults>(0), getApplicationContext(), this);

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
                    movieMainRoots = response.body().getResults();

                } else {
                    if (!connectionControl()) {
                        Log.e("MainActivity", Constants.ERROR_1);
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
                    movieMainRoots = response.body().getResults();

                } else {
                    if (!connectionControl()) {
                        Log.e("MainActivity", Constants.ERROR_1);
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
                    movieMainRoots = response.body().getResults();
                } else {
                    if (!connectionControl()) {
                        Log.e("MainActivity", Constants.ERROR_1);
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

    @Override
    public void onClick(View view, int position, ImageView imageView) {
        Intent i = new Intent(this, DetailActivity.class);

        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this,
                        imageView,
                        Objects.requireNonNull(ViewCompat.getTransitionName(imageView)));
        Log.e("MOVIEID", movieMainRoots.get(position).getId() + "");
        i.putExtra(Constants.INTENT_MOVIE_NAME, movieMainRoots.get(position).getOriginal_title());
        i.putExtra(Constants.INTENT_MOVIE_DATE, movieMainRoots.get(position).getRelease_date());
        i.putExtra(Constants.INTENT_MOVIE_VOTE_AVERAGE, movieMainRoots.get(position).getVote_average());
        i.putExtra(Constants.INTENT_MOVIE_POSTER, movieMainRoots.get(position).getPoster_path());
        i.putExtra(Constants.INTENT_MOVIE_BACKDROP, movieMainRoots.get(position).getBackdrop_path());
        i.putExtra(Constants.INTENT_MOVIE_ID, movieMainRoots.get(position).getId());
        startActivity(i, options.toBundle());
    }

    private void initViews() {
        recyclerviewLstMovie = findViewById(R.id.recyclerview_movies);
        error_dataTransfer = findViewById(R.id.error_dataTransfer);
        progressBar = findViewById(R.id.progressBar);
        menuButton = findViewById(R.id.imagebutton_check_list_type);

    }

    private void statusBarSettings() {
        // Transparent statusbar
        StatusBarUtil.setTransparent(this);
        // Activity UI Settings Flags
        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY; // En Kritik bayrak. eğer ilk temas sırasınıda dahil etmek istiyorsak kullanıyoruz.

        final View decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener
                (new View.OnSystemUiVisibilityChangeListener() {
                    @Override
                    public void onSystemUiVisibilityChange(int visibility) {
                        if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                            decorView.setSystemUiVisibility(flags);
                        }
                    }
                });

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }


}
