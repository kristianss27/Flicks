package com.cristiansanchez.flicks.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cristiansanchez.flicks.R;
import com.cristiansanchez.flicks.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by kristianss27 on 10/10/16.
 */
public class MovieArrayAdapter extends ArrayAdapter<Movie> {

    private static class ViewHolder{
        ImageView imgMovie;
        TextView tvTitle;
        TextView tvReview;
    }

    public MovieArrayAdapter(Context context, List<Movie> movieList) {
        super(context, android.R.layout.simple_list_item_1,movieList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Movie movie = getItem(position);
        ViewHolder viewHolder;

        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.movie_item,parent,false);
            viewHolder.imgMovie = (ImageView) convertView.findViewById(R.id.imgMovie);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.tvReview = (TextView) convertView.findViewById(R.id.tvReview);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvTitle.setText(movie.getOriginalTitle());
        viewHolder.tvReview.setText(movie.getOverView());

        Picasso.with(getContext()).load(movie.getPosterPath()).into(viewHolder.imgMovie);

        return convertView;
    }
}
