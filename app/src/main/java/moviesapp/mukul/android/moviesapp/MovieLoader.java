package moviesapp.mukul.android.moviesapp;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mukul on 9/2/2016.
 */
public class MovieLoader extends AsyncTaskLoader<List<MovieRecord>>{

    private static final String LOG_TAG = MovieLoader.class.getName();

    private String mUrl;

    public MovieLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<MovieRecord> loadInBackground() {

        List<MovieRecord> result = null;

        if (mUrl == null) {
            return null;
        }
        result = QueryUtils.fetchMovieData(mUrl);

        return result;
    }

}
