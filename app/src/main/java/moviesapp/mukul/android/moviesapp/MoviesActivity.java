package moviesapp.mukul.android.moviesapp;

import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MoviesActivity extends AppCompatActivity {

    public static final String LOG_TAG = MoviesActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        ArrayList<MovieRecord> movies = new ArrayList<>();
        movies.add(new MovieRecord(2.5,"Akira",
                "Akira is the official Hindi remake of Tamil hit - Mounaguru","2 Sept 2016","https://www.youtube.com/watch?v=QsCkty3mpg0"));
        movies.add(new MovieRecord(2.33,"Yea Toh Two Much Ho Gayaa",
                "Lookalike twin brothers living in two parts of the world get trapped in some unwitting situations.","2 Sep 2016","https://www.youtube.com/watch?v=fvOWhjF_nHo"));
        movies.add(new MovieRecord(1.0,"Sunshine Music Tours and Travels","","2 Sep 2016","https://www.youtube.com/watch?v=VE24_u0idYQ"));
        movies.add(new MovieRecord(1.0,"Island City","The film follows three comic-dramatic stories set in Mumbai. The first is about a diligent office worker who wins the office 'Fun Committee Award', which entitles him to a whole day full of fun. He is most reluctant to leave the safety of his cubicle but he has to. Prescribed fun modules have to be completed and non-compliance is not an option.","2 Sep 2016","https://www.youtube.com/watch?v=qHE7ao_YiJ8"));
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, movies);
        MoviesAdapter movieadapter = new MoviesAdapter(this,movies);
        ListView movies_view = (ListView) findViewById(R.id.movies_list);
        movies_view.setAdapter(movieadapter);






    }
}
