package com.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxy implements InvocationHandler {

	private Object target;
	
	@Override
	public Object invoke(Object obj, Method method, Object[] params) throws Throwable {
		System.out.println("jdkProxy start");
		System.out.println(target == obj);
		Object result = method.invoke(obj, params);
		System.out.println("jdkProxy end");
		return result;
	}

	public Object getProxy(Object target) {
		this.target = target;
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	} 
	
	public static void main(String[] args) {
		JdkProxy jdkProxy = new JdkProxy();
		HelloProxy helloProxy = (HelloProxy)jdkProxy.getProxy(new HelloProxyImpl());
		helloProxy.sayHello();
	}
}
