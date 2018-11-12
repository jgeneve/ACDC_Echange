package com.acdc.cnoyel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * The class Post is used interact with a post(or news) that you want to create
 * @author Cedric NOYEL
 */
public class Post {
	private String title;
	private String text;
	private String category;
	private String author;
	private List<String> linkList = new ArrayList<>();
	private List<String> imgList = new ArrayList<>();

	public Post() {
		
	}

	/** Getter returning the title of a post
	 * @return
	 * 		title - a String containing the title of the current post
	 */
	public String getTitle() {
		return title;
	}

	/** Setter defining the title of a post	
	 * @param
	 * 		title - String corresponding to the title of the current post
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/** Getter returning the body text of a post
	 * @return
	 * 		text - a String containing the body of the current post
	 */
	public String getText() {
		return text;
	}

	/** Setter defining the body text of a post
	 * @param
	 * 		text - String corresponding to the body text of the current post
	 */
	public void setText(String text) {
		this.text = text;
	}

	/** Getter returning the category of a post
	 * @return
	 * 		category - a String containing the category of the current post
	 */
	public String getCategory() {
		return category;
	}

	/** Setter defining the category of a post 
	 * @param
	 * 		category - String corresponding to the category of the current post
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/** Getter returning the author of a post
	 * @return
	 * 		author - a String containing the author of the current post
	 */
	public String getAuthor() {
		return author;
	}

	/** Setter defining the author of a post
	 * @param
	 * 		author - String corresponding to the author of the current post
	 */
	public void setAuthor(String author) {
		this.author = author;
	}	
	
	/** Getter returning the list of links in the current post
	 * @return
	 * 		linkList - a List<String> containing all the links of the current post
	 */
	public List<String> getLinkList() {
		return linkList;
	}

	/** Setter defining the list of links in the current post
	 * @param
	 * 		linkList - List<String> corresponding to every link of the current post
	 */
	public void setLinkList(List<String> linkList) {
		this.linkList = linkList;
	}

	/** Getter returning the list of imgage links in the current post
	 * @return
	 * 		imgList - a List<String> containing all the images links of the current post
	 */
	public List<String> getImgList() {
		return imgList;
	}

	/** Setter defining the list of image links in the current post
	 * @param
	 * 		imgList - List<String> corresponding to every imagelink of the current post
	 */
	public void setImgList(List<String> imgList) {
		this.imgList = imgList;
	}

	/** Method returning a markdown String corresponding to the current post
	 * @return
	 * 		postMarkdown - aString containing the formatted markdown of the current post
	 */
	public String toMarkdown() {
		StringBuffer txt = new StringBuffer("");
		String date = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		
		// ''HEADER''
		txt.append("---\n"
				+ "layout: post" + "\n"
				+ "title: \"" + this.title + "\"\n"
				+ "date: " + date + "\n"
				+ "categories: " + this.category + "\n"
				+ "---" + "\n\n");
		// ''AUTHOR''
		txt.append("*By " + this.author + "*" + "\n\n");

		// ''BODY''
		txt.append(this.text);

		// ''IMAGES''
		if (!imgList.isEmpty()) {
			txt.append("<br /><br />#Images:");
			for (int i=0; i<this.imgList.size(); i++) {
				txt.append("<br />![" + " Image " + i + "](" + this.imgList.get(i) + " \"Image" + i + "\")");
			}
		}
		
		// ''LINKS''
		if (!linkList.isEmpty()) {
			txt.append("<br /><br />Links:");
			for (int i=0; i<this.linkList.size(); i++) {
				txt.append("<br />[" + this.linkList.get(i) + "](" + this.linkList.get(i) + ")");
			}
		}
		
		String postMarkdown = txt.toString();
		return postMarkdown;
	}

	
}
