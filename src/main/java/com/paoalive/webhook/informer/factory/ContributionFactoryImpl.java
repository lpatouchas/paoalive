package com.paoalive.webhook.informer.factory;

import com.paoalive.webhook.informer.dto.ContributionDto;
import com.paoalive.webhook.informer.dto.CrawlRawDataDto;
import com.paoalive.webhook.informer.dto.PaoAliveException;
import com.paoalive.webhook.informer.helper.IntegerHelper;
import com.paoalive.webhook.informer.service.message.MessageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author leonidas_patouhas
 * on 8/8/2019.
 */
@Slf4j
@Component
@AllArgsConstructor
public class ContributionFactoryImpl implements ContributionFactory {

    private MessageService messageService;

    /**
     *
     * @param oldTotal
     * @param oldNumberOfDonations
     * @param crawlRawData
     * @return
     * @throws PaoAliveException
     */
    @Override
    public ContributionDto create(Integer oldTotal, Integer oldNumberOfDonations, CrawlRawDataDto crawlRawData) throws PaoAliveException {

        Integer latest = IntegerHelper.parseString(crawlRawData.getTotal());
        Integer total = latest;
        Integer latestContribution = latest - oldTotal;
        Integer numberOfDonations = crawlRawData.getNumberOfDonationsInt() - oldNumberOfDonations;

        String message = messageService.generateMessage(total, latestContribution, numberOfDonations);

        ContributionDto contribution = ContributionDto.builder()
                                                      .latestContribution(latestContribution)
                                                      .numberOfDonations(numberOfDonations)
                                                      .message(message)
                                                      .total(total)
                                                      .biggestDonor(crawlRawData.getBiggestDonor())
                                                      .build();
        log.debug(contribution.toString());
        return contribution;
    }

}
