package com.service;

import com.dao.userInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.myServiceDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class myServiceService {
    @Autowired
    myServiceDao myServiceDao;

    public Object queryServiceInfoByUserId(String username) {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            resArr = myServiceDao.queryServiceInfoByUserId(username);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
    }

    public Object queryServiceInfoByUserId2(String username, int num) {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            resArr = myServiceDao.queryServiceInfoByUserId2(username,num);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
    }

    public Object queryServiceInfoByUserId3(String username, int num) {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            resArr = myServiceDao.queryServiceInfoByUserId3(username,num);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
    }

    public Object queryPageListNum() {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            resArr = myServiceDao.queryPageListNum();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr.size();
    }
}
