package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.alarmlogDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/27 0027.
 */
@Service
public class alarmlogService {
    @Autowired
    alarmlogDao alarmlogDao;

    public Object queryalarmlog(String[] ids) {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            resArr = alarmlogDao.queryalarmlog(ids);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
    }

    public int editalarmlog(int id,int alarmLogID, int alarmType, String alarmText, String occurrenceTime) throws Exception {
        int resArr = alarmlogDao.editalarmlog(id,alarmLogID,alarmType,alarmText,occurrenceTime);
        return resArr;
    }

    public int addalarmlog(int alarmLogID, int alarmType, String alarmText, String occurrenceTime) throws Exception {
        int resArr = alarmlogDao.addalarmlog(alarmLogID,alarmType,alarmText,occurrenceTime);
        return resArr;
    }

    public int delalarmlog(String[] ids) throws Exception {
        int resArr = alarmlogDao.delalarmlog(ids);
        return resArr;
    }
}
