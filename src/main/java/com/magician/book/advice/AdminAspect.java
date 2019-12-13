package com.magician.book.advice;

import com.magician.book.utils.SignVerification;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Aspect
@Component
public class AdminAspect {
//    private final static Logger logger = LoggerFactory.getLogger(LoginAspect.class);
//
//    @Pointcut("execution(public * com.magician.book.controller.reader.*.*(..))")
//    public void log() {}
//    @Around("log()")
//    public Object signVerification(ProceedingJoinPoint pjp) throws Throwable{
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        HttpServletResponse response = attributes.getResponse();
//
//        SignVerification v = new SignVerification();
//        if (v.Verification())//已经登录
//        {
//            request.getSession().removeAttribute("errorMsg");
//            return pjp.proceed();//继续执行被拦截的方法
//        }
//        else {//未登录
//            //构建JSON
//            request.getSession().setAttribute("errorMsg", "请重新登陆");
//            response.sendRedirect(request.getContextPath() + "/admin/login");
//        }
//        return null;
//    }
}
