package com.gdktuts.jetpacksubmission.repository;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gdktuts.jetpacksubmission.BuildConfig;
import com.gdktuts.jetpacksubmission.utils.ContentServices;
import com.gdktuts.jetpacksubmission.utils.EspressoIdlingResource;
import com.gdktuts.jetpacksubmission.ui.movie.MovieResponse;
import com.gdktuts.jetpacksubmission.utils.RetrofitServices;
import com.gdktuts.jetpacksubmission.ui.tvshow.TvShowResponse;
import com.gdktuts.jetpacksubmission.ui.movie.MovieDataModel;
import com.gdktuts.jetpacksubmission.ui.tvshow.TvShowDataModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiRepository {

    public Application application;
    private ContentServices contentServices;

    public ApiRepository(Application application) {
        this.application = application;
    }

    LiveData<ArrayList<MovieDataModel>> getLiveMovies() {
        EspressoIdlingResource.countIncrement();
        contentServices = RetrofitServices.getRetrofitInstance().create(ContentServices.class);

        MutableLiveData<ArrayList<MovieDataModel>> mutableMovies = new MutableLiveData<>();
        contentServices.getMovies(BuildConfig.TMDB_API_Key).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    mutableMovies.setValue(response.body().moviesList);
                }
                EspressoIdlingResource.countDecrement();
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                mutableMovies.setValue(null);
            }
        });
        return mutableMovies;
    }

    public LiveData<MovieDataModel> getLiveMovieDetail(int id) {
        EspressoIdlingResource.countIncrement();
        contentServices = RetrofitServices.getRetrofitInstance().create(ContentServices.class);

        MutableLiveData<MovieDataModel> mutableMovieDetails = new MutableLiveData<>();
        contentServices.getMovieDetails(id, BuildConfig.TMDB_API_Key).enqueue(new Callback<MovieDataModel>() {
            @Override
            public void onResponse(Call<MovieDataModel> call, Response<MovieDataModel> response) {
                if (response.isSuccessful()) {
                    mutableMovieDetails.setValue(response.body());
                }
                EspressoIdlingResource.countDecrement();
            }

            @Override
            public void onFailure(Call<MovieDataModel> call, Throwable t) {
                mutableMovieDetails.setValue(null);
            }
        });
        return mutableMovieDetails;
    }


    public LiveData<ArrayList<TvShowDataModel>> getLiveTvShows() {
        EspressoIdlingResource.countIncrement();
        contentServices = RetrofitServices.getRetrofitInstance().create(ContentServices.class);

        MutableLiveData<ArrayList<TvShowDataModel>> mutableTvShows = new MutableLiveData<>();
        contentServices.getTvShows(BuildConfig.TMDB_API_Key).enqueue(new Callback<TvShowResponse>() {
            @Override
            public void onResponse(Call<TvShowResponse> call, Response<TvShowResponse> response) {
                if (response.isSuccessful()){
                    assert response.body() != null;
                    mutableTvShows.setValue(response.body().tvShowsList);
                }
                EspressoIdlingResource.countDecrement();
            }

            @Override
            public void onFailure(Call<TvShowResponse> call, Throwable t) {
                mutableTvShows.setValue(null);
            }
        });
        return mutableTvShows;
    }

    public LiveData<TvShowDataModel> getLiveTvShowDetail(int id) {
        EspressoIdlingResource.countIncrement();
        contentServices = RetrofitServices.getRetrofitInstance().create(ContentServices.class);

        MutableLiveData<TvShowDataModel> mutableTvShowDetails = new MutableLiveData<>();
        contentServices.getTvShowDetails(id, BuildConfig.TMDB_API_Key).enqueue(new Callback<TvShowDataModel>() {
            @Override
            public void onResponse(Call<TvShowDataModel> call, Response<TvShowDataModel> response) {
                if (response.isSuccessful()) {
                    mutableTvShowDetails.setValue(response.body());
                }
                EspressoIdlingResource.countDecrement();
            }

            @Override
            public void onFailure(Call<TvShowDataModel> call, Throwable t) {
                mutableTvShowDetails.setValue(null);
            }
        });
        return mutableTvShowDetails;
    }

}
