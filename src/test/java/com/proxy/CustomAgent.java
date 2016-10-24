package com.proxy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

public class CustomAgent implements  ClassFileTransformer {

    /*
     *  http://blog.csdn.net/ykdsg/article/details/12080071
     * Java Instrumentation指的是可以用独立于应用程序之外的代理（agent）程序来监测和协助运行在JVM上的应用程序
     * premain方法，在main方法启动前执行。
     * agentmain应用程序启动之后再开启代理程序，通过Attach Tools API的VirtualMachine.attach方法绑定到目标VM，并向其中加载代理jar
     * VM会根据manifest中指定的代理类，使用于main类相同的系统类加载器（即ClassLoader.getSystemClassLoader()获得的加载器）加载代理类。
     */
    public static void premain(String agentArgs, Instrumentation inst) {
        inst.addTransformer(new CustomAgent());
    }
    
    /*
     *  http://www.apihome.cn/api/java/ClassFileTransformer.html
     * 此方法的实现可以转换提供的类文件，并返回一个新的替换类文件
     * 在转换器使用 addTransformer 注册之后，每次定义新类和重定义类时都将调用该转换器。
     */
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, 
            byte[] classfileBuffer) throws IllegalClassFormatException {
        if(className.contains("proxy/$Proxy") || className.contains("$$Enhancer")) { 
            int lastIndexOf = className.lastIndexOf("/") + 1;
            String fileName = className.substring(lastIndexOf) + ".class";
            exportClazzToFile(fileName, classfileBuffer);
            System.out.println(className + " --> export to " + new File("").getAbsolutePath() + " Succeess!");
        }
        return classfileBuffer;
    }
    
    private void exportClazzToFile(String fileName,byte[] data) {
        try {
            File file = new File(fileName);
            if(!file.exists()) 
                file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

}
