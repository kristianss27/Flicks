package com.cristiansanchez.flicks.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;

/**
 * Created by kristianss27 on 10/17/16.
 */
@Parcel
public class MovieTrailers {
    String name;
    String size;
    String source;
    String type;

    public MovieTrailers(){}

    public MovieTrailers(JSONObject jsonObject) throws JSONException{
        this.name = jsonObject.getString("name");
        this.size = jsonObject.getString("size");
        this.source = jsonObject.getString("source");
        this.type = jsonObject.getString("type");
    }

    public static ArrayList<MovieTrailers> fromJSONArray(JSONArray jsonArray){
        ArrayList<MovieTrailers> arrayList = new ArrayList<MovieTrailers>(jsonArray.length());
        for(int i = 0;i < jsonArray.length(); i++){
            try {
                arrayList.add(new MovieTrailers(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Log.d("MODEL LIST SIZE","Size: "+arrayList.size());
        return arrayList;
    }

    public String getName() {
        return name;
    }

    public String getSize() {
        return size;
    }

    public String getSource() {
        return source;
    }

    public String getType() {
        return type;
    }
}
