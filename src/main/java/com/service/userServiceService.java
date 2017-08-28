package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.userServiceDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class userServiceService {

    @Autowired
    userServiceDao userServiceDao;

    public Object getServiceDetail(String searchInfo, int searchType) {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            resArr = userServiceDao.getServiceDetail(searchInfo,searchType);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
    }

    public Object getServiceDetail2(String searchInfo, int searchType, int num) {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            resArr = userServiceDao.getServiceDetail2(searchInfo,searchType,num);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
    }

    public Object getServiceDetail3(String searchInfo, int searchType, int num) {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            resArr = userServiceDao.getServiceDetail3(searchInfo,searchType,num);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
    }

    public Object queryPageListNum() {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            resArr = userServiceDao.queryPageListNum();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr.size();
    }
}
