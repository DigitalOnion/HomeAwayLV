package com.outerspace.homeawaylv.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class VenueItem implements Parcelable {
    public String id;
    public String name;
    public String address;
    public String iconURL;
    public String distance;
    public float longitude;
    public float latitude;
    public boolean favorite;
    public ArrayList<ShortCategory> categoryList;

    public static final Parcelable.Creator<VenueItem> CREATOR
            = new Parcelable.Creator<VenueItem>() {
                    @Override
                    public VenueItem createFromParcel(Parcel parcel) {
                        return new VenueItem(parcel);
                    }

                    @Override
                    public VenueItem[] newArray(int size) {
                        return new VenueItem[size];
                    }
    };

    public VenueItem() { }

    public VenueItem(Parcel parcel) {
        id = parcel.readString();
        name = parcel.readString();
        address = parcel.readString();
        iconURL = parcel.readString();
        distance = parcel.readString();
        longitude = parcel.readFloat();
        latitude = parcel.readFloat();
        favorite = parcel.readInt() != 0;
        categoryList = new ArrayList<ShortCategory>();
        parcel.readList(categoryList, ShortCategory.class.getClassLoader());
        }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(address);
        parcel.writeString(iconURL);
        parcel.writeString(distance);
        parcel.writeFloat(longitude);
        parcel.writeFloat(latitude);
        parcel.writeInt(favorite ? 1 : 0 );
        parcel.writeList(categoryList);
    }
}
