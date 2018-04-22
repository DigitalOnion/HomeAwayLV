package com.outerspace.homeawaylv;

import org.junit.Before;
import org.junit.Test;

import com.outerspace.homeawaylv.model.persistence.FavoriteStore;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class SampleUnitTest {
    private static final String EQUALS_MESSAGE = "TESTING ON upperCase";

    SampleInnerClass sample;

    @Before
    public void sampleBefore() {
        sample = new SampleInnerClass();
        sample.name = "Luis";
    }

    @Test
    public void favoriteOperations() {
        String uName;
        uName = sample.upperCase();

        assertTrue(EQUALS_MESSAGE, uName.equals("LUIS"));
    }

    private class SampleInnerClass {
        public String name;
        public String upperCase() {
            return name.toUpperCase();
        }
    }
}