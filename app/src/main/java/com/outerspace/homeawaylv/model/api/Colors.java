
package com.outerspace.homeawaylv.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Colors {

    @SerializedName("highlightColor")
    @Expose
    public HighlightColor highlightColor;
    @SerializedName("highlightTextColor")
    @Expose
    public HighlightTextColor highlightTextColor;
    @SerializedName("algoVersion")
    @Expose
    public Long algoVersion;

}
