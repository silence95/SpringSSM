package com.proxy;

public interface HelloProxy {

	public void sayHello();
	
	// Illegal modifier for the interface method sayFinalHello; only public & abstract are permitted
	// public static void sayStaticHello(); 
	//	public final void sayFinalHello();
}
