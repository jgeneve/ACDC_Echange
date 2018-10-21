package com.acdc.cnoyel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class Post {
	private String title;
	private String text;
	private String category;
	private String author;
	private List<String> linkList;
	private List<String> imgList;
	
	public Post() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}	
	
	public List<String> getLinkList() {
		return linkList;
	}

	public void setLinkList(List<String> linkList) {
		this.linkList = linkList;
	}

	public List<String> getImgList() {
		return imgList;
	}

	public void setImgList(List<String> imgList) {
		this.imgList = imgList;
	}
	
	
	/**
	 * @return a string in markdown format
	 */
	public String generateMarkdownString() {
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
		
		return txt.toString();
	}

	
}
