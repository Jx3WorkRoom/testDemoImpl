package com.service;

import com.dao.userDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/15 0015.
 */
@Service
public class userService {
    @Autowired
    userDao userDao;

    public List<Map<String,Object>> queryUser() {
        List<Map<String, Object>> resArr = new ArrayList<Map<String, Object>>();
        try {
            resArr = userDao.queryUser();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resArr;
    }

    public int editUser(String id, String username,Integer usergroup, Integer userAuthority, String password, String employeeNo,String createTime, String role) throws Exception {
        int editSureFlag = userDao.editUser(id,username,usergroup,userAuthority,password,employeeNo,createTime,role);
        return editSureFlag;
    }

    public int delUser(String[] ids) throws Exception {
        int delSureFlag = userDao.delUser(ids);
        return delSureFlag;
    }

    public int addUser(String username, String password, Integer userGroup, String employeeNo, Integer userAuthority, String createTime, String role) throws Exception {
        int addSureFlag = userDao.addUser(username,password,userGroup,employeeNo,userAuthority,createTime,role);
        return addSureFlag;
    }

    public int testAddTable(String str) throws Exception {
        int testAddTableFlag = userDao.testAddUserTable(str);
        return testAddTableFlag;
    }
}
