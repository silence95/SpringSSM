package com.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class TestAspect {

    // 1、切点符合运算定义切面
    @Before("execution(* queryUser(..))")
    public void greetToRun() {
        System.out.println("hello greetToRun running");
    }
    
    // 2、命名切点
    @Pointcut("execution(* testSpringBean(..))") private void beforeGreet() {}
    
    @Before("beforeGreet()")
    public void beforeGreetToRun() {
        System.out.println("hello beforeGreetToRun running");
    }
    
    // 访问连接点信息
    @Around("beforeGreet()")
    public Object joinPointAccess(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("joinPointAccess access method is:" + pjp.getTarget());
        return pjp.proceed();
    }
}
