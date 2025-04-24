package com.test.TestServer.configuration;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerLoggingAspect {

    private static final Logger log = LoggerFactory.getLogger(ControllerLoggingAspect.class);

    // Pointcut to match all methods in classes annotated with @RestController
    @Before("within(@org.springframework.web.bind.annotation.RestController *) && execution(* *(..))")
    public void logBeforeControllerMethod(JoinPoint joinPoint) {
        String controllerName = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        log.info("{}.{} is executing", controllerName, methodName);
    }

    // Pointcut to match all methods in classes annotated with @RestController
    @AfterReturning("within(@org.springframework.web.bind.annotation.RestController *) && execution(* *(..))")
    public void logAfterControllerMethod(JoinPoint joinPoint) {
        String controllerName = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        log.info("{}.{} was executed", controllerName, methodName);
    }
}