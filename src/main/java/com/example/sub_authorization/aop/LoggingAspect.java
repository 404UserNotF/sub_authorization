package com.example.sub_authorization.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Aspect
@Controller
public class LoggingAspect {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * log request infos and response infos
     * */
    @Before("com.example.sub_authorization.aop.PointCuts.UserControllerPointCut()")
    public void UserControllerBeforeAdvice(JoinPoint joinPoint){
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        if(sra != null){
            request = sra.getRequest();
            response = sra.getResponse();
        }

        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String queryString = request.getQueryString();
        long startTime = System.currentTimeMillis();
        logger.info("{url:{}, method:{}, queryString:{}, time:{}}", url, method, queryString, startTime);
        logger.info("end point:" + joinPoint.getSignature().toString());
        logger.info(response.toString());
    }

    @Around("com.example.sub_authorization.aop.PointCuts.UserDaoLoggingPointCut()")
    public Object UserDaoAdvice(ProceedingJoinPoint pjp) throws Throwable {
        Long startTime = System.currentTimeMillis();
        Object res = pjp.proceed();
        Long endTime = System.currentTimeMillis();
        Long lasting = endTime - startTime;
        logger.info(pjp.getSignature().toString() + " takes " + lasting + " millis");
        return res;
    }


    /*@Around("com.example.sub_authorization.aop.PointCuts.UserControllerPointCut()")
    public Object UserControllerAdvice(ProceedingJoinPoint pjp) throws Throwable {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        if(sra != null){
            request = sra.getRequest();
        }

        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String queryString = request.getQueryString();
        long startTime = System.currentTimeMillis();
        logger.info("{url:{}, method:{}, queryString:{}, time:{}}", url, method, queryString, startTime);
        logger.info("end point:" + pjp.getSignature().toString() + " is processing");
        Object res = null;
        try{
            res = pjp.proceed();
        }catch (Throwable t){
            logger.error("An Exception Happened: " + t.getMessage() + " When " + pjp.getSignature().toString() + "is processing");
            throw t;
        }
        return res;
    }*/
    @AfterThrowing(value = "com.example.sub_authorization.aop.PointCuts.UserControllerPointCut()", throwing = "e")
    public void UserControllerAfterThrowingAdvice(Exception e){
        logger.error("An Exception Happened: " + e.getMessage());
    }
}
