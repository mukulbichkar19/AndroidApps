package moviesapp.mukul.android.moviesapp;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by mukul on 8/31/2016.
 */
public class MoviesAdapter extends ArrayAdapter<MovieRecord> {


    public MoviesAdapter(Activity context, List<MovieRecord> movieRecords) {
        super(context, 0, movieRecords);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;

        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }
        MovieRecord currentMovie = getItem(position);

        // Populate the movie rating
        TextView movie_rating_view = (TextView) listItemView.findViewById(R.id.movie_rating);
        String formatted_movie_rating = formattedRating(currentMovie.getmRating());
        movie_rating_view.setText(formatted_movie_rating);

        // Populate the Movie title
        TextView movie_title_view = (TextView) listItemView.findViewById(R.id.movie_title);
        movie_title_view.setText(currentMovie.getmMovieTitle());

        // Populate movie description
        TextView movie_description = (TextView) listItemView.findViewById(R.id.movie_description);
        movie_description.setText(currentMovie.getmMovieDescription());

        // Populate movie release date
        TextView movie_release_date_view = (TextView) listItemView.findViewById(R.id.movie_release_date);
        movie_release_date_view.setText(currentMovie.getmReleaseDate());

        return listItemView;







    }

    private String formattedRating(double movieRating) {
        DecimalFormat rating = new DecimalFormat("0.0");
        return rating.format(movieRating);
    }
}
