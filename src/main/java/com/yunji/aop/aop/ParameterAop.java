package com.yunji.aop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class ParameterAop {
    /*
    * controller
    */
    @Pointcut("execution(* com.yunji.aop.controller..*.*(..))")
    private void cut(){}

    /*
    * before 옆에 명시한 메소드 실행 전 실행하도록 명시
    */
    @Before("cut()")
    public void before(JoinPoint joinPoint){

        //method name
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        System.out.println("method name= "+ method.getName());

        Object[] args = joinPoint.getArgs();
        for (Object obj : args){
            System.out.println("type:"+ obj.getClass().getSimpleName());
            System.out.println("value:"+ obj);
        }
    }

    /*
     * 메서드가 실행 된 후 해당 Object 값을 확인하도록 설정
     */
    @AfterReturning(value = "cut()", returning = "returnObj")
    public void afterReturn(JoinPoint joinPoint, Object returnObj){
        System.out.println("return obj");
        System.out.println(returnObj);
    }

}
