package com.asg;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;

public class Crawler {
    private HashSet<String> urlSet;
    private static int MAX_DEPTH = 2;

    public Crawler() {
        this.urlSet = new HashSet<String>();
    }

    public void crawlPageTextsAndLinks(String url, int depth) {
        if(this.urlSet.contains(url)) {
            return;
        }

        if(depth > MAX_DEPTH) {
            return;
        }

        depth++;

        try {
            this.urlSet.add(url);

            Document document = Jsoup.connect(url).timeout(10000).ignoreContentType(true).get();
            Indexer indexer = new Indexer(document, url);
            System.out.println(url);

            Elements availbleLinkOnpage = document.select("a[href]");
            for(Element link : availbleLinkOnpage) {
                crawlPageTextsAndLinks(link.attr("abs:href"), depth);
            }
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Crawler crawler = new Crawler();
        crawler.crawlPageTextsAndLinks("https://www.tpointtech.com/", 1);
    }
}