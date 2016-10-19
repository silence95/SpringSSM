package com.dataSource;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.core.annotation.AnnotationUtils;

public class DataSourceExchange implements MethodInterceptor{

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
       String methodName = invocation.getMethod().getName();
       Method methodObj = null;
       Method[] methods = invocation.getThis().getClass().getMethods();
       for(Method method : methods) {
           if(method.getName().equals(methodName)) {
               methodObj = method;
               break;
           }
       }
       
        DataSource dataSource = AnnotationUtils.findAnnotation(methodObj, DataSource.class);
        DataSourceHolder.setDatasources(dataSource.value());
        return invocation.proceed();
    }

}
