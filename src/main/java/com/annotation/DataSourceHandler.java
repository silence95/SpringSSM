package com.annotation;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class DataSourceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("annotation-driven", new DataSourceAnnotationParser());
    }

}
