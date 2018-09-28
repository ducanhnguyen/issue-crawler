package com.fit.object;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Page extends HashSet<Issue> implements IGithubIssueCrawler {
	private URL url;

	public static void main(String[] args) {

	}

	public HashSet<Issue> getIssueLinks(URL linkPage) {
		HashSet<Issue> issues = new HashSet<Issue>();
		Document doc = null;
		try {
			final int CONNECTION_TIMEOUT = 50000;
			doc = Jsoup.parse(linkPage, CONNECTION_TIMEOUT);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (doc != null) {
			Elements issueLinks = doc.select(AN_ISSUE_SIGNAL);
			for (Element issueLink : issueLinks)
				try {
					String link = extractLinkFromAnIssueElement(issueLink);
					if (link != null) {
						URL url = new URL(link);
						issues.add(new Issue(url));
					}
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
		}
		return issues;
	}

	/**
	 * 
	 * @param anIssueElement E.g., "<a href="/bitcoin/bitcoin/issues/14134" class=
	 *                       "link-gray-dark v-align-middle no-underline h4
	 *                       js-navigation-open"> windows: Show sync progress in
	 *                       task bar </a>"
	 * @return
	 */
	private String extractLinkFromAnIssueElement(Element anIssueElement) {
		String issueLink = anIssueElement.attr("href");
		if (issueLink.startsWith("/bitcoin/bitcoin/issues/")) {
			issueLink = "https://github.com" + issueLink;
		} else
			issueLink = null;
		return issueLink;
	}

	public Page(URL url) {
		this.url = url;
	}

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return url.toString();
	}
}
