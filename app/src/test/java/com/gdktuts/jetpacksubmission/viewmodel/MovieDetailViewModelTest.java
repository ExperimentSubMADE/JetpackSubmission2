package com.gdktuts.jetpacksubmission.viewmodel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.gdktuts.jetpacksubmission.repository.ContentRepository;
import com.gdktuts.jetpacksubmission.ui.movie.MovieDataModel;
import com.gdktuts.jetpacksubmission.utils.FakeContentDataDummy;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MovieDetailViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    private MovieDetailViewModel testMovieDetailViewModel;
    private ContentRepository testContentRepository = mock(ContentRepository.class);
    private MovieDataModel movieDataTest = FakeContentDataDummy.movieDataDummies().get(0);
    private int movieId = movieDataTest.getMovieId();

    @Before
    public void setUp() {
        testMovieDetailViewModel = new MovieDetailViewModel(testContentRepository);
        testMovieDetailViewModel.setMovieId(movieId);
    }

    @Test
    public void getMovieDetailTest() {
        MutableLiveData<MovieDataModel> mutableMovieDetailTest = new MutableLiveData<>();
        mutableMovieDetailTest.setValue(movieDataTest);

        when(testContentRepository.getMovieDetails(movieId)).thenReturn(mutableMovieDetailTest);

        Observer<MovieDataModel> observerTest = mock(Observer.class);
        testMovieDetailViewModel.getMovieDetailViewModel().observeForever(observerTest);
        verify(observerTest).onChanged(movieDataTest);
    }

}