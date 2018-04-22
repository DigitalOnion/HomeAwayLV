
package com.outerspace.homeawaylv.model.api;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SuggestedFilters {

    @SerializedName("header")
    @Expose
    public String header;
    @SerializedName("filters")
    @Expose
    public List<Filter> filters = null;

}
