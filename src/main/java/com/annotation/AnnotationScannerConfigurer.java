package com.annotation;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/* 1、Spring会读取ApplicationContextAware类型的的JavaBean，
     并调用setApplicationContext(ApplicationContext applicationContext)传入Spring的applicationContext
   2、Spring会在BeanFactory的相关处理完成后调用postProcessBeanFactory方法，进行定制的功能
*/
public class AnnotationScannerConfigurer implements BeanFactoryPostProcessor,ApplicationContextAware  {

    private ApplicationContext applicationContext;
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // TODO Auto-generated method stub
        
    }

}
