package com.magician.book.controller;

import com.magician.book.pojo.Reader;
import com.magician.book.pojo.Writer;
import com.magician.book.utils.APIResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegisterController {
    @RequestMapping("/reader/register")
    public APIResult readerregister(Reader reader){




        return new APIResult();
    }

    @RequestMapping("/writer/register")
    public APIResult writerregister(Writer writer){

        return new APIResult();
    }


}
