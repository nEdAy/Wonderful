package com.stone.shop.model;

import cn.bmob.v3.BmobObject;

/**
 * 商品实体类
 * @date 2014-4-24
 * @author Stone
 */
public class Good extends BmobObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String shopID = ""; 		// 商店ID
	
	private String type = ""; 		// 类型
	private String name = ""; 		// 名称
	private String price = ""; 		// 价格
	
	public Good(String name, String price) {
		this.name = name;
		this.price  = price;
	}
	
	public String getShopID() {
		return shopID;
	}

	public void setShopID(String shopID) {
		this.shopID = shopID;
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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}
