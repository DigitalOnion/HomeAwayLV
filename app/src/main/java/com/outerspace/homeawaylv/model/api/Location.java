
package com.outerspace.homeawaylv.model.api;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("address")
    @Expose
    public String address;
    @SerializedName("crossStreet")
    @Expose
    public String crossStreet;
    @SerializedName("lat")
    @Expose
    public Float lat;
    @SerializedName("lng")
    @Expose
    public Float lng;
    @SerializedName("labeledLatLngs")
    @Expose
    public List<LabeledLatLng> labeledLatLngs = null;
    @SerializedName("postalCode")
    @Expose
    public String postalCode;
    @SerializedName("cc")
    @Expose
    public String cc;
    @SerializedName("city")
    @Expose
    public String city;
    @SerializedName("state")
    @Expose
    public String state;
    @SerializedName("country")
    @Expose
    public String country;
    @SerializedName("formattedAddress")
    @Expose
    public List<String> formattedAddress = null;

}
