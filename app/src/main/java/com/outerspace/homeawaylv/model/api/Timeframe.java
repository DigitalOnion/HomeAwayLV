
package com.outerspace.homeawaylv.model.api;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Timeframe {

    @SerializedName("days")
    @Expose
    public String days;
    @SerializedName("includesToday")
    @Expose
    public Boolean includesToday;
    @SerializedName("open")
    @Expose
    public List<Open> open = null;
    @SerializedName("segments")
    @Expose
    public List<Object> segments = null;

}
