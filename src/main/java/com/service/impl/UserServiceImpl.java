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
    @DataSource(value = DataSource.master)
    public List<String> queryUser() {
        return userDao.queryAllName();
    }
    
    @Override
    @DataSource(value = DataSource.bk) // 不加@DataSource就默认master
    public List<String> testDataSourceBk() {
        return userDao.queryAllName();
    }

}
