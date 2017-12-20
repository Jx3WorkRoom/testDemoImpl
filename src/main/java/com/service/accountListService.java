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

    public Object queryAccountListInfo(int tradeType, String areaSelection, String shape, String info, int pageNumSelected, int startNum, int endNum,String lowPrice,String highPrice,String hasChecked) {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            if((startNum==0 || endNum ==10) && pageNumSelected !=1){
                startNum = (pageNumSelected-1)*30;
                endNum = 30;
            }
            if("".equals(areaSelection)&&"".equals(shape)&&"".equals(info)&&"0".equals(lowPrice)&&"0".equals(highPrice)){
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
                resArr = accountListDao.queryAccountListInfo3(tradeType,selectTion1,selectTion2,selectTion3,shape,map,startNum,endNum,info,lowPrice,highPrice,hasChecked);
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
        String resArr = "";
        try {
            resArr = accountListDao.queryPageListNum();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
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

    public Object queryAccountDetailInfo(int favorId,int sourceType) {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            if(sourceType==1){
                resArr = accountListDao.queryAccountDetailInfo(favorId);
            }else{
                resArr = accountListDao.queryAccountDetailInfo2(favorId);
            }

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
                return "收藏失败!";
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
//            if(hasAuth.size()>0) {
                if (sourceType == 1) {
                    resArr = accountListDao.queryaccountDetailSource(mainId, startNum, endNum);
                } else {
                    resArr = accountListDao.queryaccountDetailSource2(userName);
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
            if(!"".equals(userName)) {
                accountListDao.insertUserFollow(favorId, userName);
            }
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

    public Object appearancePrice1(String qufu, String viewName, int priceNum, String favorDate,String userID) {
        int resArr = 0;
        try {
            resArr = accountListDao.appearancePrice1(qufu,viewName,priceNum,favorDate,userID);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
    }

    public Object appearancePrice3(String qufu, String viewName,String viewContent, int priceLow, int priceHigh,int priceHN,int PRICE_HN_HIGH, String favorDate, String userID) {
        int resArr = 0;
        try {
            resArr = accountListDao.appearancePrice3(qufu,viewName,viewContent,priceLow,priceHigh,priceHN,PRICE_HN_HIGH,favorDate,userID);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
    }

    public Object queryFaxinListInfo() {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            resArr = accountListDao.queryFaxinListInfo();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
    }

    public Object queryHeziListInfo() {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            resArr = accountListDao.queryHeziListInfo();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
    }

    public Object queryPifengListInfo() {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            resArr = accountListDao.queryPifengListInfo();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
    }

    public Object queryWuxianListInfo() {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            resArr = accountListDao.queryWuxianListInfo();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
    }

    public Object queryliuxianListInfo() {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            resArr = accountListDao.queryliuxianListInfo();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
    }

    public Object querychengyiListInfo() {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            resArr = accountListDao.querychengyiListInfo();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
    }

    public Object queryqiyuListInfo() {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            resArr = accountListDao.queryqiyuListInfo();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
    }

    public Object queryc5ListInfo() {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            resArr = accountListDao.queryc5ListInfo();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
    }

    public Object queryguajianListInfo() {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            resArr = accountListDao.queryguajianListInfo();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
    }

    public Object keepQuery(String tradeType, String areaSelection, String menpai, String tixin, String faxin, String hezi, String pifeng, String wuxian, String liuxian, String chengyi, String qiyu, String chengwu, String guajia, String lowPrice, String highPrice, String info, String username,String fanganName,int fanganType,int pipeidu) {
       int res  = 0;
        try {
            res = accountListDao.keepQuery(tradeType,areaSelection,menpai,tixin,faxin,hezi,pifeng,wuxian,liuxian,chengyi,qiyu,chengwu,guajia,lowPrice,highPrice,info,username,fanganName,fanganType,pipeidu);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return res;
    }

    public Object getKeepQuery(String username) {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            resArr = accountListDao.getKeepQuery(username);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
    }

    public int delectKeepQuery(String selectId) {
        int res  = 0;
        try {
            res = accountListDao.delectKeepQuery(selectId);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return res;
    }
}
