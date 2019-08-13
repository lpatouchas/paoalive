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

import java.util.Map;
import java.util.function.Function;

/**
 * @author leonidas_patouhas
 * on 8/8/2019.
 */
@Service
@Slf4j
public class WebHookContributionNotificationImpl extends AbstractWebHookServiceImpl implements ContributionService {

    public WebHookContributionNotificationImpl(RestTemplate restTemplate, CrawlRawDataDto comparisonValues, PaoAliveConfiguration configuration, Map<String,Function<String, HttpEntity<String>>> webHooks) {
        super(restTemplate, comparisonValues, configuration,webHooks);
    }

    @Override
    @EventListener(condition = "#contribution.latestContribution > 0")
    public void process(ContributionDto contribution) {
        log.info(contribution.getMessage());
        callWebHooks(contribution.getMessage());
    }
}
