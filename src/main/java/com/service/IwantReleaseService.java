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
            int insertResult = iwantReleaseDao.saveWyjbInfo(cheatType,belongQf,tixin,roleName,cheatIntro,cheatInfo,pageUrl);    //D_post_bar_11
            int insertResult2 = iwantReleaseDao.saveHbbgfo(cheatType,belongQf,tixin,roleName,cheatIntro,cheatInfo,pageUrl);     //C_POST_BAR_11
            if(insertResult==1 && insertResult2==1) {
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
            int insertResult = iwantReleaseDao.saveWgjyInfo(tradeType,belongQf,viewName,priceNum,favorInfo);    //D_POST_BAR_16
            int insertResult2 = iwantReleaseDao.saveWgjyxxInfo(tradeType,belongQf,viewName,priceNum,favorInfo);    //C_POST_BAR_13
            if(insertResult==1 && insertResult2==1) {
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
            int insertResult = iwantReleaseDao.saveDjjyInfo(tradeType, belongQf, propName, priceNum, favorInfo);    //D_POST_BAR_18
            int insertResult2 = iwantReleaseDao.saveDjjyxxInfo(tradeType, belongQf, propName, priceNum, favorInfo);    //C_POST_BAR_15
            if(insertResult==1 && insertResult2==1) {
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
    public String saveJbjyInfo(int tradeType, String belongQf, int goldTotal, int unitPrice, int ifSplit, String favorInfo){
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            int insertResult = iwantReleaseDao.saveJbjyInfo(tradeType, belongQf, goldTotal, unitPrice, ifSplit, favorInfo);     //D_POST_BAR_19
            int insertResult2 = iwantReleaseDao.saveYxjbjyInfo(tradeType, belongQf, goldTotal, unitPrice, ifSplit, favorInfo);     //C_POST_BAR_19
            if(insertResult==1 && insertResult2==1) {
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
            int insertResult = iwantReleaseDao.saveDlddInfo(cheatType, belongQf,  favorInfo);   //D_POST_BAR_20
            int insertResult2 = iwantReleaseDao.saveYxddxxInfo(cheatType, belongQf,  favorInfo);   //C_POST_BAR_17
            if(insertResult==1 && insertResult2==1) {
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

    //账号快售快速发布
    public String saveZhssInfo(String belongQf,String tixin,int priceNum,String accoInfo){
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            int insertResult = iwantReleaseDao.saveZhssInfo(belongQf,tixin,priceNum,accoInfo);  //D_POST_BAR_13
            int insertResult2 = iwantReleaseDao.saveZhjyxxInfo(belongQf,tixin,priceNum,accoInfo);  //C_POST_BAR_12
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

    //账号收售详细发布
    public String saveZhssxxfbInfo(String BELONG_QF, String TIXIN, String PRICE_NUM, String FACE_NUM, String BACK_NUM, String WAIST_NUM, String LEFT_NUM, String RIGHT_NUM, String qtxych, String
                                   CRED_NUM, String TOP_NUM, String CONSUM_NUM, String INTEG_NUM, String GOLD_NUM, String PET_NUM, String CREATE_ACCO, String CARD_TIME, String CURR_NUM, String TWO_INPUT, String THREE_INPUT, String _95cw, String _90cw, String _80cw, String _70cw, String
                                   mptx, String XUANJIN_95, String XIAOTIE_95, String XUANJIN_90, String XIAOTIE_90, String XUANJIN_80, String XIAOTIE_80, String XUANJIN_70, String XIAOTIE_70,
                                   String PVP_HPS, String PVE_HPS, String PVP_T, String PVP_IN, String PVE_IN, String PVP_OUT, String PVE_OUT, String OTHER_EXPLAIN){
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            int insertResult = iwantReleaseDao.saveZhssxxfbInfo(BELONG_QF,TIXIN,PRICE_NUM,FACE_NUM,BACK_NUM,WAIST_NUM,LEFT_NUM,RIGHT_NUM,qtxych,
                    CRED_NUM,TOP_NUM,CONSUM_NUM,INTEG_NUM,GOLD_NUM,PET_NUM,CREATE_ACCO,CARD_TIME,CURR_NUM,TWO_INPUT,THREE_INPUT,_95cw,_90cw,_80cw,_70cw,
                    mptx,XUANJIN_95,XIAOTIE_95,XUANJIN_90,XIAOTIE_90,XUANJIN_80,XIAOTIE_80,XUANJIN_70,XIAOTIE_70,
                    PVP_HPS,PVE_HPS,PVP_T,PVP_IN,PVE_IN,PVP_OUT,PVE_OUT,OTHER_EXPLAIN);     //D_POST_BAR_14
            //int insertResult2 = iwantReleaseDao.saveZhjywyxxInfo(BELONG_QF,TIXIN,PRICE_NUM,FACE_NUM,BACK_NUM,WAIST_NUM,LEFT_NUM,RIGHT_NUM,qtxych,
            //        CRED_NUM,TOP_NUM,CONSUM_NUM,INTEG_NUM,GOLD_NUM,PET_NUM,CREATE_ACCO,CARD_TIME,CURR_NUM,TWO_INPUT,THREE_INPUT,_95cw,_90cw,_80cw,_70cw,
            //        mptx,XUANJIN_95,XIAOTIE_95,XUANJIN_90,XIAOTIE_90,XUANJIN_80,XIAOTIE_80,XUANJIN_70,XIAOTIE_70,
            //        PVP_HPS,PVE_HPS,PVP_T,PVP_IN,PVE_IN,PVP_OUT,PVE_OUT,OTHER_EXPLAIN);     //C_POST_BAR_12_1

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

    //获取特征词
    public Object getTzc(String type, String parNum, String cate){
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            resArr = iwantReleaseDao.getTzc(type, parNum, cate);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
    }

    //获取图片地址
    public String getPicturePath(String favorId, String seqNum) throws Exception {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        StringBuffer COLLECT_CONT = new StringBuffer();
        resArr = iwantReleaseDao.getPicturePath(favorId,seqNum);
        Map<String,Object> map = resArr.get(0);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if(entry.getValue()!=""&&entry.getValue()!="[]") {
                COLLECT_CONT.append(entry.getValue());
            }
        }

        return COLLECT_CONT.toString();
    }

}
