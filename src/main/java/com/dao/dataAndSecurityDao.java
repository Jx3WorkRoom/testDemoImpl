package com.dao;


import com.utils.CommonDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class dataAndSecurityDao {
    private JdbcTemplate jdbcTemplate;
    private CommonDao commondao;
    public String listSql = "";
    public dataAndSecurityDao(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
        if(this.jdbcTemplate !=null)
            this.commondao = new CommonDao(this.jdbcTemplate);
    }

    public List<Map<String,Object>> getUserInfo(String usernmae) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" select * from f_user_info where user_id = (select id from userinfo where username ='"+usernmae+"' ) limit 0,1");
        System.out.println(sql);
        return this.commondao.query(sql.toString(), paramList);
    }

    public int editUserInfo(int userId, String key, String newValue) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" update f_user_info set "+key+" = '"+newValue+"' where USER_ID ="+userId);
        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);
    }

    public String userIsEmpty2(String loginName) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" select user_tel from f_user_info where login_name = '"+loginName+"'");
        System.out.println(sql);
        return this.commondao.queryOne(sql.toString(), paramList);
    }

    public void HasTel(String newtel) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" select user_tel from f_user_info where user_tel = '"+newtel+"' limit 0,1");
        System.out.println(sql);
        this.commondao.queryOne(sql.toString(), paramList);
    }
}
