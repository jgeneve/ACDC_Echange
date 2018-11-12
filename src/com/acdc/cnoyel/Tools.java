package com.acdc.cnoyel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Tools {

	/**
	 * Method that wait user to enter text
	 * 
	 * @return userInput - String entered by the user
	 * @throws IOException on invalid user input
	 */
	public static String getStringUserInput() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String userInput = null;
		try {
			userInput = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return userInput;
	}

	/**
	 * Method that returns a list of elements separated by a separator in a String
	 * 
	 * @param str - String with elements separated with a separator
	 * @param separator - String to use to split elements
	 * @return list - List<String> elements from the input string split by separator
	 */
	public static List<String> stringToList(String str, String separator) {
		List<String> list = new ArrayList<>();
		if (!str.isEmpty()) {
			String[] arrayLink = str.split(separator);
			list = new ArrayList<String>();
			for(int i=0; i<arrayLink.length; i++) {
				list.add(arrayLink[i]);
			}
		}
		return list;
	}

	/** Create a file into the '_post' directory
	 * @param markdownString - String containing all the markdown to insert in the file
	 * @param filePath - String of the filepath to create
	 * @return file - File containing the generated markdown
	 * @throws IOException
	 */
	public static File createMarkdownFile(String markdownString, String filePath) {
		// new File("_post").mkdir(); // If not exist, create folder 
		File file = new File(filePath);
		BufferedWriter output;
		try {
			output = Files.newBufferedWriter(file.toPath(), StandardCharsets.UTF_8);
			output.write(markdownString);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}

	/**
	 * Method to execute a given command on a given path
	 * @param cmd - String of the command to execute
	 * @param path - String of the path where to execute the command
	 * @param waitUserAction - Boolean if true wait the user to press enter, if false don't wait
	 */
	public static void executeCmd(String cmd, String path, boolean waitUserAction) {
		System.out.println("COMMAND RUN: " + cmd + "\r	in " + path);
		ProcessBuilder builder = new ProcessBuilder();
		builder.directory(new File(path));
		if(System.getProperty("os.name").toLowerCase().startsWith("windows"))
			builder.command("cmd.exe", "/c", cmd); // IF windows os
		else builder.command("sh", "-c", cmd); // ELSE unix
		try {
			Process process = builder.start();
			StreamGobbler streamGobbler = new StreamGobbler(process.getInputStream(), System.out::println);		
			Executors.newSingleThreadExecutor().submit(streamGobbler);
			if (waitUserAction) {
				TimeUnit.SECONDS.sleep(3);
				System.out.println("Press <Enter> to end demo");
				System.in.read();
			} else {
				int exitCode = process.waitFor();
				assert exitCode == 0;
			}
			process.destroy();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to execute in a new thread a given command on a given path
	 * @param cmd - String of the command to execute
	 * @param path - String of the path where to execute the command
	 */
	public static void executeCmd(String cmd, String path) {
		executeCmd(cmd, path, false);
	}

	/** Class used to run command in terminal **/
	private static class StreamGobbler implements Runnable {
		private java.io.InputStream inputStream;
		private java.util.function.Consumer<String> consumer;

		public StreamGobbler(java.io.InputStream inputStream, java.util.function.Consumer<String> consumer) {
			this.inputStream = inputStream;
			this.consumer = consumer;
		}

		@Override
		public void run() {
			new java.io.BufferedReader(new java.io.InputStreamReader(inputStream)).lines()
			.forEach(consumer);
		}
	}
}

