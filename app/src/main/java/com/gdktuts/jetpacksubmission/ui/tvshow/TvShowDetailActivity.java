package com.gdktuts.jetpacksubmission.ui.tvshow;

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
import com.gdktuts.jetpacksubmission.viewmodel.TvShowDetailViewModel;
import com.gdktuts.jetpacksubmission.utils.GlideApp;

import java.text.DecimalFormat;

public class TvShowDetailActivity extends AppCompatActivity {

    public static final String EXTRA_INFO_TV_SHOW = "extra_info_tv_show";
    private ImageView imageviewTvShowImage;
    private TextView textviewTvShowTitle, textviewTvShowRating, textviewTvShowOverview, textviewTvShowReleaseDate;
    private ShimmerFrameLayout shimmerLayoutTvShowDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_show_detail);

        imageviewTvShowImage = findViewById(R.id.imageviewTvShowImage);
        textviewTvShowTitle = findViewById(R.id.textviewTvShowTitle);
        textviewTvShowRating = findViewById(R.id.textviewTvShowRating);
        textviewTvShowOverview = findViewById(R.id.textviewTvShowOverview);
        textviewTvShowReleaseDate = findViewById(R.id.textviewTvShowReleaseDate);
        shimmerLayoutTvShowDetail = findViewById(R.id.shimmerLayoutTvShowDetail);

        shimmerLayoutTvShowDetail.setVisibility(View.VISIBLE);
        shimmerLayoutTvShowDetail.startShimmer();

        ViewModelFactory viewModelFactory = ViewModelFactory.getInstance(getApplication());
        TvShowDetailViewModel tvShowDetailViewModel = ViewModelProviders.of(this, viewModelFactory)
                                                        .get(TvShowDetailViewModel.class);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int tvShowId = bundle.getInt(EXTRA_INFO_TV_SHOW);
            tvShowDetailViewModel.setTvShowId(tvShowId);
        }

        tvShowDetailViewModel.getTvShowDetailViewModel().observe(this, tvShowDataModel -> {
            if (tvShowDataModel != null) {
                setTvShowDetailRecyclerView(tvShowDataModel);
                shimmerLayoutTvShowDetail.stopShimmer();
                shimmerLayoutTvShowDetail.setVisibility(View.GONE);
            }
        });

    }

    private void setTvShowDetailRecyclerView(TvShowDataModel tvShowDataModel) {

        GlideApp.with(this)
                .load(BuildConfig.POSTER_URL + tvShowDataModel.getTvShowPosterPath())
                .into(imageviewTvShowImage);

        textviewTvShowTitle.setText(tvShowDataModel.getTvShowTitle());
        textviewTvShowOverview.setText(tvShowDataModel.getTvShowOverview());
        textviewTvShowReleaseDate.setText(tvShowDataModel.getTvShowReleaseDate());

        Double countRating = tvShowDataModel.getTvShowRating()*10;
        String textRating = new DecimalFormat("##").format(countRating);
        textviewTvShowRating.setText(textRating + "%");

    }

}
