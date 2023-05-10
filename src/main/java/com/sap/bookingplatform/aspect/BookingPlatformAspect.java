package com.sap.bookingplatform.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class BookingPlatformAspect {


	    @Before("execution(* com.sap.bookingplatform.controller..*(..)))")
	    public void logMethod(JoinPoint joinPoint) {
	        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
	        log.info("Executing method {} ", signature.getName());
	    }
}
