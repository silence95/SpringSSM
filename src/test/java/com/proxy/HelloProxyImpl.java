package com.proxy;

public class HelloProxyImpl implements HelloProxy{

	@Override
	public void sayHello() {
		System.out.println("hello proxy");
	}
	
//	public static void sayStaticHello() {
//	    System.out.println("static hello proxy");
//	}
//	
//	public final void sayFinalHello() {
//        System.out.println("final hello proxy");
//    }
}
