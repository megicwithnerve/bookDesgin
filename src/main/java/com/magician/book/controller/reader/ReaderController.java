package com.magician.book.controller.reader;

import com.magician.book.utils.Pager;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller()
@RequestMapping("reader")
public class ReaderController {

    @RequestMapping("/getComments")
    @ResponseBody
    public Pager getCommnets(Integer bookId, Integer indexpage){

        return new Pager(indexpage,10);
    }


}
