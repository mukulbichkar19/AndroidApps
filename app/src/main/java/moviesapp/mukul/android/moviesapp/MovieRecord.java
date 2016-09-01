package moviesapp.mukul.android.moviesapp;

/**
 * Created by mukul on 8/31/2016.
 */
public class MovieRecord {

    private double mRating;
    private String mMovieTitle;
    private String mMovieDescription;
    private String mReleaseDate;
    private String mTrailerUrl;

    public MovieRecord(double mRating, String mMovieTitle,String mMovieDescription, String mReleaseDate, String mTrailerUrl){

        this.mRating = mRating;
        this.mMovieTitle = mMovieTitle;
        this.mMovieDescription = mMovieDescription;
        this.mReleaseDate = mReleaseDate;
        this.mTrailerUrl = mTrailerUrl;
    }

    public double getmRating(){
        return this.mRating;
    }

    public String getmMovieTitle(){
        return this.mMovieTitle;
    }

    public String getmMovieDescription(){
        return this.mMovieDescription;
    }

    public String getmReleaseDate(){
        return this.mReleaseDate;
    }

    public String getmTrailerUrl(){
        return this.mTrailerUrl;
    }


}
