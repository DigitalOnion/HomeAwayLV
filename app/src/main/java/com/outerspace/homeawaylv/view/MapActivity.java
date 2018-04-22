package com.outerspace.homeawaylv.view;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.outerspace.homeawaylv.R;
import com.outerspace.homeawaylv.model.VenueItem;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {
    private Context context;
    private GoogleMap mMap;
    private ArrayList<VenueItem> venueItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        context = this.getBaseContext();

        Intent intent = this.getIntent();
        venueItems = intent.getParcelableArrayListExtra(MainActivity.VENUE_ITEM_LIST);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        float latitude;
        float longitude;
        String markerTitle;
        float zoomLevel;
        float bearing;
        float tilt;

        TypedValue value = new TypedValue();
        getResources().getValue(R.dimen.zoom_level, value, true);
        zoomLevel = value.getFloat();
        getResources().getValue(R.dimen.bearing, value, true);
        bearing = value.getFloat();
        getResources().getValue(R.dimen.tilt, value, true);
        tilt = value.getFloat();

        getResources().getValue(R.dimen.center_latitude, value, true);
        latitude = value.getFloat();
        getResources().getValue(R.dimen.center_longitude, value, true);
        longitude = value.getFloat();
        markerTitle = getString(R.string.center_marker_title);

        LatLng cityCenter = new LatLng(latitude, longitude);

        mMap.addMarker(new MarkerOptions().position(cityCenter).title(markerTitle));
        mMap.moveCamera( CameraUpdateFactory
                .newCameraPosition(
                        new CameraPosition(
                                cityCenter,
                                zoomLevel,
                                bearing,
                                tilt)
                )
        );

        LatLng markerCoord;
        for(VenueItem item: venueItems) {
            latitude = item.latitude;
            longitude = item.longitude;
            markerTitle = item.name;
            markerCoord = new LatLng(latitude, longitude);

            mMap.addMarker(new MarkerOptions().position(markerCoord).title(markerTitle));
        }

    }

}
