package com.asianinfo.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.asianinfo.user.model.UserInfo;

/**
 * 
    * @ClassName: UserDao
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author weijin
    *
 */
public interface IUserDao {

	/**
	 * 
	    * @Title: getAllUsers
	    * @Description: 查询所有用户信息，忽略分页
	    * @param @return    参数
	    * @return List<UserInfo>    返回类型
	    * @throws
	 */
	public List<UserInfo> getAllUsers();
	/**
	 * 
	    * @Title: getUserByUseName
	    * @Description: TODO(这里用一句话描述这个方法的作用)
	    * @param @param userName
	    * @param @return    参数
	    * @return UserInfo    返回类型
	    * @throws
	 */
	public UserInfo getUserByUseName(String userName);
	/**
	 * 
	    * @Title: addUser
	    * @Description: TODO(这里用一句话描述这个方法的作用)
	    * @param @param userinfo
	    * @param @return    参数
	    * @return int    返回类型
	    * @throws
	 */
	public int addUser(UserInfo userinfo);
	/**
	 * 
	    * @Title: delUser
	    * @Description: TODO(这里用一句话描述这个方法的作用)
	    * @param @param id
	    * @param @return    参数
	    * @return int    返回类型
	    * @throws
	 */
	public void delUser(int id);
	
	/**
	 * 
	    * @Title: updateUser
	    * @Description: TODO(这里用一句话描述这个方法的作用)
	    * @param @param user
	    * @param @return    参数
	    * @return int    返回类型
	    * @throws
	 */
	public int updateUser(UserInfo user);
	/**
	 * 
	    * @Title: OAuth
	    * @Description: 用户验证
	    * @param @param userName
	    * @param @param password
	    * @param @return    参数
	    * @return int    返回类型
	    * @throws
	 */
	public int auth(UserInfo user);
}

