
package com.outerspace.homeawaylv.model.api;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PageUpdates {

    @SerializedName("count")
    @Expose
    public Long count;
    @SerializedName("items")
    @Expose
    public List<Object> items = null;

}
