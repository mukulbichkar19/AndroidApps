package moviesapp.mukul.android.moviesapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mukul on 9/1/2016.
 */
public class QueryUtils {

    private static final String LOG_TAG = QueryUtils.class.getName();
    private static final String SAMPLE_JSON_STRING= "{\"Id\":\"37f9b48b\",\"ImdbId\":\"tt5465370\",\"OriginalTitle\":\"Naam Hai Akira\",\"Title\":\"Akira\",\"Description\":\"An action thriller in which a self-reliant college student takes on corrupt police officers single-handedly to prove her innocence.\",\"TrailerLink\":\"https://www.youtube.com/watch?v=QsCkty3mpg0\",\"TrailerEmbedCode\":\"<iframe width=\\\"850\\\" height=\\\"450\\\" src=\\\"https://www.youtube.com/embed/QsCkty3mpg0\\\" frameborder=\\\"0\\\" allowfullscreen></iframe>\",\"Country\":\"IN\",\"Region\":\"BOLLYWOOD\",\"Genre\":\"Action\",\"RatingCount\":2,\"Rating\":2.5,\"CensorRating\":\"U/A\",\"ReleaseDate\":\"9/2/2016\",\"Runtime\":140,\"Budget\":0,\"Revenue\":0,\"PosterPath\":\"https://s3-ap-southeast-1.amazonaws.com/cinemalytics/movie/453E156E9F53ED9DF66858CF0F551FAA.jpg\"},\n"
            +"{\"Id\":\"d7636e27\",\"ImdbId\":\"tt5573110\",\"OriginalTitle\":\"Yea Toh Two Much Ho Gayaa\",\"Title\":\"Yea Toh Two Much Ho Gayaa\",\"Description\":\"Lookalike twin brothers living in two parts of the world get trapped in some unwitting situations.\",\"TrailerLink\":\"https://www.youtube.com/watch?v=fvOWhjF_nHo\",\"TrailerEmbedCode\":\"<iframe width=\\\"850\\\" height=\\\"450\\\" src=\\\"https://www.youtube.com/embed/fvOWhjF_nHo\\\" frameborder=\\\"0\\\" allowfullscreen></iframe>\",\"Country\":\"IN\",\"Region\":\"BOLLYWOOD\",\"Genre\":\"Drama\",\"RatingCount\":3,\"Rating\":2.33333,\"CensorRating\":\"U/A\",\"ReleaseDate\":\"9/2/2016\",\"Runtime\":125,\"Budget\":0,\"Revenue\":0,\"PosterPath\":\"https://s3-ap-southeast-1.amazonaws.com/cinemalytics/movie/68F9648174388CFD3CE29BFAB8CF23EA.jpg\"},\n"
            +"{\"Id\":\"86954a68\",\"ImdbId\":\"tt5994822\",\"OriginalTitle\":\"Sunshine Music Tours and Travels\",\"Title\":\"Sunshine Music Tours And Travels\",\"Description\":\"This is a road movie that revolves around two young boys who are chasing their dreams. They take a journey from Kashmir to Goa along with several strangers.\",\"TrailerLink\":\"https://www.youtube.com/watch?v=VE24_u0idYQ\",\"TrailerEmbedCode\":\"<iframe width=\\\"850\\\" height=\\\"450\\\" src=\\\"https://www.youtube.com/embed/VE24_u0idYQ\\\" frameborder=\\\"0\\\" allowfullscreen></iframe>\",\"Country\":\"IN\",\"Region\":\"BOLLYWOOD\",\"Genre\":\"Drama\",\"RatingCount\":1,\"Rating\":1.0,\"CensorRating\":\"A\",\"ReleaseDate\":\"9/2/2016\",\"Runtime\":120,\"Budget\":0,\"Revenue\":0,\"PosterPath\":\"https://s3-ap-southeast-1.amazonaws.com/cinemalytics/movie/271387BF99331D2D86DC83A27220C49A.jpg\"},{\"Id\":\"5a7fafdf\",\"ImdbId\":\"tt4916048\",\"OriginalTitle\":\"Island City\",\"Title\":\"Island City\",\"Description\":\"The film follows three comic-dramatic stories set in Mumbai. The first is about a diligent office worker who wins the office 'Fun Committee Award', which entitles him to a whole day full of fun. He is most reluctant to leave the safety of his cubicle but he has to. Prescribed fun modules have to be completed and non-compliance is not an option.\\r\\n\\r\\nThe second story begins with a domineering head-of-the-family, Anil, who is on life support. Seeking some relief, his family decides to buy a TV, which Anil had banned. Now every night the family plugs into a popular soap whose hero, Purshottam, is a man ideal in every way and they are smitten. Then suddenly, comes the news that Anil is better and may be home soon. Will they have to let Purshottam go? The third one centers on Aarti whose repetitive existence is slowly making her more and more mechanical and numb. Deep inside ferments a disconnect and unease that she is unable to articulate to anyone. Then one day there arrives a most intimate letter and everything changes...\",\"TrailerLink\":\"https://www.youtube.com/watch?v=qHE7ao_YiJ8\",\"TrailerEmbedCode\":\"<iframe width=\\\"850\\\" height=\\\"450\\\" src=\\\"https://www.youtube.com/embed/qHE7ao_YiJ8\\\" frameborder=\\\"0\\\" allowfullscreen></iframe>\",\"Country\":\"IN\",\"Region\":\"BOLLYWOOD\",\"Genre\":\"Drama\",\"RatingCount\":1,\"Rating\":1.0,\"CensorRating\":\"A\",\"ReleaseDate\":\"9/2/2016\",\"Runtime\":111,\"Budget\":0,\"Revenue\":0,\"PosterPath\":\"https://s3-ap-southeast-1.amazonaws.com/cinemalytics/movie/75BD1FEAE8947E0D61A0BE62E3C23252.jpg\"}";


