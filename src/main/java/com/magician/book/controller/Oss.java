package com.magician.book.controller;

import com.aliyun.oss.OSSClient;
import com.magician.book.utils.APIResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Controller
public class Oss {

    @Value("${endpoint}")
    String endpoint;
    // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
    @Value("${accessKeyId}")
    String accessKeyId;
    @Value("${accessKeySecret}")
    String accessKeySecret;
    @Value("${bucketName}")
    String bucketName;

    OSSClient ossClient;
    Oss(){

    }

    //上传图片 文件
    @RequestMapping("/addp.do")
    @ResponseBody
    public APIResult addp(@RequestParam("file") MultipartFile  file,
                          @RequestParam("objectName") String objectName) throws IOException {//
        this.ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        ossClient.putObject(bucketName, "image/zsj/"+objectName,new ByteArrayInputStream(file.getBytes()));
        ossClient.shutdown();
        return new APIResult("上传成功",true,200);
    }

    @RequestMapping("/up.do")
    public String up(){
        return "pages/index.jsp";
    }
}
