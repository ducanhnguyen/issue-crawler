package com.fit;

import com.fit.object.IGithubIssueCrawler;

public class ClosedIssueLinkCrawler extends IssueLinkCrawler {

	public static void main(String[] args) {
		IssueLinkCrawler crawler = new ClosedIssueLinkCrawler();
		crawler.setIssuePageLinkFile(IGithubIssueCrawler.Bitcoin.CLOSED_ISSUE_PAGES_FILE);
		crawler.setBaseIssueLinkPath("https://github.com");
		crawler.setIssueLinksFile(IGithubIssueCrawler.Bitcoin.CLOSED_ISSUE_LINKS_FILE);
		crawler.crawl();
	}
}
