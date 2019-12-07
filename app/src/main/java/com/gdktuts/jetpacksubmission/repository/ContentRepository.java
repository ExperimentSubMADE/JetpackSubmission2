package com.gdktuts.jetpacksubmission.repository;

import androidx.lifecycle.LiveData;

import com.gdktuts.jetpacksubmission.ui.movie.MovieDataModel;
import com.gdktuts.jetpacksubmission.ui.tvshow.TvShowDataModel;

import java.util.ArrayList;

public class ContentRepository implements ContentDataSources {

    private volatile static ContentRepository contentRepository = null;
    private final RemoteRepository remoteRepository;

    public ContentRepository(RemoteRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    public static ContentRepository getInstance(RemoteRepository remoteRepository) {
        if (contentRepository == null) {
            synchronized (ContentRepository.class) {
                if (contentRepository == null) {
                    contentRepository = new ContentRepository(remoteRepository);
                }
            }
        }
        return contentRepository;
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
