/**
 * Project Name:docker-usermodel
 * File Name:TestUser.java
 * Package Name:com.asianinfo.user
 * Date:2015年11月11日下午10:23:56
 * Copyright (c) 2015, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.asianinfo.user;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.asianinfo.user.dao.IUserDao;
import com.asianinfo.user.model.UserInfo;

import junit.framework.Assert;

/**
 * ClassName:TestUser <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015年11月11日 下午10:23:56 <br/>
 * @author   weijin
 * @version  
 * 
 * @see 	 
 */
public class TestUser {
	@BeforeClass  
    public static void setUp() throws Exception {  
        System.out.println("一个测试开始。。");  
    }  
  
    @AfterClass
    public static void tearDown() throws Exception {  
        System.out.println("一个测试结束");  
    }  
  
    @Test
    public  void testSelectAll() {
       System.out.println(111);
    }
    @Test
    @Ignore
    public void testGetUserByUseName(){
    	System.out.println("这个测试被忽略了，如果不用ignore 就执行测试");
    }
    @Test
    @Ignore
    public void testAddUser(){
    	
    }
    @Test
    @Ignore
    public void testGetUserAuth(){
    	
    }
    @Test
    @Ignore
    public void testDelUser(){
    	
    }
}
