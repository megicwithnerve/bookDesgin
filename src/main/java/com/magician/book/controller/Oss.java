package com.magician.book.controller;

import com.aliyun.oss.OSSClient;
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
    public String addp(@RequestParam("file") MultipartFile file,
                       @RequestParam("objectName") String objectName) throws IOException {//
        System.out.println(endpoint);
        this.ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        // String objectName="1.jpg";
        //String content = "bookroom";
        //System.out.printf(objectName);
        //System.out.printf(map);
        // ByteArrayInputStream c = content.

//        System.out.printf("vvvv");
        System.out.printf(file.getName());

        ossClient.putObject(bucketName, "image/zsj/"+objectName,new ByteArrayInputStream(file.getBytes()));
        ossClient.shutdown();
        System.out.println();
        return "上传成功";
    }

    @RequestMapping("/up.do")
    public String up(){
        return "pages/index.jsp";
    }
}
