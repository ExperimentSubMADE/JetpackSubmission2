package com.gdktuts.jetpacksubmission.viewmodel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.gdktuts.jetpacksubmission.repository.ContentRepository;
import com.gdktuts.jetpacksubmission.ui.tvshow.TvShowDataModel;
import com.gdktuts.jetpacksubmission.utils.FakeContentDataDummy;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TvShowViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    private TvShowViewModel testTvShowViewModel;
    private ContentRepository testContentRepository = mock(ContentRepository.class);

    @Before
    public void setUp() {
        testTvShowViewModel = new TvShowViewModel(testContentRepository);
    }

    @Test
    public void getTvShowTest() {
        ArrayList<TvShowDataModel> tvShowDataTest = FakeContentDataDummy.tvShowDataDummies();
        MutableLiveData<ArrayList<TvShowDataModel>> testMutableTvShow = new MutableLiveData<>();
        testMutableTvShow.setValue(tvShowDataTest);

        when(testContentRepository.getTvShowDatas()).thenReturn(testMutableTvShow);

        Observer<ArrayList<TvShowDataModel>> observerTest = mock(Observer.class);
        testTvShowViewModel.getTvShowViewModel().observeForever(observerTest);
        verify(observerTest).onChanged(tvShowDataTest);
    }

}