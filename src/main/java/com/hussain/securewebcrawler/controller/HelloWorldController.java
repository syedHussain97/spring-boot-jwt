package com.hussain.securewebcrawler.controller;

import com.hussain.securewebcrawler.crawler.LocationWithDateAndPrice;
import com.hussain.securewebcrawler.crawler.ProcessedDetailsCollector;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.function.Supplier;

@RestController
public class HelloWorldController {

    private final Supplier<Set<LocationWithDateAndPrice>> locationWithDateAndPriceSupplier;

    public HelloWorldController(final ProcessedDetailsCollector processedDetailsCollector) {
        this.locationWithDateAndPriceSupplier = processedDetailsCollector::getLocationWithDateAndPrices;
    }

    @RequestMapping({"/hello"})
    public String firstPage() {
        return "Hello World";
    }


    @RequestMapping("/get-info")
    public ResponseEntity<Set<LocationWithDateAndPrice>> getLocationWithDateAndPrice() {

        final Set<LocationWithDateAndPrice> body = locationWithDateAndPriceSupplier.get();

//        final ImmutableSet<Date> collect =
//                body.stream().map(LocationWithDateAndPrice::date).collect(ImmutableSet.toImmutableSet());
//
//        final ImmutableList<ResponseInfo> responseInfo = collect.stream().map(date -> {
//            final ResponseInfo.Builder builder = ResponseInfo.builder();
//            builder.date(date);
//
//            body.stream().filter(locationWithDateAndPrice -> locationWithDateAndPrice.date().equals(date))
//                    .forEach(locationWithDateAndPrice -> builder.putLocationIdValueMap(
//                            locationWithDateAndPrice.locationId(), locationWithDateAndPrice.value()));
//
//
//            return builder.build();
//        }).collect(ImmutableList.toImmutableList());

        return ResponseEntity.ok(body);
    }

}