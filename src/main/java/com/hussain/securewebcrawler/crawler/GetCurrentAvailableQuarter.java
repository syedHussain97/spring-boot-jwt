package com.hussain.securewebcrawler.crawler;

import java.util.Calendar;
import java.util.function.Supplier;

import static java.util.Calendar.MAY;

public class GetCurrentAvailableQuarter implements Supplier<String> {

    @Override
    public String get() {

        final int currentMonthAvailable = Calendar.getInstance().get(Calendar.MONTH) / 3;

        final int year = Calendar.getInstance().get(Calendar.YEAR);

        return (currentMonthAvailable >= Calendar.FEBRUARY && currentMonthAvailable <= MAY)
                ? "May " + year :
                (currentMonthAvailable >= Calendar.JUNE && currentMonthAvailable <= Calendar.AUGUST)
                        ? "August " + year :
                        (currentMonthAvailable >= Calendar.SEPTEMBER && currentMonthAvailable <= Calendar.NOVEMBER) ?
                                "November" + year :
                                "February " + year;
    }
}
