package com.gdktuts.jetpacksubmission.repository;

import androidx.lifecycle.LiveData;

import com.gdktuts.jetpacksubmission.ui.movie.MovieDataModel;
import com.gdktuts.jetpacksubmission.ui.tvshow.TvShowDataModel;

import java.util.ArrayList;

public interface ContentDataSources {

    LiveData<ArrayList<MovieDataModel>> getMovieDatas();

    LiveData<MovieDataModel> getMovieDetails(int id);

    LiveData<ArrayList<TvShowDataModel>> getTvShowDatas();

    LiveData<TvShowDataModel> getTvShowDetails(int id);

}
