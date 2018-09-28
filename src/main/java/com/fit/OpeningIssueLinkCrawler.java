package com.fit;

import com.fit.object.IGithubIssueCrawler;

public class OpeningIssueLinkCrawler extends IssueLinkCrawler {
	
	public static void main(String[] args) {
		IssueLinkCrawler crawler = new OpeningIssueLinkCrawler();
		crawler.setIssuePageLinkFile(IGithubIssueCrawler.Bitcoin.OPENING_ISSUE_PAGES_FILE);
		crawler.setBaseIssueLinkPath("https://github.com");
		crawler.setIssueLinksFile(IGithubIssueCrawler.Bitcoin.OPENING_ISSUE_LINKS_FILE);
		crawler.crawl();
	}
}
