package com.paoalive.webhook.informer.dto;

import com.paoalive.webhook.informer.helper.IntegerHelper;
import lombok.Builder;
import lombok.Data;

/**
 * @author leonidas_patouhas
 * on 8/8/2019.
 */
@Data
@Builder
public class CrawlRawDataDto {


    private String total;
    private String numberOfDonations;
    private String biggestDonor;

    public Integer getTotalInt(){
        return IntegerHelper.parseString(total);
    }

    public Integer getNumberOfDonationsInt() {
        return Integer.parseInt(numberOfDonations);
    }
}