
package com.outerspace.homeawaylv.model.api;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HereNow {

    @SerializedName("count")
    @Expose
    public Long count;
    @SerializedName("summary")
    @Expose
    public String summary;
    @SerializedName("groups")
    @Expose
    public List<Object> groups = null;

}
