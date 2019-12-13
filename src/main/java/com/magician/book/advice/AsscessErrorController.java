package com.magician.book.advice;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AsscessErrorController implements ErrorController {
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request){
        //获取statusCode:401,404,500
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if(statusCode == 500){
            return "error/500.html";
        }else if(statusCode == 404){
            //对应的是/error/404.html、/error/404.jsp等，文件位于/templates下面
            return "error/404.html";
        }else if(statusCode == 403){
            return "error/403.html";
        }else{
            return "error/500.html";
        }

    }


    @Override
    public String getErrorPath() {
        return "/error";
    }
}
