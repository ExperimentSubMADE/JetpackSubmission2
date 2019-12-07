package com.gdktuts.jetpacksubmission.ui.tvshow;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class TvShowDataModel implements Parcelable {

    @SerializedName("id")
    private int tvShowId;

    @SerializedName("vote_average")
    private double tvShowRating;

    @SerializedName("name")
    private String tvShowTitle;

    @SerializedName("overview")
    private String tvShowOverview;

    @SerializedName("poster_path")
    private String tvShowPosterPath;

    @SerializedName("first_air_date")
    private String tvShowReleaseDate;

    public TvShowDataModel(int tvShowId, double tvShowRating, String tvShowTitle,
                           String tvShowOverview, String tvShowPosterPath, String tvShowReleaseDate) {
        this.tvShowId = tvShowId;
        this.tvShowRating = tvShowRating;
        this.tvShowTitle = tvShowTitle;
        this.tvShowOverview = tvShowOverview;
        this.tvShowPosterPath = tvShowPosterPath;
        this.tvShowReleaseDate = tvShowReleaseDate;
    }

    protected TvShowDataModel(Parcel in) {
        tvShowId = in.readInt();
        tvShowRating = in.readDouble();
        tvShowTitle = in.readString();
        tvShowOverview = in.readString();
        tvShowPosterPath = in.readString();
        tvShowReleaseDate = in.readString();
    }

    public int getTvShowId() {
        return tvShowId;
    }

    public String getTvShowTitle() {
        return tvShowTitle;
    }

    public String getTvShowOverview() {
        return tvShowOverview;
    }

    public double getTvShowRating() {
        return tvShowRating;
    }

    public String getTvShowPosterPath() {
        return tvShowPosterPath;
    }

    public String getTvShowReleaseDate() {
        return tvShowReleaseDate;
    }

    public static final Creator<TvShowDataModel> CREATOR = new Creator<TvShowDataModel>() {
        @Override
        public TvShowDataModel createFromParcel(Parcel in) {
            return new TvShowDataModel(in);
        }

        @Override
        public TvShowDataModel[] newArray(int size) {
            return new TvShowDataModel[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(tvShowId);
        parcel.writeDouble(tvShowRating);
        parcel.writeString(tvShowTitle);
        parcel.writeString(tvShowOverview);
        parcel.writeString(tvShowPosterPath);
        parcel.writeString(tvShowReleaseDate);
    }
}
