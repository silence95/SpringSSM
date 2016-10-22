package com.common;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class DynamicPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer{
   
    @Override
    protected void loadProperties(Properties props) throws IOException {
        String evn = System.getProperty("env");
        String resourceName = "jdbc.properties";
        if(evn != null)
            resourceName = evn + "jdbc.properties";
        Resource location = new ClassPathResource(resourceName);
        setLocation(location);
        super.loadProperties(props);
    }
    
}
