package com.cristiansanchez.flicks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.cristiansanchez.flicks.adapters.MovieArrayAdapter;
import com.cristiansanchez.flicks.adapters.MovieArrayAdapterHeterogenous;
import com.cristiansanchez.flicks.models.Movie;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MovieActivity extends AppCompatActivity {

    public ArrayList<Movie> movies;
    public ListView listView;
    public MovieArrayAdapter movieArrayAdapter;
    public MovieArrayAdapterHeterogenous movieArrayAdapterHeterogenous;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        listView = (ListView) findViewById(R.id.lvMovies);
        movies = new ArrayList<>();
        /*We have created a ArrayAdapter to our listView (Homogenous)
        movieArrayAdapter = new MovieArrayAdapter(this,movies);
        listView.setAdapter(movieArrayAdapter);
        youtube api key AIzaSyBB9ZVodyVmdjOuME9RPf1NC-OJE1dK4kQ 
        */

        //In order to control different view layouts is necesary implement a heterogenous listview with the right ArrayAdapter
        movieArrayAdapterHeterogenous = new MovieArrayAdapterHeterogenous(this,movies);
        listView.setAdapter(movieArrayAdapterHeterogenous);
        String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray jsonArray = null;
                try {
                    jsonArray = response.getJSONArray("results");
                    movies.addAll(Movie.fromJSONArray(jsonArray));
                    movieArrayAdapterHeterogenous.notifyDataSetChanged();
                    Log.d("DEBUG",movies.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }
}
