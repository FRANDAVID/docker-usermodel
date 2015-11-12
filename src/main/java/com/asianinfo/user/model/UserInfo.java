package com.asianinfo.user.model;

import java.io.Serializable;

/**
 * 
 * @ClassName: UserInfo
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author weijin
 *
 */
public class UserInfo implements Serializable {
	private static final long serialVersionUID = -8392762359358358375L;
	private String userName; // 登录名称
	private String passwd; // 登录密码
	private String nickName;// 用户昵称
	private Integer userType;// 用户类型
	private String comments;//备注
	private Integer id; // 主键id
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	
	
}
