package com.fit.object;

import java.io.File;

import com.fit.utils.Utils;

public interface IGithubIssueCrawler {
	interface Bitcoin {
		File OPENING_ISSUE_PAGES_FILE = Utils.normalizePath(new File("./crawl_data/bitcoin/opening_issue_pages.txt"));
		File CLOSED_ISSUE_PAGES_FILE = Utils.normalizePath(new File("./crawl_data/bitcoin/closed_issue_pages.txt"));

		File OPENING_ISSUE_LINKS_FILE = Utils.normalizePath(new File("./crawl_data/bitcoin/opening_issue_links.txt"));
		File OPENING_ISSUE_CONTENT_FOLDER = Utils.normalizePath(new File("./crawl_data/bitcoin/opening_issues"));

		File CLOSED_ISSUE_LINKS_FILE = Utils.normalizePath(new File("./crawl_data/bitcoin/closed_issue_links.txt"));
		File CLOSED_ISSUE_CONTENT_FOLDER = Utils.normalizePath(new File("./crawl_data/bitcoin/closed_issues"));

		String ISSUE_REPO_BITCOIN_URL = "https:/github.com/bitcoin/bitcoin/issues";
	}

	String AN_ISSUE_SIGNAL = "a[class=link-gray-dark v-align-middle no-underline h4 js-navigation-open]";
	String[] NO_ISSUE_ON_A_PAGE = new String[] { "There aren’t any open issues.", "No results matched your search." };
}
