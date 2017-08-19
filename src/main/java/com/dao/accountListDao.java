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
                " a.*,b.USER_FOLLOW,B.USER_ISVALID " +
                " FROM" +
                " c_post_bar_12 a" +
                " LEFT JOIN f_user_follow b on a.main_id = b.main_id " +
                " WHERE" +
                " a.TRADE_TYPE = "+tradeType+" " +
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
                " select a.*,b.USER_FOLLOW,B.USER_ISVALID  FROM c_post_bar_12 a LEFT JOIN f_user_follow b on a.main_id = b.main_id " +
                        " WHERE " +
                        "  a.TRADE_TYPE = "+tradeType +
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
        int num = listSql.indexOf("LIMIT");
        sql.append(listSql.substring(0,num-1));
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

    public List<Map<String,Object>> queryAccountDetailInfo(int favorId) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" SELECT" +
                " a.*, b.*, c.*" +
                " FROM" +
                " c_post_bar_12 a" +
                " LEFT JOIN c_post_bar_14 b ON a.FAVOR_ID = b.FAVOR_ID" +
                " LEFT JOIN f_user_follow c ON a.main_id = c.main_id" +
                " WHERE" +
                " a.FAVOR_ID =" +favorId);
        System.out.println(sql);
        return this.commondao.query(sql.toString(), paramList);
    }

    public String selectUserId(String userName) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" select id from userinfo where username = '"+userName+"' LIMIT 0,1");
        System.out.println(sql);
        return this.commondao.queryOne(sql.toString(), paramList);
    }

    public int selectIsvalid(String userId, String mainId) {
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

    public List<Map<String,Object>> queryCollectCont(String mainId) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" select BELONG_QF,TIXIN,TITLE_NAME,WAIGUAN_NAME,HORSE_NAME,ARM_NAME,STRA_NAME,PEND_NAME from C_POST_BAR_12 where main_id ='"+mainId + "' GROUP BY MAIN_ID");
        System.out.println(sql);
        return this.commondao.query(sql.toString(), paramList);
    }

    public int insertuserIsvalid(String uuid, String userId, String mainId, String collect_date, int collect_type, int mod_id, int coll_type, String collect_cont, int collect_stusta, String favor_date) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        String createTime =  new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd hh:mm:ss").replace("\\s*","");
        sql.append(" insert into F_USER_COLL_INFO(record_id,createtime,updatetime,user_id,main_id,collect_date,collect_type,mod_id,coll_type,collect_cont,collect_stusta,favor_date) " +
                " VAlUES('"+uuid+"','"+createTime+"','"+createTime+"',"+userId+",'"+mainId+"','"+collect_date+"',"+collect_type+","+mod_id+","+coll_type+",'"+collect_cont+"','"+collect_stusta+"','"+favor_date+"')");
        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);
    }

    public int edituserIsvalid(String userId, String mainId, int isValided) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" update F_USER_COLL_INFO set " +
                " COLL_TYPE = '"+isValided+"'"+
                " where user_id = '"+userId+"'"+
                " and main_id = '"+mainId+"'");
        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);
    }

    public List<Map<String,Object>> queryaccountDetailSource(String mainId, int startNum, int endNum) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append("select REPLY_TIME,PAGE_URL,BELONG_FLOOR from C_POST_BAR_12 where MAIN_ID ='"+mainId+"' LIMIT "+startNum+","+endNum);
        System.out.println(sql);
        listSql = sql.toString();
        return this.commondao.query(sql.toString(), paramList);
    }

    public List<Map<String,Object>> queryaccountDetailSource2(int userId) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append("select user_qq from f_user_info where USER_ID = "+userId);
        System.out.println(sql);
        return this.commondao.query(sql.toString(), paramList);
    }

    public int accountDetailSubmitIsValid(int favorId) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" update f_user_follow set USER_ISVALID = USER_ISVALID+1 where main_id = (select main_id from c_post_bar_12 WHERE favor_id = "+favorId+")");
        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);
    }

    public int addUserFollow(int favorId) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" UPDATE f_user_follow SET user_follow = USER_FOLLOW + 1 WHERE main_id = ( SELECT main_id FROM c_post_bar_12 WHERE favor_id = "+favorId+")");
        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);
    }

    public void insertUserFollow(int favorId) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        String dateTime = new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd").replace("\\s*","");
        sql.append(" INSERT into f_user_follow(RECORD_ID,CREATETIME,UPDATETIME,ISVALID,MAIN_ID,USER_FOLLOW,USER_ISVALID) VALUES('',"+dateTime+","+dateTime+",'1',(select main_id from c_post_bar_12 where FAVOR_ID ="+favorId+" ),'1',0)");
        System.out.println(sql);
        this.commondao.update(sql.toString(), paramList);
    }
}