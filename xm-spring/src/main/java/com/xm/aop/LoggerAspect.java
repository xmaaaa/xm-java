package com.xm.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author XM
 * @date 2023/1/17
 */
@Component
@Aspect
public class LoggerAspect {

    @Pointcut("execution(* com.xm.service.*.*(..))")
    public void log() {
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        // .......
        // 辅助业务1
        // .......
        System.out.println("before");
    }

    @After("log()")
    public void doAfter() {
        // .......
        // 辅助业务2
        // .......
        System.out.println("after");
    }
}
