package com.proxy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

public class CustomAgent implements  ClassFileTransformer {

    public static void premain(String agentArgs, Instrumentation inst) {
        inst.addTransformer(new CustomAgent());
    }
    
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
