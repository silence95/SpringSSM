package com.proxyUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import sun.misc.ProxyGenerator;

import com.proxy.HelloProxyImpl;

// JDK提供了sun.misc.ProxyGenerator.generateProxyClass(String proxyName,class[] interfaces) 底层方法来产生动态代理类的字节码
public class JdkProxyUtil {

    private static FileOutputStream fos;

    public static <T> void generateProxyClass(Class<T> target, String proxyName) {
        byte[] clazzB = ProxyGenerator.generateProxyClass(proxyName,target.getInterfaces());
        try {
            File file = new File("src/test/java/com/proxyGenerateClazz/" + proxyName + ".class");
            fos = new FileOutputStream(file);
            fos.write(clazzB);
            fos.flush();
            System.out.print(proxyName + " generate to " + new File("src/test/java/com/proxyGenerateClazz/").getAbsolutePath() + " Succeess!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        JdkProxyUtil.generateProxyClass(HelloProxyImpl.class, "JdkHelloProxyClazz");
    }
}
