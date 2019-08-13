package com.paoalive.webhook.informer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.IOException;

/**
 * @author leonidas_patouhas
 * on 7/8/2019.
 */
public class PlayTest {

    @Test
    public void testme() throws IOException {

            Document doc = Jsoup.connect("https://paoalive.gr/supporters?sort=amount")
                                .get();

            String latestTotal = doc.select("div > header > div.fixed-header.evergreen-bg.py-3 > div > div > div.position-static.col-md-auto.col-12 > div.fw-lg.d-inline-block.h-total").first().childNode(0).toString().trim();

            String sum = doc.select("div > div > div > h3 > span")
                                .first().text();

        String donor = doc.select("#my-table2 > tbody > tr:nth-child(1)")
                        .first().text().trim();
            System.out.println(latestTotal);
        System.out.println(sum);
        System.out.println(donor);

    }
}
