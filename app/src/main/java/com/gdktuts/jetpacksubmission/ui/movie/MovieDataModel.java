package com.gdktuts.jetpacksubmission.ui.movie;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class MovieDataModel implements Parcelable {

    @SerializedName("id")
    private int movieId;

    @SerializedName("vote_average")
    private double movieRating;

    @SerializedName("title")
    private String movieTitle;

    @SerializedName("overview")
    private String movieOverview;

    @SerializedName("poster_path")
    private String moviePosterPath;

    @SerializedName("release_date")
    private String movieReleaseDate;

    public MovieDataModel(int movieId, double movieRating, String movieTitle, String movieOverview,
                          String moviePosterPath, String movieReleaseDate) {
        this.movieId = movieId;
        this.movieRating = movieRating;
        this.movieTitle = movieTitle;
        this.movieOverview = movieOverview;
        this.moviePosterPath = moviePosterPath;
        this.movieReleaseDate = movieReleaseDate;
    }

    protected MovieDataModel(Parcel in) {
        movieId = in.readInt();
        movieRating = in.readDouble();
        movieTitle = in.readString();
        movieOverview = in.readString();
        moviePosterPath = in.readString();
        movieReleaseDate = in.readString();
    }

    public int getMovieId() {
        return movieId;
    }

    public double getMovieRating() {
        return movieRating;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getMovieOverview() {
        return movieOverview;
    }

    public String getMoviePosterPath() {
        return moviePosterPath;
    }

    public String getMovieReleaseDate() {
        return movieReleaseDate;
    }

    public static final Creator<MovieDataModel> CREATOR = new Creator<MovieDataModel>() {
        @Override
        public MovieDataModel createFromParcel(Parcel in) {
            return new MovieDataModel(in);
        }

        @Override
        public MovieDataModel[] newArray(int size) {
            return new MovieDataModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(movieId);
        parcel.writeDouble(movieRating);
        parcel.writeString(movieTitle);
        parcel.writeString(movieOverview);
        parcel.writeString(moviePosterPath);
        parcel.writeString(movieReleaseDate);
    }
}
