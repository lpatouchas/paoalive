package com.paoalive.webhook.informer.controller;

import com.paoalive.webhook.informer.dto.ContributionDto;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

/**
 * Simple reactive controller that returns the latest contributions.
 *
 * @author leonidas_patouhas
 * on 9/8/2019.
 */
@RestController
@RequestMapping("/paoalive")
public class PaoAliveController {

    private String latestContributionMessage = "Initializing";

    @GetMapping
    public Flux<String> getContributions() {
        Flux<String> messages = Flux.fromStream(Stream.generate(() -> latestContributionMessage))
                                    .map(s -> "<p>" .concat(s)
                                                   .concat("</p>"))
                                    .delaySequence(Duration.ofMillis(1800000))
                                    .onErrorReturn("An error occurred during Paoalive integration!");

        return messages;
    }


    @EventListener
    public void process(ContributionDto contribution) {
        latestContributionMessage = contribution.getMessage();
    }
}
