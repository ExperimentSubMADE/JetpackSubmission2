package com.gdktuts.jetpacksubmission.testing;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.gdktuts.jetpacksubmission.MainViewpagerAdapter;
import com.gdktuts.jetpacksubmission.R;
import com.gdktuts.jetpacksubmission.ui.movie.MovieFragment;
import com.gdktuts.jetpacksubmission.ui.tvshow.TvShowFragment;
import com.google.android.material.tabs.TabLayout;

public class SingleFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tablayoutMainNavigation = findViewById(R.id.tablayoutMainNavigation);
        ViewPager viewpagerMainNavigation = findViewById(R.id.viewpagerMainNavigation);

        MainViewpagerAdapter mainViewpagerAdapter = new MainViewpagerAdapter(getSupportFragmentManager(), 0);

        mainViewpagerAdapter.addFragment(new MovieFragment(), getResources().getString(R.string.tablayout_title_movies));
        mainViewpagerAdapter.addFragment(new TvShowFragment(), getResources().getString(R.string.tablayout_title_tvshow));

        viewpagerMainNavigation.setAdapter(mainViewpagerAdapter);
        tablayoutMainNavigation.setupWithViewPager(viewpagerMainNavigation);

    }
}
