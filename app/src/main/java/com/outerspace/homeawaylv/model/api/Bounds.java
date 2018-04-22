
package com.outerspace.homeawaylv.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bounds {

    @SerializedName("ne")
    @Expose
    public Ne ne;
    @SerializedName("sw")
    @Expose
    public Sw sw;

}
