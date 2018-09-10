package com.fit.object;
import java.io.File;

public interface ICrawler {
	File PAGES = new File("./pages.txt");
	File ISSUES = new File("./issues.txt");

	String ISSUE_REPO_BITCOIN_URL = "https://github.com//bitcoin//bitcoin//issues";
	String AN_ISSUE_SIGNAL = "a[class=link-gray-dark v-align-middle no-underline h4 js-navigation-open]";
	String NO_ISSUE_ON_A_PAGE = "There aren’t any open issues.";
}
