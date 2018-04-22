
package com.outerspace.homeawaylv.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Photo {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("createdAt")
    @Expose
    public Long createdAt;
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
    @SerializedName("user")
    @Expose
    public User user;
    @SerializedName("visibility")
    @Expose
    public String visibility;

}