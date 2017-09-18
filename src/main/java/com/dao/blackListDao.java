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
public class blackListDao {
    private JdbcTemplate jdbcTemplate;
    private CommonDao commondao;
    String listSql = "";
    public blackListDao(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
        if(this.jdbcTemplate !=null)
            this.commondao = new CommonDao(this.jdbcTemplate);
    }

    public List<Map<String,Object>> queryblackListInfo(String shape, int startNum, int endNum) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        if("".equals(shape)){
            sql.append(" select c.*," +
                    "  d.USER_FOLLOW," +
                    " d.USER_ISVALID," +
                    " e.COLL_TYPE,e.user_id userIdColl from (SELECT" +
                    " a.*," +
                    " b.PAR_NAME" +
                    " FROM" +
                    " c_post_bar_11 a," +
                    " g_pub_par_1 b" +
                    " WHERE" +
                    " a.CHEAT_INFO IS NOT NULL" +
                    " AND b.PAR_SERIES = 1016" +
                    " AND b.PAR_NUM = a.CHEAT_TYPE" +
                    " GROUP BY" +
                    " a.MAIN_ID" +
                    " ORDER BY" +
                    " a.FAVOR_DATE DESC)  c LEFT JOIN f_user_follow d " +
                    " ON c.main_id = d.main_id  LEFT JOIN F_USER_COLL_INFO e ON e.MAIN_ID=c.MAIN_ID LIMIT "+startNum+","+endNum
            );
        }else{
            sql.append(" select c.*," +
                    "  d.USER_FOLLOW," +
                    " d.USER_ISVALID," +
                    " e.COLL_TYPE ,e.user_id userIdColl from (SELECT" +
                    " a.*," +
                    " b.PAR_NAME" +
                    " FROM" +
                    " c_post_bar_11 a," +
                    " g_pub_par_1 b" +
                    " WHERE" +
                    " a.CHEAT_INFO IS NOT NULL" +
                    " AND a.CHEAT_INFO like '%"+shape+"%'" +
                    " AND b.PAR_SERIES = 1016" +
                    " AND b.PAR_NUM = a.CHEAT_TYPE" +
                    " GROUP BY" +
                    " a.MAIN_ID" +
                    " ORDER BY" +
                    " a.FAVOR_DATE DESC)  c LEFT JOIN f_user_follow d ON " +
                    " c.main_id = d.main_id LEFT JOIN F_USER_COLL_INFO e ON e.MAIN_ID=c.MAIN_ID LIMIT "+startNum+","+endNum
            );
        }
        System.out.println(sql);
        listSql = sql.toString();
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
        sql.append(" select BELONG_QF,TIXIN,ROLE_NAME,CHEAT_INFO from C_POST_BAR_11 where main_id ='"+mainId + "' GROUP BY MAIN_ID");
        System.out.println(sql);
        return this.commondao.query(sql.toString(), paramList);
    }

    public int insertuserIsvalid(String uuid, String userId, String mainId, String collect_date, int collect_type, int mod_id, int coll_type, String collect_cont, int collect_stusta, String favor_date) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        String createTime =  new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd hh:mm:ss").replace("\\s*","");
        sql.append(" insert into F_USER_COLL_INFO(record_id,createtime,updatetime,user_id,main_id,collect_date,collect_type,mod_id,coll_type,collect_cont,collect_stusta,favor_date) " +
                " VAlUES('"+uuid+"','"+createTime+"','"+createTime+"',"+userId+",'"+mainId+"','"+collect_date+"',"+collect_type+","+mod_id+","+1+",'"+collect_cont+"','"+collect_stusta+"','"+favor_date+"')");
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

    public List<Map<String,Object>> queryblackListByFavorIdInfo(int favorId) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" select * from (SELECT" +
                " a.*," +
                " b.PAR_NAME" +
                " FROM" +
                " c_post_bar_11 a," +
                " g_pub_par_1 b" +
                " WHERE" +
                " a.FAVOR_ID =" +favorId+
                " AND" +
                " b.PAR_SERIES = 1016" +
                " and" +
                " a.CHEAT_TYPE = b.PAR_NUM) c LEFT JOIN D_post_bar_21 d ON c.favor_id = c.favor_id");
        System.out.println(sql);
        return this.commondao.query(sql.toString(), paramList);
    }

    public int addUserFollow(int favorId) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" UPDATE f_user_follow SET user_follow = USER_FOLLOW + 1 WHERE main_id = ( SELECT main_id FROM c_post_bar_11 WHERE favor_id = "+favorId+")");
        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);
    }

    public void insertUserFollow(int favorId,String username) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        String dateTime = new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd hh:mm:ss").replace("\\s*","");
        if(username==null) {
            sql.append(" INSERT into f_user_follow(RECORD_ID,CREATETIME,UPDATETIME,ISVALID,MAIN_ID,USER_FOLLOW,USER_ISVALID) VALUES('','" + dateTime + "','" + dateTime + "','1','" + favorId + "','1',0)");
        }else{
            sql.append(" INSERT into f_user_follow(RECORD_ID,CREATETIME,UPDATETIME,ISVALID,USER_ID,MAIN_ID,USER_FOLLOW,USER_ISVALID) VALUES('','"+dateTime+"','"+dateTime+"','1',(select id from userinfo where username ='"+username+"'),'"+favorId+"','1',0)");
        }
        System.out.println(sql);
        this.commondao.update(sql.toString(), paramList);
    }
}
