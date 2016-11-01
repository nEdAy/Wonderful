package com.stone.shop.model;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobGeoPoint;

public class Attendance extends BmobObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private int scnumber = 00000000; // 学号
	private BmobGeoPoint location;//地理坐标
	private String Type;//签到类型
	public int getScnumber() {
		return scnumber;
	}
	public void setScnumber(int scnumber) {
		this.scnumber = scnumber;
	}
	public BmobGeoPoint getLocation() {
		return location;
	}
	public void setLocation(BmobGeoPoint location) {
		this.location = location;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

}
