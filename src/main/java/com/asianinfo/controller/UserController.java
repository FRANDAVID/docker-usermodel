/**
 * Project Name:docker-usermodel
 * File Name:UserController.java
 * Package Name:com.asianinfo.controller
 *
*/

package com.asianinfo.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.asianinfo.config.Config;
import com.asianinfo.config.MD5;
import com.asianinfo.user.model.UserInfo;
import com.asianinfo.user.service.IUserService;

import sun.misc.BASE64Decoder;

/**
 * ClassName:UserController <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * 
 * @author weijin
 * @version
 * 
 * @see
 */
@Controller
@RequestMapping("/users")
public class UserController {
	@Autowired
	public IUserService userService;
	private static final Logger logger = Logger.getLogger(UserController.class);

	/**
	 * 
	 * @Title: createUser
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param
	 *            user
	 * @param @param
	 *            ucBuilder
	 * @param @param
	 *            response
	 * @param @return
	 *            参数
	 * @return JSONObject 返回类型
	 * @throws {"userName":"a","passwd":"111111"}
	 */
	@RequestMapping(value = "/user", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody JSONObject createUser(@RequestBody UserInfo user, HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("注册用户名用户名 " + user.getUserName());
		JSONObject result = new JSONObject();
		try {
			if (!checkHeaderAuth(request, response)) {
				result.put(Config.codeFlag, Config.errorCode);
				result.put(Config.messageFlag, "无权进行操作,请登录后操作");
				// return result;
			}
			if (null != userService.getUserByUseName(user.getUserName())) {
				logger.info("用户 " + user.getUserName() + " 已经存在");
				result.put(Config.codeFlag, Config.errorCode);
				result.put(Config.messageFlag, "用户已经存在");
				return result;
			}
		} catch (IOException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		// md5(passwd+fix)作为数据库存储的密码
		String passwd = MD5.GetMD5Code(user.getPasswd());
		user.setPasswd(passwd);
		userService.addUser(user);
		result.put(Config.codeFlag, Config.successCode);
		result.put(Config.messageFlag, Config.successMes);
		return result;
	}

	// 修改用户信息
	// {"id":"7","userName":"asianinfo","passwd":"e3ceb5881a0a1fdaad01296d7554868d","nickName":"管理员","userType":3,"comments":"修改用户"}
	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public @ResponseBody JSONObject updateUser(@RequestBody UserInfo user, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject result = new JSONObject();
		try {
			if (!checkHeaderAuth(request, response)) {
				result.put(Config.codeFlag, Config.errorCode);
				result.put(Config.messageFlag, "无权进行操作,请登录后操作");
				return result;
			}
		} catch (IOException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		// md5(passwd+fix)作为数据库存储的密码
		userService.updateUser(user);
		result.put(Config.codeFlag, Config.successCode);
		result.put(Config.messageFlag, Config.successMes);
		return result;
	}

	/**
	 * 
	 * @throws IOException
	 * @Title: showUser @Description: 查询用户信息 @param @param
	 *         userName @param @param request @param @return 参数 @return
	 *         JSONObject 返回类型 @throws
	 */
	@RequestMapping(value = "/{userName}", method = RequestMethod.GET)
	public @ResponseBody JSONObject showUser(@PathVariable String userName, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		JSONObject result = new JSONObject();
		if (!checkHeaderAuth(request, response)) {
			result.put(Config.codeFlag, Config.errorCode);
			result.put(Config.messageFlag, "无权进行操作");
			response.setHeader("WWW-authenticate", "Basic realm=\"无权进行操作\"");
			return result;
		}
		List list = userService.getAllUsers();
		UserInfo user = userService.getUserByUseName(userName);
		if (user != null) {
			result.put(Config.codeFlag, Config.successCode);
			result.put(Config.messageFlag, Config.successMes);
			result.put("result", user);
		} else {
			result.put(Config.codeFlag, Config.successCode);
			result.put(Config.messageFlag, "没有相关信息");
		}
		return result;

	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject delUser(@PathVariable Integer id, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		JSONObject result = new JSONObject();
		if (!checkHeaderAuth(request, response)) {
			result.put(Config.codeFlag, Config.errorCode);
			result.put(Config.messageFlag, "无权进行操作");
			response.setHeader("WWW-authenticate", "Basic realm=\"无权进行操作\"");
			return result;
		}

		try {
			userService.delUser(id);
			result.put(Config.codeFlag, Config.successCode);
			result.put(Config.messageFlag, Config.successMes);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.put(Config.codeFlag, Config.errorCode);
			result.put(Config.messageFlag, "删除异常");
			return result;
		}

	}

	/**
	 * 
	 * @Title: auth @Description: 用户登录 @param @param request @param @param
	 * response @param @return @param @throws IOException 参数 @return JSONObject
	 * 返回类型 @throws
	 */
	@RequestMapping(value = "/auth", method = RequestMethod.GET)
	public @ResponseBody JSONObject auth(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject result = new JSONObject();
		HttpSession session = request.getSession();
		String authorization = request.getHeader("authorization");
		if (authorization == null || authorization.equals("")) {
			result.put(Config.codeFlag, Config.errorCode);
			result.put(Config.messageFlag, "请输入用户名密码后重试");
			return result;
		}
		String userAndPass = new String(new BASE64Decoder().decodeBuffer(authorization.split(" ")[1]));
		String userName = userAndPass.split(":")[0];
		String passwd = userAndPass.split(":")[1];
		passwd = MD5.GetMD5Code(passwd);
		if (userService.getUserAuth(userName, passwd) > 0) {
			session.setAttribute("user", userName);
			result.put(Config.codeFlag, Config.successCode);
			result.put(Config.messageFlag, Config.successMes);
			response.setHeader("Authorization", authorization);
			return result;

		} else {
			result.put(Config.codeFlag, Config.errorCode);
			result.put(Config.messageFlag, "请输入用户名密码后重试");
			return result;
		}
	}

	private boolean checkHeaderAuth(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		String user = (String) session.getAttribute("user");
		String pass;
		if (user == null) {
			try {
				response.setCharacterEncoding("utf-8");
				String authorization = request.getHeader("authorization");
				if (authorization == null || authorization.equals("")) {
					return false;
				}
				String userAndPass = new String(new BASE64Decoder().decodeBuffer(authorization.split(" ")[1]));
				if (userAndPass.split(":").length < 2) {
					return false;
				}
				user = userAndPass.split(":")[0];
				pass = userAndPass.split(":")[1];
				if (userService.getUserAuth(user, pass) > 0) {
					session.setAttribute("user", user);
					return true;
				} else {
					return false;
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				return false;
			}
		} else {
			return true;
		}
	}

	private String getFromBASE64(String s) {
		if (s == null)
			return null;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(s);
			return new String(b);
		} catch (Exception e) {
			return null;
		}
	}
}
