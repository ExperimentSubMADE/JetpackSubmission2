package com.gdktuts.jetpacksubmission.ui.tvshow;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.gdktuts.jetpacksubmission.R;
import com.gdktuts.jetpacksubmission.viewmodel.ViewModelFactory;
import com.gdktuts.jetpacksubmission.viewmodel.TvShowViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment {

    private RecyclerView recyclerviewTvShowItem;
    private TvShowAdapter tvShowAdapter;
    private ShimmerFrameLayout shimmerLayoutTvShow;

    public TvShowFragment() {
        // Required empty public constructor
    }

    @NonNull
    private static TvShowViewModel obtainViewModel(FragmentActivity fragmentActivity) {
        ViewModelFactory viewModelFactory = ViewModelFactory.getInstance(fragmentActivity.getApplication());
        return ViewModelProviders.of(fragmentActivity, viewModelFactory).get(TvShowViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerviewTvShowItem = view.findViewById(R.id.recylerviewTvShowItem);
        shimmerLayoutTvShow = view.findViewById(R.id.shimmerlayoutTvShow);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        shimmerLayoutTvShow.setVisibility(View.VISIBLE);
        shimmerLayoutTvShow.startShimmer();
        if (getActivity() != null) {
            TvShowViewModel tvShowViewModel = obtainViewModel(getActivity());
            tvShowAdapter = new TvShowAdapter(getActivity(), tvShowClickCallback);
            tvShowViewModel.getTvShowViewModel().observe(this, this::setRecylerView);
        }
    }

    private TvShowClickCallback tvShowClickCallback = tvShowDataModel -> {
        Intent intent = new Intent(getActivity(), TvShowDetailActivity.class);
        intent.putExtra(TvShowDetailActivity.EXTRA_INFO_TV_SHOW, tvShowDataModel.getTvShowId());
        startActivity(intent);
    };

    private void setRecylerView(ArrayList<TvShowDataModel> tvShowsContent) {
        tvShowAdapter.setTvShowDataModels(tvShowsContent);
        recyclerviewTvShowItem.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerviewTvShowItem.setHasFixedSize(true);
        recyclerviewTvShowItem.setAdapter(tvShowAdapter);
        shimmerLayoutTvShow.stopShimmer();
        shimmerLayoutTvShow.setVisibility(View.GONE);
    }

}
