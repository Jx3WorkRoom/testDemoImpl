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

    public List<Map<String,Object>> queryIndex(int tradeType) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" select a.*, count(distinct a.MAIN_ID) rowNum, b.* FROM c_post_bar_12 a, f_user_follow b " +
                    " WHERE a.MAIN_ID = b.MAIN_ID " +
                    " AND a.TRADE_TYPE = "+tradeType+" " +
                    " AND a.BELONG_QF is not NULL" +
                    " AND a.TIXIN is not NULL" +
                    " AND a.REPLY_CONTENT IS NOT NULL" +
                    " AND a.PRICE_NUM is NOT null"+
                    " GROUP BY a.MAIN_ID ORDER BY a.REPLY_TIME DESC LIMIT 0,10");
        System.out.println(sql);
        return this.commondao.query(sql.toString(), paramList);
    }
}
