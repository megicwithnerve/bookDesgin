package com.magician.book.advice;



import com.magician.book.utils.APIResult;
import com.magician.book.utils.WebCode;
import com.magician.book.utils.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常捕获处理
 */
@RestControllerAdvice
public class ExceptionControllerAdvice {
    private static final Logger logger= LoggerFactory.getLogger(ExceptionControllerAdvice.class);



    // 捕捉shiro的异常
//    @ExceptionHandler(ShiroException.class)
    public APIResult handle401() {

        return new APIResult("您没有权限访问",false,401);
    }


    // 捕捉其他所有异常
    @ExceptionHandler(Exception.class)
    public Object globalException(HttpServletRequest request, HandlerMethod handlerMethod, Throwable ex) {
        if(WebUtils.isAjax(handlerMethod)){
            return new APIResult("访问出错，无法访问: " + ex.getMessage(),false, getStatus(request).value());

        }else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("/error/500.html"); //这里需要在templates文件夹下新建一个/error/500.html文件用作错误页面
            modelAndView.addObject("errorMsg",ex.getMessage());
            return modelAndView;
        }

    }

    /**
     * 判断是否是Ajax请求
     *
     * @param request
     * @return
     */
    public boolean isAjax(HttpServletRequest request) {
        return (request.getHeader("X-Requested-With") != null &&
                "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString()));
    }



    /**
     * 获取响应状态码
     * @param request
     * @return
     */
    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }

    /**
     * 捕捉404异常,这个方法只在配置
     * spring.mvc.throw-exception-if-no-handler-found=true来后起作用
     *
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public APIResult handle(HttpServletRequest request, NoHandlerFoundException e) {

        return new APIResult("没有【"+request.getMethod()+"】"+request.getRequestURI()+"方法可以访问",false, WebCode.RESOURCES_NOT_FOUND);
    }
}
