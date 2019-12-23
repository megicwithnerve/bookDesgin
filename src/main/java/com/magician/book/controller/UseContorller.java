package com.magician.book.controller;

import com.magician.book.services.UserService;
import com.magician.book.utils.APIResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UseContorller {

    @Autowired
    UserService userService;

    @RequestMapping("/sendEmail")
    @ResponseBody
    public APIResult sendEmail(@RequestParam("email") String email){
        userService.sendEmail("5555");
        return null;
    }

    @RequestMapping("/getCode")
    @ResponseBody
    public APIResult getCode(){
        userService.getverifyCode("");
        return null;
    }
}

