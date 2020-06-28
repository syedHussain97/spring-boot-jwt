package com.solarcity.crawler;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.BinaryParseData;
import edu.uci.ics.crawler4j.url.WebURL;

import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Pattern;

public class ExcelSheetCrawler extends WebCrawler {

    private static final Pattern EXCEL_PATTERN = Pattern.compile(".*(\\.(xlsx))$");

    private final Supplier<String> getCurrentQuarter;
    private final String urlToVisit;
    private final Function<Page, Set<LocationWithDateAndPrice>> processPageFunction;

    public ExcelSheetCrawler(final Supplier<String> getCurrentQuarter,
                             final Function<Page, Set<LocationWithDateAndPrice>> processPageFunction,
                             final String urlToVisit) {
        this.getCurrentQuarter = getCurrentQuarter;
        this.urlToVisit = urlToVisit;
        this.processPageFunction = processPageFunction;
    }

    @Override
    public boolean shouldVisit(final Page referringPage, final WebURL url) {
        final String urlString = url.getURL().toLowerCase();

        return EXCEL_PATTERN.matcher(urlString).matches()
                || urlString.equals(urlToVisit);
    }

    @Override
    public void visit(final Page page) {
        final String url = page.getWebURL().getURL();

        // We are only interested in processing images which are bigger than 10k
        if (!EXCEL_PATTERN.matcher(url).matches() ||
                !(page.getParseData() instanceof BinaryParseData ||
                        page.getContentData().length < 10 * 1024)) {
            return;
        } else if (page.getWebURL().getAnchor().contains(getCurrentQuarter.get())) {
            processPageFunction.apply(page);
        }
    }
}