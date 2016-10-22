package com.dataSource;

public class DataSourceHolder {

    public static ThreadLocal<String> datasources = new ThreadLocal<String>();

    public static String getDatasources() {
        return datasources.get();
    }

    public static void setDatasources(String value) {
        datasources.set(value);
    }
    
}
