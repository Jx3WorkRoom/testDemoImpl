package com.service;

import com.dao.segmentWordDao;
import com.utils.MyDateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.appearanceSaleDao;

import java.util.*;

@Service
public class appearanceSaleService {
    @Autowired
    appearanceSaleDao appearanceSaleDao;

    @Autowired
    segmentWordDao segmentWordDao;

    public Object queryAppearanceSaleInfo(String tieba, String shape, int pageNumSelected, int startNum, int endNum) {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            if((startNum==0 || endNum ==20) && pageNumSelected !=1){
                startNum = (pageNumSelected-1)*20;
                endNum = 20;
            }
            if("".equals(shape)&&"".equals(tieba)){
                resArr = appearanceSaleDao.queryappearanceSaleInfo2(startNum,endNum);
            }else{
                Map<String,Set<String>> map =segmentWordDao.test(shape);
                resArr = appearanceSaleDao.queryappearanceSaleInfo3(tieba,map,startNum,endNum,shape);
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
            resArr = appearanceSaleDao.querySelectionListInfo();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
    }

    public Object queryPageListNum() {
        String resArr = "";
        try {
            resArr = appearanceSaleDao.queryPageListNum();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
    }

    public Object queryTixinListInfo() {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            resArr = appearanceSaleDao.queryTixinListInfo();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
    }

    public String userIsvalid(String userName, String mainId ,  int isValided,String replyTime) throws Exception {
        String userId = appearanceSaleDao.selectUserId(userName);
        int result = appearanceSaleDao.selectIsvalid(userId,mainId);
        if(result==-1){
            String COLLECT_DATE = new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd").replace("\\s*","");
            String recordId = UUID.randomUUID().toString();
            int COLLECT_TYPE = 2;
            int MOD_ID = 22;
            int COLL_TYPE = 1;
            StringBuffer COLLECT_CONT = new StringBuffer();
            int COLLECT_STUSTA = isValided;
            String FAVOR_DATE = replyTime.replace("\\s*","");
            List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
            try {
                resArr = appearanceSaleDao.queryCollectCont(mainId);
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
            int insertResult = appearanceSaleDao.insertuserIsvalid(recordId,userId,mainId,COLLECT_DATE,COLLECT_TYPE,MOD_ID,COLL_TYPE,COLLECT_CONT.toString(),COLLECT_STUSTA,FAVOR_DATE);
            if(insertResult==1) {
                return "收藏成功!";
            }else{
                return "收藏失败!";
            }
        }else {
            int edutResult = appearanceSaleDao.edituserIsvalid(userId,mainId,isValided);
            if (isValided == 1)
                return "已收藏";
            else
                return "已取消收藏";
        }
    }

    public Object queryappearanceSaleSource(String mainId, int sourceType, int userId,String userName) {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> hasAuth = new ArrayList<Map<String, Object>>();
        try {
//            hasAuth = appearanceSaleDao.hasAuth(userName);
//            if(hasAuth.size()>0) {
                if (sourceType == 1) {
                    resArr = appearanceSaleDao.queryappearanceSaleSource(mainId);
                } else {
                    resArr = appearanceSaleDao.queryappearanceSaleSource2(userId);
                }
//            }else {
//                return "noAuth";
//            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
    }

    public Object protDisable(String mainId,String userName) throws Exception {
        int resArr = 0;
        try {
                resArr = appearanceSaleDao.protDisable(mainId);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(resArr==1){
            return "提交成功";
        }else{
            appearanceSaleDao.insertUserFollow(mainId,userName);
            return "提交成功";
        }
    }

    public void addUserFollow(String mainId,String userName) throws Exception {
        int resultNum = appearanceSaleDao.addUserFollow(mainId);
        if(resultNum==0){
            appearanceSaleDao.insertUserFollow(mainId,userName);
        }
    }

    public Object queryAppearanceSaleInfo2(int tradeType, String areaSelection, String shape, int pageNumSelected, int startNum, int endNum) {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            if((startNum==0 || endNum ==20) && pageNumSelected !=1){
                startNum = (pageNumSelected-1)*20;
                endNum = 20;
            }
            if("".equals(shape)&&"".equals(areaSelection)){
                resArr = appearanceSaleDao.queryappearanceSaleInfo22(tradeType,startNum);
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
                resArr = appearanceSaleDao.queryappearanceSaleInfo222(tradeType,selectTion1,selectTion2,selectTion3,map,startNum,endNum,shape);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
    }

    public Object queryappearanceSaleSource2(String mainId) {
        String resArr = "";
        try {
            resArr = appearanceSaleDao.queryappearanceSaleSource22(mainId);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
    }

    public Object queryAppearanceSaleInfo3(String areaSelection, String shape, int pageNumSelected, int startNum, int endNum) {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            if((startNum==0 || endNum ==20) && pageNumSelected !=1){
                startNum = (pageNumSelected-1)*20;
                endNum = 20;
            }
            if("".equals(shape)&&"".equals(areaSelection)){
                resArr = appearanceSaleDao.queryappearanceSaleInfo33(startNum);
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
                resArr = appearanceSaleDao.queryappearanceSaleInfo333(selectTion1,selectTion2,selectTion3,shape,startNum,endNum);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
    }

    public Object queryAppearanceSaleInfo4(String areaSelection, String shape, int pageNumSelected, int startNum, int endNum) {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            if((startNum==0 || endNum ==20) && pageNumSelected !=1){
                startNum = (pageNumSelected-1)*20;
                endNum = 20;
            }
            if("".equals(shape)&&"".equals(areaSelection)){
                resArr = appearanceSaleDao.queryappearanceSaleInfo4(startNum);
            }else{
                String selectTion1 = "";
                String selectTion2 = "";
                String selectTion3 = "";
                String selectTion4 = "";
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
                    }else if(length==4){
                        selectTion1 = areaSelection.split(",")[0];
                        selectTion2 = areaSelection.split(",")[1];
                        selectTion3 = areaSelection.split(",")[2];
                        selectTion4 = areaSelection.split(",")[3];
                    }
                }
                resArr = appearanceSaleDao.queryappearanceSaleInfo44(selectTion1,selectTion2,selectTion3,selectTion4,shape,startNum,endNum);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
    }
}
