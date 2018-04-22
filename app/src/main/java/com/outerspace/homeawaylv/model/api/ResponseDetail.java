
package com.outerspace.homeawaylv.model.api;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseDetail {
    @SerializedName("venue")
    @Expose
    public Venue venue;

}
