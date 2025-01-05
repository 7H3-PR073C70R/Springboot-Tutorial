package com.protector.StudentApp.aop;

import com.protector.StudentApp.authentication.model.User;
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
    @Before("execution(* com.protector.StudentApp.authentication.service.UserService.*(..))")
    public void logRegisterMethodCall(JoinPoint joinPoint, User user) {
        LOGGER.info(joinPoint.getSignature().getName() + " user " + user.toString());
    }

    @AfterReturning("execution(* com.protector.StudentApp.authentication.service.UserService.*(..))")
    public void logRegisterMethodCallSuccessfully(JoinPoint joinPoint, User user) {
        LOGGER.info(joinPoint.getSignature().getName() + " user " + user.toString() + " execution successful");
    }

    @AfterThrowing("execution(* com.protector.StudentApp.authentication.service.UserService.*(..))")
    public void logRegisterMethodCallFailed(JoinPoint joinPoint, User user) {
        LOGGER.info(joinPoint.getSignature().getName() + " user " + user.toString() + " execution failed");
    }

    @After("execution(* com.protector.StudentApp.*.*(..))")
    public  void logMethodExecuted(JoinPoint joinPoint) {
        LOGGER.info(joinPoint.getSignature().getName() + " Method executed");
    }

    @AfterThrowing("execution(* com.protector.StudentApp.*.*(..))")
    public  void logMethodCrashed(JoinPoint joinPoint) {
        LOGGER.info(joinPoint.getSignature().getName() + " Method has some issues");
    }

    @AfterReturning("execution(* com.protector.StudentApp.*.*(..))")
    public  void logMethodExecutedSuccessFully(JoinPoint joinPoint) {
        LOGGER.info(joinPoint.getSignature().getName() + " Method executed successfully");
    }
}
