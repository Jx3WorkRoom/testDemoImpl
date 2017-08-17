package com.dao;

import com.utils.CommonDao;
import com.utils.MyDateTimeUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class goldExchangeListDao {

    private JdbcTemplate jdbcTemplate;
    private CommonDao commondao;
    String listSql = "";
    public goldExchangeListDao(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
        if(this.jdbcTemplate !=null)
            this.commondao = new CommonDao(this.jdbcTemplate);
    }

    public List<Map<String,Object>> querygoldExchangeListInfo1(int tradeType, String selectTion1, String selectTion2, String selectTion3, int startNum, int endNum) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append("SELECT" +
                " a.*, count(DISTINCT a.MAIN_ID) rowNum," +
                " b.*" +
                " FROM" +
                " c_post_bar_19 a," +
                " f_user_follow b" +
                " WHERE" +
                " a.MAIN_ID = b.MAIN_ID" +
                " AND a.BELONG_QF = '"+selectTion1+"%"+selectTion2+"%"+selectTion3+"%'" +
                " AND a.TRADE_TYPE = "+tradeType+" " +
                " AND a.BELONG_QF is not NULL" +
                " AND a.GOLD_TOTAL is not NULL" +
                " AND a.UNIT_PRICE IS NOT NULL" +
                " GROUP BY" +
                " a.MAIN_ID" +
                " ORDER BY" +
                " a.FAVOR_DATE DESC" +
                " LIMIT "+startNum+"," + endNum);
        System.out.println(sql);
        listSql = sql.toString();
        return this.commondao.query(sql.toString(), paramList);
    }

    public List<Map<String,Object>> querygoldExchangeListInfo2(int tradeType, int startNum, int endNum) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(
                " select a.*, count(distinct a.MAIN_ID) rowNum, b.* FROM c_post_bar_19 a, f_user_follow b " +
                        " WHERE a.MAIN_ID = b.MAIN_ID " +
                        " AND a.TRADE_TYPE = "+tradeType +
                        " AND a.BELONG_QF is not NULL" +
                        " AND a.GOLD_TOTAL is not NULL" +
                        " AND a.UNIT_PRICE IS NOT NULL" +
                        " GROUP BY a.MAIN_ID ORDER BY a.FAVOR_DATE DESC " +
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

    public List<Map<String,Object>> queryPageListNum(int tradeType) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(listSql.substring(0,listSql.length()-10));
        System.out.println(sql);
        return this.commondao.query(sql.toString(), paramList);
    }

    public String selectUserId(String userName) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" select id from userinfo where username = '"+userName+"' limit 0,1");
        System.out.println(sql);
        return this.commondao.queryOne(sql.toString(), paramList);
    }

    public int selectIsvalid(String userId, int mainId) {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" SELECT COLL_TYPE FROM f_user_COLL_info WHERE MAIN_ID = '"+mainId+"' AND USER_ID = '"+userId+"' ");
        System.out.println(sql);
        int COLL_TYPE = -1;
        try {
            COLL_TYPE = Integer.parseInt(this.commondao.queryOne(sql.toString(), paramList));
        }catch (Exception e){
            return COLL_TYPE;//未查到
        }
        return COLL_TYPE;
    }

    public List<Map<String,Object>> queryCollectCont(int mainId) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" select BELONG_QF,GOLD_TOTAL,UNIT_PRICE from C_POST_BAR_15 where main_id ="+mainId + " GROUP BY MAIN_ID");
        System.out.println(sql);
        return this.commondao.query(sql.toString(), paramList);
    }

    public int insertuserIsvalid(String uuid, String userId, int mainId, String collect_date, int collect_type, int mod_id, int coll_type, String collect_cont, int collect_stusta, String favor_date) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        String createTime =  new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd hh:mm:ss").replace("\\s*","");
        sql.append(" insert into F_USER_COLL_INFO(record_id,createtime,updatetime,user_id,main_id,collect_date,collect_type,mod_id,coll_type,collect_cont,collect_stusta,favor_date) " +
                " VAlUES('"+uuid+"','"+createTime+"','"+createTime+"',"+userId+","+mainId+",'"+collect_date+"',"+collect_type+","+mod_id+","+coll_type+",'"+collect_cont+"','"+collect_stusta+"','"+favor_date+"')");
        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);
    }

    public int edituserIsvalid(String userId, int mainId, int isValided) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" update F_USER_COLL_INFO set " +
                " COLL_TYPE = '"+isValided+"'"+
                " where user_id = '"+userId+"'"+
                " and main_id = '"+mainId+"'");
        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);
    }

    public List<Map<String,Object>> queryappearanceSaleSource(int userId) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append("select user_qq from f_user_info where USER_ID = "+userId);
        System.out.println(sql);
        return this.commondao.query(sql.toString(), paramList);
    }
}
