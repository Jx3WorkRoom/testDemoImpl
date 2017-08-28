package com.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.userManageDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class userManageService {
    @Autowired
    userManageDao userManageDao;

    public Object userManageInfo() {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            resArr = userManageDao.userManageInfo();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
    }
}
