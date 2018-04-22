package com.outerspace.homeawaylv;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.outerspace.homeawaylv.model.ShortCategory;
import com.outerspace.homeawaylv.model.VenueItem;

import android.os.Bundle;
import android.os.Parcel;

import static org.junit.Assert.*;

public class VenueItemParsableTest {
    private VenueItem venueItem;

    @Before
    public void testVenueItemSetup() {

        System.out.println("Starting VenueItemSetup");

        venueItem = new VenueItem();

        venueItem.id = "ThisIsAFakeVenueItemID";
        venueItem.name = "Cocrodile Fish Shop";
        venueItem.address = "Cocrodile Dr. 4500 ";
        venueItem.latitude = 47.608508d;
        venueItem.longitude = -122.324294d;
        venueItem.distance = "123N 456W";
        venueItem.favorite = true;
        venueItem.categoryList = new ArrayList<>();

        ShortCategory shortCategory;

        shortCategory = new ShortCategory();
        shortCategory.name = "Aquarium";
        shortCategory.iconUrl = "http://icons.com/aquarium.png";
        venueItem.categoryList.add(shortCategory);

        shortCategory = new ShortCategory();
        shortCategory.name = "Coffee";
        shortCategory.iconUrl = "http://icons.com/coffee.png";
        venueItem.categoryList.add(shortCategory);

        System.out.println("Finishing VenueItemSetup");
    }

    @Test
    public void testVenueOperations() {
        System.out.println("Starting TEST");
        Parcel parcel = Parcel.obtain();
        assertNotNull("PARSEL SHOULD NOT BE NULL", parcel);

        System.out.println("TESTING: WriteToParcel");
        venueItem.writeToParcel(parcel, venueItem.describeContents());
        parcel.setDataPosition(0);

        System.out.println("TESTING: CreateFromParcel");
        VenueItem venueParcelled = VenueItem.CREATOR.createFromParcel(parcel);

        assertTrue(venueItem.name.equals( venueParcelled.name ));
    }
}
