package com.paoalive.webhook.informer.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * @author leonidas_patouhas
 * on 6/8/2019.
 */
@Getter
@Builder
@ToString
public class ContributionDto {


    private Integer latestContribution;
    private Integer total;
    private String message;
    private Integer numberOfDonations;
    private String biggestDonor;

}
