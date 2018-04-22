package com.outerspace.homeawaylv.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public class ShortCategory implements Parcelable {
    public String name;
    public String iconUrl;

    private static final String NAME = "name";
    private static final String ICON_URL = "icon_url";

    public static final Parcelable.Creator<ShortCategory> CREATOR =
            new Parcelable.Creator<ShortCategory>() {

                @Override
                public ShortCategory createFromParcel(Parcel parcel) {
                    return new ShortCategory(parcel);
                }

                @Override
                public ShortCategory[] newArray(int size) {
                    return new ShortCategory[size];
                }
            };

    public ShortCategory() { }

    public ShortCategory(Parcel parcel) {
        name = parcel.readString();
        iconUrl = parcel.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(name);
        parcel.writeString(iconUrl);
    }
}
