/**
 * Project Name:docker-usermodel
 * File Name:IUserService.java
 * Package Name:com.asinainfo.user.service
 *
*/

package com.asianinfo.user.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.asianinfo.user.model.UserInfo;

/**
 * ClassName:IUserService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * @author   weijin
 * @version  
 * 
 * @see 	 
 */
@Component
public interface IUserService {


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
	    * @Title: getUserAuth
	    * @Description: TODO(这里用一句话描述这个方法的作用)
	    * @param @param loginStr
	    * @param @param password
	    * @param @return    参数
	    * @return UserInfo    返回类型
	    * @throws
	 */
	public int getUserAuth(String loginStr,String password);
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

	public int updateUser(UserInfo user);
}

