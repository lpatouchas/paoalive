package com.paoalive.webhook.informer.service.crawl;

import com.paoalive.webhook.informer.configuration.PaoAliveConfiguration;
import com.paoalive.webhook.informer.dto.CrawlRawDataDto;
import com.paoalive.webhook.informer.dto.PaoAliveException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author leonidas_patouhas
 * on 8/8/2019.
 */
@Slf4j
@Service
@AllArgsConstructor
public class PaoAliveCrawlServiceImpl implements PaoAliveCrawlService {

    private PaoAliveConfiguration configuration;

    @Override
    public CrawlRawDataDto crawl() {

        Document doc = getDocument();

        String latestTotal = doc.select(configuration.getTotalSelector())
                                .first()
                                .childNode(0)
                                .toString()
                                .trim();

        String newNumberOfDonations = doc.select(configuration.getDonationsSelector())
                                         .first()
                                         .text()
                                         .trim();

        String latestBiggestDonor = doc.select(configuration.getDonorSelector())
                                      .first()
                                      .text()
                                      .trim();

        CrawlRawDataDto data = CrawlRawDataDto.builder()
                                              .biggestDonor(latestBiggestDonor)
                                              .total(latestTotal)
                                              .numberOfDonations(newNumberOfDonations)
                                              .build();

        log.debug(data.toString());
        return data;
    }

    private Document getDocument() throws PaoAliveException {
        try {
            return Jsoup.connect(configuration.getPaoaliveurl())
                        .get();
        } catch (IOException e) {
            throw new PaoAliveException(e);
        }
    }
}
