package moviesapp.mukul.android.moviesapp;

import android.app.DownloadManager;
import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.graphics.Movie;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MoviesActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<MovieRecord>> {

    public static final String LOG_TAG = MoviesActivity.class.getName();

    private static final int MOVIE_LOADER_ID = 1;

    private static final String REQUEST_MOVIES_DATA = "https://api.cinemalytics.com/v1/movie/upcoming?auth_token=FCCB2729939E4173D5F2330BF76B62C6";

    private MoviesAdapter movieadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        movieadapter = new MoviesAdapter(this,new ArrayList<MovieRecord>());
        ListView movies_view = (ListView) findViewById(R.id.movies_list);
        movies_view.setAdapter(movieadapter);

        movies_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                MovieRecord currentMovie = movieadapter.getItem(position);
                //System.out.println(currentMovie.getmTrailerUrl());
                if(currentMovie.getmTrailerUrl() == "no trailer available"){
                    Toast toast = Toast.makeText(getApplicationContext(),"Sorry no trailer available for "+currentMovie.getmMovieTitle() + " stay tuned for updates",Toast.LENGTH_LONG);
                    toast.show();
                    return;

                }
                Uri parseUri = Uri.parse(currentMovie.getmTrailerUrl());



                Intent browserIntent = new Intent(Intent.ACTION_VIEW, parseUri);

                startActivity(browserIntent);
            }
        });

        //ArrayList<MovieRecord> movies = new ArrayList<>();
        /*ArrayList<MovieRecord> movies = new ArrayList<>(); ;
        *//*try {
            movies = QueryUtils.extractMovies();
        } catch (JSONException e) {
            Log.e("MoviesActivity","JSON Error");
            e.printStackTrace();
        }*//*

        movies.add(new MovieRecord(2.5,"Akira",
                "Action","2 Sept 2016","https://www.youtube.com/watch?v=QsCkty3mpg0"));
        movies.add(new MovieRecord(2.33,"Yea Toh Two Much Ho Gayaa",
                "Drama","2 Sep 2016","https://www.youtube.com/watch?v=fvOWhjF_nHo"));
        movies.add(new MovieRecord(1.0,"Sunshine Music Tours and Travels","Drama","2 Sep 2016","https://www.youtube.com/watch?v=VE24_u0idYQ"));
        movies.add(new MovieRecord(1.0,"Island City","Drama","2 Sep 2016","https://www.youtube.com/watch?v=qHE7ao_YiJ8"));*/
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, movies);
      /*  MoviesAsyncTask task = new MoviesAsyncTask();
        task.execute();*/

        LoaderManager loadermanager = getLoaderManager();
        loadermanager.initLoader(MOVIE_LOADER_ID, null, this);








    }



    @Override
    public Loader<List<MovieRecord>> onCreateLoader(int id, Bundle args) {
        return new MovieLoader(this, REQUEST_MOVIES_DATA);

    }

    @Override
    public void onLoadFinished(Loader<List<MovieRecord>> loader, List<MovieRecord> data) {
        movieadapter.clear();

        if(data != null && !data.isEmpty()){
            movieadapter.addAll(data);

        }
    }

    @Override
    public void onLoaderReset(Loader<List<MovieRecord>> loader) {
        movieadapter.clear();
    }

    /*private class MoviesAsyncTask extends AsyncTask<String,Void, List<MovieRecord>>{




        @Override
        protected List<MovieRecord> doInBackground(String... params) {

            URL url = QueryUtils.createURL(REQUEST_MOVIES_DATA);
            String jsonResponse = "";
            List<MovieRecord> movieslist = new ArrayList<>();

            try {
                jsonResponse = QueryUtils.makeHttpRequest(url);
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("doInBackground",LOG_TAG);
            }

            try {
                movieslist = QueryUtils.extractMovies(jsonResponse);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return movieslist;
        }

        @Override
        protected void onPostExecute(List<MovieRecord> movies) {
            if(movies == null){
                return;
            }
            updateUi(movies);
        }
    }
*/







}
