package com.gdktuts.jetpacksubmission.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.gdktuts.jetpacksubmission.Injection;
import com.gdktuts.jetpacksubmission.repository.ContentRepository;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private static volatile ViewModelFactory viewModelFactory;
    private final ContentRepository contentRepository;

    public ViewModelFactory(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    public static ViewModelFactory getInstance(Application application) {
        if (viewModelFactory == null) {
            synchronized (ViewModelFactory.class) {
                if (viewModelFactory == null) {
                    viewModelFactory = new ViewModelFactory(Injection.provideRepository(application));
                }
            }
        }
        return viewModelFactory;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MovieViewModel.class)) {
            return (T) new MovieViewModel(contentRepository);
        }
        else if (modelClass.isAssignableFrom(MovieDetailViewModel.class)) {
            return (T) new MovieDetailViewModel(contentRepository);
        }
        else if (modelClass.isAssignableFrom(TvShowViewModel.class)) {
            return (T) new TvShowViewModel(contentRepository);
        }
        else if (modelClass.isAssignableFrom(TvShowDetailViewModel.class)) {
            return (T) new TvShowDetailViewModel(contentRepository);
        }

        throw new IllegalArgumentException("Unknown ViewModel class! : " + modelClass.getName());
    }
}
