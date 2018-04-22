
package com.outerspace.homeawaylv.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HighlightColor {

    @SerializedName("photoId")
    @Expose
    public String photoId;
    @SerializedName("value")
    @Expose
    public Long value;

}