    private QueryUtils(){}

    public static List<MovieRecord> fetchMovieData(String requesturl){

        URL url = QueryUtils.createURL(requesturl);
        String jsonResponse = "";
        List<MovieRecord> movieslist = new ArrayList<>();

        try {
            jsonResponse = QueryUtils.makeHttpRequest(url);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("loadInBackground",LOG_TAG);
        }

        try {
            movieslist = QueryUtils.extractMovies(jsonResponse);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //System.out.println(movieslist.get(2).getmMovieTitle());
        /*try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        return movieslist;
    }






    public static ArrayList<MovieRecord> extractMovies(String moviesJSON) throws JSONException {

        // 1. Creating an empty arraylist of Movies
        ArrayList<MovieRecord> movies = new ArrayList<>();



        // Parse JSON String here
       JSONArray rootJSON = new JSONArray(moviesJSON);

        //System.out.println("The json object is "+rootJSON);

        // Process JSON
        for(int i=0;i<rootJSON.length();i++)
        {
            JSONObject movieObject = rootJSON.getJSONObject(i);
            double rating = Double.parseDouble(movieObject.getString("Rating"));
            String movie_name = movieObject.getString("Title");
            String movie_genre = movieObject.getString("Genre");
            String release_date = movieObject.getString("ReleaseDate");
            String trailer_url = movieObject.getString("TrailerLink");
            //String movie_poster = movieObject.getString("PosterPath");
            if(trailer_url.equals("")){
                trailer_url = "no trailer available";
            }

            movies.add(new MovieRecord(rating,movie_name,movie_genre,release_date,trailer_url));


        }





        return movies;
    }

    public static URL createURL(String stringUrl)  {
        URL url = null;

        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.e("MalformedURL",LOG_TAG);
            return null;
        }
        return url;

    }

    public static String makeHttpRequest(URL url) throws IOException {

        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputstream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            inputstream = urlConnection.getInputStream();
            jsonResponse = readFromStream(inputstream);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("makeHttpRequest",LOG_TAG);
        }finally{
            if(urlConnection != null){
                urlConnection.disconnect();
            }
            if(inputstream != null){
                inputstream.close();
            }
        }

        return jsonResponse;


    }

    public static String readFromStream(InputStream inputstream) throws IOException {

        StringBuilder output = new StringBuilder();
        if(inputstream != null){
            InputStreamReader inputstreamreader = new InputStreamReader(inputstream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputstreamreader);
            String line =   reader.readLine();
            while(line !=  null){

                output.append(line);
                line = reader.readLine();

            }


        }

        return output.toString();

    }




}
