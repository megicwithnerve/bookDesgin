package com.magician.book.services;

import com.alibaba.druid.wall.violation.ErrorCode;
import com.github.pagehelper.util.StringUtil;
import com.sun.jmx.snmp.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Map;
import java.util.Random;

@Service
public class UserService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String mailUserName;
    /**
     * 获取重置密码需要的验证码
     * @param map
     * @return
     */
    public String getCode(Map<String, Object> map) {

        String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);//生成短信验证码
        Timestamp outDate = new Timestamp(System.currentTimeMillis() + 5 * 60 * 1000);// 5分钟后过期
        //将验证码 和 过期时间更新到数据库

        System.out.println(verifyCode);
        System.out.println(outDate);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<html><head><title></title></head><body>");
        stringBuilder.append("您好<br/>");
        stringBuilder.append("您的验证码是：").append(verifyCode).append("<br/>");
        stringBuilder.append("您可以复制此验证码并返回至XXX，以验证您的邮箱。<br/>");
        stringBuilder.append("此验证码只能使用一次，在5分钟内有效。验证成功则自动失效。<br/>");
        stringBuilder.append("如果您没有进行上述操作，请忽略此邮件。");
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        //发送验证码到手机或者是邮箱
//        if (true){ //邮箱
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
            mimeMessageHelper.setFrom(mailUserName);//这里只是设置username 并没有设置host和password，因为host和password在springboot启动创建JavaMailSender实例的时候已经读取了
            mimeMessageHelper.setTo("1442554245@qq.com");
            mimeMessage.setSubject("邮箱验证-XXX");
            mimeMessageHelper.setText(stringBuilder.toString(),true);
            System.out.println("111111");
            mailSender.send(mimeMessage);
        }catch (Exception e){
            e.printStackTrace();
        }

//        }
        return  "发送成功";
    }

}
