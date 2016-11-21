package com.util;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContextHold {

    private static Logger logger = LoggerFactory.getLogger(ContextHold.class);
    
    private static ThreadLocal<Context> context = new ThreadLocal<Context>() {
        @Override
        protected Context initialValue() {
            logger.debug("inital context.");
            return new Context();
        }
    };
    
    public static HttpServletRequest getHttpServletRequest() {
        return context.get().getHttpServletRequest();
    }
    
    public static void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        context.get().setHttpServletRequest(httpServletRequest);
        return ;
    }
}
