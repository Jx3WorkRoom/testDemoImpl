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
                registerDao.addAuth(loginName);
            }
            return "注册成功";
        }
    }

    public Object checkIsEmpty(String loginName, String userName,String tel) {
        if(!"".equals(loginName)){
            try {
                registerDao.checkIsEmpty1(loginName);
                return "账号已存在!";
            }catch (Exception e) {
                return "";
            }
        }
        if(!"".equals(userName)){
            try {
                registerDao.checkIsEmpty2(userName);
                return "昵称已存在!";
            }catch (Exception e) {
                return "";
            }
        }
        if(!"".equals(tel)){
            try {
                registerDao.checkIsEmpty3(tel);
                return "此手机号已注册!";
            }catch (Exception e) {
                return "";
            }
        }
        return "";
    }
}
