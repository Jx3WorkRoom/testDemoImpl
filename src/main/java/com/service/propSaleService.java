package com.service;

import com.dao.segmentWordDao;
import com.utils.MyDateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.propSaleDao;

import java.util.*;

@Service
public class propSaleService {
    @Autowired
    propSaleDao propSaleDao;

    @Autowired
    segmentWordDao segmentWordDao;

    public Object queryPropSaleInfo(int tradeType, String areaSelection, String shape, int pageNumSelected, int startNum, int endNum) {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            if((startNum==0 || endNum ==20) && pageNumSelected !=1){
                startNum = (pageNumSelected-1)*20;
                endNum = 20;
            }
            if("".equals(areaSelection)&&"".equals(shape)){
                resArr = propSaleDao.queryappearanceSaleInfo2(tradeType,startNum,endNum);

            }else{
                String selectTion1 = "";
                String selectTion2 = "";
                String selectTion3 = "";
                if(!"".equals(areaSelection)) {
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
                }
                Map<String,Set<String>> map =segmentWordDao.test(shape);
                resArr = propSaleDao.queryappearanceSaleInfo3(tradeType,selectTion1,selectTion2,selectTion3,map,startNum,endNum,shape);
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
            resArr = propSaleDao.querySelectionListInfo();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
    }

    public Object queryPageListNum() {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            resArr = propSaleDao.queryPageListNum();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr.size();
    }


    public String userIsvalid(String userName, String mainId, int isValided, String replyTime) throws Exception {
        String userId = propSaleDao.selectUserId(userName);
        int result = propSaleDao.selectIsvalid(userId,mainId);
        if(result==-1){
            String COLLECT_DATE = new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd").replace("\\s*","");
            String recordId = UUID.randomUUID().toString();
            int COLLECT_TYPE = 3;
            int MOD_ID = 32;
            int COLL_TYPE = 1;
            StringBuffer COLLECT_CONT = new StringBuffer();
            int COLLECT_STUSTA = isValided;
            String FAVOR_DATE = replyTime.replace("\\s*","");
            List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
            try {
                resArr = propSaleDao.queryCollectCont(mainId);
                Map<String,Object> map = resArr.get(0);
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    if(entry.getValue()!=""&&entry.getValue()!="[]") {
                        COLLECT_CONT.append(entry.getValue());
                        COLLECT_CONT.append("，");
                    }
                }
                COLLECT_CONT.delete(COLLECT_CONT.length()-1,COLLECT_CONT.length());
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            int insertResult = propSaleDao.insertuserIsvalid(recordId,userId,mainId,COLLECT_DATE,COLLECT_TYPE,MOD_ID,COLL_TYPE,COLLECT_CONT.toString(),COLLECT_STUSTA,FAVOR_DATE);
            if(insertResult==1) {
                return "收藏成功!";
            }else{
                return "收藏成功!";
            }
        }else {
                int edutResult = propSaleDao.edituserIsvalid(userId,mainId,isValided);
            if (isValided == 1)
                return "已收藏";
            else
                return "已取消收藏";
        }
    }

    public Object queryPropSaleSource(String mainId, int sourceType, int userId,String userName) {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> hasAuth = new ArrayList<Map<String, Object>>();
        try {
//            hasAuth = propSaleDao.hasAuth(userName);
//            if(hasAuth.size()>0) {
                if (sourceType == 1) {
                    resArr = propSaleDao.queryappearanceSaleSource(mainId);
                } else {
                    resArr = propSaleDao.queryappearanceSaleSource2(userId);
                }
//            }else{
//                return "noAuth";
//            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
    }

    public void addUserFollow(String mainId,String userName) throws Exception {
        int resultNum = propSaleDao.addUserFollow(mainId);
        if(resultNum==0){
            propSaleDao.insertUserFollow(mainId,userName);
        }
    }

    public Object protDisable(String mainId,String userName) throws Exception {
        int resArr = 0;
        try {
            resArr = propSaleDao.protDisable(mainId);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(resArr==1){
            return "提交成功";
        }else{
            propSaleDao.insertUserFollow(mainId,userName);
            return "提交成功";
        }
    }
}
