package com.paoalive.webhook.informer.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author leonidas_patouhas
 * on 9/8/2019.
 */
@Configuration
@Getter
@Setter
public class PaoAliveConfiguration {

    @Value("${application.paoalive.url}")
    private String paoaliveurl;

    @Value("${application.total.selector}")
    private String totalSelector;

    @Value("${application.donations.selector}")
    private String donationsSelector;

    @Value("${application.donor.selector}")
    private String donorSelector;

    @Value("${application.slack.url}")
    protected String slackUrl;
}
