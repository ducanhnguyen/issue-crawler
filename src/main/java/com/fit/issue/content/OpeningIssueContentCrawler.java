package com.fit.issue.content;

import com.fit.ContentCrawler;
import com.fit.object.IGithubIssueCrawler;

/**
 * Crawl the content of opening issues
 * 
 * @author Duc-Anh Nguyen
 *
 */
public class OpeningIssueContentCrawler extends ContentCrawler {

	public static void main(String[] args) {
		ContentCrawler contentCrawler = new OpeningIssueContentCrawler();
		contentCrawler.setCrawlStrategy(CONTINUOUS_CRAWEL_STRATEGY);
		contentCrawler.setLinksFile(IGithubIssueCrawler.Bitcoin.OPENING_ISSUE_LINKS_FILE);
		contentCrawler.setOutputFolder(IGithubIssueCrawler.Bitcoin.OPENING_ISSUE_CONTENT_FOLDER);
		contentCrawler.crawl();
	}
}
