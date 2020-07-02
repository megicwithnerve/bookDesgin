package com.magician.book.controller;

import com.magician.book.pojo.Reader;
import com.magician.book.pojo.Writer;
import com.magician.book.services.ReaderService;
import com.magician.book.services.UserService;
import com.magician.book.services.WriterService;
import com.magician.book.utils.APIResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;

@Controller
public class RegisterController {

    @Autowired
    ReaderService readerService;

    @Autowired
    WriterService writerService;

    @Autowired
    UserService userService;

    @RequestMapping("/register/reader")
    @ResponseBody
    public APIResult readerRegister(Reader reader,String code){

        return readerService.ReaderResgiter(reader,code);
    }

    @RequestMapping("/register/writer")
    @ResponseBody
    public APIResult writerRegister(Writer writer,String code){

        return writerService.insertWriter(writer);
    }

    @RequestMapping(value = "/register/getCode/reader", method = RequestMethod.POST)
    @ResponseBody
    public APIResult readergetCode(String email) throws MessagingException {
        return userService.registerSendEmail(email,0);
    }

    @RequestMapping(value = "/register/getCode/writer", method = RequestMethod.POST)
    @ResponseBody
    public APIResult writergetCode(String email) throws MessagingException {
        return userService.registerSendEmail(email,1);
    }


}
