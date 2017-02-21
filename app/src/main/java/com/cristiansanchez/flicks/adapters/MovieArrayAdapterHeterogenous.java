package com.cristiansanchez.flicks.adapters;


import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cristiansanchez.flicks.R;
import com.cristiansanchez.flicks.activities.MovieDetailActivity;
import com.cristiansanchez.flicks.activities.PlayYoutubeActivity;
import com.cristiansanchez.flicks.models.Movie;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.List;

/**
 * Created by kristianss27 on 10/15/16.
 */
public class MovieArrayAdapterHeterogenous extends ArrayAdapter<Movie>{

    private static class ViewHolder{
        ImageView imgMovie;
        ImageView imgPlayIcon;
        TextView tvTitle;
        TextView tvReview;
    }

    public MovieArrayAdapterHeterogenous(Context context, List<Movie> movieList) {
        super(context, android.R.layout.simple_list_item_1,movieList);
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).viewType.ordinal();
    }

    @Override
    public int getViewTypeCount() {
        return Movie.ViewType.values().length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("GET VIEW","GET VIEW " + position + " " + convertView);
        Movie movie = getItem(position);
        String imgUrl = null;
        int imgLoading = 0;
        int imgNotFound = 0;
        ViewHolder viewHolder = null;

        int orientation = getContext().getResources().getConfiguration().orientation;
        movie.setOrientation(orientation);
        if(orientation == Configuration.ORIENTATION_PORTRAIT){
            imgLoading = R.drawable.loading_v;
            imgNotFound = R.drawable.image_not_found_v;
        }
        else if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            imgLoading = R.drawable.loading_land_scape_white;
            imgNotFound = R.drawable.image_not_found;
        }
        else{

        }

        int type = getItemViewType(position);

        if(convertView==null){
            viewHolder = new ViewHolder();
            if(type==Movie.ViewType.REGULAR.ordinal()){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.movie_item,null);
                viewHolder.imgMovie = (ImageView) convertView.findViewById(R.id.imgMovie);
                viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
                viewHolder.tvReview = (TextView) convertView.findViewById(R.id.tvReview);
            }
            else if(type==Movie.ViewType.POPULAR.ordinal()){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.movie_item_popular,null);
                ImageView imgMovie = (ImageView) convertView.findViewById(R.id.imgMoviePopular);
                ImageView imgPlayIcon = (ImageView) convertView.findViewById(R.id.imgPlayIcon);

                viewHolder.imgMovie = imgMovie;
                viewHolder.imgPlayIcon = imgPlayIcon;
            }
            else{

            }

            convertView.setTag(viewHolder);

        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if(viewHolder.tvTitle!=null){
            viewHolder.tvTitle.setText(movie.getOriginalTitle());
        }
        if(viewHolder.tvReview!=null){
            viewHolder.tvReview.setText(movie.getOverView());
        }

        if(type==Movie.ViewType.POPULAR.ordinal() && movie.getVideo().equalsIgnoreCase("true")){
            viewHolder.imgMovie.setVisibility(View.GONE);
            viewHolder.imgPlayIcon.bringToFront();
            viewHolder.imgPlayIcon.invalidate();
            viewHolder.imgPlayIcon.setVisibility(View.VISIBLE);
            viewHolder.imgMovie.setVisibility(View.VISIBLE);
        }
        else if(type==Movie.ViewType.POPULAR.ordinal() && movie.getVideo().equalsIgnoreCase("false")){
            viewHolder.imgPlayIcon.setVisibility(View.GONE);
            viewHolder.imgMovie.bringToFront();
            viewHolder.imgMovie.invalidate();
            viewHolder.imgMovie.setVisibility(View.VISIBLE);
            viewHolder.imgPlayIcon.setVisibility(View.INVISIBLE);
        }
        else{

        }


        viewHolder.imgMovie.setTag(movie);
        viewHolder.imgMovie.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Movie movie = (Movie) v.getTag();
                if(movie.viewType == Movie.ViewType.POPULAR && movie.getVideo().equalsIgnoreCase("true")){
                    Intent intent = new Intent(getContext(), PlayYoutubeActivity.class);
                    intent.putExtra("movie", Parcels.wrap(movie));
                    getContext().startActivity(intent);
                }
                else{
                    Intent intent = new Intent(getContext(), MovieDetailActivity.class);
                    intent.putExtra("movie", Parcels.wrap(movie));
                    getContext().startActivity(intent);
                }

            }
        });


        imgUrl =(type == Movie.ViewType.POPULAR.ordinal())?movie.getBackDropPath():movie.getPosterPath();
        Picasso.with(getContext())
                .load(imgUrl)
                .fit()
                .placeholder(imgLoading)
                .error(imgNotFound)
                .into(viewHolder.imgMovie);



        return convertView;
    }

}
