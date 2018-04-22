
package com.outerspace.homeawaylv.model.api;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Venue {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("location")
    @Expose
    public Location location;
    @SerializedName("categories")
    @Expose
    public List<Category> categories = null;
    @SerializedName("photos")
    @Expose
    public Photos photos;
    @SerializedName("venuePage")
    @Expose
    public VenuePage venuePage;

}
