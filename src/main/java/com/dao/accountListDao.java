package com.dao;

import com.utils.CommonDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class accountListDao {
    private JdbcTemplate jdbcTemplate;
    private CommonDao commondao;
    public String listSql = "";
    public accountListDao(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
        if(this.jdbcTemplate !=null)
            this.commondao = new CommonDao(this.jdbcTemplate);
    }

    public List<Map<String,Object>> queryAccountListInfo1(
            int tradeType, String selectTion1, String selectTion2, String selectTion3,
            String shape, String info, int startNum, int endNum) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append("SELECT" +
                " a.*, count(DISTINCT a.MAIN_ID) rowNum," +
                " b.*" +
                " FROM" +
                " c_post_bar_12 a," +
                " f_user_follow b" +
                " WHERE" +
                " a.MAIN_ID = b.MAIN_ID" +
                " AND a.BELONG_QF like '"+selectTion1+"%"+selectTion2+"%"+selectTion3+"%'");
        if(!"".equals(shape)) {
            sql.append(
                    " AND a.TIXIN like '%" + shape + "%'");
        }
        if(!"".equals(info)) {
            sql.append(
                    " AND a.REPLY_CONTENT like'%" + info + "%'");
        }
        sql.append(
                " AND a.TRADE_TYPE = "+tradeType+" " +
                " AND a.BELONG_QF is not NULL" +
                " AND a.TIXIN is not NULL" +
                " AND a.REPLY_CONTENT IS NOT NULL" +
                " AND a.PRICE_NUM is NOT null"+
                " GROUP BY" +
                " a.MAIN_ID" +
                " ORDER BY" +
                " a.REPLY_TIME DESC" +
                " LIMIT "+startNum+"," + endNum);
        System.out.println(sql);
        listSql = sql.toString();
        return this.commondao.query(sql.toString(), paramList);
    }

    public List<Map<String,Object>> queryAccountListInfo2(int tradeType, int startNum, int endNum) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(
                " select a.*, count(distinct a.MAIN_ID) rowNum, b.* FROM c_post_bar_12 a, f_user_follow b " +
                        " WHERE a.MAIN_ID = b.MAIN_ID " +
                        " AND a.TRADE_TYPE = "+tradeType +
                        " AND a.BELONG_QF is not NULL" +
                        " AND a.TIXIN is not NULL" +
                        " AND a.REPLY_CONTENT IS NOT NULL" +
                        " AND a.PRICE_NUM is NOT null"+
                        " GROUP BY a.MAIN_ID ORDER BY a.REPLY_TIME DESC " +
                        " LIMIT "+startNum+","+endNum);

        System.out.println(sql);
        listSql = sql.toString();
        return this.commondao.query(sql.toString(), paramList);
    }

    public List<Map<String,Object>> querySelectionListInfo() throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" select qufu_type,qufu_qu,qufu_fu from c_post_bar_21 ");
        System.out.println(sql);
        return this.commondao.query(sql.toString(), paramList);
    }

    public List<Map<String,Object>> queryPageListNum() throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(listSql.substring(0,listSql.length()-10));
        System.out.println(sql);
        return this.commondao.query(sql.toString(), paramList);
    }

    public List<Map<String,Object>> queryTixinListInfo() throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" select * from b_post_bar_big WHERE KEYWORD_BIG_TYPE = '2-user_mptixing' ");
        System.out.println(sql);
        return this.commondao.query(sql.toString(), paramList);
    }

    public List<Map<String,Object>> queryInfoListInfo() throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" SELECT * FROM b_post_bar_big " +
                " WHERE KEYWORD_BIG_TYPE != '4-user_xingxi'  " +
                " && KEYWORD_BIG_TYPE != '9-user_daoju' " +
                " && KEYWORD_BIG_TYPE != '10-user_dailian' " +
                " && KEYWORD_BIG_TYPE != '11-user_qiza' " +
                " && KEYWORD_BIG_TYPE != '12-user_qufu' " +
                " && KEYWORD_BIG_TYPE != '15-user_jinbi'");
        System.out.println(sql);
        return this.commondao.query(sql.toString(), paramList);
    }
}
