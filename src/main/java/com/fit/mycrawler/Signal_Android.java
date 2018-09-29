package com.fit.mycrawler;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;

import com.fit.ContentCrawler;
import com.fit.issue.content.ClosedIssueContentCrawler;
import com.fit.issue.content.IssueRelatedApiVersionFilter;
import com.fit.issue.content.OpeningIssueContentCrawler;
import com.fit.issue.link.ClosedIssueLinksCrawler;
import com.fit.issue.link.IssueLinkCrawler;
import com.fit.issuepage.ClosedIssuePageLinkCrawler;
import com.fit.issuepage.IssuePageLinkCrawler;

/**
 * Githublink: Signal-Android https://github.com/signalapp/Signal-Android <br/>
 * 
 * @author Duc-Anh Nguyen
 *
 */
public class Signal_Android {
	public Signal_Android() {
	}

	public static void main(String[] args) {
		Signal_Android crawler = new Signal_Android();

		// crawl issue page links
		crawler.crawlIssuePages("https://github.com/signalapp/Signal-Android/issues",
				new File("./crawl_data/Signal-Android/closed_issue_pages.txt"), "&q=is%3Aissue+is%3Aclosed");
		crawler.crawlIssuePages("https://github.com/signalapp/Signal-Android/issues",
				new File("./crawl_data/Signal-Android/opening_issue_pages.txt"), "&q=is%3Aopen+is%3Aissue");

		// crawl closed issue links
		IssueLinkCrawler closedIssueCrawler = new ClosedIssueLinksCrawler();
		closedIssueCrawler.setIssuePageLinkFile(new File("./crawl_data/Signal-Android/closed_issue_pages.txt"));
		closedIssueCrawler.setBaseIssueLinkPath("https://github.com");
		closedIssueCrawler.setIssueLinksFile(new File("./crawl_data/Signal-Android/closed_issue_links.txt"));
		closedIssueCrawler.crawl();

		// crawl opening issue links
		IssueLinkCrawler openingIssueCrawler = new ClosedIssueLinksCrawler();
		openingIssueCrawler.setIssuePageLinkFile(new File("./crawl_data/Signal-Android/opening_issue_pages.txt"));
		openingIssueCrawler.setBaseIssueLinkPath("https://github.com");
		openingIssueCrawler.setIssueLinksFile(new File("./crawl_data/Signal-Android/opening_issue_links.txt"));
		openingIssueCrawler.crawl();

		// crawl closed issue content
		ContentCrawler contentCrawler = new ClosedIssueContentCrawler();
		contentCrawler.setLinksFile(new File("./crawl_data/Signal-Android/closed_issue_links.txt"));
		contentCrawler.setOutputFolder(new File("./crawl_data/Signal-Android/closed_issues"));
		contentCrawler.crawl();

		// crawl opening issue content
		ContentCrawler contentCrawler2 = new OpeningIssueContentCrawler();
		contentCrawler2.setLinksFile(new File("./crawl_data/Signal-Android/opening_issue_links.txt"));
		contentCrawler2.setOutputFolder(new File("./crawl_data/Signal-Android/opening_issues"));
		contentCrawler2.crawl();

		// Filter closed issues related to API version
		IssueRelatedApiVersionFilter closedFilter = new IssueRelatedApiVersionFilter();
		closedFilter.setIssueFolder(new File("./crawl_data/Signal-Android/closed_issues"));
		closedFilter.setBaseIssueRepo("https://github.com/signalapp/Signal-Android/issues");
		closedFilter.setOutputCsvFile(new File("./crawl_data/Signal-Android/api.csv"));
		closedFilter.setIssueType("closed");
		closedFilter.appendToCsvFile(closedFilter.filter());

		// Filter closed issues related to API version
		IssueRelatedApiVersionFilter openingFilter = new IssueRelatedApiVersionFilter();
		openingFilter.setIssueFolder(new File("./crawl_data/Signal-Android/opening_issues"));
		openingFilter.setBaseIssueRepo("https://github.com/signalapp/Signal-Android/issues");
		openingFilter.setOutputCsvFile(new File("./crawl_data/Signal-Android/api.csv"));
		openingFilter.setIssueType("opening");
		openingFilter.appendToCsvFile(openingFilter.filter());
	}

	/**
	 * Crawl issue pages
	 * 
	 * @param issueRepoURL  Ex: "https:/github.com/bitcoin/bitcoin/issues"
	 * @param issuePageFile Ex: "./crawl_data/bitcoin/closed_issue_pages.txt"
	 * @param tail          Ex: "&q=is%3Aissue+is%3Aclosed" or
	 *                      "&q=is%3Aopen+is%3Aissue"
	 */
	public void crawlIssuePages(String issueRepoURL, File issuePageFile, String tail) {
		IssuePageLinkCrawler crawler = new ClosedIssuePageLinkCrawler();

		URL theFirstIssuePage = null;
		try {
			theFirstIssuePage = new URL(issueRepoURL);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		if (theFirstIssuePage != null)
			crawler.setTheFirstIssuePage(theFirstIssuePage);

		crawler.setIssueTail(tail);
		crawler.setIssuePageFile(issuePageFile);

		// Get all issue pages
		HashSet<URL> issuePages = new HashSet<URL>();

		issuePages = crawler.crawlIssuePages();
		System.out.println("Export pages done! size = " + issuePages.size());
	}

}
