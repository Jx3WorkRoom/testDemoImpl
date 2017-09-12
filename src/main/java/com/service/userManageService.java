package com.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.userManageDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class userManageService {
    @Autowired
    userManageDao userManageDao;

    public Object userManageInfo() {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            resArr = userManageDao.userManageInfo();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
    }

    public Object delMod(int modId) throws Exception {
        int result = userManageDao.delMod(modId);
        if(result>0){
            return "删除成功!";
        }else{
            return "删除失败!";
        }
    }

    public Object addMod(String belong_web, int modId, String mod_name, int modType, int visitRole, int registRole, String serverCost, String serverNum) throws Exception {
        int result = userManageDao.addMod(belong_web,modId,mod_name,modType,visitRole,registRole,serverCost,serverNum);
        if(result>0){
            return "添加成功!";
        }else{
            return "添加失败!";
        }
    }

    public Object editMod(String belong_web, int modId, String mod_name, int modType, int visitRole, int registRole, String serverCost, String serverNum) throws Exception {
        int result = userManageDao.editMod(belong_web,modId,mod_name,modType,visitRole,registRole,serverCost,serverNum);
        if(result>0){
            return "修改成功!";
        }else{
            return "修改失败!";
        }
    }

    public Object modDetail(int modId) {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            resArr = userManageDao.modDetail(modId);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
    }
}
