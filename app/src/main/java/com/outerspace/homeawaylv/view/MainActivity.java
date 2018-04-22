package com.outerspace.homeawaylv.view;

import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.outerspace.homeawaylv.R;
import com.outerspace.homeawaylv.databinding.ActivityMainBinding;
import com.outerspace.homeawaylv.viewmodel.MainViewModel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static final String VENUE_ITEM_LIST = "venue_item_list";
    public static final String START_DETAIL_MESSAGE = "start_detail_message";
    public static final String ID_FOR_DETAIL = "event_id_for_detail";
    public static final String DEFAULT_ID = "";

    MainViewModel viewModel;
    private BroadcastReceiver startDetailReceiver;

    public MainActivity() {
        startDetailReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String venueId = intent.getStringExtra(ID_FOR_DETAIL);
                if(venueId == null)
                    venueId = DEFAULT_ID;
                viewModel.showDetailFragment(venueId);
            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new MainViewModel( binding, getSupportFragmentManager() );
        binding.setMainViewModel(viewModel);

        LocalBroadcastManager.getInstance(this)
                .registerReceiver(
                        startDetailReceiver,
                        new IntentFilter(START_DETAIL_MESSAGE));
    }

    @Override
    protected void onStart() {
        super.onStart();
        viewModel.showTypeAheadFragment();
    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(startDetailReceiver);
        super.onDestroy();
    }

    public void onMapDetail(View view) {
        // TODO - this will not be in use. The Details screen uses a Static Map.
        Context context = this.getBaseContext();
        Intent intent = new Intent(context, MapActivity.class);
        this.startActivity(intent);
    }
}
