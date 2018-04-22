
package com.outerspace.homeawaylv.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Price {

    @SerializedName("tier")
    @Expose
    public Long tier;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("currency")
    @Expose
    public String currency;

}
