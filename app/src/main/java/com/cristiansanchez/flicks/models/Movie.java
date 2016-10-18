package com.cristiansanchez.flicks.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;

/**
 * Created by kristianss27 on 10/10/16.
 */
@Parcel
public class Movie {

    String id;
    String posterPath;
    String adult;
    String overView;
    String releaseDate;
    String originalTitle;
    String backDropPath;
    long voteAverage;
    String popularity;
    String originalLanguage;
    String video;
    boolean moreAverage;
    int orientation=0;
    public ViewType viewType;

    public Movie(){}

    public enum ViewType{
        REGULAR,POPULAR
    }

    public Movie(JSONObject jsonObject) throws JSONException{
        this.id = jsonObject.getString("id");
        this.originalTitle = jsonObject.getString("original_title");
        this.posterPath = jsonObject.getString("poster_path");
        this.overView = jsonObject.getString("overview");
        this.releaseDate = jsonObject.getString("release_date");
        this.backDropPath = jsonObject.getString("backdrop_path");
        this.voteAverage = jsonObject.getLong("vote_average");
        this.originalLanguage = jsonObject.getString("original_language");
        this.video = jsonObject.getString("video");
        this.popularity = jsonObject.getString("popularity");
        boolean isPopular = this.voteAverage>=5?true:false;
        this.viewType = isPopular?ViewType.POPULAR:ViewType.REGULAR;

    }

    public static ArrayList<Movie> fromJSONArray(JSONArray jsonArray){
        JSONObject jsonObject = null;
        ArrayList<Movie> moviesArrayList = new ArrayList<Movie>(jsonArray.length());
        for (int i = 0; i < jsonArray.length(); i++) {
            try{
                Movie movie = new Movie(jsonArray.getJSONObject(i));
                //movie.setVideo((i<2)?"true":"false");
                moviesArrayList.add(movie);
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

    public String getBackDropPath() {
        return String.format("https://image.tmdb.org/t/p/w780/%s",backDropPath);
    }

    public long getVoteAverage() {
        return voteAverage;
    }

    public boolean isMoreAverage() {
        return moreAverage;
    }

    public String getPopularity() {
        return popularity;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

}
