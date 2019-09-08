package com.ardakazanci.popularmovielist.ui.detail;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.ardakazanci.popularmovielist.R;
import com.ardakazanci.popularmovielist.common.Constants;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.jaeger.library.StatusBarUtil;


public class DetailActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView movieBackDrop;
    // GET INTENT VARIABLE
    private String intentGetMovieName;
    private String intentGetMovieDate;
    private float intentGetMovieVoteAverage;
    private String intentGetMoviePosterPath;
    private String intentGetMovieBackdropPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle intentExtras = getIntent().getExtras();
        if (intentExtras == null) {
            return;
        }

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

            /*Log.i("D",intentGetMovieName);
            Log.i("D",intentGetMovieDate);
            Log.i("D",intentGetMovieVoteAverage+"");
            Log.i("D",intentGetMoviePosterPath);
            Log.i("D",intentGetMovieBackdropPath);*/
        } else {

            Log.e("DetailActivity", "Intent Eksik Veri");

        }


        initViews();

        // Eski sürümler için destekleyici toolbar
        setSupportActionBar(toolbar);
        // Toolbar içinde yer alacak değer
        collapsingToolbarLayout.setTitle("Suicide Squad");
        // Transparan ve etkileşim dinleyici ile statusbar ayarları
        statusBarSettings();


    }

    // Geri butonuna tıklandığında (home)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
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

    private void initViews() {
        toolbar = findViewById(R.id.detailToolbar);
        collapsingToolbarLayout = findViewById(R.id.detailCollapsingToolbarLayout);
    }

}
