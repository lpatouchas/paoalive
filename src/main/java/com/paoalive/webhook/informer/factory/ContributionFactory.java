package com.paoalive.webhook.informer.factory;

import com.paoalive.webhook.informer.dto.ContributionDto;
import com.paoalive.webhook.informer.dto.CrawlRawDataDto;
import com.paoalive.webhook.informer.dto.PaoAliveException;

/**
 * @author leonidas_patouhas
 * on 8/8/2019.
 */
public interface ContributionFactory {

    ContributionDto create(Integer oldTotal, Integer oldNumberOfDonations, CrawlRawDataDto crawlRawData) throws PaoAliveException;
}
