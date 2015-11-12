/**
 * Project Name:docker-usermodel
 * File Name:UserServiceImpl.java
 * Package Name:com.asinainfo.user.service.impl
 *
*/

package com.asianinfo.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asianinfo.user.dao.IUserDao;
import com.asianinfo.user.model.UserInfo;
import com.asianinfo.user.service.IUserService;

/**
 * ClassName:UserServiceImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * @author   weijin
 * @version  
 * 
 * @see 	 
 */
@Service("userService")
public class UserServiceImpl implements IUserService {
	@Resource
	public IUserDao userDao;

	public List<UserInfo> getAllUsers() {

		return userDao.getAllUsers();
	}

	public UserInfo getUserByUseName(String userName) {

		UserInfo user = userDao.getUserByUseName(userName);
		return user;
		
	}

	public int addUser(UserInfo userinfo) {
		int result = userDao.addUser(userinfo);
		if(result>0){
			return result;
		}else{
			return -1;
		}
	}

	public int getUserAuth(String loginStr, String passwd) {
		UserInfo user = new UserInfo();
		user.setUserName(loginStr);
		user.setPasswd(passwd);
		int result = userDao.auth(user);
		return result;
	}

	public void delUser(int id) {

		userDao.delUser(id);

	}

	@Override
	public int updateUser(UserInfo user) {
		
		int result = userDao.updateUser(user);
		return result;
	}

}

