package com.magician.book.utils;

import com.auth0.jwt.JWT;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class TokenUtil {
    //id
    public static Integer getTokenUserId() {
        String token = getRequest("token");// 从 http 请求头中取出 token
        if(token == null || token.equals("")){
            return null;
        }
        String userId = JWT.decode(token).getAudience().get(0);
        return Integer.valueOf(userId);
    }
    //用户类型
    public static UserType getTokenUserType(){
        String token = getRequest("token");// 从 http 请求头中取出 token
//        System.out.println(JWT.decode(token).getAudience().size());
        String userType = null;
        if(token != null && !token.equals("")){
            userType = JWT.decode(token).getAudience().get(1);
        }
        if(userType==null){
            return UserType.no;
        }
        return UserType.valueOf(userType);
    }


    /**
     * 获取request 的 cookie值
     *
     * @return
     */
    public static String getRequest(String key) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                      .getRequestAttributes();
               if(requestAttributes == null)
                return null;
              HttpServletRequest request =  requestAttributes.getRequest();
        Cookie[] cookies = request.getCookies();
        if(cookies == null){
            return null;
        }
        for (Cookie c:cookies) {
            if(c.getName().equals(key)){
                return c.getValue();
            }
        }

        return null;
    }

}
