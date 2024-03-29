package com.gdktuts.jetpacksubmission.ui.tvshow;

import com.gdktuts.jetpacksubmission.ui.tvshow.TvShowDataModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TvShowResponse {

    @SerializedName("results")
    @Expose
    public ArrayList<TvShowDataModel> tvShowsList;

}
