package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.IndexDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class IndexService {
    @Autowired
    IndexDao indexDao;

    public Object queryIndexInfo(int tradeType) {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            resArr = indexDao.queryIndex(tradeType);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
    }
}
