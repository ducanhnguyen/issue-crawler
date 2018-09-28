package com.fit;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Utils {
	public static List<String> readFileContent(File file) {
		List<String> content = new ArrayList<String>();
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			String str;
			while ((str = in.readLine()) != null)
				content.add(str);
			in.close();
		} catch (IOException e) {
		}
		return content;
	}

	public static boolean exportToFile(File file, String content) {
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(file));
			writer.write(content);
			writer.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Export a set of URL to file
	 * 
	 * @param file
	 * @param urls
	 */
	public static void exportUrlsToFile(File file, Set<URL> urls) {
		String pages = new String();

		if (urls != null)
			for (URL url : urls)
				pages += url + "\n";

		Utils.exportToFile(file, pages);
	}
}
