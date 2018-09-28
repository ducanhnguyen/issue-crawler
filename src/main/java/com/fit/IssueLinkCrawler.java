package com.fit;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.fit.object.IGithubIssueCrawler;

public class IssueLinkCrawler extends LinkCrawler {

	private File issuePageLinkFile = null;
	private String baseIssueLinkPath = new String();
	private File issueLinksFile = null;

	public static void main(String[] args) {
		IssueLinkCrawler crawler = new OpeningIssueLinkCrawler();
		crawler.setIssuePageLinkFile(IGithubIssueCrawler.Bitcoin.OPENING_ISSUE_PAGES_FILE);
		crawler.setBaseIssueLinkPath("https://github.com");
		crawler.setIssueLinksFile(IGithubIssueCrawler.Bitcoin.OPENING_ISSUE_LINKS_FILE);
		crawler.crawl();
	}

	public Set<URL> crawl() {
		Set<URL> issueLinks = new HashSet<URL>();

		List<String> issuePageLinks = Utils.readFileContent(issuePageLinkFile);

		for (String issuePageLink : issuePageLinks) {
			System.out.println("issuePageLink=" + issuePageLink);
			URL issuePageLinkURL = null;
			try {
				issuePageLinkURL = new URL(issuePageLink);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}

			if (issuePageLinkURL != null) {
				// Get Document object of an url
				Document doc = null;
				do {
					final int CONNECTION_TIMEOUT = 50000;
					try {
						doc = Jsoup.parse(issuePageLinkURL, CONNECTION_TIMEOUT);
					} catch (IOException e) {
						System.out.println("Can not parse the link. Wait for 5	 minutes");
						try {
							Thread.sleep(300000); // 5 minutes
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
					}
				} while (doc == null);

				// Get all issues in the Document
				Elements issues = doc.select(IGithubIssueCrawler.AN_ISSUE_SIGNAL);
				for (Element issue : issues) {
					/**
					 * An example of issue on github:
					 * <a href="/bitcoin/bitcoin/issues/14187" class="link-gray-dark v-align-middle
					 * no-underline h4 js-navigation-open"> wallet: SendMoney does not report error
					 * correctly </a>
					 */
					String issueLink = issue.attr("href");
					if (issueLink.contains("/issues/")) {
						try {
							issueLinks.add(new URL(baseIssueLinkPath + issueLink));
						} catch (MalformedURLException e) {
							e.printStackTrace();
						}
					}
				}
			}
			System.out.println("Write new issue links to file. size = " + issueLinks.size());
			Utils.exportUrlsToFile(getIssueLinksFile(), issueLinks);
		}

		return issueLinks;
	}

	public File getIssuePageLinkFile() {
		return issuePageLinkFile;
	}

	public void setIssuePageLinkFile(File issuePageLinkFile) {
		this.issuePageLinkFile = issuePageLinkFile;
	}

	public String getBaseIssueLinkPath() {
		return baseIssueLinkPath;
	}

	public void setBaseIssueLinkPath(String baseIssueLinkPath) {
		this.baseIssueLinkPath = baseIssueLinkPath;
	}

	public File getIssueLinksFile() {
		return issueLinksFile;
	}

	public void setIssueLinksFile(File issueLinksFile) {
		if (issueLinksFile.exists())
			issueLinksFile.delete();
		this.issueLinksFile = issueLinksFile;
	}

}
