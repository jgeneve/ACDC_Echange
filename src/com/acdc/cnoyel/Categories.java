package com.acdc.cnoyel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is used to interact with elements concerning post's categories
 * (categories are stored in a text file, categories in the textfile are always lowercase)
 * 
 * @author Cedric NOYEL
 *
 */
public class Categories {

	public static String categoriesFilePath = "." + File.separator + "categories.txt";
	public static File categoriesFile = new File(categoriesFilePath);
	
	/**
	 * add a category into 'category/categories.txt' file
	 * if the category already exist, it does not add it
	 * @param categoryName - String name of the category to add
	 * @throws IOException
	 */
	public static void addCategory(String categoryName) {
		// remove all categories with this name and add one 
		// -> so we are sure that there is no duplicated category
		removeCategory(categoryName); 
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(categoriesFilePath, true));
			writer.append(categoryName); 
			writer.newLine();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String selectCategory() {
		// TODO
		return null;
	}

	/** Method used to remove a category of the categories text file
	 * @param
	 * 		categoryStr - String of the category to remove
	 * @throws IOException 
	 */
	public static void removeCategory(String categoryStr) {
		String categoryToRemove = categoryStr.trim().toLowerCase().replace(" ", "-");
		try {
			List<String> out = Files.lines(categoriesFile.toPath())
			                    .filter(line -> !line.contains(categoryToRemove))
			                    .collect(Collectors.toList());
		    Files.write(categoriesFile.toPath(), out, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/** Method returning a list of categories in the categories.txt file
	 * @return
	 * 		categoryList - List<String> containing each category from categories.txt file
	 */
	public static List<String> getCategories() {
		List<String> categoryList = new ArrayList<>();
		try {
			if (!categoriesFile.exists())	{ 
				categoriesFile.createNewFile();

				BufferedReader reader;
				reader = new BufferedReader(new FileReader(categoriesFile));
				
				// For each line (one category by line), we add one string to the categoryList
				String line;
				while(( line = reader.readLine()) != null) {
					categoryList.add(line);
				}
				
				reader.close();
			}			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return categoryList;
	}
	
}
