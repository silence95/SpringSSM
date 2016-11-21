package com.util;

import javax.servlet.http.HttpServletRequest;

public class Context {

    private HttpServletRequest httpServletRequest;

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }
    
}
