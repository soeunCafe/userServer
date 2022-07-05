package com.cafe.user.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    @Around("execution(* com.cafe.user.controller.*.*(..))")
    public Object loggerAround(ProceedingJoinPoint proceedingJoinPoint) throws  Throwable{
        long beforeTimeMillis = System.currentTimeMillis();
        log.info("start : " + beforeTimeMillis);
        Object result = proceedingJoinPoint.proceed(); // 여기 전후로 나눠진다.
        long afterTimeMillis = System.currentTimeMillis();
        log.info("before : "+afterTimeMillis + " 시간차: "+(afterTimeMillis-beforeTimeMillis));
        return result;
    }
}
