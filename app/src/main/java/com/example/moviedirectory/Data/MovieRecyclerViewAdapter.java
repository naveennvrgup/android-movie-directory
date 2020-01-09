package com.example.moviedirectory.Data;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviedirectory.Model.Movie;
import com.example.moviedirectory.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<Movie> movieList;
    private View itemView;


    public MovieRecyclerViewAdapter(Context context, List<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MovieRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = movieList.get(position);

        holder.title.setText(movie.getTitle());
        holder.year.setText(movie.getYear());
        holder.type.setText(movie.getMovieType());

        Picasso.with(context)
                .load(movie.getPoster())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.poster);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title, year, type;
        ImageView poster;

        public ViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.movieTitleID);
            poster = itemView.findViewById(R.id.movieImageID);
            year = itemView.findViewById(R.id.movieReleaseID);
            type = itemView.findViewById(R.id.movieCatID);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("movie", "clicked");
                }
            });
        }

        @Override
        public void onClick(View v) {
            Log.i("movie", "clicked");
        }
    }
}
