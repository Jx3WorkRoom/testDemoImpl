package com.dao;

import com.utils.CommonDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class userServiceDao {
    private JdbcTemplate jdbcTemplate;
    private CommonDao commondao;
    public String listSql = "";
    public userServiceDao(JdbcTemplate jdbcTemplate) {
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

    public List<Map<String,Object>> getServiceDetail(String searchInfo, int searchType) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" select a.*,b.mod_name FROM f_sys_mod_5 a,f_sys_mod_1 b where a.MOD_ID = b.MOD_ID ");
        if(!"".equals(searchInfo)) {
            if (searchType == 1) {
                sql.append(" and a.USER_ID in (select user_id from f_user_info where LOGIN_NAME like '%" + searchInfo + "%') ");
            } else if (searchType == 2) {
                sql.append("  and a.USER_ID in (select user_id from f_user_info where USER_NAME like '%" + searchInfo + "%') ");
            } else if (searchType == 3) {
                sql.append("  and a.USER_ID in (select user_id from f_user_info where USER_TEL like '%" + searchInfo + "%') ");
            }
        }
        System.out.println(sql);
        return this.commondao.query(sql.toString(), paramList);
    }

    public List<Map<String,Object>> getServiceDetail2(String searchInfo, int searchType, int num) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append("  select a.*,b.mod_name FROM f_sys_mod_3 a,f_sys_mod_1 b where a.MOD_ID = b.MOD_ID " );
        if(!"".equals(searchInfo)) {
            if (searchType == 1) {
                sql.append(" and a.USER_ID in (select user_id from f_user_info where LOGIN_NAME like '%" + searchInfo + "%') ");
            } else if (searchType == 2) {
                sql.append("  and a.USER_ID in (select user_id from f_user_info where USER_NAME like '%" + searchInfo + "%') ");
            } else if (searchType == 3) {
                sql.append("  and a.USER_ID in (select user_id from f_user_info where USER_TEL like '%" + searchInfo + "%') ");
            }
        }
        sql.append(" LIMIT "+num+",20");
        System.out.println(sql);
        listSql = sql.toString();
        return this.commondao.query(sql.toString(), paramList);
    }

    public List<Map<String,Object>> getServiceDetail3(String searchInfo, int searchType, int num) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append("  select a.*,b.mod_name FROM f_sys_mod_4 a,f_sys_mod_1 b where a.MOD_ID = b.MOD_ID " );
        if(!"".equals(searchInfo)) {
            if (searchType == 1) {
                sql.append(" and a.USER_ID in (select user_id from f_user_info where LOGIN_NAME like '%" + searchInfo + "%') ");
            } else if (searchType == 2) {
                sql.append("  and a.USER_ID in (select user_id from f_user_info where USER_NAME like '%" + searchInfo + "%') ");
            } else if (searchType == 3) {
                sql.append("  and a.USER_ID in (select user_id from f_user_info where USER_TEL like '%" + searchInfo + "%') ");
            }
        }
        sql.append(" LIMIT "+num+",20");
        System.out.println(sql);
        listSql = sql.toString();
        return this.commondao.query(sql.toString(), paramList);
    }
}
