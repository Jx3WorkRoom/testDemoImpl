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
    public String saveWyjbInfo(String userId, int cheatType, String belongQf, String tixin, String roleName, String cheatIntro, String cheatInfo, String pageUrl) {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            String recordId = UUID.randomUUID().toString()/*.replace("-", "")*/;
            int favorId = iwantReleaseDao.getSequence3();
            int insertResult = iwantReleaseDao.saveWyjbInfo(recordId,favorId,userId,cheatType,"["+belongQf+"]","["+tixin+"]",roleName,cheatIntro,cheatInfo,pageUrl);    //D_post_bar_11
            int insertResult2 = iwantReleaseDao.saveHbbgfo(recordId,favorId,userId,cheatType,"["+belongQf+"]","["+tixin+"]",roleName,cheatIntro,cheatInfo,pageUrl);     //C_POST_BAR_11
            String createTime =  new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd hh:mm:ss").replace("\\s*","");
            String updateTime = createTime;
            String favorDate = createTime;
            String COLLECT_CONT = "【欺诈类型】"+cheatType+'\n'+"【涉事区服】"+belongQf+'\n'+"【被黑经历】"+cheatIntro+'\n'+"【黑鬼资料综述】"+cheatInfo;
            int ISVALID = 1;
            int favorType =7;
            int COLLECT_STUSTA =1;
            int insertResult3 = iwantReleaseDao.savePub(recordId,createTime,updateTime,ISVALID,favorId,userId,favorDate,favorType,COLLECT_CONT,COLLECT_STUSTA);
            if(insertResult==1 && insertResult2==1&&insertResult3==1) {
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
    public String saveWgjyInfo(String userId, int tradeType, String belongQf, String viewName, int priceNum, String favorInfo) {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            String recordId = UUID.randomUUID().toString()/*.replace("-", "")*/;
            int favorId = iwantReleaseDao.getSequence3();
            int insertResult = iwantReleaseDao.saveWgjyInfo(recordId,favorId, userId,tradeType,"["+belongQf+"]",viewName,priceNum,favorInfo);    //D_POST_BAR_16
            int insertResult2 = iwantReleaseDao.saveWgjyxxInfo(recordId,favorId, userId,tradeType,"["+belongQf+"]",viewName,priceNum,favorInfo);    //C_POST_BAR_13
            String createTime =  new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd hh:mm:ss").replace("\\s*","");
            String updateTime = createTime;
            String favorDate = createTime;
            String COLLECT_CONT = "【所属区分】"+belongQf+'\n'+"【外观名】"+viewName+'\n'+"【价格】"+priceNum;
            int ISVALID = 1;
            int favorType =3;
            int COLLECT_STUSTA =1;
            int insertResult3 = iwantReleaseDao.savePub(recordId,createTime,updateTime,ISVALID,favorId,userId,favorDate,favorType,COLLECT_CONT,COLLECT_STUSTA);
            if(insertResult==1 && insertResult2==1&&insertResult3==1) {
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
    public String saveDjjyInfo(String userId,int tradeType, String belongQf, String propName, int priceNum, String favorInfo) {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            String recordId = UUID.randomUUID().toString()/*.replace("-", "")*/;
            int favorId = iwantReleaseDao.getSequence3();
            int insertResult = iwantReleaseDao.saveDjjyInfo(recordId,favorId, userId,tradeType, "["+belongQf+"]", propName, priceNum, favorInfo);    //D_POST_BAR_18
            int insertResult2 = iwantReleaseDao.saveDjjyxxInfo(recordId,favorId, userId,tradeType, "["+belongQf+"]", propName, priceNum, favorInfo);    //C_POST_BAR_15
            String createTime =  new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd hh:mm:ss").replace("\\s*","");
            String updateTime = createTime;
            String favorDate = createTime;
            String COLLECT_CONT = "【所属区分】"+belongQf+'\n'+"【道具名】"+propName+'\n'+"【价格】"+priceNum;
            int ISVALID = 1;
            int favorType =4;
            int COLLECT_STUSTA =1;
            int insertResult3 = iwantReleaseDao.savePub(recordId,createTime,updateTime,ISVALID,favorId,userId,favorDate,favorType,COLLECT_CONT,COLLECT_STUSTA);
            if(insertResult==1 && insertResult2==1&&insertResult3==1) {
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
    public String saveJbjyInfo(String userId, int tradeType, String belongQf, int goldTotal, int unitPrice, int ifSplit, String favorInfo){
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            String recordId = UUID.randomUUID().toString()/*.replace("-", "")*/;
            int favorId = iwantReleaseDao.getSequence3();
            int insertResult = iwantReleaseDao.saveJbjyInfo(recordId,favorId, userId, tradeType, "["+belongQf+"]", goldTotal, unitPrice, ifSplit, favorInfo);     //D_POST_BAR_19
            int insertResult2 = iwantReleaseDao.saveYxjbjyInfo(recordId,favorId, userId, tradeType, "["+belongQf+"]", goldTotal, unitPrice, ifSplit, favorInfo);     //C_POST_BAR_19
            String createTime =  new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd hh:mm:ss").replace("\\s*","");
            String updateTime = createTime;
            String favorDate = createTime;
            String COLLECT_CONT = "【所属区分】"+belongQf+'\n'+"【金币总量】"+goldTotal+'\n'+"【单价】"+unitPrice;
            int ISVALID = 1;
            int favorType =5;
            int COLLECT_STUSTA =1;
            int insertResult3 = iwantReleaseDao.savePub(recordId,createTime,updateTime,ISVALID,favorId,userId,favorDate,favorType,COLLECT_CONT,COLLECT_STUSTA);
            if(insertResult==1 && insertResult2==1&&insertResult3==1) {
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
    public String saveDlddInfo(String userId, int cheatType, String belongQf, String favorInfo){
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            String recordId = UUID.randomUUID().toString()/*.replace("-", "")*/;
            int favorId = iwantReleaseDao.getSequence3();
            int insertResult = iwantReleaseDao.saveDlddInfo(recordId,favorId, userId, cheatType, "["+belongQf+"]",  favorInfo);   //D_POST_BAR_20
            int insertResult2 = iwantReleaseDao.saveYxddxxInfo(recordId,favorId, userId, cheatType, "["+belongQf+"]",  favorInfo);   //C_POST_BAR_17
            String createTime =  new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd hh:mm:ss").replace("\\s*","");
            String updateTime = createTime;
            String favorDate = createTime;
            String COLLECT_CONT = "【所属区分】"+belongQf+'\n'+"【代练说明】"+favorInfo;
            int ISVALID = 1;
            int favorType =6;
            int COLLECT_STUSTA =1;
            int insertResult3 = iwantReleaseDao.savePub(recordId,createTime,updateTime,ISVALID,favorId,userId,favorDate,favorType,COLLECT_CONT,COLLECT_STUSTA);
            if(insertResult==1 && insertResult2==1&&insertResult3==1) {
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
    public String saveZhssInfo(String userId,String belongQf,String tixin,int priceNum,String accoInfo){
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            String recordId = UUID.randomUUID().toString()/*.replace("-", "")*/;
            int favorId = iwantReleaseDao.getSequence3();
            int insertResult = iwantReleaseDao.saveZhssInfo(recordId,favorId,userId,belongQf,tixin,priceNum,accoInfo);  //D_POST_BAR_13
            int insertResult2 = iwantReleaseDao.saveZhjyxxInfo(recordId,favorId,userId,belongQf,tixin,String.valueOf(priceNum),accoInfo);  //C_POST_BAR_12
            String createTime =  new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd hh:mm:ss").replace("\\s*","");
            String updateTime = createTime;
            String favorDate = createTime;
            String COLLECT_CONT = accoInfo;
            int ISVALID = 1;
            int favorType =1;
            int COLLECT_STUSTA =1;
            int insertResult3 = iwantReleaseDao.savePub(recordId,createTime,updateTime,ISVALID,favorId,userId,favorDate,favorType,COLLECT_CONT,COLLECT_STUSTA);
            if(insertResult==1 && insertResult2==1&&insertResult3==1) {
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
    public String saveZhssxxfbInfo(String userId, String BELONG_QF, String TIXIN, String PRICE_NUM, String FACE_NUM, String BACK_NUM, String WAIST_NUM, String LEFT_NUM, String RIGHT_NUM, String qtxych, String
                                   CRED_NUM, String TOP_NUM, String CONSUM_NUM, String INTEG_NUM, String GOLD_NUM, String PET_NUM, String CREATE_ACCO, String CARD_TIME, String CURR_NUM, String TWO_INPUT, String THREE_INPUT, String _95cw, String _90cw, String _80cw, String _70cw, String
                                   mptx, String XUANJIN_95, String XIAOTIE_95, String XUANJIN_90, String XIAOTIE_90, String XUANJIN_80, String XIAOTIE_80, String XUANJIN_70, String XIAOTIE_70,
                                   String PVP_HPS, String PVE_HPS, String PVP_T, String PVP_IN, String PVE_IN, String PVP_OUT, String PVE_OUT, String OTHER_EXPLAIN){

        try {
            List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
            String recordId = UUID.randomUUID().toString()/*.replace("-", "")*/;
            int favorId = iwantReleaseDao.getSequence3();
            int insertResult = iwantReleaseDao.saveZhssxxfbInfo(recordId,favorId,userId,BELONG_QF,TIXIN,PRICE_NUM,FACE_NUM,BACK_NUM,WAIST_NUM,LEFT_NUM,RIGHT_NUM,qtxych,
                    CRED_NUM,TOP_NUM,CONSUM_NUM,INTEG_NUM,GOLD_NUM,PET_NUM,CREATE_ACCO,CARD_TIME,CURR_NUM,TWO_INPUT,THREE_INPUT,_95cw,_90cw,_80cw,_70cw,
                    mptx,XUANJIN_95,XIAOTIE_95,XUANJIN_90,XIAOTIE_90,XUANJIN_80,XIAOTIE_80,XUANJIN_70,XIAOTIE_70,
                    PVP_HPS,PVE_HPS,PVP_T,PVP_IN,PVE_IN,PVP_OUT,PVE_OUT,OTHER_EXPLAIN);     //D_POST_BAR_14
            //int insertResult2 = iwantReleaseDao.saveZhjywyxxInfo(recordId,favorId, BELONG_QF,TIXIN,PRICE_NUM,FACE_NUM,BACK_NUM,WAIST_NUM,LEFT_NUM,RIGHT_NUM,qtxych,
            //        CRED_NUM,TOP_NUM,CONSUM_NUM,INTEG_NUM,GOLD_NUM,PET_NUM,CREATE_ACCO,CARD_TIME,CURR_NUM,TWO_INPUT,THREE_INPUT,_95cw,_90cw,_80cw,_70cw,
            //        mptx,XUANJIN_95,XIAOTIE_95,XUANJIN_90,XIAOTIE_90,XUANJIN_80,XIAOTIE_80,XUANJIN_70,XIAOTIE_70,
            //        PVP_HPS,PVE_HPS,PVP_T,PVP_IN,PVE_IN,PVP_OUT,PVE_OUT,OTHER_EXPLAIN);     //C_POST_BAR_12_1
            int insertResult2 = iwantReleaseDao.saveZhjyxxInfo(recordId,favorId,userId,BELONG_QF,TIXIN,PRICE_NUM,FACE_NUM);  //C_POST_BAR_12
            String createTime =  new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd hh:mm:ss").replace("\\s*","");
            String updateTime = createTime;
            String favorDate = createTime;
            String COLLECT_CONT = "【所属区服】"+BELONG_QF+'\n'+"【门派体型】"+TIXIN+'\n'+"【价格】"+PRICE_NUM;
            int ISVALID = 1;
            int favorType =2;
            int COLLECT_STUSTA =1;
            int insertResult3 = iwantReleaseDao.savePub(recordId,createTime,updateTime,ISVALID,favorId,userId,favorDate,favorType,COLLECT_CONT,COLLECT_STUSTA);
            if(insertResult==1 && insertResult2==1&&insertResult3==1) {
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
