package com.paoalive.webhook.informer.service;

import com.paoalive.webhook.informer.dto.ContributionDto;

/**
 * @author leonidas_patouhas
 * on 8/8/2019.
 */
public interface ContributionService {

    void process(ContributionDto contribution);
}
