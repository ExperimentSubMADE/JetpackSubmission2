package com.gdktuts.jetpacksubmission.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.gdktuts.jetpacksubmission.repository.ContentRepository;
import com.gdktuts.jetpacksubmission.ui.tvshow.TvShowDataModel;

import java.util.ArrayList;

public class TvShowViewModel extends ViewModel {

    private ContentRepository contentRepository;

    public TvShowViewModel(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    public LiveData<ArrayList<TvShowDataModel>> getTvShowViewModel() {
        return contentRepository.getTvShowDatas();
    }
}
