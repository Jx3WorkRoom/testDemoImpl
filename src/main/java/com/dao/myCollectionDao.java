package com.dao;

import com.utils.CommonDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class myCollectionDao {
    private JdbcTemplate jdbcTemplate;
    private CommonDao commondao;
    String listSql ="";
    public myCollectionDao(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
        if(this.jdbcTemplate !=null)
            this.commondao = new CommonDao(this.jdbcTemplate);
    }

    public List<Map<String,Object>> queryPageListNum() throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        int num = listSql.indexOf("LIMIT");
        sql.append(listSql.substring(0,num-1));
        System.out.println(sql);
        return this.commondao.query(sql.toString(), paramList);
    }

    public List<Map<String,Object>> myCollectionInfo(String userName, int num) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" select * from  f_user_coll_info where  USER_ID = (select USER_ID from userinfo where username='"+userName+"') AND COLL_TYPE =1 LIMIT "+num+",10");
        System.out.println(sql);
        listSql = sql.toString();
        return this.commondao.query(sql.toString(), paramList);
    }

    public int removeRecord(String[] ids) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();

        sql.append(" update f_user_coll_info set COLL_TYPE = 0 where RECORD_ID='"+ids[0]+"'");
        for(int i=0;i<ids.length;i++){
            sql.append(" and RECORD_ID='"+ids[0]+"'");
        }
        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);
    }
}
