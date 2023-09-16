package com.anuradha.annexservice.aop;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;


@Aspect
@Component
public class LoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger("api");

    @Pointcut("execution(* com.anuradha.annexservice.controller.*.*(..))")
    private void restControllerMethods() {
    }

    @Before("restControllerMethods()")
    public void logRequest(JoinPoint joinPoint) {
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes != null) {
                logRequestInfo(attributes.getRequest(), joinPoint);
            }
        } catch (IOException e) {
            LOGGER.error("Request - logging exception: {}", e.getMessage(), e);
        }
    }

    @Around(value = "restControllerMethods())")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        LOGGER.info("Method execution time : {} ms", executionTime);
        return proceed;
    }

    @AfterReturning(pointcut = "restControllerMethods()", returning = "response")
    public void logResponse(Object response) {
        // Log response information
        LOGGER.info("Response - {}", response);
    }

    private void logRequestInfo(HttpServletRequest request, JoinPoint joinPoint) throws IOException {
        String method = request.getMethod();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();

        LOGGER.info("Request - Method: {} | URL: {} | IP: {}", method, url, ip);

        logRequestParameters(request);
        LOGGER.info("Request - Arguments: {}", Arrays.toString(joinPoint.getArgs()));
        logClassMethod(joinPoint);
    }

    private void logRequestParameters(HttpServletRequest request) throws IOException {
        String requestParams = request.getParameterMap().entrySet().stream().map(e -> e.getKey() + "=" + String.join(", ", e.getValue())).collect(Collectors.joining(" "));
        LOGGER.info("Request - Parameters: {}", requestParams);
    }

    private void logClassMethod(JoinPoint joinPoint) {
        String declaringTypeName = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        LOGGER.info("Class Method: {}#{}", declaringTypeName, methodName);
    }


}
