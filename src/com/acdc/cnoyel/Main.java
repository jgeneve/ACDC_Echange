package com.acdc.cnoyel;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) {

		// CONFIG REPERTOIRE LOCAL ET DISTANT
		Git git = new Git();
		git.setLocalRepo(System.getProperty("user.home") + File.separator + "eclipse-workspace" + File.separator 
				+ "ACDC" + File.separator + "web-master" + File.separator + "BLOG" + File.separator);
		git.setGithubRepo("https://github.com/CedricNoyel/BLOGACDC_website.git");
		
		// USER ACTIONS
		System.out.println("Titre de la publication");
		String title = Tools.getStringUserInput();
		
		System.out.println("Catégorie de la publication");
		String category = Tools.getStringUserInput();

		System.out.println("Auteur de la publication");
		String author = Tools.getStringUserInput();

		System.out.println("Texte de la publication");
		String bodyText = Tools.getStringUserInput();
		
		System.out.println("Liens de la publication (séparés par un espace si plusieurs ou laisser vide si aucun)");
		List<String> linkList = Tools.stringToList(Tools.getStringUserInput(), " ");
		
		System.out.println("Liens des images (séparés par un espace si plusieurs images ou laisser vide si aucune)");
		List<String> imgLinkList = Tools.stringToList(Tools.getStringUserInput(), " ");
		
		// CREATE THE POST
		Post newPost = new Post(); // CREATE THE NEW POST
		newPost.setTitle(title);
		newPost.setCategory(category.trim().toLowerCase().replace(" ", "-"));
		newPost.setAuthor(author);
		newPost.setText(bodyText);
		newPost.setLinkList(linkList);
		newPost.setImgList(imgLinkList);

		// CATEGORY
		Categories.addCategory(category.trim().toLowerCase().replace(" ", "-")); // --> if not exist, add it into category/categories.txt file
		String markdownString = newPost.toMarkdown();
				
		// CREATE MARKDOWN FILE
		System.out.println(markdownString);
		String markdownFileName = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()) + "-" + newPost.getTitle().replace(" ", "-") + ".markdown";
		String markdownFilePath = git.getLocalRepo() + "_posts" + File.separator + markdownFileName;
        Tools.createMarkdownFile(markdownString, markdownFilePath);

		
		// DISPLAY DEMO
		Tools.executeCmd("bundle exec jekyll serve -o", git.getLocalRepo(), true);
		System.out.println("Appuyez sur <entrer> pour valider et publier");
		Tools.getStringUserInput();
		
		// GIT & GITHUB
		Tools.executeCmd("git add .", git.getLocalRepo());
		Tools.executeCmd("git commit -m \"add " + markdownFileName + "\" .", git.getLocalRepo());
		Tools.executeCmd("git push", git.getLocalRepo());
		
		System.out.println("- End -");
	}
	
}
