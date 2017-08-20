package com.service;

import com.dao.IwantReleaseDao;
import com.dao.accountListDao;
import com.utils.MyDateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IwantReleaseService {

    @Autowired
    IwantReleaseDao iwantReleaseDao;

    public String saveInfo(int cheatType, String belongQf, String tixin, String roleName, String cheatIntro, String cheatInfo, String pageUrl) {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            int insertResult = iwantReleaseDao.saveInfo(cheatType,belongQf,tixin,roleName,cheatIntro,cheatInfo,pageUrl);
            if(insertResult==1) {
                return "保存成功!";
            }else{
                return "保存失败!";
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "保存失败!";
    }

}
