package com.hussain.securewebcrawler.crawler;

import com.google.common.collect.ImmutableSet;

import java.util.Set;

public class ProcessedDetailsCollector {

    private ImmutableSet<LocationWithDateAndPrice> locationWithDateAndPrices =
            new ImmutableSet.Builder<LocationWithDateAndPrice>().build();

    public Set<LocationWithDateAndPrice> getLocationWithDateAndPrices() {

        return ImmutableSet.copyOf(locationWithDateAndPrices);
    }

    public void setLocationWithDateAndPrices(final Set<LocationWithDateAndPrice> locationWithDateAndPrices) {
        this.locationWithDateAndPrices = ImmutableSet.copyOf(locationWithDateAndPrices);
    }

}
