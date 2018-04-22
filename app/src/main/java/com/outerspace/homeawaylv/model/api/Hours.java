
package com.outerspace.homeawaylv.model.api;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hours {

    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("richStatus")
    @Expose
    public RichStatus richStatus;
    @SerializedName("isOpen")
    @Expose
    public Boolean isOpen;
    @SerializedName("isLocalHoliday")
    @Expose
    public Boolean isLocalHoliday;
    @SerializedName("dayData")
    @Expose
    public List<Object> dayData = null;
    @SerializedName("timeframes")
    @Expose
    public List<Timeframe> timeframes = null;

}
