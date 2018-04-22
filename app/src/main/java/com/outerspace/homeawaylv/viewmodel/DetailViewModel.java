package com.outerspace.homeawaylv.viewmodel;

import java.util.Locale;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.jakewharton.rxbinding2.widget.RxCompoundButton;
import com.outerspace.homeawaylv.R;
import com.outerspace.homeawaylv.databinding.FragmentDetailBinding;
import com.outerspace.homeawaylv.model.IWebApi;
import com.outerspace.homeawaylv.model.api.FourSquareVenueDetail;
import com.outerspace.homeawaylv.model.api.Venue;
import com.outerspace.homeawaylv.model.persistence.FavoriteStore;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.TypedValue;
import android.widget.CheckBox;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailViewModel {
    private float latitude, latitudeVenue;
    private float longitude, longitudeVenue;
    private String markerTitle;
    private float zoomLevel, bearing, tilt;

    private static final String COLOR_BLUE = "blue";
    private static final String COLOR_RED = "red";
    private static final String FLOAT_FORMAT = "%f";
    private static final String INT_FORMAT = "%d";

    private Context context;
    private FragmentDetailBinding detailBinding;

    private Venue venue = null;

    private String endPoint;
    private String clientId;
    private String clientSecret;
    private String apiVersion;

    private Single<FourSquareVenueDetail> single;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();


    public DetailViewModel(Context context) {
        this.context = context;
        endPoint = context.getString(R.string.endpoint);
        clientId = context.getString(R.string.client_id);
        clientSecret = context.getString(R.string.app_secret);
        apiVersion = context.getString(R.string.api_version);
    }

    public void setDetailBinding(FragmentDetailBinding detailBinding) {
        this.detailBinding = detailBinding;
    }

    public void fetchEvent(String venueId) {
        boolean bLikeIt = FavoriteStore.getInstance().isFavorite(venueId);

        IWebApi api = getRetrofitApi();
        single = api.requestVenueDetail(venueId, clientId, clientSecret, apiVersion);
        compositeDisposable.add(
             single
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( venueDetail -> {
                    venue = venueDetail.response.venue;
                    initialize();
                    detailBinding.setDisplayVenue(venue);
                    detailBinding.likeIt.setChecked(bLikeIt);

                    detailBinding.detailDistance.setText(
                            geoDistance(latitude, latitudeVenue, longitude, longitudeVenue));

                    String url = staticMapUrl(venue);
                    Glide.with(context)
                            .load(url)
                            .into(detailBinding.staticMap);
                })
        );
    }

    public void destroy() {
        compositeDisposable.clear();
    }

    @SuppressLint("CheckResult")
    public void subscribeLike(CheckBox likeCheckbox) {
        RxCompoundButton.checkedChanges(likeCheckbox)
                .subscribe( checked -> {
                    if(venue != null) {
                        FavoriteStore store = FavoriteStore.getInstance(likeCheckbox.getContext());
                        if(checked)
                            store.markAsFavorite(venue.id);
                        else
                            store.removeFavorite(venue.id);
                    }
                });
    }

    private IWebApi getRetrofitApi() {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(endPoint)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(IWebApi.class);
    }

    private void initialize() {
        TypedValue value = new TypedValue();
        context.getResources().getValue(R.dimen.zoom_level, value, true);
        zoomLevel = value.getFloat();
        context.getResources().getValue(R.dimen.bearing, value, true);
        bearing = value.getFloat();
        context.getResources().getValue(R.dimen.tilt, value, true);
        tilt = value.getFloat();

        context.getResources().getValue(R.dimen.center_latitude, value, true);
        latitude = value.getFloat();
        context.getResources().getValue(R.dimen.center_longitude, value, true);
        longitude = value.getFloat();
        markerTitle = context.getString(R.string.center_marker_title);

        latitudeVenue  = venue.location.lat;
        longitudeVenue = venue.location.lng;
    }

    public String staticMapUrl(Venue venue) {

        String url = context.getString(R.string.static_map_url);
        String key = context.getString(R.string.google_static_map_key);
        String mapSize = context.getString(R.string.map_size);
        String mapType = context.getString(R.string.map_type);

        StringBuilder sb = new StringBuilder();
        sb.append(url);
        sb.append('?').append("center=").append(markerTitle);
        sb.append('&').append("zoom=").append(String.format(Locale.US, INT_FORMAT, Math.round(zoomLevel)));
        sb.append('&').append("size=").append(mapSize);
        sb.append('&').append("maptype=").append(mapType);
        sb.append('&').append("markers=").append(
                markerString(COLOR_BLUE,
                        markerTitle, latitude, longitude));
        sb.append('&').append("markers=").append(
                markerString(COLOR_RED,
                        venue.name, venue.location.lat, venue.location.lng));
        sb.append('&').append("key=").append(key);
        String result = sb.toString();
        return sb.toString();
    }

    private String markerString(String color, String label, float lat, float lng) {
        StringBuilder sb = new StringBuilder();
        sb.append("color:").append(color).append('|');
        sb.append("label:").append(label).append('|');
        sb.append(String.format(Locale.US, FLOAT_FORMAT, lat)).append(',');
        sb.append(String.format(Locale.US, FLOAT_FORMAT, lng));
        return sb.toString();
    }

    public String geoDistance(float lat1, float lng1, float lat2, float lng2) {
        float alfaLat = lat2 - lat1;
        float alfaLng = lng1 - lng2;
        char NS = alfaLat >= 0 ? 'N' : 'S';
        char EW = alfaLng >= 0 ? 'E' : 'W';
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(Locale.US, FLOAT_FORMAT, Math.abs(alfaLat))).append(NS).append(' ');
        sb.append(String.format(Locale.US, FLOAT_FORMAT, Math.abs(alfaLng))).append(EW);
        return sb.toString();
    }


}
