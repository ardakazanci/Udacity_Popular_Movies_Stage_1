package com.ardakazanci.popularmovielist.ui.detail;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ardakazanci.popularmovielist.R;
import com.ardakazanci.popularmovielist.api.RetrofitClient;
import com.ardakazanci.popularmovielist.api.RetrofitGetData;
import com.ardakazanci.popularmovielist.common.Constants;
import com.ardakazanci.popularmovielist.model.detail.MovieDetailRoot;
import com.ardakazanci.popularmovielist.ui.main.MainActivity;
import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.jaeger.library.StatusBarUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailActivity extends AppCompatActivity {
    private AppBarLayout detailAppBarLayout;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView movieBackdropImage, moviePosterImage;
    private TextView movieName, movieDate;
    private TextView detailTxtVoteAverage;
    private ImageButton detailFavoriteButton;
    private TextView detailsMovieTxtOverview;
    private TextView detailsMovieTxtType;
    private TextView detailsMovieTagLine;

    // GET INTENT VARIABLE
    private double intentGetMovieId;
    private String intentGetMovieName;
    private String intentGetMovieDate;
    private float intentGetMovieVoteAverage;
    private String intentGetMoviePosterPath;
    private String intentGetMovieBackdropPath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);
        initViews();

        /* Gelen Verilerin Okunması ve eşleştirilmesini sağlayan metot */
        /* Bundle Başlangıç */
        Bundle intentExtras = getIntent().getExtras();
        if (intentExtras == null) {
            return;
        }

        intentGetMovieId = intentExtras.getDouble(Constants.INTENT_MOVIE_ID, 0);
        intentGetMovieName = intentExtras.getString(Constants.INTENT_MOVIE_NAME);
        intentGetMovieDate = intentExtras.getString(Constants.INTENT_MOVIE_DATE);
        intentGetMovieVoteAverage = intentExtras.getFloat(Constants.INTENT_MOVIE_VOTE_AVERAGE, 0);
        intentGetMoviePosterPath = intentExtras.getString(Constants.INTENT_MOVIE_POSTER);
        intentGetMovieBackdropPath = intentExtras.getString(Constants.INTENT_MOVIE_BACKDROP);


        if (intentGetMovieName != null &&
                intentGetMovieDate != null &&
                intentGetMoviePosterPath != null &&
                intentGetMovieBackdropPath != null

        ) {

            setImageResource(intentGetMoviePosterPath, moviePosterImage);
            setImageResource(intentGetMovieBackdropPath, movieBackdropImage);
            movieName.setText(intentGetMovieName);
            movieDate.setText(intentGetMovieDate);
            collapsingToolbarLayout.setTitleEnabled(false);
            toolbarTextShowSettings(movieName.getText().toString());
            detailTxtVoteAverage.setText(String.valueOf(intentGetMovieVoteAverage));
            // getMovieDetail
            Log.e("DetailGetter", "" + intentGetMovieId);
            getMovieDetailsResults(intentGetMovieId);


        } else {

            Log.e("DetailActivity", "Intent Eksik Veri");

        }
        /* Bundle Son */


        // Eski sürümler için destekleyici toolbar
        setSupportActionBar(toolbar);
        // Toolbar içinde yer alacak değer
        collapsingToolbarLayout.setTitle("Suicide Squad");
        // Transparan ve etkileşim dinleyici ile statusbar ayarları
        statusBarSettings();


    }

    /* BackPressed durumunu ele alan metot. */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            //finish();
            onBackPressed();


        }
        return super.onOptionsItemSelected(item);
    }

    /* Statusbar ayarlaması yapan fonksiyon (Transparent)*/
    private void statusBarSettings() {
        // Transparent statusbar
        StatusBarUtil.setTransparent(this);
        // Activity UI Settings Flags
        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
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

    /* DetailActivity içerisinde ki view elemanlarının yüklenmesini sağlayan fonksiyon */
    private void initViews() {
        toolbar = findViewById(R.id.detailToolbar);
        collapsingToolbarLayout = findViewById(R.id.detailCollapsingToolbarLayout);

        movieBackdropImage = findViewById(R.id.detailMovieBackdrop);
        movieDate = findViewById(R.id.detailMovieDate);
        movieName = findViewById(R.id.detailMovieName);
        moviePosterImage = findViewById(R.id.detailMoviePoster);
        detailAppBarLayout = findViewById(R.id.detailAppBarLayout);

        detailTxtVoteAverage = findViewById(R.id.detailTextVoteAverage);
        detailFavoriteButton = findViewById(R.id.detailIButtonFavorite);
        detailsMovieTxtOverview = findViewById(R.id.detailsMovieOverview);
        detailsMovieTxtType = findViewById(R.id.detailsMovieTxtType);
        detailsMovieTagLine = findViewById(R.id.detailsMovieTagLine);

    }

    /* İlgili pathlerde imageların gösterilmesini sağlayan fonksiyon */
    private void setImageResource(String imageUrl, ImageView imageContainer) {
        Glide
                .with(this)
                .load(Constants.BASE_IMAGE_POSTER_URL + imageUrl)
                .centerCrop()
                .into(imageContainer);

    }

    /* AppBarLayout için title gösterim ayarlaması sağlayan fonksiyon */
    private void toolbarTextShowSettings(final String text) {
        detailAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {

            boolean isVisible = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    toolbar.setTitle(text);
                    isVisible = true;
                } else if (isVisible) {
                    toolbar.setTitle("");
                    isVisible = false;
                }
            }
        });
    }


    private void getMovieDetailsResults(double movieID) {

        int movie_id = (int) movieID;
        RetrofitGetData rService = RetrofitClient.getRetrofitInstance().create(RetrofitGetData.class);
        Call<MovieDetailRoot> call = rService.getMovieIdDetail(movie_id, Constants.API_KEY);

        call.enqueue(new Callback<MovieDetailRoot>() {
            @Override
            public void onResponse(Call<MovieDetailRoot> call, Response<MovieDetailRoot> response) {

                if (response.isSuccessful()) {
                    Log.i("DetailActivity", response.body().getMovieOverview());
                    detailsMovieTxtOverview.setText(response.body().getMovieOverview());
                    Log.i("DetailActivity", "MovieType->" + response.body().getMovieDetailGenresResults().get(0).getGenresName());
                    detailsMovieTxtType.setText(response.body().getMovieDetailGenresResults().get(0).getGenresName());
                    detailsMovieTagLine.setText(response.body().getTagline());
                } else {

                    Log.e("DetailActivity", "OnResponse Problem");
                }

            }

            @Override
            public void onFailure(Call<MovieDetailRoot> call, Throwable t) {
                Log.e("DetailActivity", "Retrofit Problem" + t.getMessage());
            }
        });


    }

}
