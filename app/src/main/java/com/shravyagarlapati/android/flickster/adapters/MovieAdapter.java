package com.shravyagarlapati.android.flickster.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.shravyagarlapati.android.flickster.R;
import com.shravyagarlapati.android.flickster.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by shravyagarlapati on 7/20/16.
 */
public class MovieAdapter extends ArrayAdapter<Movie> {

    private static class ViewHolder {
        TextView title;
        TextView overview;
        ImageView posterImage;
        ImageView backdropImage;
    }

    public MovieAdapter(Context context, List<Movie> list) {
        super(context, android.R.layout.simple_list_item_1, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Get the item
        Movie movie = getItem(position);

        //Check if existing view is being reused
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            int type = getItemViewType(position);
            convertView = getInflatedLayoutForType(type);
            viewHolder = new ViewHolder();
            convertView.setTag(viewHolder);
        } else
            viewHolder = (ViewHolder) convertView.getTag();

        viewHolder.backdropImage = (ImageView) convertView.findViewById(R.id.iVMovie);
        viewHolder.backdropImage.setImageResource(0);

        int orientation = getContext().getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE || orientation == Configuration.ORIENTATION_PORTRAIT) {
            //Log.d("Movie Rating:", movie.getOriginalTitle() + ":" + movie.getAverageRating());
            if (movie.getAverageRating() > 5) {
                Picasso.with(getContext()).load(movie.getBackdropPath()).fit().centerInside()
                        .placeholder(R.drawable.movieimage).error(R.drawable.oops).into(viewHolder.backdropImage);
            } else {
                viewHolder.title = (TextView) convertView.findViewById(R.id.tvTitle);
                viewHolder.overview = (TextView) convertView.findViewById(R.id.tvOverview);
                viewHolder.posterImage = (ImageView) convertView.findViewById(R.id.iVMovie);
                viewHolder.posterImage.setImageResource(0);

                Picasso.with(getContext()).load(movie.getPosterPath()).fit().centerInside()
                        .placeholder(R.drawable.movieimage).error(R.drawable.oops).into(viewHolder.posterImage);
                if(viewHolder.title!=null && viewHolder.overview!=null) {
                    viewHolder.title.setText(movie.getOriginalTitle());
                    viewHolder.overview.setText(movie.getOverview());
                }
            }
        }

        return convertView;
    }

    // Get the type of View that will be created by getView(int, View, ViewGroup)
    // for the specified item.
    @Override
    public int getItemViewType(int position) {
        // Return an integer here representing the type of View.
        // Note: Integers must be in the range 0 to getViewTypeCount() - 1
        if (getItem(position).getAverageRating() > 5)
            return getItem(position).imageType.BACKDROP.ordinal();
        else
            return getItem(position).imageType.POSTER.ordinal();

    }

    private View getInflatedLayoutForType(int type) {
        if (type == Movie.ImageType.BACKDROP.ordinal()) {
            return LayoutInflater.from(getContext()).inflate(R.layout.item_movie_image, null);
        } else if (type == Movie.ImageType.POSTER.ordinal()) {
            return LayoutInflater.from(getContext()).inflate(R.layout.item_movie, null);
        } else
            return null;

    }

}




