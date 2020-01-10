package com.example.moviedirectory.Activities;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.moviedirectory.Model.Movie;
import com.example.moviedirectory.Util.Constants;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moviedirectory.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MovieDetailActivity extends AppCompatActivity {
    private Movie movie;
    private ImageView poster;
    private TextView title, year, director, actors, category, rating, writers, plot, boxoffice, runtime;
    private RequestQueue requestQueue;
    private String movieID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        requestQueue = Volley.newRequestQueue(this);

        movie = (Movie) getIntent().getSerializableExtra("movie");
        movieID = movie.getImdbId();

        setupUI();
        getmovieDetails();
    }

    public void setupUI() {
        poster = findViewById(R.id.movieDetailImage);
        title = findViewById(R.id.movieDetailTitle);
        year = findViewById(R.id.movieDetailRelease);
        director = findViewById(R.id.movieDetailDirector);
        actors = findViewById(R.id.movieDetailActors);
        category = findViewById(R.id.movieDetailCat);
        rating = findViewById(R.id.movieDetailRating);
        writers = findViewById(R.id.movieDetailWriters);
        plot = findViewById(R.id.movieDetailPlot);
        boxoffice = findViewById(R.id.movieDetailBoxOffice);
        runtime = findViewById(R.id.movieDetailRuntime);
    }

    public void getmovieDetails() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                Constants.URL + movieID + Constants.API_KEY,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.has("Ratings")) {
                                Picasso.with(getApplicationContext())
                                        .load(response.getString("Poster"))
                                        .into(poster);

                                title.setText(response.getString("Title"));
                                year.setText(response.getString("Released"));
                                director.setText(response.getString("Director"));
                                writers.setText(response.getString("Writer"));
                                plot.setText(response.getString("Plot"));
                                runtime.setText(response.getString("Runtime"));
                                actors.setText(response.getString("Actors"));
                                boxoffice.setText(response.getString("BoxOffice"));
                                category.setText(response.getString("Genre"));
                                rating.setText(response.getString("imdbRating"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }
}
