package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.registerDao;
@Service
public class registerService {
    @Autowired
    registerDao registerDao;

    public Object addUser(String loginName, String userName, String loginWord, String tel) throws Exception {
        int resultNum = 0;
        resultNum =registerDao.isHas(loginName);
        if(resultNum>0){
            return "用户已经存在";
        }else {
            resultNum = registerDao.addUser(loginName, userName, loginWord, tel);
            if (resultNum > 0) {
                registerDao.addUser2(loginName, loginWord);
            }
            return "注册成功";
        }
    }
}
