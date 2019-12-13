package com.magician.book.utils;

import com.magician.book.pojo.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;


public class SignVerification {
    ServletRequestAttributes attributes =   (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = attributes.getRequest();
    HttpServletResponse response = attributes.getResponse();

    HttpSession session= request.getSession();
    public boolean Verification(){
        return true;
//        System.out.println("拦截器preHandle方法");
//        LoginToken token = new LoginToken();
//        User user = new User();
//        if(request.getCookies() != null) {
//            for (Cookie cookie : request.getCookies()) {
//                if (cookie.getName().equals("tokenType")) {
//                    System.out.println(cookie.getValue());
//                    if(cookie.getValue().equals(UserType.reader.toString())){
//                        token.setUserType(UserType.reader);
//                    }
//                }else if(cookie.getName().equals("tokenTime")){
//                    token.setDate(Long.valueOf(cookie.getValue()));
//                }
//
//
//            }
//        }
//        System.out.println(token);
//        if(token.getUserType() != null && token.getUserType() == UserType.reader){
//            Date d = new Date();
//            System.out.println(d.getTime());
//            if(token.getDate() > d.getTime()){
//                Long time = d.getTime()+10000;//更新时间
//                Cookie cookieTime = new Cookie("tokenTime",time.toString());
//                cookieTime.setMaxAge(60*60*60);
//                cookieTime.setPath(request.getContextPath());
//                response.addCookie(cookieTime);
//                return true;
//            }
      //  }

       // return false;
  }

}
