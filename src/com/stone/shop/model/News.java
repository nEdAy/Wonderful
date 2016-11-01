package com.stone.shop.model;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * 首页校内新闻实体类
 * 
 * @date 2014-5-3
 * @author Stone
 */
public class News extends BmobObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String type; // 新闻类型
	private String title; // 新闻标题
	private String author; // 新闻作者
	private String content; // 新闻内容
	private BmobFile picNews = null;  //新闻图片

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public BmobFile getPicNews() {
		return picNews;
	}

	public void setPicNews(BmobFile picNews) {
		this.picNews = picNews;
	}

}
