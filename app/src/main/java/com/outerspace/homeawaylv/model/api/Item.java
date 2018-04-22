
package com.outerspace.homeawaylv.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("reasons")
    @Expose
    public Reasons reasons;
    @SerializedName("venue")
    @Expose
    public Venue venue;
    @SerializedName("referralId")
    @Expose
    public String referralId;

}
