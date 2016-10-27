package com.annotation;

import java.io.IOException;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;
import org.w3c.dom.Element;

import com.dataSource.DataSource;

// xml里面子定义注解：半成品
public class DataSourceAnnotationParser implements BeanDefinitionParser{
 
    private static final String RESOURCE_PATTERN = "/**/*.class";
    private ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
 
    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
//        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
//        parserContext.getRegistry().registerBeanDefinition("advisor", beanDefinition);
//        String basePackage = element.getAttribute("base-package");
        String basePackage = "com.service";
        String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + 
                ClassUtils.convertClassNameToResourcePath(basePackage) + RESOURCE_PATTERN;
        try {
            Resource[] resources = resourcePatternResolver.getResources(pattern);
            MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(this.resourcePatternResolver);
            for(Resource resource : resources) {
                MetadataReader reader = readerFactory.getMetadataReader(resource);
                String className = reader.getClassMetadata().getClassName();
                Class<?> clazz = Class.forName(className);
                DataSource dataSource = clazz.getAnnotation(DataSource.class);
                System.out.println(clazz.toString());
                if(dataSource != null) {
                    System.out.println("annotaion:" + clazz.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
