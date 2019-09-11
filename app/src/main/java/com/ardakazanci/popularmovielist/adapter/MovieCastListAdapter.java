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
import com.ardakazanci.popularmovielist.model.detail.MovieDetailCastResult;
import com.bumptech.glide.Glide;

import java.util.List;

public class MovieCastListAdapter extends RecyclerView.Adapter<MovieCastListAdapter.CustomViewHolder> {

    private List<MovieDetailCastResult> movieDetailCastResults;

    private Context context;

    public MovieCastListAdapter(List<MovieDetailCastResult> movieDetailCastResults, Context context) {
        this.movieDetailCastResults = movieDetailCastResults;
        this.context = context;

    }


    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_lay_cast, parent, false);
        return new CustomViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

        MovieDetailCastResult castInfo = movieDetailCastResults.get(position);
        StringBuilder sb = new StringBuilder(Constants.BASE_IMAGE_POSTER_URL).append(castInfo.getProfile_path());


        holder.cast_name.setText(castInfo.getName());
        Glide.with(holder.itemView.getContext()).load(sb.toString()).into(holder.cast_profile_image);


    }

    @Override
    public int getItemCount() {
        return movieDetailCastResults.size();
    }


    public void updateMovieCastList(List<MovieDetailCastResult> items) {

        movieDetailCastResults = items;
        notifyDataSetChanged();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        ImageView cast_profile_image;
        TextView cast_name;

        public CustomViewHolder(View itemView) {
            super(itemView);

            cast_profile_image = itemView.findViewById(R.id.detailItemCastImage);
            cast_name = itemView.findViewById(R.id.detailItemCastName);


        }
    }


}
