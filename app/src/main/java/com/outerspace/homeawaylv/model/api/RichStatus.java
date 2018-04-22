
package com.outerspace.homeawaylv.model.api;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RichStatus {

    @SerializedName("entities")
    @Expose
    public List<Object> entities = null;
    @SerializedName("text")
    @Expose
    public String text;

}
