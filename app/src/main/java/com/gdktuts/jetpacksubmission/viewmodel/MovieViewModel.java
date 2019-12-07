package com.gdktuts.jetpacksubmission.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.gdktuts.jetpacksubmission.repository.ContentRepository;
import com.gdktuts.jetpacksubmission.ui.movie.MovieDataModel;

import java.util.ArrayList;

public class MovieViewModel extends ViewModel {

    private ContentRepository contentRepository;

    public MovieViewModel(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    public LiveData<ArrayList<MovieDataModel>> getMovieViewModel() {
        return contentRepository.getMovieDatas();
    }
}
