
package com.outerspace.homeawaylv.model.api;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Likes {

    @SerializedName("count")
    @Expose
    public Long count;
    @SerializedName("groups")
    @Expose
    public List<Group> groups = null;
    @SerializedName("summary")
    @Expose
    public String summary;

}
