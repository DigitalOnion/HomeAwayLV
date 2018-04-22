
package com.outerspace.homeawaylv.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Geocode {

    @SerializedName("what")
    @Expose
    public String what;
    @SerializedName("where")
    @Expose
    public String where;
    @SerializedName("center")
    @Expose
    public Center center;
    @SerializedName("displayString")
    @Expose
    public String displayString;
    @SerializedName("cc")
    @Expose
    public String cc;
    @SerializedName("geometry")
    @Expose
    public Geometry geometry;
    @SerializedName("slug")
    @Expose
    public String slug;
    @SerializedName("longId")
    @Expose
    public String longId;

}
