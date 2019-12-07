package com.gdktuts.jetpacksubmission.ui.tvshow;

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

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder> {

    private ArrayList<TvShowDataModel> tvShowDataModels = new ArrayList<>();
    private Context context;
    private TvShowClickCallback tvShowClickCallback;

    TvShowAdapter(Context context, TvShowClickCallback tvShowClickCallback) {
        this.context = context;
        this.tvShowClickCallback = tvShowClickCallback;
    }

    void setTvShowDataModels(ArrayList<TvShowDataModel> tvShowDataModels) {
        if (tvShowDataModels == null) return;
        this.tvShowDataModels.clear();
        this.tvShowDataModels.addAll(tvShowDataModels);
    }

    @NonNull
    @Override
    public TvShowAdapter.TvShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_content_layout, parent, false);
        return new TvShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowAdapter.TvShowViewHolder holder, int position) {

        TvShowDataModel tvShowDataModel = tvShowDataModels.get(position);

        Glide.with(context)
                .load(BuildConfig.POSTER_URL + tvShowDataModel.getTvShowPosterPath())
                .into(holder.imageContent);

        holder.textContentName.setText(tvShowDataModel.getTvShowTitle());

        Double countRating = tvShowDataModel.getTvShowRating()*10;
        String textRating = new DecimalFormat("##").format(countRating);
        holder.textContentRating.setText(textRating + "%");

        holder.bind(tvShowDataModel);
        holder.itemView.setOnClickListener(view -> tvShowClickCallback.onClick(holder.tvShowDataModel));

    }

    @Override
    public int getItemCount() {
        return tvShowDataModels.size();
    }

    class TvShowViewHolder extends RecyclerView.ViewHolder {

        TextView textContentName, textContentRating;
        ImageView imageContent;
        TvShowDataModel tvShowDataModel;

        TvShowViewHolder(@NonNull View itemView) {
            super(itemView);

            textContentName = itemView.findViewById(R.id.textviewContentNameItem);
            textContentRating = itemView.findViewById(R.id.textviewContentRatingItem);
            imageContent = itemView.findViewById(R.id.imageviewContentItem);

        }

        void bind(TvShowDataModel tvShowDataModel) {
            this.tvShowDataModel = tvShowDataModel;
        }

    }
}
