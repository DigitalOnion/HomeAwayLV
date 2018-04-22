
package com.outerspace.homeawaylv.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("pluralName")
    @Expose
    public String pluralName;
    @SerializedName("shortName")
    @Expose
    public String shortName;
    @SerializedName("icon")
    @Expose
    public Icon icon;
    @SerializedName("primary")
    @Expose
    public Boolean primary;

}
