package com.controller;

import java.lang.management.ManagementFactory;
import java.util.logging.LoggingMXBean;

import javax.management.MBeanServer;
import javax.management.MBeanServerInvocationHandler;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.service.LogService;
import com.service.impl.LogServiceImpl;

@Controller
@RequestMapping("/log")
public class LogController {

    private static Logger logger = LoggerFactory.getLogger(LogServiceImpl.class);
    @Autowired LogService logService;
    
    @RequestMapping(value="/changeLogLevel",method={RequestMethod.GET},produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String changeLogLevel(@RequestParam("loggerName")String loggerName,@RequestParam("logLevel")String logLevel) {
        
        try {
            MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
            ObjectName objectName = new ObjectName("ch.qos.logback.classic:Name=default,Type=ch.qos.logback.classic.jmx.JMXConfigurator");
            LoggingMXBean mxbeanProxy = (LoggingMXBean) MBeanServerInvocationHandler.newProxyInstance(mbs, objectName, LoggingMXBean.class, false);
            mxbeanProxy.setLoggerLevel(loggerName, logLevel);
            logger.info("update logger {} to {} level", loggerName, logLevel);
        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
            return "update failed";
        }
        return "update successfully";
    }
    
    @RequestMapping(value="/printLog",method={RequestMethod.GET},produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody void printLog() {
        logService.printLog();
    }
}
