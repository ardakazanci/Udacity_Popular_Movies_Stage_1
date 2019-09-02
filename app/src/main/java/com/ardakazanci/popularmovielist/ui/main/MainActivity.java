package com.ardakazanci.popularmovielist.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.ardakazanci.popularmovielist.R;
import com.ardakazanci.popularmovielist.adapter.MovieListAdapter;
import com.ardakazanci.popularmovielist.api.RetrofitClient;
import com.ardakazanci.popularmovielist.api.RetrofitGetData;
import com.ardakazanci.popularmovielist.common.Constants;
import com.ardakazanci.popularmovielist.model.main.MovieMainResults;
import com.ardakazanci.popularmovielist.model.main.MovieMainRoot;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private MovieListAdapter adapter;
    private RecyclerView recyclerviewLstMovie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerviewLstMovie = findViewById(R.id.recyclerview_movies);

        adapter = new MovieListAdapter(new ArrayList<MovieMainResults>(0), getApplicationContext());

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2, RecyclerView.VERTICAL, false);

        recyclerviewLstMovie.setLayoutManager(layoutManager);

        recyclerviewLstMovie.setAdapter(adapter);

        recyclerviewLstMovie.setHasFixedSize(true);

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
                    adapter.updateMovieList(response.body().getResults());
                } else {
                    int statusCode = response.code();
                    Log.e("MainActivity-Retrofit", "" + statusCode);
                }


            }

            // Bağlantı başarısız olursa
            @Override
            public void onFailure(Call<MovieMainRoot> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Film bilgileri alınamadı", Toast.LENGTH_SHORT).show();
                Log.e("MainActivity", t.getMessage());
            }
        });


    }


}
