/**
 * 
 */
package com.jeetemplates.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

/**
 * File Utils.
 * 
 * @author jeetemplates
 */
public class FileUtils {

	public final static String FILE_NAME = "C:\\Temp\\input.txt";
	public final static String OUTPUT_FILE_NAME = "C:\\Temp\\output.txt";
	public final static Charset ENCODING = StandardCharsets.UTF_8;

	/**
	 * Read small files. Read line by line.
	 * 
	 * @param fileName
	 *            : file name
	 * @return list of all lines in the file
	 * @throws IOException
	 */
	public List<String> readSmallTextFile(String fileName) throws IOException {
		Path path = Paths.get(fileName);
		return Files.readAllLines(path, ENCODING);
	}

	/**
	 * Write small files. Write a list of lines.
	 * 
	 * @param lines
	 *            : list of lines
	 * @param fileName
	 *            : file name
	 * @throws IOException
	 */
	public void writeSmallTextFile(List<String> lines, String fileName) throws IOException {
		Path path = Paths.get(fileName);
		Files.write(path, lines, ENCODING);
	}

	/**
	 * Read large file.
	 * 
	 * @param fileName
	 *            : file name
	 * @throws IOException
	 */
	public void readLargerTextFile(String fileName) throws IOException {
		Path path = Paths.get(fileName);
		try (Scanner scanner = new Scanner(path, ENCODING.name())) {
			while (scanner.hasNextLine()) {
				// process each line in some way
				log(scanner.nextLine());
			}
		}
	}

	/**
	 * Read large file.
	 * 
	 * @param fileName
	 *            : file name
	 * @throws IOException
	 */
	public void readLargerTextFileAlternate(String fileName) throws IOException {
		Path path = Paths.get(fileName);
		try (BufferedReader reader = Files.newBufferedReader(path, ENCODING)) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				// process each line in some way
				log(line);
			}
		}
	}

	/**
	 * Write large file.
	 * 
	 * @param fileName
	 *            : file name
	 * @param lines
	 *            : list of lines
	 * @throws IOException
	 */
	public void writeLargerTextFile(String fileName, List<String> lines) throws IOException {
		Path path = Paths.get(fileName);
		try (BufferedWriter writer = Files.newBufferedWriter(path, ENCODING)) {
			for (String line : lines) {
				writer.write(line);
				writer.newLine();
			}
		}
	}

	private static void log(Object aMsg) {
		System.out.println(String.valueOf(aMsg));
	}

}
