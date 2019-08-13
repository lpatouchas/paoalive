package com.paoalive.webhook.informer.service;

import com.paoalive.webhook.informer.dto.ContributionDto;
import com.paoalive.webhook.informer.factory.ContributionFactory;
import com.paoalive.webhook.informer.dto.CrawlRawDataDto;
import com.paoalive.webhook.informer.service.crawl.PaoAliveCrawlService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * @author leonidas_patouhas
 * on 8/8/2019.
 */
@Slf4j
@Service
@AllArgsConstructor
public class PaoAliveService {

    public PaoAliveCrawlService paoAliveCrawlService;

    public ContributionFactory contributionFactory;

    private ApplicationEventPublisher contributionPublisher;

    private CrawlRawDataDto comparisonValues;

    public void inform() {

        //
        // 1. Crawl paoalive for raw data
        //
        CrawlRawDataDto crawlRawData = paoAliveCrawlService.crawl();

        //
        // 2. Create contribution dto with the required details
        //
        ContributionDto contribution = contributionFactory.create(comparisonValues.getTotalInt(), comparisonValues.getNumberOfDonationsInt(), crawlRawData);

        //
        // 3. Publish event for any subscriber
        //
        contributionPublisher.publishEvent(contribution);

        //
        // 4. Update values that will be used for comparison for the next crawl
        //
        updateComparisonValues(crawlRawData);
    }

    /**
     * Not async safe since this needs to be done after the eventListeners evaluation
     * @param newData
     */
    private void updateComparisonValues(CrawlRawDataDto newData) {
        comparisonValues.setTotal(newData.getTotal());
        comparisonValues.setNumberOfDonations(newData.getNumberOfDonations());
        comparisonValues.setBiggestDonor(newData.getBiggestDonor());
        log.debug("New Comparison values: " + comparisonValues.toString());
    }

}
