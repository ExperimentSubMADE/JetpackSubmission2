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

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TvShowDetailViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    private TvShowDetailViewModel testTvShowDetailViewModel;
    private ContentRepository testContentRepository = mock(ContentRepository.class);
    private TvShowDataModel tvShowDataTest = FakeContentDataDummy.tvShowDataDummies().get(0);
    private int tvShowId = tvShowDataTest.getTvShowId();

    @Before
    public void setUp() {
        testTvShowDetailViewModel = new TvShowDetailViewModel(testContentRepository);
        testTvShowDetailViewModel.setTvShowId(tvShowId);
    }

    @Test
    public void getTvShowDetailTest() {
        MutableLiveData<TvShowDataModel> mutableTvShowDetailTest = new MutableLiveData<>();
        mutableTvShowDetailTest.setValue(tvShowDataTest);

        when(testContentRepository.getTvShowDetails(tvShowId)).thenReturn(mutableTvShowDetailTest);

        Observer<TvShowDataModel> observerTest = mock(Observer.class);
        testTvShowDetailViewModel.getTvShowDetailViewModel().observeForever(observerTest);
        verify(observerTest).onChanged(tvShowDataTest);
    }

}