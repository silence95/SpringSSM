package com.proxy;

public class StaticProxy implements HelloProxy{

	private HelloProxy helloProxy;

	public StaticProxy(HelloProxy helloProxy) {
		this.helloProxy = helloProxy;
	}
	
	@Override
	public void sayHello() {
		System.out.println("staticProxy start");
		helloProxy.sayHello();
		System.out.println("staticProxy end");
	}
	
	
	public static void main(String[] args) {
		HelloProxyImpl helloProxyImpl = new HelloProxyImpl();
		StaticProxy staticProxy = new StaticProxy(helloProxyImpl);
		staticProxy.sayHello();
	}
}
