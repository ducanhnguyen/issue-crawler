package com.fit.issue.content;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.fit.object.IGithubIssueCrawler;
import com.fit.utils.CsvManager;
import com.fit.utils.Utils;
import com.opencsv.CSVWriter;

/**
 * Filter to get issues related to API version
 * 
 * @author Duc-Anh Nguyen
 *
 */
public class IssueRelatedApiVersionFilter extends AbstractIssueFilter {
	/**
	 * The folder containing issues content
	 */
	private File issueFolder = null;

	private Set<ApiVersion> apiVersions = new HashSet<ApiVersion>();

	private File outputCsvFile = null;

	private String baseIssueRepo = new String();

	// Closed or Opening
	private String issueType = new String();

	public static void main(String[] args) {
		IssueRelatedApiVersionFilter filter = new IssueRelatedApiVersionFilter();
		filter.setIssueFolder(IGithubIssueCrawler.Bitcoin.CLOSED_ISSUE_CONTENT_FOLDER);
		filter.setBaseIssueRepo(IGithubIssueCrawler.Bitcoin.ISSUE_REPO_BITCOIN_URL);
		filter.setOutputCsvFile(IGithubIssueCrawler.Bitcoin.ISSUE_RELATED_API_FILE);
		filter.setIssueType("closed");
		Set<ApiVersion> apiVersions = filter.filter();
		filter.appendToCsvFile(apiVersions);
		System.out.println("Export done! " + filter.getOutputCsvFile().getAbsolutePath());
	}

	public IssueRelatedApiVersionFilter() {
	}

	public Set<ApiVersion> filter() {
		Set<ApiVersion> selectedIssues = new HashSet<ApiVersion>();

		if (getIssueFolder().exists()) {
			Set<File> issueFiles = Utils.getFilesInAFolder(getIssueFolder());

			int count = 0;
			for (File issueFile : issueFiles) {
				System.out.println("Parse file id=" + (count++) + "/" + issueFiles.size());

				List<String> content = Utils.readFileContent(issueFile);
				String contentStr = Utils.convertToString(content);

				Document doc = Jsoup.parse(contentStr);

				// Get all posts
				Elements posts = doc.getElementsByClass("d-block comment-body markdown-body  js-comment-body");

				// Find related API version
				for (Element post : posts)

					for (String apiVersionSignal : API_VERSION_SIGNALS) {
						String clonedText = new String(post.text());

						while (clonedText.contains(apiVersionSignal)) {
							// Get the sentence containing the related API token
							int pos = clonedText.indexOf(apiVersionSignal);

							int length = 200;
							int startPos = pos - length / 2 < 0 ? 0 : pos - length / 2;
							int endPos = pos + length / 2 > clonedText.length() - 1 ? clonedText.length() - 1
									: pos + length / 2;
							String apiRelatedSentence = clonedText.substring(startPos, endPos);

//							for (; startPos >= 0; startPos--)
//								if (clonedText.toCharArray()[startPos] == '.') {
//									break;
//								}
//
//							for (; endPos < clonedText.length() - 1; endPos++)
//								if (clonedText.toCharArray()[endPos] == '.') {
//									break;
//								}
//							String apiRelatedSentence = clonedText.substring(startPos + 1, endPos + 1);

							// Add to output set
							ApiVersion api = new ApiVersion();
							api.setIssueFile(issueFile);
							api.getRelatedApiVersionTokens().add(apiRelatedSentence);
							api.setBaseIsseRepo(getBaseIssueRepo());

							selectedIssues.add(api);
							System.out.println("Found one: \"" + apiRelatedSentence + "\"");

							clonedText = clonedText.substring(endPos + 1);
						}
					}
			}
		}
		return selectedIssues;
	}

	public void appendToCsvFile(Set<ApiVersion> apiVersions) {
		if (outputCsvFile != null) {
			CsvManager csvWritter = new CsvManager();
			String[] headers = new String[] { "issue type", "issue link", "issue description" };

			List<String[]> records = new ArrayList<String[]>();
			for (ApiVersion api : apiVersions) {
				String link = api.getIssueLink();

				for (String description : api.getRelatedApiVersionTokens()) {
					description = description.replace(CSVWriter.DEFAULT_LINE_END, ". ")
							.replace(CSVWriter.RFC4180_LINE_END, ". ").replace(CSVWriter.DEFAULT_SEPARATOR + "", ";");
					records.add(new String[] { getIssueType(), link, description });
				}
			}

			csvWritter.appendRecordsToCsv(outputCsvFile, headers, records);
			csvWritter.closeCSVWriter();
		}
	}

	public File getIssueFolder() {
		return issueFolder;
	}

	public void setIssueFolder(File issueFolder) {
		this.issueFolder = issueFolder;
	}

	public Set<ApiVersion> getApiVersions() {
		return apiVersions;
	}

	public void setApiVersions(Set<ApiVersion> apiVersions) {
		this.apiVersions = apiVersions;
	}

	public String getBaseIssueRepo() {
		return baseIssueRepo;
	}

	public void setBaseIssueRepo(String baseIssueRepo) {
		this.baseIssueRepo = baseIssueRepo;
	}

	public File getOutputCsvFile() {
		return outputCsvFile;
	}

	public void setOutputCsvFile(File outputCsvFile) {
		this.outputCsvFile = outputCsvFile;
	}

	public String getIssueType() {
		return issueType;
	}

	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}

	public static final String[] API_VERSION_SIGNALS = new String[] { " API ", " API' "
			/*
			 * "incompatible API", "conflict API", "API conflict", "API change",
			 * "API version change"
			 */ };
}
