package com.dao;

import com.utils.CommonDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class userManageDao {
    private JdbcTemplate jdbcTemplate;
    private CommonDao commondao;
    public userManageDao(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
        if(this.jdbcTemplate !=null)
            this.commondao = new CommonDao(this.jdbcTemplate);
    }

    public List<Map<String,Object>> userManageInfo() throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" SELECT a.*,b.START_DATE,b.SERVER_COST,b.SERVER_NUM FROM f_sys_mod_1 a LEFT JOIN f_sys_mod_2 b ON a.mod_id = b.MOD_ID ORDER BY a.MOD_ID ");
        System.out.println(sql);
        return this.commondao.query(sql.toString(), paramList);
    }
}
