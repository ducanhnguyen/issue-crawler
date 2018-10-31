package com.fit.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Utils {
	public static void appendToFile(File file, String content) {
		BufferedWriter bw = null;
		FileWriter fw = null;

		if (!file.exists())
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}

		try {
			fw = new FileWriter(file, true);
			bw = new BufferedWriter(fw);
			bw.write(content);
		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

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

	public static File normalizePath(File path) {
		String normalizedPath = new String();
		try {
			normalizedPath = path.getCanonicalPath().replace("\\", File.separator).replace("//", File.separator)
					.replace("/", File.separator);
		} catch (IOException e) {
			e.printStackTrace();
		}
		File normalizedFile = new File(normalizedPath);

		return normalizedFile;
	}

	public static String convertToString(List<String> lines) {
		String str = new String();
		for (String line : lines) {
			str += line + "\n";
		}
		return str;
	}

	public static Set<File> getFilesInAFolder(File folder) {
		Set<File> files = new HashSet<File>();

		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				files.add(listOfFiles[i]);
			} else if (listOfFiles[i].isDirectory()) {
				// ignore
			}
		}
		return files;
	}

}
