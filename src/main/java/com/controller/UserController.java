package com.controller;


import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dataSource.DataSource;
import com.google.gson.Gson;
import com.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired UserService userService;
    
    @RequestMapping(value="/query", method={RequestMethod.GET}, produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String query() {
        List<String> names = userService.queryUser();
        return new Gson().toJson(names);
    }
    
    @RequestMapping(value="/testDataSource", method={RequestMethod.GET}, produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String testDataSource() {
        testDataSource2();
        List<String> names = userService.testDataSourceBk();
        return new Gson().toJson(names);
    }
    
    @DataSource
    public void testDataSource2() {
        return ;
    }
    
    @PostConstruct
    public void testPostConstruct() {
        System.err.println("testPostConstruct"); // constructor > @PostConstruct > InitializingBean > init-method
    }
}
