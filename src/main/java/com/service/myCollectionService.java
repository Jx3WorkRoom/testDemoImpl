package com.service;

import com.dao.dataAndSecurityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.myCollectionDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class myCollectionService {
    @Autowired
    myCollectionDao myCollectionDao;


    public Object myCollectionInfo(String userName, int num) {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            resArr = myCollectionDao.myCollectionInfo(userName,num);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
    }

    public Object queryPageListNum() {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            resArr = myCollectionDao.queryPageListNum();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr.size();
    }

    public Object removeRecord(String[] ids) {
        int resArr = 0;
        try {
            resArr = myCollectionDao.removeRecord(ids);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(resArr==0){
            return "删除异常!";
        }else {
            return "删除成功!";
        }
    }
}
