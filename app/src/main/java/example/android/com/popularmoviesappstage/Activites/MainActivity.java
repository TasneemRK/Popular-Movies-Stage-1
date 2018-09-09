package example.android.com.popularmoviesappstage.Activites;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import example.android.com.popularmoviesappstage.Adapters.MoviesAdapter;
import example.android.com.popularmoviesappstage.Models.Movie;
import example.android.com.popularmoviesappstage.R;
import example.android.com.popularmoviesappstage.Utils.Constant;
import example.android.com.popularmoviesappstage.Utils.NetworkUtils;

public class MainActivity extends AppCompatActivity implements MoviesAdapter.RecycleItemClick {

    public static final String RESULT_JSON = "results";
    public static final String ID_JSON = "id";
    public static final String TITLE_JSON = "original_title";
    public static final String IMAGE_JSON = "poster_path";
    public static final String OVERVIEW_JSON = "overview";
    public static final String DATE_JSON = "release_date";
    public static final String RATING_JSON = "vote_average";
    public static final int GRID_SPANCOUNT = 2;

    List<Movie> movies = new ArrayList<>();
    MoviesAdapter adapter;
    RecyclerView moviesRecycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        moviesRecycle = findViewById(R.id.moviesRecycleView);
        adapter = new MoviesAdapter(this, movies, this);
        moviesRecycle.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, GRID_SPANCOUNT);
        moviesRecycle.setLayoutManager(layoutManager);

        new GetMoviesAsyncTask().execute(Constant.ALL_MOIVES_API);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sort_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.popular_sort:
                new GetMoviesAsyncTask().execute(Constant.SORT_BY_POPULARITY_API);
                adapter.notifyDataSetChanged();
                break;
            case R.id.highestRate_sort:
                new GetMoviesAsyncTask().execute(Constant.SORT_BY_TOP_RATES_API);
                adapter.notifyDataSetChanged();
                break;

        }
        return true;
    }

    @Override
    public void onItemClickListener(int clickIndex) {
        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
        startActivity(intent);
    }


    public class GetMoviesAsyncTask extends AsyncTask<String, Void, List<Movie>> implements MoviesAdapter.RecycleItemClick{

        List<Movie> movies = new ArrayList<>();

        @Override
        protected List<Movie> doInBackground(String... strings) {
            if (strings[0] == null) {
                return null;
            }

            URL url = NetworkUtils.buildUrl(strings[0]);
            try {
                String result = NetworkUtils.getResponseFromHttpUrl(url);
                JSONObject jsonObject = new JSONObject(result);
                JSONArray resultsArray = jsonObject.getJSONArray(RESULT_JSON);
                for (int i = 0; i < resultsArray.length(); i++) {
                    JSONObject resultJson = resultsArray.getJSONObject(i);
                    Movie movie = new Movie();
                    movie.setId(resultJson.getInt(ID_JSON));
                    movie.setOriginal_title(resultJson.getString(TITLE_JSON));
                    movie.setImage(Constant.IMAGE_URL+resultJson.getString(IMAGE_JSON));

                    movie.setOverview(resultJson.getString(OVERVIEW_JSON));
                    movie.setRelease_date(resultJson.getString(DATE_JSON));
                    movie.setRating(resultJson.getDouble(RATING_JSON)+"");
                    movies.add(movie);
                }

                return movies;

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return movies;
        }

        @Override
        protected void onPostExecute(List<Movie> movies1) {
            adapter = new MoviesAdapter(MainActivity.this, movies1, this);
            moviesRecycle.setAdapter(adapter);
        }

        @Override
        public void onItemClickListener(int clickIndex) {
            Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
            intent.putExtra(TITLE_JSON,movies.get(clickIndex).getOriginal_title());
            intent.putExtra(IMAGE_JSON,movies.get(clickIndex).getImage());
            intent.putExtra(OVERVIEW_JSON,movies.get(clickIndex).getOverview());
            intent.putExtra(RATING_JSON,movies.get(clickIndex).getRating());
            intent.putExtra(DATE_JSON,movies.get(clickIndex).getRelease_date());
            startActivity(intent);
        }
    }
}
