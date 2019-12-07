package com.gdktuts.jetpacksubmission.repository;

import androidx.lifecycle.LiveData;

import com.gdktuts.jetpacksubmission.ui.movie.MovieDataModel;
import com.gdktuts.jetpacksubmission.ui.tvshow.TvShowDataModel;

import java.util.ArrayList;

public class FakeContentRepository implements ContentDataSources {

    private volatile static FakeContentRepository fakeContentRepository = null;
    private final RemoteRepository remoteRepository;

    public FakeContentRepository(RemoteRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    @Override
    public LiveData<ArrayList<MovieDataModel>> getMovieDatas() {
        return remoteRepository.getMoviesLive();
    }

    @Override
    public LiveData<MovieDataModel> getMovieDetails(int id) {
        return remoteRepository.getMovieDetailsLive(id);
    }

    @Override
    public LiveData<ArrayList<TvShowDataModel>> getTvShowDatas() {
        return remoteRepository.getTvShowsLive();
    }

    @Override
    public LiveData<TvShowDataModel> getTvShowDetails(int id) {
        return remoteRepository.getTvShowDetailsLive(id);
    }
}
