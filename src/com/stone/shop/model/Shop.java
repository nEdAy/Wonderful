package com.stone.shop.model;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * 店铺实体类， 实现序列化, Activity之间实现传递
 * @date 2014-4-24
 * @author Stone
 */
public class Shop extends BmobObject implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userID; 		// 主人
	private String type; 		// 类型
	private String name; 		// 店名
	private String location; 	// 地理位置
	private String phone;		// 联系电话
	private String info; 		// 简介
	private String sale; 		// 促销信息

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getSale() {
		return sale;
	}

	public void setSale(String sale) {
		this.sale = sale;
	}

}
