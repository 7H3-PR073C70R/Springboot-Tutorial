package com.protector.springbootrest.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    // return type, class-name.method-name(arguments)
    // e.g void com.protector.springbootrest.JobRestController.addJobPost(JobPost)
    @Before("execution(* com.protector.springbootrest.service.JobService.*(..))")
    public  void logMethodCall(JoinPoint joinPoint) {
        LOGGER.info(joinPoint.getSignature().getName() + " Method called");
    }

    @After("execution(* com.protector.springbootrest.service.JobService.*(..))")
    public  void logMethodExecuted(JoinPoint joinPoint) {
        LOGGER.info(joinPoint.getSignature().getName() + " Method executed");
    }

    @AfterThrowing("execution(* com.protector.springbootrest.service.JobService.*(..))")
    public  void logMethodCrashed(JoinPoint joinPoint) {
        LOGGER.info(joinPoint.getSignature().getName() + " Method has some issues");
    }

    @AfterReturning("execution(* com.protector.springbootrest.service.JobService.*(..))")
    public  void logMethodExecutedSuccessFully(JoinPoint joinPoint) {
        LOGGER.info(joinPoint.getSignature().getName() + " Method executed successfully");
    }
}
