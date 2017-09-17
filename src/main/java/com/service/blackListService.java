package com.service;

import com.utils.MyDateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.blackListDao;

import java.util.*;

@Service
public class blackListService {
    @Autowired
    blackListDao blackListDao;

    public Object queryblackListInfo(String shape, int pageNumSelected, int startNum, int endNum) {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            if((startNum==0 || endNum ==10) && pageNumSelected !=1){
                startNum = (pageNumSelected-1)*10;
                endNum = startNum+10;
            }
            resArr = blackListDao.queryblackListInfo(shape,startNum,endNum);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
    }

    public Object queryPageListNum() {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            resArr = blackListDao.queryPageListNum();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr.size();
    }

    public String userIsvalid(String userName, String mainId, int isValided, String replyTime) throws Exception {
        String userId = blackListDao.selectUserId(userName);
        int result = blackListDao.selectIsvalid(userId,mainId);
        if(result==-1){
            String COLLECT_DATE = new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd").replace("\\s*","");
            String recordId = UUID.randomUUID().toString();
            int COLLECT_TYPE = 6;
            int MOD_ID = 62;
            int COLL_TYPE = 1;
            StringBuffer COLLECT_CONT = new StringBuffer();
            int COLLECT_STUSTA = isValided;
            String FAVOR_DATE = replyTime.replace("\\s*","");
            List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
            try {
                resArr = blackListDao.queryCollectCont(mainId);
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
            int insertResult = blackListDao.insertuserIsvalid(recordId,userId,mainId,COLLECT_DATE,COLLECT_TYPE,MOD_ID,COLL_TYPE,COLLECT_CONT.toString(),COLLECT_STUSTA,FAVOR_DATE);
            if(insertResult==1) {
                return "收藏成功!";
            }else{
                return "收藏成功!";
            }
        }else {
            int edutResult = blackListDao.edituserIsvalid(userId,mainId,isValided);
            if (isValided == 1)
                return "已收藏";
            else
                return "已取消收藏";
        }
    }

    public Object queryblackListByFavorIdInfo(int favorId) {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            resArr = blackListDao.queryblackListByFavorIdInfo(favorId);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
    }

    public void addUserFollow(int favorId,String username) throws Exception {
        int resultNum = blackListDao.addUserFollow(favorId);
        if(resultNum==0){
            if(!"".equals(username)) {
                try {
                    blackListDao.insertUserFollow(favorId, username);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
