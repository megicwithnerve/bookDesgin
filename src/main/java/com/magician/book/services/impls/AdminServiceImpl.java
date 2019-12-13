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
        List<Admin> list = adminMapper.selectByEmailAndPwd(loginName,pwd);
        System.out.println(list.get(0));
        APIResult apiResult = new APIResult("ss",true,200,list);
//        apiResult.setData(list);
        return apiResult;
    }
}
