package com.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.service.LogService;

@Service
public class LogServiceImpl implements LogService{

    private static Logger logger = LoggerFactory.getLogger(LogServiceImpl.class);
    private static Logger fileLogger = LoggerFactory.getLogger("fileLog");
    
    @Override
    public void printLog() {
        logger.debug("--debug {}" , "hello log");
        logger.info("--info {}" , "hello log");
        
        fileLogger.debug("--debug {}" , "hello log");
        fileLogger.info("--info {}" , "hello log");
    }
    
    public static void main(String[] args) {
        new LogServiceImpl().printLog();
    }
}
