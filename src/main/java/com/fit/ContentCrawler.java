package com.fit;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.fit.issue.content.ClosedIssueContentCrawler;
import com.fit.object.IGithubIssueCrawler;
import com.fit.utils.Utils;

/**
 * Crawl content of a link
 * 
 * @author Duc-Anh Nguyen
 *
 */
public class ContentCrawler extends AbstractCrawler {

	/**
	 * This file contain a set of links. Each link is stored in a line. <br/>
	 * Example </br>
	 * "https://github.com/bitcoin/bitcoin/issues/1028
	 * https://github.com/bitcoin/bitcoin/issues/2359
	 * https://github.com/bitcoin/bitcoin/issues/6716
	 * https://github.com/bitcoin/bitcoin/issues/6717"
	 */
	private File linksFile = null;

	/**
	 * The folder storing the content of all links
	 */
	private File outputFolder = null;

	public static void main(String[] args) {
		ContentCrawler contentCrawler = new ClosedIssueContentCrawler();
		contentCrawler.setCrawlStrategy(CONTINUOUS_CRAWEL_STRATEGY);
		contentCrawler.setLinksFile(IGithubIssueCrawler.Bitcoin.CLOSED_ISSUE_LINKS_FILE);
		contentCrawler.setOutputFolder(IGithubIssueCrawler.Bitcoin.CLOSED_ISSUE_CONTENT_FOLDER);
		contentCrawler.crawl();
	}

	public void crawl() {
		if (linksFile != null && linksFile.exists() && outputFolder != null && outputFolder.exists()) {
			List<String> lines = Utils.readFileContent(linksFile);
			for (String line : lines) {
				URL link = null;
				try {
					link = new URL(line);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}

				if (link != null) {
					String linkLastToken = line.substring(line.lastIndexOf("/"));
					File linkFile = new File(outputFolder.getAbsolutePath() + linkLastToken + ".html");

					if (linkFile.exists()) {
						// crawl before
						System.out.println("Crawl before. Ignored!");
					} else {
						String content = crawlContent(link);
						Utils.exportToFile(linkFile, content);
						System.out.println(linkFile.getAbsolutePath() + " is created");
					}
				}
			}
		}
	}

	private String crawlContent(URL link) {
		String content = new String();
		Document doc = null;
		do {
			final int CONNECTION_TIMEOUT = 50000;
			try {
				doc = Jsoup.parse(link, CONNECTION_TIMEOUT);
			} catch (IOException e) {
				System.out.println("Can not parse the link. Wait for 5	 minutes");
				try {
					Thread.sleep(300000); // 5 minutes
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		} while (doc == null);

		content = doc.html();
		return content;
	}

	public File getLinksFile() {
		return linksFile;
	}

	public void setLinksFile(File issueFile) {
		this.linksFile = issueFile;
	}

	public void setOutputFolder(File outputFolder) {
		if (!outputFolder.exists())
			outputFolder.mkdir();

		this.outputFolder = outputFolder;
	}

	public File getOutputFolder() {
		return outputFolder;
	}

}
