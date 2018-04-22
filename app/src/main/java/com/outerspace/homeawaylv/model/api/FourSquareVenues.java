
package com.outerspace.homeawaylv.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FourSquareVenues {

    @SerializedName("meta")
    @Expose
    public Meta meta;
    @SerializedName("response")
    @Expose
    public Response response;

}
