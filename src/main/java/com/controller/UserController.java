package com.controller;


import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.service.UserService;
import com.util.LoginUtil;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired UserService userService;
    
    @RequestMapping(value="/queryloginInfo", method={RequestMethod.GET}, produces="text/plain;charset=UTF-8")
    public @ResponseBody String queryloginInfo() {
        return userService.queryloginInfo();
    }
    
    @RequestMapping(value="/query", method={RequestMethod.GET}, produces="application/json;charset=UTF-8")
    public @ResponseBody String query() {
        List<String> names = userService.queryUser();
        return new Gson().toJson(names);
    }
    
    @RequestMapping(value="/testDataSource", method={RequestMethod.GET}, produces="application/json;charset=UTF-8")
    public @ResponseBody String testDataSource() {
        List<String> names = userService.testDataSourceBk();
        return new Gson().toJson(names);
    }
    
    public void testDataSource2() {
        return ;
    }
    
    @PostConstruct
    public void testPostConstruct() {
        System.err.println("testPostConstruct"); // constructor > @PostConstruct > InitializingBean > init-method
    }
    
    @RequestMapping(value="/login", method={RequestMethod.POST}, produces="text/plain;charset=UTF-8")
    public @ResponseBody String login(HttpServletRequest request, HttpServletResponse response, @RequestParam("loginName")String loginName) {
        request.getSession().setAttribute("loginUser", loginName);
        return LoginUtil.getLoginName();
    }
}
