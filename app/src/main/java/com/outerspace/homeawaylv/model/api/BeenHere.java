
package com.outerspace.homeawaylv.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BeenHere {

    @SerializedName("count")
    @Expose
    public Long count;
    @SerializedName("unconfirmedCount")
    @Expose
    public Long unconfirmedCount;
    @SerializedName("marked")
    @Expose
    public Boolean marked;
    @SerializedName("lastCheckinExpiredAt")
    @Expose
    public Long lastCheckinExpiredAt;

}
