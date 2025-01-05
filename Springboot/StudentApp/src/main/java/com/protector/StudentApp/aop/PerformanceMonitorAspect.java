//package com.protector.StudentApp.aop;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//@Component
//@Aspect
//public class PerformanceMonitorAspect {
//
//    public static final Logger LOGGER=LoggerFactory.getLogger(PerformanceMonitorAspect.class);
//
//
//
//    @Around("execution (* com.protector.studentapp.*(..))")
//    public Object monitorTime(ProceedingJoinPoint jp) throws Throwable {
//
//        long start = System.currentTimeMillis();
//
//        Object obj = jp.proceed();
//
//        long end = System.currentTimeMillis();
//
//        LOGGER.info("Time taken by: "+jp.getSignature().getName()+" "+(end-start)+" ms");
//        return obj;
//    }
//}