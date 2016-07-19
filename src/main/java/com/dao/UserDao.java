package com.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository("userDao")
public interface UserDao {

    public List<String> queryAllName();
}
