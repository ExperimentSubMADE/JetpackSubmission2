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

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MovieViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    private MovieViewModel testMovieViewModel;
    private ContentRepository testContentRepository = mock(ContentRepository.class);

    @Before
    public void setUp() {
        testMovieViewModel = new MovieViewModel(testContentRepository);
    }

    @Test
    public void getMovieTest() {
        ArrayList<MovieDataModel> movieDataTest = FakeContentDataDummy.movieDataDummies();
        MutableLiveData<ArrayList<MovieDataModel>> testMutableMovie = new MutableLiveData<>();
        testMutableMovie.setValue(movieDataTest);

        when(testContentRepository.getMovieDatas()).thenReturn(testMutableMovie);

        Observer<ArrayList<MovieDataModel>> observerTest = mock(Observer.class);
        testMovieViewModel.getMovieViewModel().observeForever(observerTest);
        verify(observerTest).onChanged(movieDataTest);
    }

}