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
    public String saveWyjbInfo(String recordId,String userId, int cheatType, String belongQf, String tixin, String roleName, String cheatIntro, String cheatInfo, String pageUrl,String filePath) {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            int favorId = iwantReleaseDao.getSequence3();
            int insertResult = iwantReleaseDao.saveWyjbInfo(recordId,favorId,userId,cheatType,"["+belongQf+"]","["+tixin+"]",roleName,cheatIntro,cheatInfo,pageUrl);    //D_post_bar_11
            int insertResult2 = iwantReleaseDao.saveHbbgfo(recordId,favorId,userId,cheatType,"["+belongQf+"]","["+tixin+"]",roleName,cheatIntro,cheatInfo,pageUrl);     //C_POST_BAR_11
            String createTime =  new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd hh:mm:ss").replace("\\s*","");
            String updateTime = createTime;
            String favorDate = createTime;
            String cheatTypeSuffer ="";
            try {
                cheatTypeSuffer = iwantReleaseDao.queryCheatType(cheatType);
            }catch (Exception e){
                e.printStackTrace();
            }
            String COLLECT_CONT = "【欺诈类型】"+cheatTypeSuffer+'\n'+"【涉事区服】"+belongQf+'\n'+"【被黑经历】"+cheatIntro+'\n'+"【黑鬼资料综述】"+cheatInfo;
            int ISVALID = 1;
            int favorType =7;
            int COLLECT_STUSTA =1;
            int insertResult3 = iwantReleaseDao.savePub(recordId,createTime,updateTime,ISVALID,favorId,userId,favorDate,favorType,COLLECT_CONT,COLLECT_STUSTA);
            int insertResult4 = iwantReleaseDao.saveImageInfo(recordId, favorId, userId, 1, filePath);     //d_post_bar_21
            if(insertResult==1 && insertResult2==1&&insertResult3==1&&insertResult4==1) {
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
    //我要举报-编辑
    public String upeditWyjbInfo(int favorId, String userId, int cheatType, String belongQf, String tixin, String roleName, String cheatIntro, String cheatInfo, String pageUrl,String filePath) {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            String recordId = "";
            int insertResult = iwantReleaseDao.upeditWyjbInfo(favorId,userId,cheatType,"["+belongQf+"]","["+tixin+"]",roleName,cheatIntro,cheatInfo,pageUrl);    //D_post_bar_11
            int insertResult2 = iwantReleaseDao.upeditHbbgfo(favorId,userId,cheatType,"["+belongQf+"]","["+tixin+"]",roleName,cheatIntro,cheatInfo,pageUrl);     //C_POST_BAR_11
            String createTime =  new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd hh:mm:ss").replace("\\s*","");
            String updateTime = createTime;
            String favorDate = createTime;
            String COLLECT_CONT = "【欺诈类型】"+cheatType+'\n'+"【涉事区服】"+belongQf+'\n'+"【被黑经历】"+cheatIntro+'\n'+"【黑鬼资料综述】"+cheatInfo;
            int ISVALID = 1;
            int favorType =7;
            int COLLECT_STUSTA =1;
            int insertResult3 = iwantReleaseDao.updatePub(recordId,createTime,updateTime,ISVALID,favorId,userId,favorDate,favorType,COLLECT_CONT,COLLECT_STUSTA);
            int insertResult4 = iwantReleaseDao.updateImageInfo( favorId, userId, 1, filePath);     //d_post_bar_21
            if(insertResult==1 && insertResult2==1 && insertResult3==1 && insertResult4==1) {
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
            int insertResult2 = iwantReleaseDao.saveWgjyxxInfo(recordId,favorId, userId,tradeType,belongQf,viewName,priceNum,favorInfo);    //C_POST_BAR_13
            String createTime =  new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd hh:mm:ss").replace("\\s*","");
            String updateTime = createTime;
            String favorDate = createTime;
            String COLLECT_CONT = "【所属区服】"+belongQf+'\n'+"【外观名】"+viewName+'\n'+"【价格】"+priceNum;
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
    public String upeditWgjyInfo(int favorId, String userId, int tradeType, String belongQf, String viewName, int priceNum, String favorInfo) {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            String recordId = "";
            int insertResult = iwantReleaseDao.upeditWgjyInfo(recordId,favorId, userId,tradeType,"["+belongQf+"]",viewName,priceNum,favorInfo);    //D_POST_BAR_16
            int insertResult2 = iwantReleaseDao.upeditWgjyxxInfo(recordId,favorId, userId,tradeType,belongQf,viewName,priceNum,favorInfo);    //C_POST_BAR_13
            String createTime =  new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd hh:mm:ss").replace("\\s*","");
            String updateTime = createTime;
            String favorDate = createTime;
            String COLLECT_CONT = "【所属区服】"+belongQf+'\n'+"【外观名】"+viewName+'\n'+"【价格】"+priceNum;
            int ISVALID = 1;
            int favorType =3;
            int COLLECT_STUSTA =1;
            int insertResult3 = iwantReleaseDao.updatePub(recordId,createTime,updateTime,ISVALID,favorId,userId,favorDate,favorType,COLLECT_CONT,COLLECT_STUSTA);
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
            int insertResult2 = iwantReleaseDao.saveDjjyxxInfo(recordId,favorId, userId,tradeType, belongQf, propName, priceNum, favorInfo);    //C_POST_BAR_15
            String createTime =  new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd hh:mm:ss").replace("\\s*","");
            String updateTime = createTime;
            String favorDate = createTime;
            String COLLECT_CONT = "【所属区服】"+belongQf+'\n'+"【道具名】"+propName+'\n'+"【价格】"+priceNum;
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

    public String upeditDjjyInfo(int favorId, String userId,int tradeType, String belongQf, String propName, int priceNum, String favorInfo) {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            String recordId = "";
            int insertResult = iwantReleaseDao.upeditDjjyInfo(recordId,favorId, userId,tradeType, "["+belongQf+"]", propName, priceNum, favorInfo);    //D_POST_BAR_18
            int insertResult2 = iwantReleaseDao.upeditDjjyxxInfo(recordId,favorId, userId,tradeType, "["+belongQf+"]", propName, priceNum, favorInfo);    //C_POST_BAR_15
            String createTime =  new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd hh:mm:ss").replace("\\s*","");
            String updateTime = createTime;
            String favorDate = createTime;
            String COLLECT_CONT = "【所属区服】"+belongQf+'\n'+"【道具名】"+propName+'\n'+"【价格】"+priceNum;
            int ISVALID = 1;
            int favorType =4;
            int COLLECT_STUSTA =1;
            int insertResult3 = iwantReleaseDao.updatePub(recordId,createTime,updateTime,ISVALID,favorId,userId,favorDate,favorType,COLLECT_CONT,COLLECT_STUSTA);
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
            int insertResult2 = iwantReleaseDao.saveYxjbjyInfo(recordId,favorId, userId, tradeType, belongQf, goldTotal, unitPrice, ifSplit, favorInfo);     //C_POST_BAR_19
            String createTime =  new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd hh:mm:ss").replace("\\s*","");
            String updateTime = createTime;
            String favorDate = createTime;
            String COLLECT_CONT = "【所属区服】"+belongQf+'\n'+"【金币总量】"+goldTotal+'\n'+"【单价】"+unitPrice;
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
    public String updateJbjyInfo(int favorId, String userId, int tradeType, String belongQf, int goldTotal, int unitPrice, int ifSplit, String favorInfo){
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            String recordId = "";
            //int favorId = iwantReleaseDao.getSequence3();
            int insertResult = iwantReleaseDao.updateJbjyInfo(recordId,favorId, userId, tradeType, "["+belongQf+"]", goldTotal, unitPrice, ifSplit, favorInfo);     //D_POST_BAR_19
            int insertResult2 = iwantReleaseDao.updateYxjbjyInfo(recordId,favorId, userId, tradeType, belongQf, goldTotal, unitPrice, ifSplit, favorInfo);     //C_POST_BAR_19
            String createTime =  new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd hh:mm:ss").replace("\\s*","");
            String updateTime = createTime;
            String favorDate = createTime;
            String COLLECT_CONT = "【所属区服】"+belongQf+'\n'+"【金币总量】"+goldTotal+'\n'+"【单价】"+unitPrice;
            int ISVALID = 1;
            int favorType =5;
            int COLLECT_STUSTA =1;
            int insertResult3 = iwantReleaseDao.updatePub(recordId,createTime,updateTime,ISVALID,favorId,userId,favorDate,favorType,COLLECT_CONT,COLLECT_STUSTA);
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
    public String saveDlddInfo(String userId, int needtype, String belongQf, String favorInfo, String filePath){
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            String recordId = UUID.randomUUID().toString()/*.replace("-", "")*/;
            int favorId = iwantReleaseDao.getSequence3();
            int insertResult = iwantReleaseDao.saveDlddInfo(recordId,favorId, userId, needtype, "["+belongQf+"]",  favorInfo);   //D_POST_BAR_20
            int insertResult2 = iwantReleaseDao.saveYxddxxInfo(recordId,favorId, userId, needtype, "["+belongQf+"]",  favorInfo);   //C_POST_BAR_17
            String createTime =  new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd hh:mm:ss").replace("\\s*","");
            String updateTime = createTime;
            String favorDate = createTime;
            String COLLECT_CONT = "【所属区服】"+belongQf+'\n'+"【代练说明】"+favorInfo;
            int ISVALID = 1;
            int favorType =6;
            int COLLECT_STUSTA =1;
            int insertResult3 = iwantReleaseDao.savePub(recordId,createTime,updateTime,ISVALID,favorId,userId,favorDate,favorType,COLLECT_CONT,COLLECT_STUSTA);
            int insertResult4 = iwantReleaseDao.saveImageInfo(recordId, favorId, userId, 1, filePath);     //d_post_bar_21
            if(insertResult==1 && insertResult2==1&&insertResult3==1&&insertResult4==1) {
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
    public String updateDlddInfo(int favorId, String userId, int needtype, String belongQf, String favorInfo, String filePath){
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            String recordId = UUID.randomUUID().toString()/*.replace("-", "")*/;
            int insertResult = iwantReleaseDao.updateDlddInfo(recordId,favorId, userId, needtype, "["+belongQf+"]",  favorInfo);   //D_POST_BAR_20
            int insertResult2 = iwantReleaseDao.updateYxddxxInfo(recordId,favorId, userId, needtype, "["+belongQf+"]",  favorInfo);   //C_POST_BAR_17
            String createTime =  new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd hh:mm:ss").replace("\\s*","");
            String updateTime = createTime;
            String favorDate = createTime;
            String COLLECT_CONT = "【所属区服】"+belongQf+'\n'+"【代练说明】"+favorInfo;
            int ISVALID = 1;
            int favorType =6;
            int COLLECT_STUSTA =1;
            int insertResult3 = iwantReleaseDao.updatePub(recordId,createTime,updateTime,ISVALID,favorId,userId,favorDate,favorType,COLLECT_CONT,COLLECT_STUSTA);
            int insertResult4 = iwantReleaseDao.updateImageInfo( favorId, userId, 1, filePath);     //d_post_bar_21
            if(insertResult==1 && insertResult2==1 && insertResult3==1 && insertResult4==1) {
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
    public String saveZhssInfo(String userId,int tradeType, String belongQf,String tixin,int priceNum,String accoInfo,String filePath){
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            String recordId = UUID.randomUUID().toString()/*.replace("-", "")*/;
            int favorId = iwantReleaseDao.getSequence3();
            int insertResult = iwantReleaseDao.saveZhssInfo(recordId,favorId,tradeType,userId,"["+belongQf+"]","["+tixin+"]",priceNum,accoInfo);  //D_POST_BAR_13
            int insertResult2 = iwantReleaseDao.saveZhjyxxInfo(recordId,favorId,tradeType,userId,"["+belongQf+"]","["+tixin+"]",String.valueOf(priceNum),accoInfo);  //C_POST_BAR_12
            String createTime =  new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd hh:mm:ss").replace("\\s*","");
            String updateTime = createTime;
            String favorDate = createTime;
            String COLLECT_CONT = accoInfo;
            int ISVALID = 1;
            int favorType =1;
            int COLLECT_STUSTA =1;
            int insertResult3 = iwantReleaseDao.savePub(recordId,createTime,updateTime,ISVALID,favorId,userId,favorDate,favorType,COLLECT_CONT,COLLECT_STUSTA);
            int insertResult4 = iwantReleaseDao.saveImageInfo(recordId, favorId, userId, 1, filePath);     //d_post_bar_21
            if(insertResult==1 && insertResult2==1&&insertResult3==1&&insertResult4==1) {
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
    public String updateZhssInfo(int favorId, String userId,int tradeType, String belongQf,String tixin,int priceNum,String accoInfo,String filePath){
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            String recordId = UUID.randomUUID().toString()/*.replace("-", "")*/;
            int insertResult = iwantReleaseDao.updateZhssInfo(recordId,favorId,tradeType,userId,"["+belongQf+"]","["+tixin+"]",priceNum,accoInfo);  //D_POST_BAR_13
            int insertResult2 = iwantReleaseDao.updateZhjyxxInfo(recordId,favorId,tradeType,userId,"["+belongQf+"]","["+tixin+"]",String.valueOf(priceNum),accoInfo);  //C_POST_BAR_12
            String createTime =  new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd hh:mm:ss").replace("\\s*","");
            String updateTime = createTime;
            String favorDate = createTime;
            String COLLECT_CONT = accoInfo;
            int ISVALID = 1;
            int favorType =1;
            int COLLECT_STUSTA =1;
            int insertResult3 = iwantReleaseDao.updatePub(recordId,createTime,updateTime,ISVALID,favorId,userId,favorDate,favorType,COLLECT_CONT,COLLECT_STUSTA);
            int insertResult4 = iwantReleaseDao.updateImageInfo( favorId, userId, 1, filePath);     //d_post_bar_21
            if(insertResult==1 && insertResult2==1 && insertResult3==1 && insertResult4==1) {
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
    public String saveZhssxxfbInfo(String userId, int tradeType, String BELONG_QF, String TIXIN, String PRICE_NUM, String FACE_NUM, String BACK_NUM, String WAIST_NUM, String LEFT_NUM, String RIGHT_NUM, String qtxych, String
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
            int insertResult2 = iwantReleaseDao.saveZhjyxxInfo(recordId,favorId,tradeType,userId,BELONG_QF,TIXIN,PRICE_NUM,FACE_NUM);  //C_POST_BAR_12
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



    //获取道具名下拉框填充信息
    public Object queryTixinListInfo(String type) {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        String keyword = "";
        try {
            if(type.equals("1")){
                keyword="user_waiguan";     //外观
            }else{
                keyword="user_daoju";   //道具
            }
            resArr = iwantReleaseDao.queryTixinListInfo(keyword);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
    }


    //获取我要举报
    public Object getReport(String mainId){
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            resArr = iwantReleaseDao.getReport(mainId);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
    }

    //获取外观交易
    public Object getAppearanceTransaction(String mainId){
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            resArr = iwantReleaseDao.getAppearanceTransaction(mainId);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
    }

    //获取道具交易
    public Object getPropTransaction(String mainId){
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            resArr = iwantReleaseDao.getPropTransaction(mainId);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
    }

    //获取金币交易
    public Object getAccountTransaction(String mainId){
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            resArr = iwantReleaseDao.getAccountTransaction(mainId);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
    }

    //获取代练交易
    public Object getAccountExchange(String mainId){
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            resArr = iwantReleaseDao.getAccountExchange(mainId);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
    }

    //获取账号收售快速发布
    public Object getQuickRelease(String mainId){
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            resArr = iwantReleaseDao.getQuickRelease(mainId);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
    }

}
