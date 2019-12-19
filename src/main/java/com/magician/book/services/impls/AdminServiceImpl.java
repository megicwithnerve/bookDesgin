package com.magician.book.services.impls;

import com.magician.book.dao.AdminMapper;
import com.magician.book.pojo.Admin;
import com.magician.book.services.AdminService;
import com.magician.book.utils.APIResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    AdminMapper adminMapper;
    @Override
    public APIResult queryByPwd(String loginName, String pwd) {
        Long id = 1L;
        APIResult apiResult = null;
        List<Admin> list = adminMapper.selectByEmailAndPwd(loginName,pwd);
        if(list.size() == 0) {
            apiResult = new APIResult("账号或密码错误", false, 200, null);
        }else {
            Admin admin = list.get(0);
            apiResult = new APIResult("账号密码正确",true,200,admin);
        }

        return apiResult;
    }
}
