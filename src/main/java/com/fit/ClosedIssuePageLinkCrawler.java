package com.fit;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;

import com.fit.object.IGithubIssueCrawler;

public class ClosedIssuePageLinkCrawler extends IssuePageLinkCrawler {

	public static void main(String[] args) {
		IssuePageLinkCrawler crawler = new ClosedIssuePageLinkCrawler();

		URL theFirstIssuePage = null;
		try {
			theFirstIssuePage = new URL(IGithubIssueCrawler.Bitcoin.ISSUE_REPO_BITCOIN_URL);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		if (theFirstIssuePage != null)
			crawler.setTheFirstIssuePage(theFirstIssuePage);

		crawler.setIssueTail("&q=is%3Aissue+is%3Aclosed");
		crawler.setIssuePageFile(IGithubIssueCrawler.Bitcoin.CLOSED_ISSUE_PAGES_FILE);

		// Get all issue pages
		HashSet<URL> issuePages = new HashSet<URL>();

		issuePages = crawler.crawlOpeningIssuePages();

		System.out.println("Export pages done! size = " + issuePages.size());
	}
}