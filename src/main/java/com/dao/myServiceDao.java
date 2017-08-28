package com.dao;

import com.utils.CommonDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class myServiceDao {
    private JdbcTemplate jdbcTemplate;
    private CommonDao commondao;
    public String listSql = "";
    public myServiceDao(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
        if(this.jdbcTemplate !=null)
            this.commondao = new CommonDao(this.jdbcTemplate);
    }

    public List<Map<String,Object>> queryServiceInfoByUserId(String username) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" select a.*,b.mod_name FROM f_sys_mod_5 a,f_sys_mod_1 b where a.MOD_ID = b.MOD_ID and a.USER_ID = (select id from userinfo where username = '"+username+"') ");
        System.out.println(sql);
        return this.commondao.query(sql.toString(), paramList);
    }

    public List<Map<String,Object>> queryServiceInfoByUserId2(String username, int num) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append("  select a.*,b.mod_name FROM f_sys_mod_3 a,f_sys_mod_1 b where a.MOD_ID = b.MOD_ID and a.USER_ID = (select id from userinfo where username = '"+username+"') LIMIT "+num+",20");
        System.out.println(sql);
        listSql = sql.toString();
        return this.commondao.query(sql.toString(), paramList);
    }

    public List<Map<String,Object>> queryPageListNum() throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        int num = listSql.indexOf("LIMIT");
        sql.append(listSql.substring(0,num-1));
        System.out.println(sql);
        return this.commondao.query(sql.toString(), paramList);
    }

    public List<Map<String,Object>> queryServiceInfoByUserId3(String username, int num) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append("  select a.*,b.mod_name FROM f_sys_mod_4 a,f_sys_mod_1 b where a.MOD_ID = b.MOD_ID and a.USER_ID = (select id from userinfo where username = '"+username+"') order BY a.CHANGE_TIME DESC LIMIT "+num+",20");
        System.out.println(sql);
        listSql = sql.toString();
        return this.commondao.query(sql.toString(), paramList);
    }
}
