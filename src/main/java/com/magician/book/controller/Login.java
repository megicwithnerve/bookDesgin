package com.magician.book.controller;

import com.magician.book.pojo.User;
import com.magician.book.services.AdminService;
import com.magician.book.services.UserService;
import com.magician.book.utils.APIResult;
import com.magician.book.utils.LoginToken;
import com.magician.book.utils.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

@Controller
public class Login {


    @PostMapping("/admin/login")
    public APIResult login(@RequestParam("userName") String userName,
                        @RequestParam("password") String password,
                        HttpSession session){

        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
            session.setAttribute("errorMsg", "用户名或密码不能为空");
            return new APIResult();
        }


        session.setAttribute("loginUser", "555555");
        session.setAttribute("loginUserId", 22);

        return new APIResult();
    }


    @PostMapping("/writer/login")
    public APIResult writerlogin(@RequestParam("userName") String userName,
                        @RequestParam("password") String password,
                        HttpSession session){
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
            session.setAttribute("errorMsg", "用户名或密码不能为空");
            return new APIResult();
        }
        String kaptchaCode = session.getAttribute("verifyCode") + "";
//        if (StringUtils.isEmpty(kaptchaCode) || !verifyCode.equals(kaptchaCode)) {
//            session.setAttribute("errorMsg", "验证码错误");
//            return "admin/login";
//        }



        return new APIResult();
    }

    @PostMapping("/reader/login")
    public APIResult readerlogin(@RequestParam("userName") String userName,
                                 @RequestParam("password") String password,
                                 HttpSession session){
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
            session.setAttribute("errorMsg", "用户名或密码不能为空");
            return new APIResult();
        }




        return new APIResult();
    }


    @Autowired
    AdminService adminService;

    @RequestMapping("/reader/loginout")
    @ResponseBody
    public APIResult loginout(){
        System.out.println("2255");
//        return "index.jsp";
        return new APIResult();
    }

    @RequestMapping("/writer/loginout")
    @ResponseBody
    public APIResult writerloginout(){
        System.out.println("2255");
//        return "index.jsp";
        return new APIResult();
    }
    @RequestMapping("/admin/loginout")
    @ResponseBody
    public APIResult adminloginout(){
        System.out.println("2255");
//        return "index.jsp";
        return new APIResult();
    }

    //重置密码
    /**
     * 获取重置密码的验证码
     * @param entity
     * @param request
     * @param response
     * @return
     */
    @Autowired
    UserService userService;

    @RequestMapping(value = "/reader/getCode", method = RequestMethod.POST)
    @ResponseBody
    public String readergetCode(String entity, HttpServletRequest request, HttpServletResponse response) throws MessagingException {
        return userService.getCode(null);
    }

    @RequestMapping(value = "/writer/getCode", method = RequestMethod.POST)
    @ResponseBody
    public String writergetCode(String entity, HttpServletRequest request, HttpServletResponse response) throws MessagingException {
        return userService.getCode(null);
    }


}
