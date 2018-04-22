package com.outerspace.homeawaylv.model;

import com.outerspace.homeawaylv.model.api.FourSquareVenueDetail;
import com.outerspace.homeawaylv.model.api.FourSquareVenues;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IWebApi {

    /*
     https://api.foursquare.com/v2/
    */

    @GET("venues/explore")
    Single<FourSquareVenues> requestVenues(
            @Query("query") String queryString,         // query string (for example: coffee shop)
            @Query("near")  String nearCityState,       // location by name. (for example: Seattle, WA)
            @Query("v") String version,                 // version MMDDYYYY
            @Query("offset") int offset,                // initial record to request (pagination)
            @Query("limit") int limit,                  // limit number of records per response (pagination)
            @Query("client_id") String ClientId,        // credentials
            @Query("client_secret") String ClientSecret // credentials
    );

    @GET("venues/{id}")
    Single<FourSquareVenueDetail> requestVenueDetail(
            @Path("id") String venueId,
            @Query("client_id") String ClientId,         // credentials
            @Query("client_secret") String ClientSecret, // credentials
            @Query("v") String version                   // version MMDDYYYY
    );
}
