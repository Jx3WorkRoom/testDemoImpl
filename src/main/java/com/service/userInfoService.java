package com.service;

import com.dao.accountListDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.userInfoDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class userInfoService {
    @Autowired
    userInfoDao userInfoDao;

    public Object queryUserInfo(int num) {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            resArr = userInfoDao.queryUserInfo(num);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
    }

    public Object queryPageListNum() {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            resArr = userInfoDao.queryPageListNum();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr.size();
    }

    public Object queryUserInfoByUserId(String userId) {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            resArr = userInfoDao.queryUserInfoByUserId(userId);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
    }

    public Object queryUserInfo1(String shape, int type, int num) {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            resArr = userInfoDao.queryUserInfo1(shape,type,num);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
    }

    public Object editLockTime(String userId, int hour) {
        int res = 0 ;
        try {
            res = userInfoDao.editLockTime(userId,hour);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(res==0){
            return "修改失败!";
        }else {
            return "修改成功!";
        }
    }
}
