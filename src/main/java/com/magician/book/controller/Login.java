package com.magician.book.controller;

import com.magician.book.pojo.Admin;
import com.magician.book.pojo.Reader;
import com.magician.book.pojo.User;
import com.magician.book.pojo.Writer;
import com.magician.book.services.AdminService;
import com.magician.book.services.ReaderService;
import com.magician.book.services.UserService;
import com.magician.book.services.WriterService;
import com.magician.book.utils.APIResult;
import com.magician.book.utils.LoginToken;
import com.magician.book.utils.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
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

    @Autowired
    WriterService writerService;

    @Autowired
    AdminService adminService;

    @Autowired
    ReaderService readerService;

    @Autowired
    UserService userService;

    @RequestMapping("login/admin")
    @ResponseBody
    public APIResult login(@RequestParam("loginName") String loginName,
                        @RequestParam("password") String password,
                        HttpSession session,
                        HttpServletResponse response){

        APIResult result = adminService.queryByPwd(loginName,password);
        if(result.isResult()){
            Admin admin = (Admin) result.getData();
            LoginToken loginToken = new LoginToken();
            loginToken.setUserType(UserType.admin);
            loginToken.setObjid(admin.getAdminId());
            loginToken.setObjPassworld(admin.getLoginPassword());
            String token = userService.getToken(loginToken);


            Cookie cookie = new Cookie("token", token);
            cookie.setPath("/");
            response.addCookie(cookie);
        }

        return result;
    }


    @RequestMapping("/login/writer")
    @ResponseBody
    public APIResult writerlogin(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        HttpSession session,
                        HttpServletResponse response){

        APIResult result = writerService.getWriterBypwd(email,password,session);
        if(result.isResult()){
            Writer writer = (Writer) result.getData();
            LoginToken loginToken = new LoginToken();
            loginToken.setUserType(UserType.writer);
            loginToken.setObjid(Long.valueOf(writer.getWriterId()));
            loginToken.setObjPassworld(writer.getPassword());
            String token = userService.getToken(loginToken);


            Cookie cookie = new Cookie("token", token);
            cookie.setPath("/");
            response.addCookie(cookie);
        }

        return result;
    }

    @RequestMapping("/login/reader")
    @ResponseBody
    public APIResult readerlogin(@RequestParam("email") String email,
                                 @RequestParam("password") String password,
                                 HttpSession session,
                                 HttpServletResponse response){
        APIResult result = readerService.queryByPwd(email,password,session);
        if(result.isResult()){
            Reader reader = (Reader) result.getData();
            LoginToken loginToken = new LoginToken();
            loginToken.setUserType(UserType.reader);
            loginToken.setObjid(Long.valueOf(reader.getReaderId()));
            loginToken.setObjPassworld(reader.getPassword());
            String token = userService.getToken(loginToken);

            System.out.println(token);
            Cookie cookie = new Cookie("token", token);
            cookie.setPath("/");
            response.addCookie(cookie);
        }

        return result;
    }




    @RequestMapping("/loginout/reader")
    public String loginout(HttpServletResponse response){
        Cookie cookie = new Cookie("token",null);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "redirect:/reader/login";
    }

    @RequestMapping("/loginout/writer")
    public String writerloginout(HttpServletResponse response){
        Cookie cookie = new Cookie("token",null);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "redirect:/origin/writer/login.html";
    }
    @RequestMapping("/loginout/admin")
    public String adminloginout(HttpServletResponse response){
        Cookie cookie = new Cookie("token",null);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "redirect:/origin/admin/login.html";
    }

    //重置密码
    /**
     * 获取重置密码的验证码
     * @param entity
     * @param request
     * @param response
     * @return
     */


    @RequestMapping(value = "/getCode/reader", method = RequestMethod.POST)
    @ResponseBody
    public APIResult readergetCode(String email) throws MessagingException {
        System.out.println(email);
        return userService.sendEmail(email,0);
    }

    @RequestMapping(value = "/getCode/writer", method = RequestMethod.POST)
    @ResponseBody
    public APIResult writergetCode(String email) throws MessagingException {
        return userService.sendEmail(email,1);
    }

    @RequestMapping(value = "/reset/reader", method = RequestMethod.POST)
    @ResponseBody
    public APIResult readerReset(String email,String code,String password) throws MessagingException {
        return userService.resetPassword(email,0,code,password);
    }

    @RequestMapping(value = "/reset/writer", method = RequestMethod.POST)
    @ResponseBody
    public APIResult writerReset(String email,String code) throws MessagingException {
        return userService.sendEmail(email,0);
    }


}
