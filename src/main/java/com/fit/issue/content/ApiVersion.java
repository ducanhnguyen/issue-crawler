package com.fit.issue.content;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * Represent an api version found while filtering
 * 
 * @author Duc-Anh Nguyen
 *
 */
public class ApiVersion {
	// Closed or Opening
	private String issueType = new String();

	private File issueFile;

	// Ex: https://github.com/bitcoin/bitcoin/issues/
	private String baseIsseRepo;

	private Set<String> relatedApiVersionTokens = new HashSet<String>();

	public File getIssueFile() {
		return issueFile;
	}

	public void setIssueFile(File issueFile) {
		this.issueFile = issueFile;
	}

	public Set<String> getRelatedApiVersionTokens() {
		return relatedApiVersionTokens;
	}

	public void setRelatedApiVersionTokens(Set<String> relatedApiVersionTokens) {
		this.relatedApiVersionTokens = relatedApiVersionTokens;
	}

	public String getBaseIsseRepo() {
		return baseIsseRepo;
	}

	public void setBaseIsseRepo(String baseIsseRepo) {
		this.baseIsseRepo = baseIsseRepo;
	}

	/**
	 * Ex: https://github.com/bitcoin/bitcoin/issues/14340
	 * 
	 * @return
	 */
	public String getIssueLink() {
		String issueLink = new String();
		if (getBaseIsseRepo().endsWith("/"))
			issueLink = getBaseIsseRepo() + getIssueFile().getName();
		else
			issueLink = getBaseIsseRepo() + "/" + getIssueFile().getName();
		return issueLink;
	}

	public String getIssueType() {
		return issueType;
	}

	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}

	@Override
	public String toString() {
		return getIssueFile().getAbsolutePath() + ": " + getRelatedApiVersionTokens().toString();
	}
}
