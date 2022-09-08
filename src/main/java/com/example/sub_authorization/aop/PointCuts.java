package com.example.sub_authorization.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PointCuts {
    // match all method in UserController
    @Pointcut("execution(* com.example.sub_authorization.controller..*.*(..))")
    public void UserControllerPointCut(){}

    @Pointcut("execution(* com.example.sub_authorization.dao..*.*(..))")
    public void UserDaoLoggingPointCut(){}
}
