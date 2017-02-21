package com.cristiansanchez.flicks.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cristiansanchez.flicks.R;
import com.cristiansanchez.flicks.activities.MovieDetailActivity;
import com.cristiansanchez.flicks.models.Movie;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.List;

/**
 * Created by kristianss27 on 10/10/16.
 */
public class MovieArrayAdapter extends ArrayAdapter<Movie>{

    private static class ViewHolder{
        ImageView imgMovie;
        TextView tvTitle;
        TextView tvReview;
        String orientation;
    }

    public MovieArrayAdapter(Context context, List<Movie> movieList) {
        super(context, android.R.layout.simple_list_item_1,movieList);
    }

    @Override
    /*
    To improve performance, we should apply the ViewHolder pattern
    which speeds up the population of the ListView considerably by caching view lookups for smoother, faster item loading
     */
    public View getView(int position, View convertView, ViewGroup parent) {

        Movie movie = getItem(position);
        ViewHolder viewHolder;
        String imgUrl = null;
        int imgLoading = 0;
        int imgNotFound = 0;

        int orientation = getContext().getResources().getConfiguration().orientation;
        movie.setOrientation(orientation);
        if(orientation == Configuration.ORIENTATION_PORTRAIT){
            imgUrl = movie.getPosterPath();
            imgLoading = R.drawable.loading_v;
            imgNotFound = R.drawable.image_not_found_v;
        }
        else if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            imgUrl = movie.getBackDropPath();
            imgLoading = R.drawable.loading_land_scape_white;
            imgNotFound = R.drawable.image_not_found;
        }
        else{
            imgUrl = movie.getPosterPath();
            imgLoading = R.drawable.loading;
            imgNotFound = R.drawable.image_not_found_v;
        }

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
        viewHolder.imgMovie.setTag(movie);
        viewHolder.imgMovie.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Movie movie = (Movie) v.getTag();
                Intent intent = new Intent(getContext(), MovieDetailActivity.class);
                intent.putExtra("movie", Parcels.wrap(movie));
                getContext().startActivity(intent);
            }
        });

        //Picasso.with(getContext()).load(poster).into(viewHolder.imgMovie);

        Picasso.with(getContext())
                .load(imgUrl)
                .placeholder(imgLoading)
                .error(imgNotFound)
                .into(viewHolder.imgMovie);

        return convertView;
    }
}
