
package com.outerspace.homeawaylv.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Filter {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("key")
    @Expose
    public String key;

}
