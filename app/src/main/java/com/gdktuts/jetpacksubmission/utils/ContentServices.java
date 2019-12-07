package com.gdktuts.jetpacksubmission.utils;

import com.gdktuts.jetpacksubmission.ui.movie.MovieResponse;
import com.gdktuts.jetpacksubmission.ui.tvshow.TvShowResponse;
import com.gdktuts.jetpacksubmission.ui.movie.MovieDataModel;
import com.gdktuts.jetpacksubmission.ui.tvshow.TvShowDataModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ContentServices {

    @GET("discover/movie")
    Call<MovieResponse> getMovies(
            @Query("api_key") String apiKey);

    @GET("movie/{movie_id}")
    Call<MovieDataModel> getMovieDetails(
            @Path("movie_id") int id,
            @Query("api_key") String apiKey);

    @GET("discover/tv")
    Call<TvShowResponse> getTvShows(
            @Query("api_key") String apiKey);

    @GET("tv/{tv_id}")
    Call<TvShowDataModel> getTvShowDetails(
            @Path("tv_id") int id,
            @Query("api_key") String apiKey);

}
