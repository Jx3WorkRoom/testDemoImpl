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
public class levelingListDao {
    private JdbcTemplate jdbcTemplate;
    private CommonDao commondao;
    String listSql = "";
    public levelingListDao(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
        if(this.jdbcTemplate !=null)
            this.commondao = new CommonDao(this.jdbcTemplate);
    }

    public List<Map<String,Object>> querylevelingListInfo1(int needType, String selectTion1, String selectTion2, String selectTion3, String shape, int startNum, int endNum) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append("SELECT" +
                " a.*,b.USER_FOLLOW,B.USER_ISVALID,c.COLL_TYPE ,c.user_id userIdColl " +
                " FROM" +
                " c_post_bar_17 a " +
                " LEFT JOIN f_user_follow b on a.main_id = b.main_id " +
                " LEFT JOIN F_USER_COLL_INFO c ON a.MAIN_ID=c.MAIN_ID  " +
                " WHERE" +
                " a.NEED_TYPE = "+needType);
        if(!"".equals(selectTion1)||!"".equals(selectTion2)||!"".equals(selectTion3)){
            if(!"".equals(selectTion1)||!"".equals(selectTion2)||!"".equals(selectTion3)) {
                if(!"".equals(selectTion3)){
                    sql.append(" AND (REPLACE (" +
                            " REPLACE (a.BELONG_QF, '[', '')," +
                            " ']'," +
                            " ''" +
                            " ) = '" + selectTion1 + "' " +
                            " || REPLACE (" +
                            "REPLACE (a.BELONG_QF, '[', '')," +
                            "']'," +
                            "''" +
                            ") = '" + selectTion1 + selectTion2 + "' " +
                            " || REPLACE (" +
                            "REPLACE (a.BELONG_QF, '[', '')," +
                            "']'," +
                            "''" +
                            ") = '" + selectTion1 + selectTion2 + selectTion3 + "' " +
                            " || a.BELONG_QF is null || a.BELONG_QF ='' )");
                }else if(!"".equals(selectTion2)){
                    sql.append(" AND (REPLACE (" +
                            " REPLACE (a.BELONG_QF, '[', '')," +
                            " ']'," +
                            " ''" +
                            " ) = '" + selectTion1 + "' " +
                            " || a.BELONG_QF "+
                            " like '%" + selectTion1 + selectTion2 + "%' " +
                            " || a.BELONG_QF is null || a.BELONG_QF ='' )");
                }else{
                    sql.append(" AND (a.BELONG_QF like '%" + selectTion1 + "%' " +
                            " || a.BELONG_QF is null || a.BELONG_QF ='' )");
                }
            }
        }
        if(!"".equals(shape)) {
            sql.append(
                    " AND a.POST_CONTENT like '%" + shape + "%'");
        }
        sql.append(
                " AND a.BELONG_QF is not NULL" +
                " AND a.POST_CONTENT IS NOT NULL" +
                " GROUP BY" +
                " a.MAIN_ID" +
                " ORDER BY" +
                " a.REPLY_TIME DESC" +
                " LIMIT "+startNum+"," + endNum);
        System.out.println(sql);
        listSql = sql.toString();
        return this.commondao.query(sql.toString(), paramList);
    }

    public List<Map<String,Object>> querylevelingListInfo2(int needType, int startNum, int endNum) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(
                " select a.*,b.USER_FOLLOW,B.USER_ISVALID,c.COLL_TYPE  FROM c_post_bar_17 a " +
                        " LEFT JOIN f_user_follow b on a.main_id = b.main_id" +
                        " LEFT JOIN F_USER_COLL_INFO c ON b.USER_ID = c.user_id" +
                        " WHERE a.NEED_TYPE = "+needType +
                        " AND a.BELONG_QF is not NULL" +
                        " AND a.POST_CONTENT IS NOT NULL" +
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

    public List<Map<String,Object>> queryPageListNum(int needType) throws Exception {
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
        sql.append(" select BELONG_QF,post_content from C_POST_BAR_17 where main_id ='"+mainId + "' GROUP BY MAIN_ID");
        System.out.println(sql);
        return this.commondao.query(sql.toString(), paramList);
    }

    public int insertuserIsvalid(String uuid, String userId, String mainId, String collect_date, int collect_type, int mod_id, int coll_type, String collect_cont, int collect_stusta, String favor_date) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        String createTime =  new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd HH:mm:ss").replace("\\s*","");
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

    public List<Map<String,Object>> querylevelingListSource(String mainId) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append("select REPLY_TIME,BELONG_QF,POST_CONTENT,PAGE_URL,BELONG_FLOOR from C_POST_BAR_17 where MAIN_ID ='"+mainId+"'");
        System.out.println(sql);
        return this.commondao.query(sql.toString(), paramList);
    }

    public List<Map<String,Object>> querylevelingListSource2(int userId) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append("select user_qq from f_user_info where USER_ID = "+userId);
        System.out.println(sql);
        return this.commondao.query(sql.toString(), paramList);
    }

    public int addUserFollow(String mainId) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" UPDATE f_user_follow SET user_follow = USER_FOLLOW + 1 WHERE main_id ='"+mainId+"'");
        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);
    }

    public void insertUserFollow(String mainId,String userName) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        String dateTime = new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd HH:mm:ss").replace("\\s*","");
        if(userName==null) {
            sql.append(" INSERT into f_user_follow(RECORD_ID,CREATETIME,UPDATETIME,ISVALID,MAIN_ID,USER_FOLLOW,USER_ISVALID) VALUES('','" + dateTime + "','" + dateTime + "','1','" + mainId + "','1',0)");
        }else{
            sql.append(" INSERT into f_user_follow(RECORD_ID,CREATETIME,UPDATETIME,ISVALID,USER_ID,MAIN_ID,USER_FOLLOW,USER_ISVALID) VALUES('','"+dateTime+"','"+dateTime+"','1',(select id from userinfo where username ='"+userName+"'),'"+mainId+"','1',0)");
        }
        System.out.println(sql);
        this.commondao.update(sql.toString(), paramList);
    }

    public int protDisable(String mainId) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" update F_USER_follow set " +
                " user_isvalid = user_isvalid+1 "+
                " where main_id = '"+mainId+"'");
        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);
    }

    public List<Map<String,Object>> hasAuth(String userName) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" SELECT a.*, b.mod_name FROM f_sys_mod_3 a,f_sys_mod_1 b WHERE a.MOD_ID = b.MOD_ID AND a.USER_ID = " +
                "( SELECT id FROM userinfo WHERE username = '"+userName+"') AND b.mod_id=54 AND a.SERVER_NUM > 1 ");
        System.out.println(sql);
        return this.commondao.query(sql.toString(), paramList);
    }

    public List<Map<String,Object>> queryLevelingDetailInfo(String favorId,String userId) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" SELECT" +
                " a.*, b.*, c.*,d.user_qq" +
                " FROM" +
                " c_post_bar_17 a" +
                " LEFT JOIN A_POST_BAR_JX3_3 b ON a.THEME_ID = b.THEME_ID and  a.BELONG_FLOOR = b.BELONG_FLOOR " +
                " LEFT JOIN f_user_follow c ON a.main_id = c.main_id" +
                " LEFT JOIN f_user_info d ON a.user_id = d.user_id and a.user_id ='"+userId+"'" +
                " WHERE" +
                " a.FAVOR_ID =" +favorId);
        System.out.println(sql);
        return this.commondao.query(sql.toString(), paramList);
    }

    public List<Map<String,Object>> queryLevelingDetailInfo2(String favorId,String userId) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" SELECT" +
                " a.*, b.*, c.*,d.user_qq" +
                " FROM" +
                " c_post_bar_17 a" +
                " LEFT JOIN d_post_bar_21  b ON a.favor_id = b.favor_id " +
                " LEFT JOIN f_user_follow c ON a.main_id = c.main_id" +
                " LEFT JOIN f_user_info d ON a.user_id = d.user_id and a.user_id ='"+userId+"'" +
                " WHERE" +
                " a.FAVOR_ID =" +favorId);
        System.out.println(sql);
        return this.commondao.query(sql.toString(), paramList);
    }

}
