package com.fit.issuepage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.fit.LinkCrawler;
import com.fit.object.IGithubIssueCrawler;
import com.fit.utils.Utils;

public class IssuePageLinkCrawler extends LinkCrawler {

	private URL theFirstIssuePage = null;
	private File issuePageFile = null;

	// The tail of issue page link
	private String issueTail = new String();

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

	public IssuePageLinkCrawler() {

	}

	/**
	 * Get all opening issue pages
	 * 
	 * @param theFirstIssuePage the first issue page
	 * @return
	 */
	public HashSet<URL> crawlOpeningIssuePages() {
		HashSet<URL> issuePages = new HashSet<URL>();

		// load the results of the previous crawls
		if (getCrawlStrategy() == CONTINUOUS_CRAWEL_STRATEGY && getIssuePageFile().exists()) {
			List<String> previousUrls = Utils.readFileContent(getIssuePageFile());
			for (String previousUrl : previousUrls)
				try {
					issuePages.add(new URL(previousUrl));
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
		}

		// crawl new pages
		if (issuePageFile != null && theFirstIssuePage != null) {
			boolean isReachedPageLimit = false;
			int pageNumId = 1;
			final int CONNECTION_TIMEOUT = 50000;

			while (!isReachedPageLimit) {

				URL newPageURL = null;
				try {
					newPageURL = new URL(theFirstIssuePage + "?page=" + pageNumId + getIssueTail());
					System.out.println("page: " + newPageURL.toString());
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}

				if (newPageURL != null) {
					if (issuePages.contains(newPageURL)) {
						// nothing to do
						System.out.println("\tThis link exists. Ignored.");
					} else {
						Document doc = null;
						do {
							try {
								doc = Jsoup.parse(newPageURL, CONNECTION_TIMEOUT);
							} catch (IOException e) {
								System.out.println("Can not parse the link. Wait for 5	 minutes");
								try {
									Thread.sleep(300000); // 5 minutes
								} catch (InterruptedException e1) {
									e1.printStackTrace();
								}
							}
						} while (doc == null);

						if (doc != null) {
							boolean noIssue = false;
							for (String noIssueSignal : IGithubIssueCrawler.NO_ISSUE_ON_A_PAGE)
								if (doc.html().contains(noIssueSignal))
									noIssue = true;

							if (noIssue)
								isReachedPageLimit = true;
							else
								issuePages.add(newPageURL);

							Utils.exportUrlsToFile(issuePageFile, issuePages);
						}
					}
					pageNumId++;
				}
			}
		}
		return issuePages;
	}

	public URL getTheFirstIssuePage() {
		return theFirstIssuePage;
	}

	public void setTheFirstIssuePage(URL theFirstIssuePage) {
		this.theFirstIssuePage = theFirstIssuePage;
	}

	public File getIssuePageFile() {
		return issuePageFile;
	}

	public void setIssuePageFile(File issuePageFile) {
		this.issuePageFile = issuePageFile;
	}

	public void setIssueTail(String issueTail) {
		this.issueTail = issueTail;
	}

	public String getIssueTail() {
		return issueTail;
	}
}
