
package com.outerspace.homeawaylv.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FourSquareVenueDetail {

    @SerializedName("meta")
    @Expose
    public Meta meta;
    @SerializedName("response")
    @Expose
    public ResponseDetail response;

}
