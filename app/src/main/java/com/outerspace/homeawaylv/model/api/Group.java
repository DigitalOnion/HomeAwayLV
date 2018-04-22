
package com.outerspace.homeawaylv.model.api;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Group {

    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("count")
    @Expose
    public Long count;
    @SerializedName("summary")
    @Expose
    public String summary;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("items")
    @Expose
    public List<Item> items = null;

}
