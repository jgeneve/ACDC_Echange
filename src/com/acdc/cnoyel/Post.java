package com.acdc.cnoyel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class Post {
	private String title;
	private String text;
	private String category;
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
	
	public String generateMarkdown() {
		StringBuffer txt = new StringBuffer("");
		String date = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		
		// ''HEADER''
		txt.append("---"
				+ "layout: post" + "\n"
				+ "title: " + this.title + "\n"
				+ "date: " + date + "\n"
				+ "categories: " + this.category + "\n"
				+ "---" + "\n\n");
		
		// ''BODY''
		txt.append(this.text);
		
		return txt.toString();
	}
	
	
}
