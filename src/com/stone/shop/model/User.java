package com.stone.shop.model;

import cn.bmob.im.bean.BmobChatUser;
import cn.bmob.v3.datatype.BmobGeoPoint;


/**
 * @author kingofglory email: kingofglory@yeah.net blog: http:www.google.com
 * @date 2014-3-14 TODO
 */

public class User extends BmobChatUser{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String TAG = "User";
	/**
	 * //显示数据拼音的首字母
	 */
	private String sortLetters;
	/**
	 * //性别-true-男
	 */
	private boolean sex;
	/**
	 * 地理坐标
	 */
	private BmobGeoPoint location;//
	

	private String qq; 			// QQ
	private String school = "天津理工大学";  // 学校
	private String cademy = "华信软件学院"; 		// 学院
	private String dorPart; 	// 宿舍楼
	private String dorNum; 		// 寝室号
	
	private boolean ImageA = false,ImageB = false;

	public BmobGeoPoint getLocation() {
		return location;
	}
	public void setLocation(BmobGeoPoint location) {
		this.location = location;
	}
	public boolean getSex() {
		return sex;
	}
	public void setSex(boolean sex) {
		this.sex = sex;
	}
	public String getSortLetters() {
		return sortLetters;
	}
	public void setSortLetters(String sortLetters) {
		this.sortLetters = sortLetters;
	}
	public String getQQ() {
		return qq;
	}

	public void setQQ(String qq) {
		this.qq = qq;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getCademy() {
		return cademy;
	}

	public void setCademy(String cademy) {
		this.cademy = cademy;
	}

	public String getDorPart() {
		return dorPart;
	}

	public void setDorPart(String dorPart) {
		this.dorPart = dorPart;
	}

	public String getDorNum() {
		return dorNum;
	}

	public void setDorNum(String dorNum) {
		this.dorNum = dorNum;
	}
	public boolean getImageA() {
		return ImageA;
	}
	public void setImageA(boolean imageA) {
		ImageA = imageA;
	}
	public boolean getImageB() {
		return ImageB;
	}
	public void setImageB(boolean imageB) {
		ImageB = imageB;
	}
}
