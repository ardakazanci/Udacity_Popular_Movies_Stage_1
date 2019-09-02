package com.ardakazanci.popularmovielist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ardakazanci.popularmovielist.R;
import com.ardakazanci.popularmovielist.common.Constants;
import com.ardakazanci.popularmovielist.model.main.MovieMainResults;
import com.ardakazanci.popularmovielist.model.main.MovieMainRoot;
import com.bumptech.glide.Glide;

import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.CustomViewHolder> {

    private List<MovieMainResults> movieDataList;
    private Context mContext;


    public MovieListAdapter(List<MovieMainResults> movieDataList, Context mContext) {
        this.movieDataList = movieDataList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_movie_layout, parent, false);
        return new CustomViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

        MovieMainResults movie = movieDataList.get(position);

        StringBuilder sb = new StringBuilder(Constants.BASE_IMAGE_POSTER_URL).append(movie.getPoster_path());

        Glide.with(holder.itemView.getContext()).load(sb.toString()).into(holder.movie_image);

        holder.movie_rate.setText(Float.toString(movie.getVote_average()));
        holder.movie_title.setText(movie.getOriginal_title());
        holder.movie_date.setText(movie.getRelease_date());

    }

    @Override
    public int getItemCount() {
        return movieDataList.size();
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {

        ImageView movie_image;
        TextView movie_title, movie_date, movie_rate;


        public CustomViewHolder(View itemView) {
            super(itemView);

            movie_image = itemView.findViewById(R.id.imageview_item_movie_poster);
            movie_title = itemView.findViewById(R.id.textview_item_movie_title);
            movie_date = itemView.findViewById(R.id.textview_item_movie_date);
            movie_rate = itemView.findViewById(R.id.textview_item_movie_rate);

        }
    }

    public void updateMovieList(List<MovieMainResults> items) {
        movieDataList = items;
        notifyDataSetChanged();
    }
}
