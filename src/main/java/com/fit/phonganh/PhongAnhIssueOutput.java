package com.fit.phonganh;

import java.io.File;
import java.util.ArrayList;

public class PhongAnhIssueOutput {
	private File firstPost = null;
	private ArrayList<File> otherPosts = new ArrayList<File>();
	private int numOfFollowers = 1; // the author of the post is the first person

	public PhongAnhIssueOutput() {
	}

	public void setFirstPost(File firstPost) {
		this.firstPost = firstPost;
	}

	public File getFirstPost() {
		return firstPost;
	}

	public void setOtherPosts(ArrayList<File> otherPosts) {
		this.otherPosts = otherPosts;
	}

	public ArrayList<File> getOtherPosts() {
		return otherPosts;
	}

	public int getNumOfFollowers() {
		return numOfFollowers;
	}

	public void setNumOfFollowers(int numOfFollowers) {
		this.numOfFollowers = numOfFollowers;
	}

	public String summarize() {
		String output = firstPost.getName();

		output += ",[";
		for (File otherPost : otherPosts)
			output += otherPost.getName() + ",";
		if (output.endsWith(","))
			output = output.substring(0, output.length() - 1);
		output += "],";

		output += numOfFollowers;

		output += "\n";
		return output;
	}
}
