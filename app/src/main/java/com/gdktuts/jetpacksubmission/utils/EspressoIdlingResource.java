package com.gdktuts.jetpacksubmission.utils;

import androidx.test.espresso.idling.CountingIdlingResource;

public class EspressoIdlingResource {

    private static final String RESOURCE = "GLOBAL";
    private static CountingIdlingResource countingIdlingResource = new CountingIdlingResource(RESOURCE);

    public static void countIncrement() {
        countingIdlingResource.increment();
    }

    public static void countDecrement() {
        countingIdlingResource.decrement();
    }

    public static CountingIdlingResource getCountingIdlingResource() {
        return countingIdlingResource;
    }
}
