package com.dataSource;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

// jdk版数据源拦截
public class DataSourceJdkExchange implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        String methodName = invocation.getMethod().getName();
        Method methodObj = null;
        Method[] methods = invocation.getThis().getClass().getMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                methodObj = method;
                break;
            }
        }

        if(methodObj.isAnnotationPresent(DataSource.class)) {
            DataSource dataSource = methodObj.getAnnotation(DataSource.class);
            DataSourceHolder.setDatasources(dataSource.value());
        } else {
            DataSourceHolder.setDatasources(DataSource.master); // 默认master
        }
        return invocation.proceed();
    }

}
