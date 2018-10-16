package com.acdc.cnoyel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {

		boolean done = false; // used to continue or stop a while loop
		String userInput; // Used to store temporally the user input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] optionArray = { "Créer une news", "Quitter le programme" };
		System.out.println("Bienvenue sur le programme du site web STACK de l'IMT Atlantique. \nVous souhaitez:");
		displayOptionArray(optionArray);
		
        System.out.print("Choix n°:	");
        userInput = br.readLine();
		
		while (!done) {
			if (userInput.equals("1")) {
				done = true;
			} else if (userInput.equals("2")) {
		        System.out.print("Exit program ...");
				System.exit(0); // END PROGRAM
			} else {
				System.out.println("Entrée non reconnue, merci de réessayer: ");
				displayOptionArray(optionArray);
				userInput = br.readLine(); // Ask user
			}
		}
		
		Post newPost = new Post(); // CREATE THE NEW POST
		
		// ASK FOR POST TITLE
		System.out.println("Entrer le titre du post: ");
		String title = br.readLine();
		newPost.setTitle(title);
		
		// ASK FOR CATEGORY
		System.out.println("Entrer la categorie du post: ");
		String category = br.readLine();
		newPost.setCategory(category);

		// ASK FOR POST BODY TEXT
		System.out.println("Entrer le texte du post: ");
		String bodyText = br.readLine();
		newPost.setText(bodyText);
		
		// ASK FOR POST LINKS
		String[] optionLinkArray = { "Oui", "Non, continuer", "Quitter" };
		System.out.println("Voulez-vous ajouter un ou plusieurs liens au post: ");
		displayOptionArray(optionLinkArray);
		userInput = br.readLine(); // Ask user

		done = false;
		while (!done) {
			if (userInput.equals("1")) {

				System.out.println("Entrer le ou les lien(s) séparés par un espace: ");
				userInput = br.readLine(); // Ask user
				
				String[] arrayLink = userInput.split(" ");
				List<String> linkList = new ArrayList<String>();
				for(int i=0; i<arrayLink.length-1; i++) {
					linkList.add(arrayLink[i]);
				}
				newPost.setLinkList(linkList);
				
				done = true;
			} else if (userInput.equals("2")) {
				// NOTHING, Just continue
				done = true;
			} else if (userInput.equals("3")) {
		        System.out.print("Exit program ...");
				System.exit(0); // END PROGRAM
			} else {
				System.out.println("Entrée non reconnue, merci de réessayer: ");
				displayOptionArray(optionArray);
				userInput = br.readLine(); // Ask user
			}
		}

		// ASK FOR POST IMAGES
		System.out.println("Voulez-vous ajouter une ou plusieurs images au post: ");
		displayOptionArray(optionLinkArray);
		userInput = br.readLine(); // Ask user

		done = false;
		while (!done) {
			if (userInput.equals("1")) {
				System.out.println("Entrer le ou les lien(s) des images séparés par un espace: ");
				userInput = br.readLine(); // Ask user

				List<String> imgList = new ArrayList<String>();;
				String[] arrayLink = userInput.split(" ");
				for(int i=0; i<arrayLink.length-1; i++) {
					imgList.add(arrayLink[i]);
				}
				newPost.setImgList(imgList);
				
				done = true;
			} else if (userInput.equals("2")) {
				// NOTHING, Just continue
				done = true;
			} else if (userInput.equals("3")) {
		        System.out.print("Exit program ...");
				System.exit(0); // END PROGRAM
			} else {
				System.out.println("Entrée non reconnue, merci de réessayer: ");
				displayOptionArray(optionArray);
				userInput = br.readLine(); // Ask user
			}
		}
		
		String markdown = newPost.generateMarkdown();
		System.out.println(markdown);

	}
	
	// Method taking a string array as input and displaying each elem with his index
	public static void displayOptionArray(String[] myArray) {
		for (int i=0; i<myArray.length ;i++) {
			System.out.println((i+1) + " - " + myArray[i]);
		}
	}
	

}
