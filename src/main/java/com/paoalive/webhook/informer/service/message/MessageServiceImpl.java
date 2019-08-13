package com.paoalive.webhook.informer.service.message;

import com.paoalive.webhook.informer.helper.IntegerHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author leonidas_patouhas
 * on 9/8/2019.
 */
@Slf4j
@Service
public class MessageServiceImpl implements MessageService {

    private static Map<Integer, String> messages = new HashMap<>();
    static {
        messages.put(1, "%d Pao friends supported with total %s euros. This may not be enough, but better than nothing!!!! New total %s euros!!");
        messages.put(2, "%d pao supporters just contributed %s euros to PaoAlive!!!! New total %s euros!!");
        messages.put(3, "Pao fans are responding tou our calls! %d Pao fans gave in total %s euros donation!!!! New total %s euros!!");
        messages.put(4, "Something big is happening! %s pao fans supported us with %s euros donation in total!!!! New total %s euros!!");

        messages.put(10, "This is out of this world! %s pao fans supported us with %s euros donation in total!!!! New total %s euros!!");
        messages.put(11, "The big guns are out!!! %s pao fans supported us with %s euros donation in total!!! New total %s euros!!");
    }

    @Override
    public String generateMessage(Integer total, Integer latestContribution, Integer numberOfDonations) {
        String message;
        if (latestContribution > 0) {
            if (latestContribution >= 1000) {
                message = getFormattedMessage(11,numberOfDonations,latestContribution,total);
            } else if (latestContribution <1000 && latestContribution >=100) {
                message = getFormattedMessage(10,numberOfDonations,latestContribution,total);
            } else {
                Random r = new Random();
                int low = 1;
                int high = 4;
                int result = r.nextInt(high-low) + low;
                message = getFormattedMessage(result,numberOfDonations,latestContribution,total);
            }

        } else {
            message =  String.format("No changes to PaoAlive total amount. Still %s", total);
        }
        return message;
    }

    private String getFormattedMessage(int msgNumber ,Integer numberOfDonations, Integer latestContributions, Integer total) {
        String message = messages.get(msgNumber);
        String latestContributionsStr = IntegerHelper.format(latestContributions);
        String totalStr = IntegerHelper.format(total);

        return String.format(message,numberOfDonations,latestContributionsStr, totalStr);
    }
}
