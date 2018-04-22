package com.outerspace.homeawaylv.view;

import com.outerspace.homeawaylv.R;
import com.outerspace.homeawaylv.databinding.FragmentTypeAheadBinding;
import com.outerspace.homeawaylv.model.VenueAdapter;
import com.outerspace.homeawaylv.model.api.FourSquareVenues;
import com.outerspace.homeawaylv.viewmodel.TypeAheadViewModel;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import io.reactivex.Flowable;

public class TypeAheadFragment extends Fragment {
    public static final String TAG = "TYPE_AHEAD_FRAGMENT";

    private FragmentTypeAheadBinding binding;
    private TypeAheadViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_type_ahead,
                container,
                false);

        viewModel = new TypeAheadViewModel(this.getContext());
        binding.setTypeAheadViewModel(viewModel);
        viewModel.setRecycler(binding.eventRecycler);

        viewModel.subscribeTypeAhead(binding.typeAhead);
        viewModel.subscribeFAB(binding.fab);
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();

        binding.eventRecycler.setLayoutManager( new LinearLayoutManager( this.getContext() ) );
        VenueAdapter adapter = new VenueAdapter(this.getContext(), viewModel.getVenueItems());
        binding.eventRecycler.setAdapter( adapter );
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.refreshRecycler(binding.typeAhead.getText());
    }

    @Override
    public void onDestroyView() {
        viewModel.destroy();
        super.onDestroyView();
    }
}
