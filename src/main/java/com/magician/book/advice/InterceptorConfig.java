package com.magician.book.advice;

import com.magician.book.utils.TokenUtil;
import com.magician.book.utils.UserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class InterceptorConfig implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(InterceptorConfig.class);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse response, Object o) throws Exception {
//        log.info("---------------------开始进入请求地址拦截----------------------------");

        String url = httpServletRequest.getRequestURI();

        String token = TokenUtil.getRequest("token");

        if(url.contains("/origin/writer")){
            if(token != null && !token.equals("")&& TokenUtil.getTokenUserType() == UserType.writer){
                return true;
            }
            try {
                response.sendRedirect("/bookread/origin/writer/login.html");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }else if(url.contains("/origin/admin")){

            if(token != null && !token.equals("")&& TokenUtil.getTokenUserType() == UserType.admin){
                return true;
            }
            try {
                response.sendRedirect("/bookread/origin/admin/login.html");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }else {
            return true;
        }

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
