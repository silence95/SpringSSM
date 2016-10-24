package com.proxy;

public class TestSubAbstractCglib extends TestAbstractCglib{

    public static void sayStaticHello() {
        System.out.println("abstractSubCglib static hello");
    }
    
    public static void main(String[] args) {
        CglibProxy cglibProxy = new CglibProxy();
        TestSubAbstractCglib testSubAbstractCglib = (TestSubAbstractCglib)cglibProxy.getProxy(new TestSubAbstractCglib());
        testSubAbstractCglib.sayHello();
        testSubAbstractCglib.sayStaticHello(); // 不能代理
    }
}
