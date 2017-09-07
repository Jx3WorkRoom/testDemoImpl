package com.service;

import com.utils.Commons;
import com.utils.MyDateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import com.dao.accountListDao;
import org.springframework.stereotype.Service;
import com.dao.segmentWordDao;
@Service
public class accountListService {

    @Autowired
    accountListDao accountListDao;

    @Autowired
    segmentWordDao segmentWordDao;

    public Object queryAccountListInfo(int tradeType, String areaSelection, String shape, String info, int pageNumSelected, int startNum, int endNum) {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            if((startNum==0 || endNum ==10) && pageNumSelected !=1){
                startNum = (pageNumSelected-1)*10;
                endNum = 10;
            }
            if("".equals(areaSelection)&&"".equals(shape)&&"".equals(info)){
                resArr = accountListDao.queryAccountListInfo2(tradeType,startNum,endNum);
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
                Map<String,Set<String>> map =segmentWordDao.test(info);
                Commons.segMentWordMap = map;
                resArr = accountListDao.queryAccountListInfo3(tradeType,selectTion1,selectTion2,selectTion3,shape,map,startNum,endNum);
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
            resArr = accountListDao.querySelectionListInfo();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
    }

    public Object queryPageListNum() {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            resArr = accountListDao.queryPageListNum();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr.size();
    }

    public Object queryTixinListInfo() {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            resArr = accountListDao.queryTixinListInfo();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
    }

    public Object queryInfoListInfo() {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            resArr = accountListDao.queryInfoListInfo();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
    }

    public Object queryAccountDetailInfo(int favorId) {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            resArr = accountListDao.queryAccountDetailInfo(favorId);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
    }

    public String userIsvalid(String userName, String mainId, int isValided, String replyTime) throws Exception {
        String userId = accountListDao.selectUserId(userName);
        int result = accountListDao.selectIsvalid(userId,mainId);
        if(result==-1){
            String COLLECT_DATE = new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd").replace("\\s*","");
            String recordId = UUID.randomUUID().toString();
            int COLLECT_TYPE = 1;
            int MOD_ID = 12;
            int COLL_TYPE = 1;
            String COLLECT_CONT = "";
            int COLLECT_STUSTA = isValided;
            String FAVOR_DATE = replyTime.replace("\\s*","");
            List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
            try {
                resArr = accountListDao.queryCollectCont(mainId);
                Map<String,Object> map = resArr.get(0);
                COLLECT_CONT =map.get("REPLY_CONTENT").toString();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            int insertResult = accountListDao.insertuserIsvalid(recordId,userId,mainId,COLLECT_DATE,COLLECT_TYPE,MOD_ID,COLL_TYPE,COLLECT_CONT.toString(),COLLECT_STUSTA,FAVOR_DATE);
            if(insertResult==1) {
                return "收藏成功!";
            }else{
                return "收藏成功!";
            }
        }else {
            if (result != isValided) {
                int edutResult = accountListDao.edituserIsvalid(userId,mainId,isValided);
            }
            if (isValided == 1)
                return "已收藏";
            else
                return "已取消收藏";
        }
    }

    public Object queryaccountDetailSource(String mainId, int sourceType, int userId,int startNum,int endNum,String userName) {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> hasAuth = new ArrayList<Map<String, Object>>();
        try {
            hasAuth = accountListDao.hasAuth(userName);
            if(hasAuth.size()>0) {
                if (sourceType == 1) {
                    resArr = accountListDao.queryaccountDetailSource(mainId, startNum, endNum);
                } else {
                    resArr = accountListDao.queryaccountDetailSource2(userId);
                }
            }else{
                return "noAuth";
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
    }

    public String accountDetailSubmitIsValid(int favorId) throws Exception {
        int result = accountListDao.accountDetailSubmitIsValid(favorId);
        if(result==1){
            return "提交成功";
        }else{
            return "提交失败";
        }
    }

    public void addUserFollow(int favorId,String userName) throws Exception {
        int resultNum = accountListDao.addUserFollow(favorId);
        if(resultNum==0){
            accountListDao.insertUserFollow(favorId,userName);
        }
    }

    public Object queryHasCollected(String mainId, String username) {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            resArr = accountListDao.queryHasCollected(mainId,username);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
    }
}
