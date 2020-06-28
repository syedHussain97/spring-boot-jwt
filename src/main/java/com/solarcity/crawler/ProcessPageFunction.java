package com.solarcity.crawler;

import com.google.common.collect.ImmutableSet;
import edu.uci.ics.crawler4j.crawler.Page;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.function.Function;

public class ProcessPageFunction implements Function<Page, Set<LocationWithDateAndPrice>> {

    private final String sheetName;

    private final String startingTag;

    private final String endingTag;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessPageFunction.class);

    public ProcessPageFunction(final String sheetName,
                               final String startingTag,
                               final String endingTag) {
        this.sheetName = sheetName;
        this.startingTag = startingTag;
        this.endingTag = endingTag;
    }


    @Override
    public Set<LocationWithDateAndPrice> apply(final Page page) {

        final Workbook workbook;
        try {
            workbook = new XSSFWorkbook(new ByteArrayInputStream(page.getContentData()));
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
        final Sheet sheet = workbook.getSheet(sheetName);
        final Iterator<Row> rowItr = sheet.iterator();

        boolean linesComponentReached = false;

        final List<Date> dateSet = new ArrayList<>(0);

        final ImmutableSet.Builder<LocationWithDateAndPrice> locationWithDateAndPriceBuilder =
                new ImmutableSet.Builder<>();

        while (rowItr.hasNext() && !linesComponentReached) {
            final Row row = rowItr.next();
            final Iterator<Cell> cellItr = row.iterator();
            Optional<String> locationId = Optional.empty();
            int rowNum = 0;
            final Map<Date, Double> dateValuesMap = new HashMap<>(0);
            while (cellItr.hasNext()) {
                final Cell cell = cellItr.next();

                if (CellType.STRING == cell.getCellType()) {
                    if (cell.getStringCellValue().contains(endingTag)) {
                        linesComponentReached = true;
                        break;
                    }
                    if (cell.getStringCellValue().contains(startingTag)) {
                        continue;
                    }

                    locationId = Optional.of(cell.getStringCellValue());

                } else if (DateUtil.isCellDateFormatted(cell)) {

                    dateSet.add(cell.getDateCellValue());
                } else if (CellType.NUMERIC == cell.getCellType()) {

                    //adding in single loop rather than looping twice
                    dateValuesMap.put(dateSet.get(rowNum - 1),
                            cell.getNumericCellValue());
                } else if (CellType._NONE == cell.getCellType()) {
                }

                rowNum++;
            }
            try {
                workbook.close();
                ((InputStream) new ByteArrayInputStream(page.getContentData())).close();
            } catch (final IOException e) {
                throw new RuntimeException(e);
            }
            locationId.ifPresent(id ->
                    locationWithDateAndPriceBuilder.add(LocationWithDateAndPrice.of(id, dateValuesMap)));
        }
        final ImmutableSet<LocationWithDateAndPrice> locationWithDateAndPrices =
                locationWithDateAndPriceBuilder.build();

        for (final LocationWithDateAndPriceType locationWithDateAndPriceType : locationWithDateAndPrices) {

            LOGGER.debug(locationWithDateAndPriceType.locationId());
        }
        return locationWithDateAndPrices;
    }
}
