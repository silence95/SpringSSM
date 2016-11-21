package com.util;

public class LoginUtil {

    public static String getLoginName() {
        return (String) ContextHold.getHttpServletRequest().getSession().getAttribute("loginUser");
    }
}
