package com.gdktuts.jetpacksubmission.ui.movie;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.gdktuts.jetpacksubmission.BuildConfig;
import com.gdktuts.jetpacksubmission.R;
import com.gdktuts.jetpacksubmission.viewmodel.ViewModelFactory;
import com.gdktuts.jetpacksubmission.viewmodel.MovieDetailViewModel;
import com.gdktuts.jetpacksubmission.utils.GlideApp;

import java.text.DecimalFormat;

public class MovieDetailActivity extends AppCompatActivity {

    public static final String EXTRA_INFO_MOVIE = "extra_info_movie";
    private ImageView imageviewMovieImage;
    private TextView textviewMovieTitle, textviewMovieRating, textviewMovieOverview, textviewMovieReleaseDate;
    private ShimmerFrameLayout shimmerLayoutMovieDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        imageviewMovieImage = findViewById(R.id.imageviewMovieImage);
        textviewMovieTitle = findViewById(R.id.textviewMovieTitle);
        textviewMovieRating = findViewById(R.id.textviewMovieRating);
        textviewMovieOverview = findViewById(R.id.textviewMovieOverview);
        textviewMovieReleaseDate = findViewById(R.id.textviewMovieReleaseDate);
        shimmerLayoutMovieDetail = findViewById(R.id.shimmerLayoutMovieDetail);

        shimmerLayoutMovieDetail.setVisibility(View.VISIBLE);
        shimmerLayoutMovieDetail.startShimmer();

        ViewModelFactory viewModelFactory = ViewModelFactory.getInstance(getApplication());
        MovieDetailViewModel movieDetailViewModel = ViewModelProviders.of(this,viewModelFactory)
                                                    .get(MovieDetailViewModel.class);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int movieId = bundle.getInt(EXTRA_INFO_MOVIE);
            movieDetailViewModel.setMovieId(movieId);
        }

        movieDetailViewModel.getMovieDetailViewModel().observe(this, movieDataModel -> {
            if (movieDataModel != null) {
                setMovieDetailRecylerView(movieDataModel);
                shimmerLayoutMovieDetail.stopShimmer();
                shimmerLayoutMovieDetail.setVisibility(View.GONE);
            }
        });

    }

    private void setMovieDetailRecylerView(MovieDataModel movieDataModel) {

        GlideApp.with(this)
                .load(BuildConfig.POSTER_URL + movieDataModel.getMoviePosterPath())
                .into(imageviewMovieImage);

        textviewMovieTitle.setText(movieDataModel.getMovieTitle());
        textviewMovieOverview.setText(movieDataModel.getMovieOverview());
        textviewMovieReleaseDate.setText(movieDataModel.getMovieReleaseDate());

        Double countRating = movieDataModel.getMovieRating()*10;
        String textRating = new DecimalFormat("##").format(countRating);
        textviewMovieRating.setText(textRating + "%");

    }

}
