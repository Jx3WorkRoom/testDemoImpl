package com.service;

import com.utils.MyDateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.propSaleDao;

import java.util.*;

@Service
public class propSaleService {
    @Autowired
    propSaleDao propSaleDao;

    public Object queryPropSaleInfo(int tradeType, String areaSelection, String shape, int pageNumSelected, int startNum, int endNum) {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            if((startNum==0 || endNum ==10) && pageNumSelected !=1){
                startNum = (pageNumSelected-1)*10;
                endNum = startNum+10;
            }
            if(!"".equals(areaSelection)&&areaSelection!=null){
                String selectTion1 = areaSelection.split(",")[0];
                String selectTion2 = areaSelection.split(",")[1];
                String selectTion3 = areaSelection.split(",")[2];
                resArr = propSaleDao.queryappearanceSaleInfo1(tradeType,selectTion1,selectTion2,selectTion3,shape,startNum,endNum);
            }else{
                resArr = propSaleDao.queryappearanceSaleInfo2(tradeType,startNum,endNum);
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

    public Object queryTixinListInfo() {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            resArr = propSaleDao.queryTixinListInfo();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
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
                    COLLECT_CONT.append(entry.getKey()+":"+entry.getValue()+"\n");
                }
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
            if (result != isValided) {
                int edutResult = propSaleDao.edituserIsvalid(userId,mainId,isValided);
            }
            if (isValided == 1)
                return "已收藏";
            else
                return "已取消收藏";
        }
    }

    public Object queryPropSaleSource(String mainId, int sourceType, int userId) {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            if(sourceType==1) {
                resArr = propSaleDao.queryappearanceSaleSource(mainId);
            }else{
                resArr = propSaleDao.queryappearanceSaleSource2(userId);
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
    }

    public void addUserFollow(String mainId) throws Exception {
        int resultNum = propSaleDao.addUserFollow(mainId);
        if(resultNum==0){
            propSaleDao.insertUserFollow(mainId);
        }
    }

    public Object protDisable(String mainId) throws Exception {
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
            propSaleDao.insertUserFollow(mainId);
            return "提交成功";
        }
    }
}
