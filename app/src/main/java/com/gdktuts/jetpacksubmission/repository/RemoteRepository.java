package com.gdktuts.jetpacksubmission.repository;

import androidx.lifecycle.LiveData;

import com.gdktuts.jetpacksubmission.ui.movie.MovieDataModel;
import com.gdktuts.jetpacksubmission.ui.tvshow.TvShowDataModel;

import java.util.ArrayList;

public class RemoteRepository {

    private static RemoteRepository remoteRepository;
    private ApiRepository apiRepository;

    public RemoteRepository(ApiRepository apiRepository) {
        this.apiRepository = apiRepository;
    }

    public static RemoteRepository getInstance(ApiRepository mApiRepository) {
        if (remoteRepository == null) {
            remoteRepository = new RemoteRepository(mApiRepository);
        }
        return remoteRepository;
    }

    public LiveData<ArrayList<MovieDataModel>> getMoviesLive() {
        return apiRepository.getLiveMovies();
    }

    public LiveData<MovieDataModel> getMovieDetailsLive(int id) {
        return apiRepository.getLiveMovieDetail(id);
    }

    public LiveData<ArrayList<TvShowDataModel>> getTvShowsLive() {
        return apiRepository.getLiveTvShows();
    }

    public LiveData<TvShowDataModel> getTvShowDetailsLive(int id) {
        return apiRepository.getLiveTvShowDetail(id);
    }
}
