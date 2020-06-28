package com.solarcity.crawler;

import edu.uci.ics.crawler4j.crawler.CrawlController;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

public class CrawlControllerExecution implements ApplicationListener<ApplicationReadyEvent> {

    final CrawlController.WebCrawlerFactory<ExcelSheetCrawler> webCrawlerFactory;
    final CrawlController crawlController;

    public CrawlControllerExecution(final CrawlController.WebCrawlerFactory<ExcelSheetCrawler> webCrawlerFactory,
                                    final CrawlController crawlController) {
        this.webCrawlerFactory = webCrawlerFactory;
        this.crawlController = crawlController;
    }

    @Override
    public void onApplicationEvent(@NotNull ApplicationReadyEvent event) {

        crawlController.start(webCrawlerFactory, 1);
    }
}
