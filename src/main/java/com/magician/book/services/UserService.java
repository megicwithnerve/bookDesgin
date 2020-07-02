package com.magician.book.services;

import com.alibaba.druid.wall.violation.ErrorCode;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.github.pagehelper.util.StringUtil;
import com.magician.book.dao.BookMapper;
import com.magician.book.dao.ReaderMapper;
import com.magician.book.dao.WriterMapper;
import com.magician.book.pojo.Book;
import com.magician.book.pojo.EmailEntity;
import com.magician.book.pojo.Reader;
import com.magician.book.pojo.Writer;
import com.magician.book.utils.*;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
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
     * @param
     * @return
     */
    public APIResult getCode(String email) {


        return  null;
    }


    @Resource
    private RedisUtil redisUtil;
    @Resource
    WriterMapper writerMapper;
    @Resource
    ReaderMapper readerMapper;
    @Resource
    BookMapper bookMapper;

    public APIResult sendEmail(String email,Integer type){
        APIResult apiResult = null;
        List list = null;
        //验证邮箱是否存在
        if(type == 0){
           list = readerMapper.getByemail(email);
        }else {
            list = writerMapper.getByemail(email);
        }

        if(list.size() == 0){
           return new APIResult("邮箱不存在",false,200);
        }


        //生成验证码
        String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);//生成短信验证码
        Timestamp outDate = new Timestamp(System.currentTimeMillis() + 5 * 60 * 1000);// 5分钟后过期

        EmailEntity emailEntity = new EmailEntity();
        //将验证码存入redis
        emailEntity.setAcount(verifyCode);
        emailEntity.setTime(outDate);
        emailEntity.setEmail("");
        //设置过期时间
        redisUtil.set(email,emailEntity);
        redisUtil.expire(email,5*60);
        //发送邮箱
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<html><head><title></title></head><body>");
        stringBuilder.append("您好<br/>");
        stringBuilder.append("您的验证码是：").append(verifyCode).append("<br/>");
        stringBuilder.append("您可以复制此验证码，以验证您的邮箱。<br/>");
        stringBuilder.append("此验证码只能使用一次，在5分钟内有效。验证成功则自动失效。<br/>");
        stringBuilder.append("如果您没有进行上述操作，请忽略此邮件。");
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        //发送验证码到手机或者是邮箱
//        if (true){ //邮箱
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
            mimeMessageHelper.setFrom(mailUserName);//这里只是设置username 并没有设置host和password，因为host和password在springboot启动创建JavaMailSender实例的时候已经读取了
            mimeMessageHelper.setTo(email);
            mimeMessage.setSubject("邮箱验证");
            mimeMessageHelper.setText(stringBuilder.toString(),true);
            mailSender.send(mimeMessage);
        }catch (Exception e){
            e.printStackTrace();
        }
        apiResult = new APIResult("验证码发送成功，请在邮箱查看",true,200);
        return apiResult;
    }

    public APIResult resetPassword(String email, Integer type, String code,String password){
        APIResult apiResult = null;
        EmailEntity emailEntity = (EmailEntity)redisUtil.get(email);
        if(emailEntity == null){
            return new APIResult("请重新发送验证码",false,500);
        }

        if(!code.equals(emailEntity.getAcount()) ){
            return new APIResult("验证码错误",false,500);
        }

        password = MD5Utils.StringInMd5(password);
        Integer result;
        if(type == 0){
            Reader reader = readerMapper.getByemail(email).get(0);
            reader.setPassword(password);
            result = readerMapper.updateByPrimaryKeySelective(reader);
        }else {
            Writer writer = writerMapper.getByemail(email).get(0);
            writer.setPassword(password);
            result = writerMapper.updateByPrimaryKeySelective(writer);
        }

        if (result == 0){
            apiResult = new APIResult("无法重置密码，请再试一次",false,500);
        }else {
            apiResult = new APIResult("成功重置密码",true,200);
        }

        return apiResult;
    }
    public APIResult registerSendEmail(String email,Integer type){
        APIResult apiResult = null;
        List list = null;
        //验证邮箱是否存在
        if(type == 0){
            list = readerMapper.getByemail(email);
        }else {
            list = writerMapper.getByemail(email);
        }

        if(list.size() != 0){
            return new APIResult("邮箱已存在",false,200);
        }


        //生成验证码
        String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);//生成短信验证码
        Timestamp outDate = new Timestamp(System.currentTimeMillis() + 5 * 60 * 1000);// 5分钟后过期

        EmailEntity emailEntity = new EmailEntity();
        //将验证码存入redis
        emailEntity.setAcount(verifyCode);
        emailEntity.setTime(outDate);
        emailEntity.setEmail("");
        //设置过期时间
        redisUtil.set(email,emailEntity);
        redisUtil.expire(email,5*60);
        //发送邮箱
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<html><head><title></title></head><body>");
        stringBuilder.append("您好<br/>");
        stringBuilder.append("您的验证码是：").append(verifyCode).append("<br/>");
        stringBuilder.append("您可以复制此验证码，以验证您的邮箱。<br/>");
        stringBuilder.append("此验证码只能使用一次，在5分钟内有效。验证成功则自动失效。<br/>");
        stringBuilder.append("如果您没有进行上述操作，请忽略此邮件。");
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        //发送验证码到手机或者是邮箱
//        if (true){ //邮箱
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
            mimeMessageHelper.setFrom(mailUserName);//这里只是设置username 并没有设置host和password，因为host和password在springboot启动创建JavaMailSender实例的时候已经读取了
            mimeMessageHelper.setTo(email);
            mimeMessage.setSubject("邮箱验证");
            mimeMessageHelper.setText(stringBuilder.toString(),true);
            mailSender.send(mimeMessage);
        }catch (Exception e){
            e.printStackTrace();
        }
        apiResult = new APIResult("验证码发送成功，请在邮箱查看",true,200);
        return apiResult;
    }



    //访问量是否增加
    public void addclicks(HttpServletRequest request, Integer bookid){

        String ip = IpUtil.getIpAddr(request);
        String str = ip+"book"+bookid;
        if(!redisUtil.hasKey(str)){
            redisUtil.set(str,"点击书籍");
            redisUtil.expire(str,24*60*60);
            Book book = bookMapper.selectByPrimaryKey(bookid);
            book.setClicks(book.getClicks()+1);
            bookMapper.updateByPrimaryKeySelective(book);
        }

    }

    public String getToken(LoginToken loginToken) {
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 24 * 60 * 60 * 1000;//24小时有效时间
        Date end = new Date(currentTime);
        String token = "";

        token = JWT.create().withAudience(loginToken.getObjid().toString(),loginToken.getUserType().toString()).withIssuedAt(start).withExpiresAt(end)
                .sign(Algorithm.HMAC256(loginToken.getObjPassworld()));
        return token;
    }
}
