package com.gdktuts.jetpacksubmission.ui.movie;

import com.gdktuts.jetpacksubmission.ui.movie.MovieDataModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MovieResponse {

    @SerializedName("results")
    @Expose
    public ArrayList<MovieDataModel> moviesList;

}
