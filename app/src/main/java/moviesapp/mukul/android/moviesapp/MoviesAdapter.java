package moviesapp.mukul.android.moviesapp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.graphics.drawable.GradientDrawable;

import com.squareup.picasso.Picasso;

/**
 * Created by mukul on 8/31/2016.
 */
public class MoviesAdapter extends ArrayAdapter<MovieRecord> {


    public MoviesAdapter(Activity context, List<MovieRecord> movieRecords) {
        super(context, 0, movieRecords);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;

        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }
        MovieRecord currentMovie = getItem(position);




        // Populate the movie rating
        //TextView movie_rating_view = (TextView) listItemView.findViewById(R.id.movie_rating);
        //String formatted_movie_rating = formattedRating(currentMovie.getmRating());
        //movie_rating_view.setText(formatted_movie_rating);
        RatingBar ratingBar = (RatingBar)listItemView.findViewById(R.id.rating);
        ratingBar.setEnabled(false);
        ratingBar.setMax(5);
        ratingBar.setStepSize(0.01f);
        ratingBar.setRating(currentMovie.getmRating());
        ratingBar.invalidate();
        /*LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);*/
        Drawable progress = ratingBar.getProgressDrawable();
        DrawableCompat.setTint(progress, Color.RED);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        //GradientDrawable ratingCircle = (GradientDrawable) movie_rating_view.getBackground();

        int ratingColor = getratingColor(currentMovie.getmRating());

        //ratingCircle.setColor(ratingColor);

        // Populate the Movie title
        TextView movie_title_view = (TextView) listItemView.findViewById(R.id.movie_title);
        movie_title_view.setText(currentMovie.getmMovieTitle());

        // Populate movie description
        TextView movie_genre = (TextView) listItemView.findViewById(R.id.movie_genre);
        movie_genre.setText(currentMovie.getmMovieGenre());

        // Populate movie release date
        TextView movie_release_date_view = (TextView) listItemView.findViewById(R.id.movie_release_date);


        Date dateObject = new Date(currentMovie.getmReleaseDate());
        String formatDate = formatDate(dateObject);
         movie_release_date_view.setText(formatDate);
       /* SimpleDateFormat df_input = new SimpleDateFormat(currentMovie.getmReleaseDate(),java.util.Locale.getDefault());
        SimpleDateFormat df_output = new SimpleDateFormat("dd MMM yyyy",java.util.Locale.getDefault());
        Date parsed = null;
        String outputDate = "";
        try {
            parsed = df_input.parse(currentMovie.getmReleaseDate());
            outputDate = df_output.format(parsed);
            //Log.e("Formatted Date",outputDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
*/

        ImageView movie_poster = (ImageView)listItemView.findViewById(R.id.movie_poster);
        System.out.println(movie_poster);
        Picasso.with(this.getContext()).load(currentMovie.getmPosterurl()).into(movie_poster);

        return listItemView;







    }

    private String formatDate(Date dateObject) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    private int getratingColor(double rating) {

        int ratingColorResourceId;
        int ratingFloor = (int)Math.ceil(rating);
        //System.out.println(ratingFloor);
        switch(ratingFloor){
            case 0:
            case 1: ratingColorResourceId = R.color.magnitude1;
                    break;
            case 2: ratingColorResourceId= R.color.magnitude2;
                    break;
            case 3: ratingColorResourceId = R.color.magnitude3;
                    break;
            case 4: ratingColorResourceId = R.color.magnitude4;
                    break;
            default: ratingColorResourceId = R.color.magnitude1;
        }
        return  ContextCompat.getColor(getContext(), ratingColorResourceId);
    }

    private String formattedRating(double movieRating) {
        DecimalFormat rating = new DecimalFormat("0.0");
        return rating.format(movieRating);
    }
}
