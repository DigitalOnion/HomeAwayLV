
package com.outerspace.homeawaylv.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BestPhoto {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("createdAt")
    @Expose
    public Long createdAt;
    @SerializedName("source")
    @Expose
    public Source source;
    @SerializedName("prefix")
    @Expose
    public String prefix;
    @SerializedName("suffix")
    @Expose
    public String suffix;
    @SerializedName("width")
    @Expose
    public Long width;
    @SerializedName("height")
    @Expose
    public Long height;
    @SerializedName("visibility")
    @Expose
    public String visibility;

}
