package com.service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.dao.accountListDao;
import org.springframework.stereotype.Service;

@Service
public class accountListService {

    @Autowired
    accountListDao accountListDao;

    public Object queryAccountListInfo(int tradeType, String areaSelection, String shape, String info, int pageNumSelected, int startNum, int endNum) {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            if((startNum==0 || endNum ==10) && pageNumSelected !=1){
                startNum = (pageNumSelected-1)*10;
                endNum = startNum+10;
            }
            if("".equals(areaSelection)&&"".equals(shape)&&"".equals(info)){
                resArr = accountListDao.queryAccountListInfo2(tradeType,startNum,endNum);
            }else{
                String selectTion1 = areaSelection.split(",")[0];
                String selectTion2 = areaSelection.split(",")[1];
                String selectTion3 = areaSelection.split(",")[2];
                resArr = accountListDao.queryAccountListInfo1(tradeType,selectTion1,selectTion2,selectTion3,shape,info,startNum,endNum);
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
}
