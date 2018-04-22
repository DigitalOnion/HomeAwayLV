package com.outerspace.homeawaylv.view;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.outerspace.homeawaylv.R;
import com.outerspace.homeawaylv.databinding.FragmentDetailBinding;
import com.outerspace.homeawaylv.viewmodel.DetailViewModel;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DetailFragment extends Fragment {
    public static final String TAG = "DETAIL_FRAGMENT";

    private DetailViewModel viewModel;
    private FragmentDetailBinding binding;
    private String venueId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle arguments = this.getArguments();
        venueId = arguments.getString(MainActivity.ID_FOR_DETAIL);

        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_detail,
                container,
                false);

        viewModel = new DetailViewModel(this.getContext());
        viewModel.setDetailBinding(binding);
        binding.setDetailViewModel(viewModel);

        viewModel.fetchEvent(venueId);
        viewModel.subscribeLike(binding.likeIt);

        CharSequence title = getResources().getString(R.string.collapsing_toolbar_title);
        binding.collapsingBar.setTitle(title);

        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onDestroyView() {
        viewModel.destroy();
        super.onDestroyView();
    }
}
