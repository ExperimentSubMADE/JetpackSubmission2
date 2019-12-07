package com.gdktuts.jetpacksubmission.ui.movie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.gdktuts.jetpacksubmission.BuildConfig;
import com.gdktuts.jetpacksubmission.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private ArrayList<MovieDataModel> movieDataModels = new ArrayList<>();
    private Context context;
    private MovieClickCallback movieClickCallback;

    MovieAdapter(Context context, MovieClickCallback movieClickCallback) {
        this.context = context;
        this.movieClickCallback = movieClickCallback;
    }

    void setMovieDataModels(ArrayList<MovieDataModel> movieDataModels) {
        if (movieDataModels == null) return;
        this.movieDataModels.clear();
        this.movieDataModels.addAll(movieDataModels);
    }

    @NonNull
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_content_layout, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder holder, int position) {

        MovieDataModel movieDataModel = movieDataModels.get(position);

        Glide.with(context)
                .load(BuildConfig.POSTER_URL + movieDataModel.getMoviePosterPath())
                .into(holder.imageContent);

        holder.textContentName.setText(movieDataModel.getMovieTitle());

        Double countRating = movieDataModel.getMovieRating()*10;
        String textRating = new DecimalFormat("##").format(countRating);
        holder.textContentRating.setText(textRating + "%");

        holder.bind(movieDataModel);
        holder.itemView.setOnClickListener(view -> movieClickCallback.onClick(holder.movieDataModel));

    }

    @Override
    public int getItemCount() {
        return movieDataModels.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        TextView textContentName, textContentRating;
        ImageView imageContent;
        MovieDataModel movieDataModel;

        MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            textContentName = itemView.findViewById(R.id.textviewContentNameItem);
            textContentRating = itemView.findViewById(R.id.textviewContentRatingItem);
            imageContent = itemView.findViewById(R.id.imageviewContentItem);

        }

        void bind(MovieDataModel movieDataModel) {
            this.movieDataModel = movieDataModel;
        }

    }
}
