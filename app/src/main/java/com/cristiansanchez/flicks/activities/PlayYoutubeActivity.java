package com.cristiansanchez.flicks.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cristiansanchez.flicks.R;
import com.cristiansanchez.flicks.models.Movie;
import com.cristiansanchez.flicks.models.MovieTrailers;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class PlayYoutubeActivity extends YouTubeBaseActivity {

    public YouTubePlayerView youTubePlayerView;
    public ArrayList<MovieTrailers> trailersArrayList;
    public MovieTrailers movieTrailers;

    @BindView(R.id.tvTitle) TextView tvTitle;
    @BindView(R.id.tvReleaseDate) TextView tvReleaseDate;
    @BindView(R.id.tvReview) TextView tvReview;
    @BindView(R.id.rbVoteAverage) RatingBar rbVoteAverage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_youtube);
        ButterKnife.bind(this);

        Movie movie = Parcels.unwrap(getIntent().getParcelableExtra("movie"));
        rbVoteAverage.setRating(movie.getVoteAverage());
        tvTitle.setText(movie.getOriginalTitle());
        tvReleaseDate.setText(movie.getReleaseDate());
        tvReview.setText(movie.getOverView());


        trailersArrayList = new ArrayList<>();
        String url = getResources().getString(R.string.movie_api_base)+(movie.getVideo().equalsIgnoreCase("true")?
                movie.getId():"209112")+"/trailers?api_key="
                +getResources().getString(R.string.movie_api_key);
        Log.d("API LINK","url:"+url);
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray jsonArray = null;
                try {
                    jsonArray = response.getJSONArray("youtube");
                    trailersArrayList.addAll(MovieTrailers.fromJSONArray(jsonArray));
                    Log.d("DEBUG","TRAILER SIZE:"+trailersArrayList.size());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                youTubePlayerView = (YouTubePlayerView) findViewById(R.id.player);

                if(trailersArrayList.size()>0){
                    movieTrailers = (MovieTrailers) trailersArrayList.get(0);
                    Log.d("DEBUG","SOURCE: "+movieTrailers.getSource());

                    youTubePlayerView.initialize(getResources().getString(R.string.youtube_api_key),
                    new YouTubePlayer.OnInitializedListener() {
                        @Override
                        public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                            YouTubePlayer youTubePlayer, boolean b) {

                            youTubePlayer.cueVideo(movieTrailers.getSource());
                        }
                        @Override
                        public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                            YouTubeInitializationResult youTubeInitializationResult) {
                            Toast.makeText(PlayYoutubeActivity.this, "Youtube Failed!", Toast.LENGTH_SHORT).show();
                        }
                    });

                }



            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });



    }
}
