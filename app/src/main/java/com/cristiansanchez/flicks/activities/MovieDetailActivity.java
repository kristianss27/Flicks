package com.cristiansanchez.flicks.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.cristiansanchez.flicks.R;
import com.cristiansanchez.flicks.models.Movie;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailActivity extends AppCompatActivity {

    @BindView(R.id.imgMovie) ImageView imageView;
    @BindView(R.id.tvTitle) TextView tvTitle;
    @BindView(R.id.tvReleaseDate) TextView tvReleaseDate;
    @BindView(R.id.tvReview) TextView tvReview;
    @BindView(R.id.rbVoteAverage) RatingBar rbVoteAverage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);

        Movie movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra("movie"));

        rbVoteAverage.setRating(movie.getVoteAverage());
        tvTitle.setText(movie.getOriginalTitle());
        tvReleaseDate.setText(movie.getReleaseDate());
        tvReview.setText(movie.getOverView());

        Picasso.with(this)
                .load(movie.getBackDropPath())
                .fit()
                .placeholder(R.drawable.loading_land_scape_white)
                .error(R.drawable.image_not_found)
                .into(imageView);

    }
}
