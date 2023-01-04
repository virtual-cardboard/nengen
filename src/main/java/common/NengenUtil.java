package common;

import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;

import java.io.File;

public class NengenUtil {

	public static String readFileAsString(String path) {
		try {
			return new String(readAllBytes(get(path)));
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String readFileAsString(File file) {
		try {
			return new String(readAllBytes(get(file.getAbsolutePath())));
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
