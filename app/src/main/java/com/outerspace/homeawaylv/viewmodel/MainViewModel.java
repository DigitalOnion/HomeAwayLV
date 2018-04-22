package com.outerspace.homeawaylv.viewmodel;

import com.google.android.gms.maps.MapFragment;
import com.outerspace.homeawaylv.databinding.ActivityMainBinding;
import com.outerspace.homeawaylv.view.DetailFragment;
import com.outerspace.homeawaylv.view.MainActivity;
import com.outerspace.homeawaylv.view.TypeAheadFragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

public class MainViewModel {

    FragmentManager fragmentManager;
    TypeAheadFragment typeAheadFragment;
    DetailFragment detailFragment;
    MapFragment mapFragment;

    ActivityMainBinding binding;

    public MainViewModel(ActivityMainBinding binding, FragmentManager fragmentManager ) {
        this.binding = binding;
        this.fragmentManager = fragmentManager;
    }

    public void showTypeAheadFragment() {
        if(typeAheadFragment == null) {
            typeAheadFragment = new TypeAheadFragment();
        }
        int holderResId = binding.fragmentHolder.getId();
        fragmentManager.beginTransaction()
                .replace(holderResId, typeAheadFragment, TypeAheadFragment.TAG)
                .commit();
    }

    public void showDetailFragment(String venueId) {
        if(detailFragment == null) {
            detailFragment = new DetailFragment();
        }
        int holderResId = binding.fragmentHolder.getId();

        Bundle arguments = new Bundle();
        arguments.putString(MainActivity.ID_FOR_DETAIL, venueId);
        detailFragment.setArguments(arguments);
        fragmentManager.beginTransaction()
                .replace(holderResId, detailFragment, TypeAheadFragment.TAG)
                .addToBackStack(detailFragment.getTag())
                .commit();
    }
}
