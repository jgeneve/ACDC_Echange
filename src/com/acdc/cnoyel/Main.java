package com.acdc.cnoyel;

import java.io.File;
import java.util.List;


public class Main {

	public static void main(String[] args) {
		
		Post newPost = askPostData();

		String githubDirectory = "https://github.com/CedricNoyel/BLOGACDC_website.git";
		String gitDirectory = "C:/Users/cedri/Desktop/BLOGACDC_website";
		String markdownFilePath = gitDirectory + File.separator + "_posts" + File.separator + newPost.getMarkdownFileName();
		
		Categories.categoriesFile = new File(gitDirectory + File.separator + "category" + File.separator + "category.txt");
		
		Categories.addCategory(newPost.getCategory());
		
        Tools.createMarkdownFile(newPost.toMarkdown(), markdownFilePath);
		Tools.executeCmd("bundle exec jekyll serve -o", gitDirectory, true);
		gitCommands(githubDirectory, gitDirectory);
		
		System.out.println("- Commandes git effectuées\r - End -");
	}

	/**
	 * Method that runs git add, commit and push
	 * @param githubDirectory - String of the distant git repository
	 * @param gitDirectory- String of the local git repository
	 */
	private static void gitCommands(String githubDirectory, String gitDirectory) {
		Tools.executeCmd("git add .", gitDirectory);
		Tools.executeCmd("git commit -m \"Add markdown file\"", gitDirectory);
		Tools.executeCmd("git push", gitDirectory);
		
		// [WORKAROUND] essayé de le faire avec la library git mais il faut qu'on ait un projet maven :/
//		try {
//			Repository localRepo = new FileRepository(gitDirectory);
//	    	Git git = new Git(localRepo); 
//			git.add().addFilepattern(".").call();
//	        git.push().setRemote(githubDirectory).call();
//		} catch (IOException e2) {
//			e2.printStackTrace();
//		} catch (NoFilepatternException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (GitAPIException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Can also be done with given username and password
//		PushCommand pushCommand = git.push();
//	    pushCommand.setCredentialsProvider(new UsernamePasswordCredentialsProvider("username", "password"));
//	    pushCommand.call();
	}

	private static Post askPostData() {
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
		
		return new Post(title, category, author, bodyText, linkList, imgLinkList);
	}
	
}
