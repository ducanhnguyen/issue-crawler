package com.fit.issuepage;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;

import com.fit.object.IGithubIssueCrawler;

public class OpeningIssuePageLinkCrawler extends IssuePageLinkCrawler {

	public static void main(String[] args) {
		OpeningIssuePageLinkCrawler crawler = new OpeningIssuePageLinkCrawler();

		URL theFirstIssuePage = null;
		try {
			theFirstIssuePage = new URL(IGithubIssueCrawler.Bitcoin.ISSUE_REPO_BITCOIN_URL);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		if (theFirstIssuePage != null)
			crawler.setTheFirstIssuePage(theFirstIssuePage);

		crawler.setIssueTail("&q=is%3Aopen+is%3Aissue");
		crawler.setIssuePageFile(IGithubIssueCrawler.Bitcoin.OPENING_ISSUE_PAGES_FILE);

		// Get all issue pages
		HashSet<URL> issuePages = new HashSet<URL>();

		issuePages = crawler.crawlOpeningIssuePages();

		System.out.println("Export pages done! size = " + issuePages.size());
	}

}