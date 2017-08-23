package com.service;

import com.utils.MyDateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.goldExchangeListDao;

import java.util.*;

@Service
public class goldExchangeListService {
    @Autowired
    goldExchangeListDao goldExchangeListDao;

    public Object queryGoldExchangeListInfo(int tradeType, String areaSelection, int pageNumSelected, int startNum, int endNum) {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            if((startNum==0 || endNum ==20) && pageNumSelected !=1){
                startNum = (pageNumSelected-1)*20;
                endNum = 20;
            }
            if(!"".equals(areaSelection)){
                String selectTion1 = "";
                String selectTion2 = "";
                String selectTion3 = "";
                    int length = areaSelection.split(",").length;
                    if(length==1){
                        selectTion1 = areaSelection.split(",")[0];
                        selectTion2 = "";
                        selectTion3 = "";
                    }else if(length==2){
                        selectTion1 = areaSelection.split(",")[0];
                        selectTion2 = areaSelection.split(",")[1];
                        selectTion3 = "";
                    }else if(length==3){
                        selectTion1 = areaSelection.split(",")[0];
                        selectTion2 = areaSelection.split(",")[1];
                        selectTion3 = areaSelection.split(",")[2];
                    }
                resArr = goldExchangeListDao.querygoldExchangeListInfo1(tradeType,selectTion1,selectTion2,selectTion3,startNum,endNum);
            }else{
                resArr = goldExchangeListDao.querygoldExchangeListInfo2(tradeType,startNum,endNum);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
    }

    public Object querySelectionListInfo() {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            resArr = goldExchangeListDao.querySelectionListInfo();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
    }

    public Object queryPageListNum(int tradeType) {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            resArr = goldExchangeListDao.queryPageListNum(tradeType);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr.size();
    }

    public String userIsvalid(String userName, String mainId, int isValided, String replyTime) throws Exception {
        String userId = goldExchangeListDao.selectUserId(userName);
        int result = goldExchangeListDao.selectIsvalid(userId,mainId);
        if(result==-1){
            String COLLECT_DATE = new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd").replace("\\s*","");
            String recordId = UUID.randomUUID().toString();
            int COLLECT_TYPE = 4;
            int MOD_ID = 42;
            int COLL_TYPE = 1;
            StringBuffer COLLECT_CONT = new StringBuffer();
            int COLLECT_STUSTA = isValided;
            String FAVOR_DATE = replyTime.replace("\\s*","");
            List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
            try {
                resArr = goldExchangeListDao.queryCollectCont(mainId);
                Map<String,Object> map = resArr.get(0);
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    if(entry.getValue()!=""&&entry.getValue()!="[]") {
                        COLLECT_CONT.append(entry.getValue());
                    }
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            int insertResult = goldExchangeListDao.insertuserIsvalid(recordId,userId,mainId,COLLECT_DATE,COLLECT_TYPE,MOD_ID,COLL_TYPE,COLLECT_CONT.toString(),COLLECT_STUSTA,FAVOR_DATE);
            if(insertResult==1) {
                return "收藏成功!";
            }else{
                return "收藏成功!";
            }
        }else {
            if (result != isValided) {
                int edutResult = goldExchangeListDao.edituserIsvalid(userId,mainId,isValided);
            }
            if (isValided == 1)
                return "已收藏";
            else
                return "已取消收藏";
        }
    }

    public Object queryGoldExchangeSource(int userId) {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            resArr = goldExchangeListDao.queryappearanceSaleSource(userId);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
    }

    public void addUserFollow(String mainId) throws Exception {
        int resultNum = goldExchangeListDao.addUserFollow(mainId);
        if(resultNum==0){
            goldExchangeListDao.insertUserFollow(mainId);
        }
    }

    public Object protDisable(String mainId) throws Exception {
        int resArr = 0;
        try {
            resArr = goldExchangeListDao.protDisable(mainId);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(resArr==1){
            return "提交成功";
        }else{
            goldExchangeListDao.insertUserFollow(mainId);
            return  "提交成功";
        }
    }
}
