package com.imooc.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @program: imooc-security
 * @description: 切面
 * @author: ouhuixuan
 * @create: 2019-09-05 16:07
 **/
//@Aspect
//@Component
public class TimeAspect {

    /**
    *  @description @Around表示在拦截的方法的前后加入此方法逻辑，execution表达式表达的是要拦截的方法
    * @Param: ProceedingJoinPoint包含了要拦截的方法的类名方法名参数等信息
    */
    @Around("execution(* com.imooc.web.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        System.out.println("time aspect start");
        Object[] args = proceedingJoinPoint.getArgs();
        Arrays.stream(args).forEach(arg -> System.out.println("arg is"+ arg));
        Long startTime = System.currentTimeMillis();
        //执行方法,返回值就是所拦截的方法的返回值
        Object proceed = proceedingJoinPoint.proceed();
        System.out.println(proceed.toString());
        System.out.println(" Time Aspect 耗时："+(System.currentTimeMillis()-startTime));
        System.out.println("time aspect end");
        return proceed;
    }
}
