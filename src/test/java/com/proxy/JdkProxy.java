package com.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxy implements InvocationHandler {

	private Object target;
	
	// proxy生成的代理类，继承java.lang.reflect.Proxy，实现HelloProxy接口
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
	    System.out.println("target:" + target + " proxy:" + proxy.getClass() + " method:" + method + " args:" + args);
		System.out.println("jdkProxy start");
		Object result = method.invoke(target, args);
		System.out.println("jdkProxy end");
		return result;
	}

	public Object getProxy(Object target) {
		this.target = target;
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	} 
	
	public static void main(String[] args) {
		JdkProxy jdkProxy = new JdkProxy();
		HelloProxy helloJdkProxy = (HelloProxy)jdkProxy.getProxy(new HelloProxyImpl());
		helloJdkProxy.sayHello();
		// com.sun.proxy.$Proxy0 cannot be cast to com.proxy.HelloProxyImpl
		// HelloProxyImpl helloJdkProxyImpl = (HelloProxyImpl)jdkProxy.getProxy(new HelloProxyImpl());
		// helloJdkProxyImpl.sayFinalHello();
		// helloJdkProxyImpl.sayStaticHello();
	}
}
