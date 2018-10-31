package com.fit.phonganh;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.fit.utils.Utils;

public class PhongAnhExporter {
	private int countId = 0;

	private File issueFolder = null;
	private File outputFolder = null;
	private String issuesSummaryName = "";

	private PhongAnhIssueOutput output = new PhongAnhIssueOutput();

	public static void main(String[] args) {
		PhongAnhExporter exporter = new PhongAnhExporter();
		exporter.setIssueFolder(new File("G:\\workspace\\java\\issue-crawler\\crawl_data\\bitcoin\\all_issues"));
		exporter.setIssuesSummaryName("_summary.txt");
		exporter.setOutputFolder(new File("G:\\workspace\\java\\issue-crawler\\crawl_data\\bitcoin\\phonganh"));
		exporter.export();
	}

	public PhongAnhExporter() {
	}

	public void export() {
		if (issueFolder.exists()) {
			File summaryFile = new File(getOutputFolder() + File.separator + issuesSummaryName);
			if (summaryFile.exists()) {
				summaryFile.delete();
			}

			int analyzedIssue = 1;
			for (File issueFile : issueFolder.listFiles()) {
				System.out.println("Analyzing " + (analyzedIssue++) + "/" + issueFolder.listFiles().length);
				String contentStr = Utils.convertToString(Utils.readFileContent(issueFile));

				Document doc = Jsoup.parse(contentStr);

				if (doc != null) {
					// Get the title of issue
					String title = "";
					Elements titleJsoup = doc.getElementsByClass("js-issue-title");
					if (titleJsoup != null && titleJsoup.size() >= 1) {
						title = removeSourceCode(titleJsoup).get(0).text();
					}

					// Get the first post of issue
					Elements posts = doc.getElementsByClass("d-block comment-body markdown-body  js-comment-body");
					String firstPost = "";
					List<String> otherPosts = new ArrayList<String>();

					if (posts != null && posts.size() >= 1) {
						posts = removeSourceCode(posts);
						firstPost = title + ". " + posts.get(0).text();

						// Get the other posts
						for (int i = 1; i < posts.size(); i++) {
							otherPosts.add(posts.get(i).text());
						}
					}

					// Count the number of followers
					int nFollowers = 1;
					Elements followers = doc.select("#partial-users-participants");
					if (followers != null && followers.size() > 0) {
						Pattern p = Pattern.compile(".*([0-9]+).*"); // Example: std::ios_base::failure, abc, ab_c
						Matcher m1 = p.matcher(followers.get(0).text());

						if (m1.find()) {
							nFollowers = Integer.parseInt(m1.group(1));
						}
					}

					// Export to file
					File fFirstPost = new File(outputFolder.getAbsolutePath() + File.separator + (countId++) + ".txt");
					Utils.exportToFile(fFirstPost, firstPost);

					PhongAnhIssueOutput issueSummary = new PhongAnhIssueOutput();
					issueSummary.setFirstPost(fFirstPost);
					issueSummary.setNumOfFollowers(nFollowers);

					for (String otherPost : otherPosts) {

						File fOtherPost = new File(
								outputFolder.getAbsolutePath() + File.separator + (countId++) + ".txt");
						Utils.exportToFile(fOtherPost, otherPost);

						issueSummary.getOtherPosts().add(fOtherPost);
					}

					Utils.appendToFile(summaryFile, issueSummary.summarize());
				}
			}
		}
	}

	private Elements removeSourceCode(Elements e) {
		if (e != null) {
			Elements sourcecode = e.select("code");
			if (sourcecode != null && sourcecode.size() >= 1)
				sourcecode.remove();
			return e;
		} else
			return null;
	}

	public void setIssueFolder(File issueFolder) {
		this.issueFolder = issueFolder;
	}

	public File getIssueFolder() {
		return issueFolder;
	}

	public void setOutput(PhongAnhIssueOutput output) {
		this.output = output;
	}

	public PhongAnhIssueOutput getOutput() {
		return output;
	}

	public void setIssuesSummaryName(String issuesSummaryName) {
		this.issuesSummaryName = issuesSummaryName;
	}

	public String getIssuesSummaryName() {
		return issuesSummaryName;
	}

	public void setOutputFolder(File outputFolder) {
		this.outputFolder = outputFolder;

		if (outputFolder.exists()) {
			for (File file : outputFolder.listFiles())
				file.delete();
		} else
			outputFolder.mkdir();
	}

	public File getOutputFolder() {
		return outputFolder;
	}
}
