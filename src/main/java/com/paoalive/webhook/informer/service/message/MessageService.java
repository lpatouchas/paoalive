package com.paoalive.webhook.informer.service.message;

/**
 * @author leonidas_patouhas
 * on 9/8/2019.
 */
public interface MessageService {

    String generateMessage(Integer total, Integer latestContribution, Integer numberOfDonations);
}
