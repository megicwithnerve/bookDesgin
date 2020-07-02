package com.magician.book.advice;

import com.magician.book.utils.SignVerification;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


//@Aspect
//@Component
public class LoginAspect {
    private final static Logger logger = LoggerFactory.getLogger(LoginAspect.class);

    @Pointcut("execution(public * com.magician.book.controller.reader.*.*(..))")
    public void log() {}
    @Around("log()")
    public Object signVerification(ProceedingJoinPoint pjp) throws Throwable{
        SignVerification v = new SignVerification();
        if (v.Verification())//已经登录
        {
            return pjp.proceed();//继续执行被拦截的方法
        }
        else {//未登录
            //构建JSON
            String response = "{\"signin\":0}";
            return response;
        }
    }
}
