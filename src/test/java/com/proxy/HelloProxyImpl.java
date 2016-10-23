package com.proxy;

public class HelloProxyImpl implements HelloProxy{

	@Override
	public void sayHello() {
		System.out.println("hello proxy");
	}
}
