package com.solarcity.crawler;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

@Configuration
public class ExcelCrawlerConfig {

    @Bean
    CrawlController crawlController(final @Value("${urltoparse}") String urlToVisit) throws Exception {
        final CrawlConfig config = new CrawlConfig();

        // Set the folder where intermediate crawl data is stored (e.g. list of urls that are extracted from previously
        // fetched pages and need to be crawled later).
        config.setCrawlStorageFolder("/tmp/crawler4j/");

        // Where should the downloaded images be stored?
        // Since images are binary content, we need to set this parameter to
        // true to make sure they are included in the crawl.
        config.setIncludeBinaryContentInCrawling(true);

        final PageFetcher pageFetcher = new PageFetcher(config);
        final RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        final RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);

        final CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);
        controller.addSeed(urlToVisit);
        return controller;
    }

    @Bean
    @NotNull
    CrawlController.WebCrawlerFactory<ExcelSheetCrawler>
    webCrawlerFactory(@NotNull final ExcelSheetCrawler excelSheetCrawler) {
        return () -> excelSheetCrawler;
    }

    @NotNull
    @Bean
    ExcelSheetCrawler excelSheetCrawler(final Supplier<String> getCurrentQuarter,
                                        final Function<Page, Set<LocationWithDateAndPrice>> processPageFunction,
                                        final @Value("${urltoparse}") String urlToVisit) {

        return new ExcelSheetCrawler(getCurrentQuarter, processPageFunction, urlToVisit);
    }

    @Bean
    @NotNull
    Supplier<String> getCurrentQuarter() {
        return new GetCurrentAvailableQuarter();
    }

    @Bean
    @NotNull
    Function<Page, Set<LocationWithDateAndPrice>> processPageFunction(
            final @Value("${sheetname}") String sheetName,
            final @Value("${startingtag}") String startingTag,
            final @Value("${endingtag}") String endingTag) {
        return new ProcessPageFunction(sheetName, startingTag, endingTag);
    }

    @Bean
    CrawlControllerExecution crawlControllerExecution(
            final CrawlController.WebCrawlerFactory<ExcelSheetCrawler> webCrawlerFactory,
            final CrawlController crawlController) {

        return new CrawlControllerExecution(webCrawlerFactory, crawlController);
    }
}
