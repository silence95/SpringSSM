package com.dataSource;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.util.ClassUtils;

// 实现advice的数据源拦截
public class DataSourceExchange implements MethodBeforeAdvice{

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
     
        // 查找当前类覆盖的方法
        Method specificMethod = ClassUtils.getMostSpecificMethod(method, target.getClass());  
        if(specificMethod.isAnnotationPresent(DataSource.class)) {
            DataSource dataSource = specificMethod.getAnnotation(DataSource.class);
            DataSourceHolder.setDatasources(dataSource.value());
        } else {
            DataSourceHolder.setDatasources(DataSource.master); // 默认master
        }
    }
}
