package com.cristiansanchez.flicks.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.cristiansanchez.flicks.R;
import com.cristiansanchez.flicks.models.Movie;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

public class MovieDetailActivity extends AppCompatActivity {

    public ImageView imageView;
    public TextView tvTitle;
    public TextView tvReleaseDate;
    public TextView tvReview;
    public RatingBar rbVoteAverage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        imageView = (ImageView) findViewById(R.id.imgMovie);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvReleaseDate = (TextView) findViewById(R.id.tvReleaseDate);
        tvReview = (TextView) findViewById(R.id.tvReview);
        RatingBar rbVoteAverage = (RatingBar) findViewById(R.id.rbVoteAverage);

        Movie movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra("movie"));

        rbVoteAverage.setRating(movie.getVoteAverage());
        tvTitle.setText(movie.getOriginalTitle());
        tvReleaseDate.setText(movie.getReleaseDate());
        tvReview.setText(movie.getOverView());
        tvReview.setMovementMethod(new ScrollingMovementMethod());


        Picasso.with(this)
                .load(movie.getBackDropPath())
                .placeholder(R.drawable.loading)
                .error(R.drawable.noimage)
                .into(imageView);

    }
}
