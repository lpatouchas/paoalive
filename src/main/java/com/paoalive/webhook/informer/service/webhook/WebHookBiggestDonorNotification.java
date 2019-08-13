package com.paoalive.webhook.informer.service.webhook;

import com.paoalive.webhook.informer.configuration.PaoAliveConfiguration;
import com.paoalive.webhook.informer.dto.ContributionDto;
import com.paoalive.webhook.informer.dto.CrawlRawDataDto;
import com.paoalive.webhook.informer.service.ContributionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Locale;
import java.util.Map;
import java.util.function.Function;

/**
 * @author leonidas_patouhas
 * on 8/8/2019.
 */
@Service
@Slf4j
public class WebHookBiggestDonorNotification extends AbstractWebHookServiceImpl implements ContributionService {

    private static final String BIGGEST_DONOR_TEXT = "UNBELIEVABLE! We have a new Biggest Contributor to PAO ALIVE!! %s! New total %s euro!";

    public WebHookBiggestDonorNotification(RestTemplate restTemplate, CrawlRawDataDto comparisonValues, PaoAliveConfiguration configuration, Map<String,Function<String, HttpEntity<String>>> webHooks) {
        super(restTemplate, comparisonValues, configuration, webHooks);
    }

    @Override
    @EventListener(condition = "@webHookBiggestDonorNotification.isNewBiggestDonor(#contribution)")
    public void process(ContributionDto contribution) {
        String message = String.format(Locale.GERMAN, BIGGEST_DONOR_TEXT, contribution.getBiggestDonor(), contribution.getTotal());
        log.info(message);

        callWebHooks(message);
    }

    public boolean isNewBiggestDonor(ContributionDto contributionDto) {
        return !comparisonValues.getBiggestDonor().trim().equals(contributionDto.getBiggestDonor().trim());
    }
}
