package com.service;

import com.dao.accountListDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.dataAndSecurityDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class dataAndSecurityService {
    @Autowired
    dataAndSecurityDao dataAndSecurityDao;

    public Object getUserInfo(String userName) {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            resArr = dataAndSecurityDao.getUserInfo(userName);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
    }

    public Object editInfo(int type, String newValue,int userId) throws Exception {
        String key = "";
        int num = 0;
        if(type==1){
            key = "USER_NAME";
            num = dataAndSecurityDao.editUserInfo(userId,key,newValue);
        }else if(type==2){
            key = "USER_SEX";
            num= dataAndSecurityDao.editUserInfo(userId,key,newValue);
        }else if(type==3){
            key = "LOGIN_WORD";
            num= dataAndSecurityDao.editUserInfo(userId,key,newValue);
        }else if(type==4){
            key = "USER_TEL";
            num= dataAndSecurityDao.editUserInfo(userId,key,newValue);
        }else if(type==5){
            key = "USER_QQ";
            num= dataAndSecurityDao.editUserInfo(userId,key,newValue);
        }else if(type==6){
            key = "USER_BAR";
            num= dataAndSecurityDao.editUserInfo(userId,key,newValue);
        }else if(type==7){
            key = "USER_MAIL";
            num= dataAndSecurityDao.editUserInfo(userId,key,newValue);
        }else if(type==8){
            key = "USER_ZFB";
            num= dataAndSecurityDao.editUserInfo(userId,key,newValue);
        }else if(type==9){
            key = "USER_WEIXIN";
            num= dataAndSecurityDao.editUserInfo(userId,key,newValue);
        }else if(type==10){
            key = "USER_SFZ";
            num= dataAndSecurityDao.editUserInfo(userId,key,newValue);
        }
        if(num==0){
            return "修改失败!";
        }else{
            return "修改成功!";
        }
    }

    public Object userIsEmpty(String loginName, String tel) {
            try {
                String telphone = dataAndSecurityDao.userIsEmpty2(loginName);
                if(!telphone.equals(tel)){
                    return "不存在该手机号";
                }
            }catch (Exception e){
                return "不存在该手机号";
            }
        return "";
    }
}
