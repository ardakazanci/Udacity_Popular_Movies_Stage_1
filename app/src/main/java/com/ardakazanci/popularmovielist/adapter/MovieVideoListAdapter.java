package com.ardakazanci.popularmovielist.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ardakazanci.popularmovielist.R;
import com.ardakazanci.popularmovielist.api.RetrofitClient;
import com.ardakazanci.popularmovielist.api.RetrofitGetData;
import com.ardakazanci.popularmovielist.common.Constants;
import com.ardakazanci.popularmovielist.model.detail.MovieDetailCastResult;
import com.ardakazanci.popularmovielist.model.detail.MovieDetailRoot;
import com.ardakazanci.popularmovielist.model.detail.MovieDetailVideoResult;
import com.ardakazanci.popularmovielist.model.detail.MovieDetailVideoRoot;
import com.ardakazanci.popularmovielist.util.UtilFunctions;
import com.bumptech.glide.Glide;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieVideoListAdapter extends RecyclerView.Adapter<MovieVideoListAdapter.CustomViewHolder> {

    private List<MovieDetailVideoResult> movieDetailVideoResults;
    private Context context;
    private double movieİd;

    public MovieVideoListAdapter(List<MovieDetailVideoResult> movieDetailVideoResults, Context context, double movieİd) {
        this.movieDetailVideoResults = movieDetailVideoResults;
        this.context = context;
        this.movieİd = movieİd;

    }


    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_lay_video, parent, false);
        return new CustomViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

        MovieDetailVideoResult videoInfo = movieDetailVideoResults.get(position);
        if (videoInfo.getMovieVideoSite().toLowerCase().equals("youtube")) {
            StringBuilder sb = new StringBuilder(Constants.BASE_VIDEO_URL).append(videoInfo.getMovieVideoKey());
            Log.e("AdapterVideo", sb.toString());

            getMovieDetailsVideo(this.movieİd, holder.videoPoster, holder.itemView.getContext());


        }


    }

    @Override
    public int getItemCount() {
        return movieDetailVideoResults.size();
    }


    public void updateMovieVideo(List<MovieDetailVideoResult> items) {

        movieDetailVideoResults = items;
        notifyDataSetChanged();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        ImageView videoPlayButton;
        ImageView videoPoster;


        public CustomViewHolder(final View itemView) {
            super(itemView);

            //videoView = itemView.findViewById(R.id.detailVideoCover);
            videoPlayButton = itemView.findViewById(R.id.detailMovieVideoPlayButton);
            videoPoster = itemView.findViewById(R.id.detailMovieVideoPoster);

            videoPlayButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    UtilFunctions.watchYoutubeVideo(itemView.getContext(), movieDetailVideoResults.get(getAdapterPosition()).getMovieVideoKey());


                }
            });


        }
    }

    private void getMovieDetailsVideo(double intentGetMovieId, final ImageView imageView, final Context context) {

        int movieIntId = (int) intentGetMovieId;

        RetrofitGetData rService = RetrofitClient.getRetrofitInstance().create(RetrofitGetData.class);

        Call<MovieDetailRoot> getMovieDetail = rService.getMovieIdDetail(movieIntId, Constants.API_KEY);

        getMovieDetail.enqueue(new Callback<MovieDetailRoot>() {
            @Override
            public void onResponse(Call<MovieDetailRoot> call, Response<MovieDetailRoot> response) {


                Glide
                        .with(context)
                        .load(Constants.BASE_IMAGE_POSTER_URL + response.body().getDetail_backdrop_path())
                        .centerCrop()
                        .into(imageView);


            }

            @Override
            public void onFailure(Call<MovieDetailRoot> call, Throwable t) {
                Log.e("DetailActivity", "" + t.getMessage());
            }
        });


    }


}
