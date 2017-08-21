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

    //我要举报
    public String saveWyjbInfo(int cheatType, String belongQf, String tixin, String roleName, String cheatIntro, String cheatInfo, String pageUrl) {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            int insertResult = iwantReleaseDao.saveWyjbInfo(cheatType,belongQf,tixin,roleName,cheatIntro,cheatInfo,pageUrl);
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
    //外观交易
    public String saveWgjyInfo(int tradeType, String belongQf, String viewName, int priceNum, String favorInfo) {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            int insertResult = iwantReleaseDao.saveWgjyInfo(tradeType,belongQf,viewName,priceNum,favorInfo);
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
    //道具交易
    public String saveDjjyInfo(int tradeType, String belongQf, String propName, int priceNum, String favorInfo) {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            int insertResult = iwantReleaseDao.saveWgjyInfo(tradeType, belongQf, propName, priceNum, favorInfo);
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
    //金币交易
    public String saveJbjyInfo(int tradeType, String belongQf, int goldTotal, int unitPrice, int ifSploit, String favorInfo){
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            int insertResult = iwantReleaseDao.saveJbjyInfo(tradeType, belongQf, goldTotal, unitPrice, ifSploit, favorInfo);
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

    //代练代打
    public String saveDlddInfo(int cheatType, String belongQf, String favorInfo){
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            int insertResult = iwantReleaseDao.saveDlddInfo(cheatType, belongQf, String favorInfo);
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
