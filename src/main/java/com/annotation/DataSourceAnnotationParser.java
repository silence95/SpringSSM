package com.annotation;

import org.springframework.aop.support.RegexpMethodPointcutAdvisor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.parsing.BeanComponentDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

// xml里面子定义注解
public class DataSourceAnnotationParser implements BeanDefinitionParser {
 
    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
     
        // 获得元素属性值
        String adviceClassName = element.getAttribute("advice-class-name");
        String pointCut = element.getAttribute("point-cut");
        
        RootBeanDefinition advice = null;
        try {
            advice = new RootBeanDefinition(Class.forName(adviceClassName));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String sourceName = parserContext.getReaderContext().registerWithGeneratedName(advice);
        
        // 动态生成advisor（正则表达式匹配）
        RootBeanDefinition responseStatusExceptionResolver = new RootBeanDefinition(RegexpMethodPointcutAdvisor.class);
        responseStatusExceptionResolver.getPropertyValues().addPropertyValue("patterns", pointCut); // 添加属性
        responseStatusExceptionResolver.getPropertyValues().addPropertyValue("advice", new RuntimeBeanReference(sourceName)); // advice此bean尚未生成，且aaplicationContextAware也为null，无法getBean获得
     
        String defaultExceptionResolverName = parserContext.getReaderContext().registerWithGeneratedName(responseStatusExceptionResolver);
        // 动态添加bean元素
        parserContext.registerComponent(new BeanComponentDefinition(responseStatusExceptionResolver, defaultExceptionResolverName));

        return null;
    }

}
