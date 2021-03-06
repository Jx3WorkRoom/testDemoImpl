package com.dao;

import com.utils.CommonDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class IndexDao {

    private JdbcTemplate jdbcTemplate;
    private CommonDao commondao;
    public IndexDao(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
        if(this.jdbcTemplate !=null)
            this.commondao = new CommonDao(this.jdbcTemplate);
    }

    public List<Map<String,Object>> queryIndex() throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" select * from d_post_bar_30 order by RECORD_DATE desc limit 0,1");
        System.out.println(sql);
        return this.commondao.query(sql.toString(), paramList);
    }

    public List<Map<String,Object>> queryIndex2(int tradeType) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" select a.*,b.USER_FOLLOW,B.USER_ISVALID FROM c_post_bar_13 a LEFT JOIN f_user_follow b on a.main_id = b.main_id " +
                " WHERE a.TRADE_TYPE = "+tradeType+
//                " AND a.BELONG_QF is not NULL" +
                " AND a.VIEW_NAME is not NULL" +
                " AND a.POST_CONTENT IS NOT NULL" +
//                " AND a.PRICE_NUM is NOT null"+
                " ORDER BY a.REPLY_TIME DESC LIMIT 0,6");
        System.out.println(sql);
        return this.commondao.query(sql.toString(), paramList);
    }

    public List<Map<String,Object>> queryIndex3(int tradeType) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" select a.*,b.USER_FOLLOW,B.USER_ISVALID FROM c_post_bar_15 a LEFT JOIN f_user_follow b on a.main_id = b.main_id " +
                " WHERE a.TRADE_TYPE = "+tradeType+
//                " AND a.BELONG_QF is not NULL" +
                " AND a.THEME_NAME is not NULL" +
                " AND a.POST_CONTENT IS NOT NULL" +
//                " AND a.PRICE_NUM is NOT null"+
                " ORDER BY a.REPLY_TIME DESC LIMIT 0,6");
        System.out.println(sql);
        return this.commondao.query(sql.toString(), paramList);
    }
}
