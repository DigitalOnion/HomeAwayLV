
package com.outerspace.homeawaylv.model.api;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("suggestedFilters")
    @Expose
    public SuggestedFilters suggestedFilters;
    @SerializedName("geocode")
    @Expose
    public Geocode geocode;
    @SerializedName("warning")
    @Expose
    public Warning warning;
    @SerializedName("headerLocation")
    @Expose
    public String headerLocation;
    @SerializedName("headerFullLocation")
    @Expose
    public String headerFullLocation;
    @SerializedName("headerLocationGranularity")
    @Expose
    public String headerLocationGranularity;
    @SerializedName("query")
    @Expose
    public String query;
    @SerializedName("totalResults")
    @Expose
    public Long totalResults;
    @SerializedName("suggestedBounds")
    @Expose
    public SuggestedBounds suggestedBounds;
    @SerializedName("groups")
    @Expose
    public List<Group> groups = null;

}
