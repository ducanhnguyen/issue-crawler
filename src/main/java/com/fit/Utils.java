package com.fit;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Utils {
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
}
