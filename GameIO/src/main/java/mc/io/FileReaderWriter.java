package mc.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileReaderWriter {

	public static String readFile(String path) {
		checkPathEmpty(path);
		Path p = checkPathValid(path);
		try {
			return String.join("\n", Files.readAllLines(p));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static void checkPathEmpty(String path) {
		if (path.trim().isEmpty()) {
			throw new IllegalArgumentException("can not read/write a file when the path is an empty string!");
		}
	}

	private static Path checkPathValid(String path) {
		Path p = Paths.get(path);
		if (Files.exists(p) == false) {
			throw new InvalidPathException(path, "the given path does not exist in the file system");
		}
		return p;
	}

}
