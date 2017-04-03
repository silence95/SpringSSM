package com.service.impl;

import java.util.List;

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.UserDao;
import com.dataSource.DataSource;
import com.service.UserService;
import com.util.LoginUtil;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired UserDao userDao;
    
    @Override
    @DataSource(value = DataSource.master)
    public List<String> queryUser() {
         return testSpringBean(); // 拦截不了，springAop的通知只能作用于springbean上，this调用自己的方法无效，可使用
//         return ((UserService)AopContext.currentProxy()).testSpringBean();//这里暴露当前代理对象到ThreadLocal
        // 会抛错，添加<aop:config expose-proxy="true"></aop:config>配置
//        return userDao.queryAllName();
    }
    
    @Override
    @DataSource(value = DataSource.bk) // 不加@DataSource就默认master
    public List<String> testDataSourceBk() {
        return userDao.queryAllName();
    }
    
    @DataSource(value = DataSource.bk)
    public List<String> testSpringBean() {
        return userDao.queryAllName();
    }
    
    public static void testStatic() {
        
    }

    @Override
    public String queryloginInfo() {
        return LoginUtil.getLoginName();
    }

}
