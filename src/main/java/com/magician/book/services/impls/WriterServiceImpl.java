package com.magician.book.services.impls;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.magician.book.dao.AdminMapper;
import com.magician.book.dao.BookMapper;
import com.magician.book.dao.WriterMapper;
import com.magician.book.pojo.Admin;
import com.magician.book.pojo.Book;
import com.magician.book.pojo.Writer;
import com.magician.book.services.WriterService;
import com.magician.book.utils.APIResult;
import com.magician.book.utils.MD5Utils;
import com.magician.book.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class WriterServiceImpl implements WriterService {

    @Resource
    BookMapper bookMapper;

    @Resource
    WriterMapper writerMapper;


    //根据id查找作者
    @Override
    public APIResult getWriterInfoById(Integer writerId) {
        APIResult apiResult = null;

        Writer writer = writerMapper.selectByPrimaryKey(writerId);
        if(writer == null){
            apiResult = new APIResult("查找失败",false,200,null);
        }else {
            apiResult = new APIResult("查找成功",true,200,writer);
        }
        return apiResult;
    }

    //更新作者信息
    @Override
    public APIResult updateWriterInfo(Writer writer) {
        APIResult apiResult = null;

        Integer result = writerMapper.updateByPrimaryKeySelective(writer);
        System.out.println(result);
        if(writer == null){
            apiResult = new APIResult("更新失败",false,200,null);
        }else {
            apiResult = new APIResult("更新成功",true,200,writer);
        }
        return apiResult;
    }

    //作者登录
    @Override
    public APIResult getWriterBypwd(String email, String pwd, HttpSession session) {
        APIResult apiResult = null;
        pwd = MD5Utils.StringInMd5(pwd);
        Writer writer = null;
        writer = writerMapper.getByEmailPwd(email,pwd);


        if(writer == null){
            apiResult = new APIResult("账号密码错误",false,500,null);
        }else {
            if(writer.getWriterStatus() == 1){
                apiResult = new APIResult("登录成功",true,200,writer);
                session.setAttribute("writerId",writer.getWriterId());
            }else if(writer.getWriterStatus() == 0){
                apiResult = new APIResult("账号待审核",false,500,writer);
            }else {
                apiResult = new APIResult("账号已封禁，请联系管理员",false,500,writer);

            }

        }
        return apiResult;
    }

    //作者注册
    @Override
    public APIResult insertWriter(Writer writer) {
        APIResult apiResult = null;

        List list = writerMapper.getByemail(writer.getEmail());
        if(list.size() != 0){
            return new APIResult("邮箱重复",false,200,null);
        }
        String password = MD5Utils.StringInMd5(writer.getPassword());
        writer.setPassword(password);
        writer.setProfit(0);
        writer.setWriterStatus(0);
        Integer result = writerMapper.insertSelective(writer);

        if(result == 0){
            apiResult = new APIResult("注册失败",false,200,null);
        }else {
            apiResult = new APIResult("注册成功,请等待审核",true,200,null);
        }
        return apiResult;
    }

    @Override
    public APIResult onlyOneWriter(String email) {
        return null;
    }

    //作者列表
    @Override
    public Pager searchWriter(Integer indexpage, Integer pagesize, String writerName, Integer status) {



        if(writerName.equals("")){
            writerName=null;
        }else {
            writerName+="%";
        }

        List<Writer> list =  writerMapper.searchWriter(writerName,status,null,null);
        Pager pager = new Pager(list.size(),indexpage,pagesize);
        List<Writer> data = writerMapper.searchWriter(writerName,status,pager.getBeginrows(),pagesize);
        pager.setData(data);
        return pager;
    }

    @Override
    public Pager getWriterMessage(Integer writerId) {
        return null;
    }

    @Override
    public APIResult updatePwd(String oldpwd, String newpwd, String repwd, Integer writerId) {
        APIResult apiResult = null;
        if(!newpwd.equals(repwd)){
            return new APIResult("新密码与重复密码不正确！",false,500,null);
        }
        oldpwd = MD5Utils.StringInMd5(oldpwd);
        newpwd = MD5Utils.StringInMd5(newpwd);
        Writer writer = writerMapper.selectByPrimaryKey(writerId);
        if(writer == null){
            apiResult = new APIResult("用户不存在,请重新登录",false,500,null);
        }else if(!writer.getPassword().equals(oldpwd)){
            apiResult = new APIResult("原密码错误",false,500,null);
        }else{
            writer.setPassword(newpwd);
            Integer result = writerMapper.updateByPrimaryKeySelective(writer);
            if(result == 0){
                apiResult = new APIResult("密码不符合规范",false,500,null);
            }else {
                apiResult = new APIResult("修改成功",true,200,null);
            }
        }
        return apiResult;
    }
}
