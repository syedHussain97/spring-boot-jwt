package com.hussain.securewebcrawler.crawler;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.collect.ImmutableMap;
import org.immutables.value.Value;

import java.util.Date;

@Value.Immutable
@Value.Style(allParameters = true,
        visibility = Value.Style.ImplementationVisibility.PUBLIC,
        typeImmutable = "LocationWithDateAndPrice")
@JsonSerialize(as = LocationWithDateAndPrice.class)
@JsonDeserialize(as = LocationWithDateAndPrice.class)
public interface LocationWithDateAndPriceType {

    String locationId();

    ImmutableMap<Date, Double> dateAndValueMap();
}
