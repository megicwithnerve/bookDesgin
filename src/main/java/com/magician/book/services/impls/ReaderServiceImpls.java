package com.magician.book.services.impls;

import com.magician.book.dao.ReaderMapper;
import com.magician.book.pojo.EmailEntity;
import com.magician.book.pojo.Reader;
import com.magician.book.services.ReaderService;
import com.magician.book.utils.APIResult;
import com.magician.book.utils.MD5Utils;
import com.magician.book.utils.Pager;
import com.magician.book.utils.RedisUtil;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class ReaderServiceImpls implements ReaderService {

    @Resource
    ReaderMapper readerMapper;
    @Resource
    private RedisUtil redisUtil;
    @Override
    public APIResult ReaderResgiter(Reader reader,String code) {
        APIResult apiResult;

        EmailEntity emailEntity = (EmailEntity)redisUtil.get(reader.getEmail());
        if(emailEntity == null){
            return new APIResult("请重新发送验证码",false,500);
        }

        if(!code.equals(emailEntity.getAcount()) ){
            return new APIResult("验证码错误",false,500);
        }

        List<Reader> list = readerMapper.getByemail(reader.getEmail());
        if(list.size() != 0){
            apiResult = new APIResult("邮箱重复",false,500,null);
            return apiResult;
        }


        reader.setBalance(0);
        String password = MD5Utils.StringInMd5(reader.getPassword());
        reader.setPassword(password);
        reader.setReaderStatus(1);
        Integer result = readerMapper.insertSelective(reader);
        if(result == 0){
            apiResult = new APIResult("注册失败",false,500,null);
        }else {
            apiResult = new APIResult("注册成功",true,200,null);
        }

        return apiResult;
    }

    @Override
    public APIResult updateReader(Reader reader) {
        APIResult apiResult = null;
        Integer result = readerMapper.updateByPrimaryKeySelective(reader);
        if(result == 0){
            apiResult = new APIResult("更新失败",false,500,null);
        }else {
            apiResult = new APIResult("更新成功",true,200,null);
        }
        return apiResult;
    }

    @Override
    public APIResult queryByPwd(String email, String pwd, HttpSession session) {
        APIResult apiResult = null;
        pwd = MD5Utils.StringInMd5(pwd);
        Reader result = readerMapper.getByEmailPwd(email,pwd);
        if(result == null ){
            apiResult = new APIResult("账号或密码错误",false,500,null);
        }else {
            if(result.getReaderStatus() == 1){
                apiResult = new APIResult("登录成功",true,200,result);
                session.setAttribute("readerId",result.getReaderId());

            }else {
                apiResult = new APIResult("账号被封禁",false,500,result);

            }
        }
        return apiResult;
    }

    @Override
    public APIResult queryByEmail(String email) {
        return null;
    }

    @Override
    public Pager searchReader(String searchName, Integer status, Integer indexpage, Integer pagesize) {
        if(searchName.equals("")){
            searchName = null;
        }else {
            searchName += "%";
        }
        List<Reader> list = readerMapper.searchReader(searchName,status,null,null);
        Pager pager = new Pager(list.size(),indexpage,pagesize);
        List<Reader> readers = readerMapper.searchReader(searchName,status,pager.getBeginrows(),pagesize);

        pager.setData(readers);

        return pager;
    }

    @Override
    public APIResult getReaderById(Integer readerId) {

        Reader reader = readerMapper.selectByPrimaryKey(readerId);
        APIResult apiResult = null;
        if(reader == null){
            apiResult = new APIResult("获取不到读者",false,500,null);
        }else {
            apiResult = new APIResult("获取成功",true,200,reader);
        }
        return apiResult;
    }

    @Override
    public APIResult updatePwd(String oldpwd, String newpwd, String repwd, Integer readerId) {
        APIResult apiResult = null;
        if(!newpwd.equals(repwd)){
            return new APIResult("新密码与重复密码不正确！",false,500,null);
        }
        oldpwd = MD5Utils.StringInMd5(oldpwd);
        newpwd = MD5Utils.StringInMd5(newpwd);
        Reader reader = readerMapper.selectByPrimaryKey(readerId);
        if(reader == null){
            apiResult = new APIResult("用户不存在,请重新登录",false,500,null);
        }else if(!reader.getPassword().equals(oldpwd)){
            apiResult = new APIResult("原密码错误",false,500,null);
        }else{
            reader.setPassword(newpwd);
            Integer result = readerMapper.updateByPrimaryKeySelective(reader);
            if(result == 0){
                apiResult = new APIResult("密码不符合规范",false,500,null);
            }else {
                apiResult = new APIResult("修改成功",true,200,null);
            }
        }
        return apiResult;
    }
}
