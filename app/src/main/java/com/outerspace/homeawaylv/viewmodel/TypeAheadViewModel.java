package com.outerspace.homeawaylv.viewmodel;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.outerspace.homeawaylv.R;
import com.outerspace.homeawaylv.model.ShortCategory;
import com.outerspace.homeawaylv.model.VenueAdapter;
import com.outerspace.homeawaylv.model.IWebApi;
import com.outerspace.homeawaylv.model.VenueItem;
import com.outerspace.homeawaylv.model.api.Category;
import com.outerspace.homeawaylv.model.api.FourSquareVenues;
import com.outerspace.homeawaylv.model.api.Group;
import com.outerspace.homeawaylv.model.api.Item;
import com.outerspace.homeawaylv.model.api.Venue;
import com.outerspace.homeawaylv.model.persistence.FavoriteStore;
import com.outerspace.homeawaylv.view.MainActivity;
import com.outerspace.homeawaylv.view.MapActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TypeAheadViewModel {
    private final String EMPTY_IMAGE_URL = "";
    private final int PER_PAGE = 10;
    private final int PAGE_NUM = 1;
    private final String CATEGORY_ICON_SIZE = "32";
    private String endPoint;
    private String clientId;
    private String clientSecret;
    private String nearCityState;
    private String version;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private RecyclerView recycler;
    private Context context;
    private ArrayList<VenueItem> venueItems;
    private FavoriteStore store;

    public TypeAheadViewModel(@Nullable Context context) {
        this.context = context;
        if(context != null) {
            endPoint = context.getString(R.string.endpoint);
            version = context.getString(R.string.api_version);
            nearCityState = context.getString(R.string.nearCityState);
            clientId = context.getString(R.string.client_id);
            clientSecret = context.getString(R.string.app_secret);
        }
        store = FavoriteStore.getInstance(context);
        venueItems = new ArrayList<>();
    }

    public void setRecycler(RecyclerView recycler) {
        this.recycler = recycler;
    }

    public ArrayList<VenueItem> getVenueItems() {
        return venueItems;
    }

    public void setVenueItems(ArrayList<VenueItem> venueItems) {
        this.venueItems = venueItems;
    }

    public  void subscribeTypeAhead(EditText typeAhead) {
        compositeDisposable.add(
            RxTextView.textChanges(typeAhead)
                    .subscribe( queryEditable -> {
                        refreshRecycler(queryEditable);
                    })
        );
    }

    public void subscribeFAB(FloatingActionButton fab) {
        compositeDisposable.add(
                RxView.clicks(fab)
                .subscribe( aVoid -> {
                    mapAllMarkers();
                    }
                )
        );
    }

    public void refreshRecycler(CharSequence queryEditable) {
        venueItems.clear();
        Single<FourSquareVenues> single;

        IWebApi api = getRetrofitApi();

        single = api.requestVenues(
                queryEditable.toString(),
                nearCityState,
                version,
                PAGE_NUM,
                PER_PAGE,
                clientId,
                clientSecret
        );

        compositeDisposable.add(
                single
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe( fourSquareVenues -> {
                        List<Group> groups = fourSquareVenues.response.groups;
                        for(Group group : groups) {
                            for(Item item: group.items) {
                                Venue venue = item.venue;
                                VenueItem venueItem = new VenueItem();
                                venueItem.name = venue.name;
                                venueItem.id = venue.id;
                                venueItem.address = venue.location.address;
                                venueItem.longitude = venue.location.lng;
                                venueItem.latitude = venue.location.lat;
                                venueItem.distance = "1234.5";
                                venueItem.favorite = FavoriteStore.getInstance().isFavorite(venue.id);
                                venueItem.iconURL = "";  // TODO: FIND THE VENUE'S LOGO OR ICON
                                venueItem.categoryList = new ArrayList<>();
                                ShortCategory shCategory = new ShortCategory();
                                for(Category category: venue.categories) {
                                    shCategory.name = category.name;
                                    shCategory.iconUrl = category.icon.prefix
                                            + CATEGORY_ICON_SIZE
                                            + category.icon.suffix;
                                    venueItem.categoryList.add(  shCategory);
                                }
                                venueItems.add(venueItem);
                            }
                        }

                        VenueAdapter adapter = new VenueAdapter(context, venueItems);
                        recycler.setAdapter( adapter );
                        recycler.getAdapter().notifyDataSetChanged();
                    })
        );
    }

    public void mapAllMarkers() {
        Intent intent = new Intent(context, MapActivity.class);
        intent.putParcelableArrayListExtra(MainActivity.VENUE_ITEM_LIST, venueItems);
        context.startActivity(intent);
    }

    public void destroy() {
        compositeDisposable.clear();
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

}
