package com.proxy;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

// cglib针对类来实现代理的，他的原理是对指定的目标类生成一个子类，并覆盖其中方法实现增强，但因为采用的是继承，所以不能对final修饰的类进行代理
public class CglibProxy implements MethodInterceptor{

    private  Object target;
    
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("target:" + target.toString()  + " method:" + method.toString() + " args:" + args.toString() + " methodProxy:" + methodProxy.toString());
        System.out.println("cglibProxy start");
        Object result = methodProxy.invokeSuper(obj, args);
        System.out.println("cglibProxy end");
        return result;
    }

    public Object getProxy(Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }
    
    public static void main(String[] args) {
        CglibProxy cglibProxy = new CglibProxy();
        HelloProxy helloCglibProxy = (HelloProxy)cglibProxy.getProxy(new HelloProxyImpl());
        helloCglibProxy.sayHello();
        
//        HelloProxyImpl helloCglibProxyImpl = (HelloProxyImpl)cglibProxy.getProxy(new HelloProxyImpl());
//        helloCglibProxyImpl.sayFinalHello(); // cannot proxy
//        helloCglibProxyImpl.sayStaticHello();// cannot proxy
    }
}
