package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.UserDao;
import com.dataSource.DataSource;
import com.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired UserDao userDao;
    
    @Override
    public List<String> queryUser() {
        // return testSpringBean(); // 拦截不了，springAop的通知只能作用于springbean上，this调用自己的方法无效，可使用
        // ((UserService)AopContext.currentProxy()).testSpringBean();
        // 会抛错，添加<aop:config expose-proxy="true"></aop:config>配置
        return userDao.queryAllName();
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

}
