package com.cristiansanchez.flicks.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by kristianss27 on 10/10/16.
 */
public class Movie {

    private String id;
    private String posterPath;
    private String adult;
    private String overView;
    private String releaseDate;
    private String originalTitle;

    public Movie(JSONObject jsonObject) throws JSONException{
        this.originalTitle = jsonObject.getString("original_title");
        this.posterPath = jsonObject.getString("poster_path");
        this.overView = jsonObject.getString("overview");
    }

    public static ArrayList<Movie> fromJSONArray(JSONArray jsonArray){
        JSONObject jsonObject = null;
        ArrayList<Movie> moviesArrayList = new ArrayList<Movie>(jsonArray.length());
        for (int i = 0; i < jsonArray.length(); i++) {
            try{
               moviesArrayList.add(new Movie(jsonArray.getJSONObject(i)));
            }
            catch (JSONException e){
                e.printStackTrace();
            }
        }
        return moviesArrayList;
    }

    public String getId() {
        return id;
    }

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s",posterPath);
    }

    public String getAdult() {
        return adult;
    }

    public String getOverView() {
        return overView;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }
}
