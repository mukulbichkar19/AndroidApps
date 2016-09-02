package moviesapp.mukul.android.moviesapp;

/**
 * Created by mukul on 8/31/2016.
 */
public class MovieRecord {

    private double mRating;
    private String mMovieTitle;
    private String mMovieGenre;
    private String mReleaseDate;
    private String mTrailerUrl;
    private String mPosterurl;

    public MovieRecord(double mRating, String mMovieTitle,String mMovieGenre, String mReleaseDate, String mTrailerUrl){

        this.mRating = mRating;
        this.mMovieTitle = mMovieTitle;
        this.mMovieGenre = mMovieGenre;
        this.mReleaseDate = mReleaseDate;
        this.mTrailerUrl = mTrailerUrl;

    }

    public MovieRecord(double mRating, String mMovieTitle,String mMovieGenre, String mReleaseDate, String mTrailerUrl,String mPosterurl){

        this.mRating = mRating;
        this.mMovieTitle = mMovieTitle;
        this.mMovieGenre = mMovieGenre;
        this.mReleaseDate = mReleaseDate;
        this.mTrailerUrl = mTrailerUrl;
        this.mPosterurl = mPosterurl;
    }

    public double getmRating(){
        return this.mRating;
    }

    public String getmMovieTitle(){
        return this.mMovieTitle;
    }

    public String getmMovieGenre(){
        return this.mMovieGenre;
    }

    public String getmReleaseDate(){
        return this.mReleaseDate;
    }

    public String getmTrailerUrl(){
        return this.mTrailerUrl;
    }

    public String getmPosterurl(){return this.mPosterurl;}


}
