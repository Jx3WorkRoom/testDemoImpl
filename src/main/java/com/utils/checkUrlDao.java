package com.utils;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class checkUrlDao {
    private JdbcTemplate jdbcTemplate;
    private CommonDao commondao;
    public checkUrlDao(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
        if(this.jdbcTemplate !=null)
            this.commondao = new CommonDao(this.jdbcTemplate);
    }

    public List<Map<String, Object>> queryUrlIsValid(String tableName) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" select PAGE_URL,FAVOR_ID from "+tableName+" where url_valid=1");
        System.out.println(sql);
        return this.commondao.query(sql.toString(), paramList);
    }

    public void del(String id,String tableName) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" update "+tableName+" set url_valid = 0 where FAVOR_ID='"+id+"'");
        this.commondao.update(sql.toString(), paramList);
    }
}
