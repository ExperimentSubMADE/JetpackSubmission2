package com.gdktuts.jetpacksubmission.repository;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;

import com.gdktuts.jetpacksubmission.utils.FakeContentDataDummy;
import com.gdktuts.jetpacksubmission.utils.LiveDataTest;
import com.gdktuts.jetpacksubmission.ui.movie.MovieDataModel;
import com.gdktuts.jetpacksubmission.ui.tvshow.TvShowDataModel;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ContentRepositoryTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private RemoteRepository remoteRepository = Mockito.mock(RemoteRepository.class);
    private FakeContentRepository fakeContentRepository = new FakeContentRepository(remoteRepository);
    private ArrayList<MovieDataModel> movieDataDummies = FakeContentDataDummy.movieDataDummies();
    private ArrayList<TvShowDataModel> tvShowDataDummies = FakeContentDataDummy.tvShowDataDummies();
    private int movieId = movieDataDummies.get(0).getMovieId();
    private int tvShowId = tvShowDataDummies.get(0).getTvShowId();

    @Test
    public void getMovieTest() {
        MutableLiveData<ArrayList<MovieDataModel>> mutableMovieTest = new MutableLiveData<>();
        mutableMovieTest.setValue(movieDataDummies);

        when(remoteRepository.getMoviesLive()).thenReturn(mutableMovieTest);
        ArrayList<MovieDataModel> moviesListTest = LiveDataTest.getValueTest(fakeContentRepository.getMovieDatas());

        verify(remoteRepository, times(1)).getMoviesLive();
        assertNotNull(moviesListTest);
        assertEquals(movieDataDummies.size(), moviesListTest.size());
    }

    @Test
    public void getMovieDetailTest() {
        MutableLiveData<MovieDataModel> mutableMovieDetailTest = new MutableLiveData<>();
        mutableMovieDetailTest.setValue(movieDataDummies.get(0));

        when(remoteRepository.getMovieDetailsLive(movieId)).thenReturn(mutableMovieDetailTest);
        MovieDataModel movieDataModelTest = LiveDataTest.getValueTest(fakeContentRepository.getMovieDetails(movieId));

        assertNotNull(movieDataModelTest);
        assertEquals(movieDataDummies.get(0).getMovieId(), movieDataModelTest.getMovieId());
    }


    @Test
    public void getTvShowTest() {
        MutableLiveData<ArrayList<TvShowDataModel>> mutableTvShowTest = new MutableLiveData<>();
        mutableTvShowTest.setValue(tvShowDataDummies);

        when(remoteRepository.getTvShowsLive()).thenReturn(mutableTvShowTest);
        ArrayList<TvShowDataModel> tvShowListTest = LiveDataTest.getValueTest(fakeContentRepository.getTvShowDatas());

        verify(remoteRepository, times(1)).getTvShowsLive();
        assertNotNull(tvShowListTest);
        assertEquals(tvShowDataDummies.size(), tvShowListTest.size());
    }

    @Test
    public void getTvShowDetailTest() {
        MutableLiveData<TvShowDataModel> mutableTvShowDetailTest = new MutableLiveData<>();
        mutableTvShowDetailTest.setValue(tvShowDataDummies.get(0));

        when(remoteRepository.getTvShowDetailsLive(tvShowId)).thenReturn(mutableTvShowDetailTest);
        TvShowDataModel tvShowDataModelTest = LiveDataTest.getValueTest(fakeContentRepository.getTvShowDetails(tvShowId));

        assertNotNull(tvShowDataModelTest);
        assertEquals(tvShowDataDummies.get(0).getTvShowId(), tvShowDataModelTest.getTvShowId());
    }

}