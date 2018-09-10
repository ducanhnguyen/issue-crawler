package com.fit;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class IssueBitcoinCrawler {
	private static final Logger logger = LogManager.getLogger(IssueBitcoinCrawler.class);


	
	public static void main(String[] args) {
		IssueBitcoinCrawler crawler = new IssueBitcoinCrawler();
		crawler.exportIssuePagesToFile(PAGES);
		
		logger.debug("Export pages done!");

		LogManager.shutdown();
	}

	public IssueBitcoinCrawler() {
	}

	

	public HashSet<URL> getIssuePages(URL theFirstIssuePage) {
		HashSet<URL> issuePages = new HashSet<URL>();

		boolean reachPageLimit = false;
		int pageNum = 1;
		while (!reachPageLimit) {
			logger.debug("page num = " + pageNum);
			try {
				URL newPageURL = new URL(theFirstIssuePage + "?page=" + pageNum++);

				final int CONNECTION_TIMEOUT = 50000;
				Document doc = Jsoup.parse(newPageURL, CONNECTION_TIMEOUT);
				if (!doc.html().contains(NO_ISSUE_ON_A_PAGE))
					issuePages.add(newPageURL);
				else
					reachPageLimit = true;
			} catch (MalformedURLException e) {
				e.printStackTrace();
				reachPageLimit = true;
			} catch (IOException e) {
				e.printStackTrace();
				reachPageLimit = true;
			}
		}
		return issuePages.size() == 0 ? null : issuePages;
	}

	public void exportIssueLinksToFile(File file, HashSet<URL> issuePages) {
		String issues = "";
		if (issuePages != null) {
			for (URL issuePage : issuePages) {
				logger.debug("Analyze " + issuePage);
				HashSet<URL> urls = getIssueLinks(issuePage);
				if (urls != null)
					for (URL url : urls)
						issues += url + "\n";
				Utils.exportToFile(ISSUES, issues);
			}
		}
	}

	public void exportIssuePagesToFile(File file) {
		// Export issue pages to file
		logger.debug("Start exporting pages");
		String pages = "";
		HashSet<URL> issuePages = null;
		try {
			issuePages = getIssuePages(new URL(ISSUE_REPO_BITCOIN_URL));
			if (issuePages != null) {
				for (URL issuePage : issuePages)
					pages += issuePage + "\n";
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		Utils.exportToFile(file, pages);
	}



	
}