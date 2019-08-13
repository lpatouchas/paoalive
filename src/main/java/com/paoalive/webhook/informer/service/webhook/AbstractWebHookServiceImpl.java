package com.paoalive.webhook.informer.service.webhook;

import com.paoalive.webhook.informer.configuration.PaoAliveConfiguration;
import com.paoalive.webhook.informer.dto.CrawlRawDataDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.function.Function;

/**
 * @author leonidas_patouhas
 * on 8/8/2019.
 */
@AllArgsConstructor
public abstract class AbstractWebHookServiceImpl {

    protected RestTemplate restTemplate;

    protected CrawlRawDataDto comparisonValues;

    protected PaoAliveConfiguration configuration;

    protected Map<String, Function<String, HttpEntity<String>>> webHooks;

    protected void callWebHooks(String message) {
        webHooks.entrySet().forEach(hook -> restTemplate.postForObject(hook.getKey(), hook.getValue().apply(message), String.class));
    }

}
