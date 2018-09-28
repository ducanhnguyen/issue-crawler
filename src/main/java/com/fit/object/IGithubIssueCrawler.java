package com.fit.object;

import java.io.File;

public interface IGithubIssueCrawler {
	interface Bitcoin {
		File OPENING_ISSUE_PAGES_FILE = new File("./crawl_data/bitcoin/opening_issue_pages.txt");
		File CLOSED_ISSUE_PAGES_FILE = new File("./crawl_data/bitcoin/closed_issue_pages.txt");
		File OPENING_ISSUE_LINKS_FILE = new File("./crawl_data/bitcoin/opening_issue_links.txt");
		File CLOSED_ISSUE_LINKS_FILE = new File("./crawl_data/bitcoin/closed_issue_links.txt");

		String ISSUE_REPO_BITCOIN_URL = "https://github.com//bitcoin//bitcoin//issues";
	}

	String AN_ISSUE_SIGNAL = "a[class=link-gray-dark v-align-middle no-underline h4 js-navigation-open]";
	String[] NO_ISSUE_ON_A_PAGE = new String[] { "There aren’t any open issues.", "No results matched your search." };
}
