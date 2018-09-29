package com.fit.issue.content;

import com.fit.ContentCrawler;
import com.fit.object.IGithubIssueCrawler;

/**
 * Crawl the content of closed issues
 * 
 * @author Duc-Anh Nguyen
 *
 */
public class ClosedIssueContentCrawler extends ContentCrawler {

	public static void main(String[] args) {
		ContentCrawler contentCrawler = new ClosedIssueContentCrawler();
		contentCrawler.setCrawlStrategy(CONTINUOUS_CRAWEL_STRATEGY);
		contentCrawler.setLinksFile(IGithubIssueCrawler.Bitcoin.CLOSED_ISSUE_LINKS_FILE);
		contentCrawler.setOutputFolder(IGithubIssueCrawler.Bitcoin.CLOSED_ISSUE_CONTENT_FOLDER);
		contentCrawler.crawl();
	}
}
