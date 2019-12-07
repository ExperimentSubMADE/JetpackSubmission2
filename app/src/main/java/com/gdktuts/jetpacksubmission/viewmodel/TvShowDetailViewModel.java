package com.gdktuts.jetpacksubmission.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.gdktuts.jetpacksubmission.repository.ContentRepository;
import com.gdktuts.jetpacksubmission.ui.tvshow.TvShowDataModel;

public class TvShowDetailViewModel extends ViewModel {

    private ContentRepository contentRepository;
    private int tvShowId;

    public TvShowDetailViewModel(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    public LiveData<TvShowDataModel> getTvShowDetailViewModel() {
        return contentRepository.getTvShowDetails(tvShowId);
    }

    public void setTvShowId(int tvShowId) {
        this.tvShowId = tvShowId;
    }
}
