package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;

/**
 * Servlet implementation class SecurityServlet
 */
public class SecurityServlet extends HttpServlet implements Filter{
	private static final long serialVersionUID = 1L;
       
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException,
            ServletException {
        
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        
        response.setHeader("P3P", "CP=CAO PSA OUR IDC DSP COR ADM DEVi TAIi PSD IVAi IVDi CONi HIS IND CNT"); // 跨域
        
        //获取用户登录后返回的信息
        if (!StringUtils.isEmpty(request.getUserPrincipal())) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String loginName = (String)request.getSession().getAttribute("loginUser");
        
        if(StringUtils.isEmpty(loginName)) {
            if(!request.getRequestURI().equals(request.getContextPath() + "/login.html") && !request.getRequestURI().equals(request.getContextPath() + "/user/login.do")) {
                if(request.getHeader("x-requested-with") != null && ("XMLHttpRequest").equalsIgnoreCase(request.getHeader("x-requested-with"))) {
                    PrintWriter writer = response.getWriter();
                    writer.print("Seesion已经失效了");
                    writer.flush();
                    writer.close();
                } else {
                    response.sendRedirect(request.getContextPath() + "/login.html");
                }
                return ;
            }
        }
        
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub
        
    }

}
