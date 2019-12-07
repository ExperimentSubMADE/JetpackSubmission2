package com.gdktuts.jetpacksubmission.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.gdktuts.jetpacksubmission.repository.ContentRepository;
import com.gdktuts.jetpacksubmission.ui.movie.MovieDataModel;

public class MovieDetailViewModel extends ViewModel {

    private ContentRepository contentRepository;
    private int movieId;

    public MovieDetailViewModel(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    public LiveData<MovieDataModel> getMovieDetailViewModel() {
        return contentRepository.getMovieDetails(movieId);
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
}
