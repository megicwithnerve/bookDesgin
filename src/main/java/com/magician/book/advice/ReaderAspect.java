package com.magician.book.advice;

import com.magician.book.utils.SignVerification;
import com.magician.book.utils.TokenUtil;
import com.magician.book.utils.UserType;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@Aspect
@Component
public class ReaderAspect {
    //    private final static Logger logger = LoggerFactory.getLogger(LoginAspect.class);
//
    @Pointcut("execution(public * com.magician.book.controller.reader.*.*(..))")
    public void log() {}
    @Around("log()")
    public Object signVerification(ProceedingJoinPoint pjp) throws Throwable{
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();
//        System.out.println("拦截！！！！！！！");
        String token = TokenUtil.getRequest("token");
        if(token != null && !token.equals("")&& TokenUtil.getTokenUserType() == UserType.reader){
            return pjp.proceed();//继续执行被拦截的方法
        }
        try {
            //构建JSON
            request.getSession().setAttribute("errorMsg", "请重新登陆");
            response.sendRedirect(request.getContextPath() + "/reader/login");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
