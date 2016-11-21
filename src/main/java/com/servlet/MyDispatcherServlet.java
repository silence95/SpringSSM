package com.servlet;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.DispatcherServlet;

import com.util.ContextHold;

public class MyDispatcherServlet extends DispatcherServlet{

    private static final long serialVersionUID = 1L;

    private static SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmmssSSS");
    private static Random random = new Random();
    
    @Override
    protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String requestId = request.getHeader("requestId"); // ajax获得uuid，login.html样例
        if(StringUtils.isEmpty(request))
            requestId = generateRequestId();
        MDC.put("requestId", requestId); // requestId
        ContextHold.setHttpServletRequest(request); // 保存request到threadLocalMap中
        super.doDispatch(request, response);
    }
    
    private String generateRequestId() {
        String dateStr = sdf.format(new Date());
        int rannum = (int)(random.nextDouble() * (99999 - 10000 + 1)) + 10000;
        return dateStr + rannum;
    }
    
    public static void main(String[] args) {
        new MyDispatcherServlet().generateRequestId();
    }
}
