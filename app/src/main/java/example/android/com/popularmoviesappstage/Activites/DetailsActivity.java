package example.android.com.popularmoviesappstage.Activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatRatingBar;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import example.android.com.popularmoviesappstage.R;


public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ImageView movie_image = findViewById(R.id.movie_image);
        TextView original_text = findViewById(R.id.original_text);
        TextView overview = findViewById(R.id.overview);
        TextView date = findViewById(R.id.date);
        TextView rating = findViewById(R.id.rating);

        Intent intent = getIntent();
        if (intent != null){
            Picasso.get().load(intent.getStringExtra(MainActivity.IMAGE_JSON)).into(movie_image);
            original_text.setText(intent.getStringExtra(MainActivity.TITLE_JSON));
            overview.setText(intent.getStringExtra(MainActivity.OVERVIEW_JSON));
            date.append(intent.getStringExtra(MainActivity.DATE_JSON));
            rating.append(intent.getStringExtra(MainActivity.RATING_JSON));
        }

    }
}
