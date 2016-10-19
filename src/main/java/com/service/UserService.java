package com.service;

import java.util.List;

import com.dataSource.DataSource;

public interface UserService {
   
    @DataSource
    public List<String> queryUser();
    
    public List<String> testDataSourceBk();
}
