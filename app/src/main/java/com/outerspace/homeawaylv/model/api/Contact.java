
package com.outerspace.homeawaylv.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Contact {

    @SerializedName("phone")
    @Expose
    public String phone;
    @SerializedName("formattedPhone")
    @Expose
    public String formattedPhone;

}
