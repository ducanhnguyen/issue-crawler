package com.fit.tamanh;

import java.io.File;

import com.fit.issue.content.IssueRelatedApiVersionFilter;

public class Filter {
	public static void main(String[] args) {
		// android
		IssueRelatedApiVersionFilter openingFilter = new IssueRelatedApiVersionFilter();
		openingFilter.setIssueFolder(new File("./crawl_data/android/opening_issues"));
		openingFilter.setBaseIssueRepo("https://github.com/surespot/android/issues");
		openingFilter.setOutputCsvFile(new File("./crawl_data/tamanh_android_api.csv"));
		openingFilter.setIssueType("opening");
		openingFilter.appendToCsvFile(openingFilter.filter());

		IssueRelatedApiVersionFilter closedFilter = new IssueRelatedApiVersionFilter();
		closedFilter.setIssueFolder(new File("./crawl_data/android/closed_issues"));
		closedFilter.setBaseIssueRepo("https://github.com/surespot/android/issues");
		closedFilter.setOutputCsvFile(new File("./crawl_data/tamanh_android_api.csv"));
		closedFilter.setIssueType("closed");
		closedFilter.appendToCsvFile(closedFilter.filter());

		// AntennaPod
		openingFilter = new IssueRelatedApiVersionFilter();
		openingFilter.setIssueFolder(new File("./crawl_data/AntennaPod/opening_issues"));
		openingFilter.setBaseIssueRepo("https://github.com/AntennaPod/AntennaPod/issues");
		openingFilter.setOutputCsvFile(new File("./crawl_data/tamanh_AntennaPod_api.csv"));
		openingFilter.setIssueType("opening");
		openingFilter.appendToCsvFile(openingFilter.filter());

		closedFilter = new IssueRelatedApiVersionFilter();
		closedFilter.setIssueFolder(new File("./crawl_data/AntennaPod/closed_issues"));
		closedFilter.setBaseIssueRepo("https://github.com/AntennaPod/AntennaPod/issues");
		closedFilter.setOutputCsvFile(new File("./crawl_data/tamanh_AntennaPod_api.csv"));
		closedFilter.setIssueType("closed");
		closedFilter.appendToCsvFile(closedFilter.filter());

		// connectbot
		openingFilter = new IssueRelatedApiVersionFilter();
		openingFilter.setIssueFolder(new File("./crawl_data/connectbot/opening_issues"));
		openingFilter.setBaseIssueRepo("https://github.com/connectbot/connectbot/issues");
		openingFilter.setOutputCsvFile(new File("./crawl_data/tamanh_connectbot_api.csv"));
		openingFilter.setIssueType("opening");
		openingFilter.appendToCsvFile(openingFilter.filter());

		closedFilter = new IssueRelatedApiVersionFilter();
		closedFilter.setIssueFolder(new File("./crawl_data/connectbot/closed_issues"));
		closedFilter.setBaseIssueRepo("https://github.com/connectbot/connectbot/issues");
		closedFilter.setOutputCsvFile(new File("./crawl_data/tamanh_connectbot_api.csv"));
		closedFilter.setIssueType("closed");
		closedFilter.appendToCsvFile(closedFilter.filter());

		// Conversations
		openingFilter = new IssueRelatedApiVersionFilter();
		openingFilter.setIssueFolder(new File("./crawl_data/Conversations/opening_issues"));
		openingFilter.setBaseIssueRepo("https://github.com/siacs/Conversations/issues");
		openingFilter.setOutputCsvFile(new File("./crawl_data/tamanh_Conversations_api.csv"));
		openingFilter.setIssueType("opening");
		openingFilter.appendToCsvFile(openingFilter.filter());

		closedFilter = new IssueRelatedApiVersionFilter();
		closedFilter.setIssueFolder(new File("./crawl_data/Conversations/closed_issues"));
		closedFilter.setBaseIssueRepo("https://github.com/siacs/Conversations/issues");
		closedFilter.setOutputCsvFile(new File("./crawl_data/tamanh_Conversations_api.csv"));
		closedFilter.setIssueType("closed");
		closedFilter.appendToCsvFile(closedFilter.filter());

		// FBReaderJ
		openingFilter = new IssueRelatedApiVersionFilter();
		openingFilter.setIssueFolder(new File("./crawl_data/FBReaderJ/opening_issues"));
		openingFilter.setBaseIssueRepo("https://github.com/geometer/FBReaderJ/issues");
		openingFilter.setOutputCsvFile(new File("./crawl_data/tamanh_FBReaderJ_api.csv"));
		openingFilter.setIssueType("opening");
		openingFilter.appendToCsvFile(openingFilter.filter());

		closedFilter = new IssueRelatedApiVersionFilter();
		closedFilter.setIssueFolder(new File("./crawl_data/FBReaderJ/closed_issues"));
		closedFilter.setBaseIssueRepo("https://github.com/geometer/FBReaderJ/issues");
		closedFilter.setOutputCsvFile(new File("./crawl_data/tamanh_FBReaderJ_api.csv"));
		closedFilter.setIssueType("closed");
		closedFilter.appendToCsvFile(closedFilter.filter());

		// k-9
		openingFilter = new IssueRelatedApiVersionFilter();
		openingFilter.setIssueFolder(new File("./crawl_data/k-9/opening_issues"));
		openingFilter.setBaseIssueRepo("https://github.com/k9mail/k-9/issues");
		openingFilter.setOutputCsvFile(new File("./crawl_data/tamanh_k9_api.csv"));
		openingFilter.setIssueType("opening");
		openingFilter.appendToCsvFile(openingFilter.filter());

		closedFilter = new IssueRelatedApiVersionFilter();
		closedFilter.setIssueFolder(new File("./crawl_data/k-9/closed_issues"));
		closedFilter.setBaseIssueRepo("https://github.com/k9mail/k-9/issues");
		closedFilter.setOutputCsvFile(new File("./crawl_data/tamanh_k9_api.csv"));
		closedFilter.setIssueType("closed");
		closedFilter.appendToCsvFile(closedFilter.filter());

		// MozStumbler
		openingFilter = new IssueRelatedApiVersionFilter();
		openingFilter.setIssueFolder(new File("./crawl_data/MozStumbler/opening_issues"));
		openingFilter.setBaseIssueRepo("https://github.com/mozilla/MozStumbler/issues");
		openingFilter.setOutputCsvFile(new File("./crawl_data/tamanh_MozStumbler_api.csv"));
		openingFilter.setIssueType("opening");
		openingFilter.appendToCsvFile(openingFilter.filter());

		closedFilter = new IssueRelatedApiVersionFilter();
		closedFilter.setIssueFolder(new File("./crawl_data/MozStumbler/closed_issues"));
		closedFilter.setBaseIssueRepo("https://github.com/mozilla/MozStumbler/issues");
		closedFilter.setOutputCsvFile(new File("./crawl_data/tamanh_MozStumbler_api.csv"));
		closedFilter.setIssueType("closed");
		closedFilter.appendToCsvFile(closedFilter.filter());

		// PressureNet
		openingFilter = new IssueRelatedApiVersionFilter();
		openingFilter.setIssueFolder(new File("./crawl_data/PressureNet/opening_issues"));
		openingFilter.setBaseIssueRepo("https://github.com/Cbsoftware/PressureNet/issues");
		openingFilter.setOutputCsvFile(new File("./crawl_data/tamanh_PressureNet_api.csv"));
		openingFilter.setIssueType("opening");
		openingFilter.appendToCsvFile(openingFilter.filter());

		closedFilter = new IssueRelatedApiVersionFilter();
		closedFilter.setIssueFolder(new File("./crawl_data/PressureNet/closed_issues"));
		closedFilter.setBaseIssueRepo("https://github.com/Cbsoftware/PressureNet/issues");
		closedFilter.setOutputCsvFile(new File("./crawl_data/tamanh_PressureNet_api.csv"));
		closedFilter.setIssueType("closed");
		closedFilter.appendToCsvFile(closedFilter.filter());

		// Signal-Android
		openingFilter = new IssueRelatedApiVersionFilter();
		openingFilter.setIssueFolder(new File("./crawl_data/Signal-Android/opening_issues"));
		openingFilter.setBaseIssueRepo("https://github.com/signalapp/Signal-Android/issues");
		openingFilter.setOutputCsvFile(new File("./crawl_data/tamanh_Signal-Android_api.csv"));
		openingFilter.setIssueType("opening");
		openingFilter.appendToCsvFile(openingFilter.filter());

		closedFilter = new IssueRelatedApiVersionFilter();
		closedFilter.setIssueFolder(new File("./crawl_data/Signal-Android/closed_issues"));
		closedFilter.setBaseIssueRepo("https://github.com/signalapp/Signal-Android/issues");
		closedFilter.setOutputCsvFile(new File("./crawl_data/tamanh_Signal-Android_api.csv"));
		closedFilter.setIssueType("closed");
		closedFilter.appendToCsvFile(closedFilter.filter());

		// WordPress-Android
		openingFilter = new IssueRelatedApiVersionFilter();
		openingFilter.setIssueFolder(new File("./crawl_data/WordPress-Android/opening_issues"));
		openingFilter.setBaseIssueRepo("https://github.com/wordpress-mobile/WordPress-Android/issues");
		openingFilter.setOutputCsvFile(new File("./crawl_data/tamanh_WordPress-Android_api.csv"));
		openingFilter.setIssueType("opening");
		openingFilter.appendToCsvFile(openingFilter.filter());

		closedFilter = new IssueRelatedApiVersionFilter();
		closedFilter.setIssueFolder(new File("./crawl_data/WordPress-Android/closed_issues"));
		closedFilter.setBaseIssueRepo("https://github.com/wordpress-mobile/WordPress-Android/issues");
		closedFilter.setOutputCsvFile(new File("./crawl_data/tamanh_WordPress-Android_api.csv"));
		closedFilter.setIssueType("closed");
		closedFilter.appendToCsvFile(closedFilter.filter());
	}
}
