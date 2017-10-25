package com.dao;


import com.utils.CommonDao;
import com.utils.MyDateTimeUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class appearanceSaleDao {
    private JdbcTemplate jdbcTemplate;
    private CommonDao commondao;
    public String listSql = "";
    public appearanceSaleDao(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
        if(this.jdbcTemplate !=null)
            this.commondao = new CommonDao(this.jdbcTemplate);
    }

    public List<Map<String,Object>> queryappearanceSaleInfo1(int tradeType, String selectTion1, String selectTion2, String selectTion3, String shape, int startNum, int endNum) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append("SELECT" +
                " a.*,b.USER_FOLLOW,B.USER_ISVALID,c.COLL_TYPE,c.user_id userIdColl " +
                " FROM" +
                " c_post_bar_13 a " +
                " LEFT JOIN f_user_follow b ON a.main_id = b.main_id " +
                " LEFT JOIN F_USER_COLL_INFO c ON a.USER_ID = c.user_id " +
                " WHERE" +
                " a.TRADE_TYPE = "+tradeType);
        if(!"".equals(selectTion1)||!"".equals(selectTion2)||!"".equals(selectTion3)) {
            sql.append(
                    " AND a.BELONG_QF like '%" + selectTion1 + "%" + selectTion2 + "%" + selectTion3 + "%'");
        }
        if(!"".equals(shape)) {
            sql.append(
                    " AND a.VIEW_NAME like '%" + shape + "%'");
        }
        sql.append(
                " AND a.BELONG_QF is not NULL" +
                " AND a.VIEW_NAME is not NULL" +
                " AND a.POST_CONTENT IS NOT NULL" +
//                " AND a.PRICE_NUM is NOT null"+
                " ORDER BY" +
                " a.REPLY_TIME DESC" +
                " LIMIT "+startNum+"," + endNum);
        System.out.println(sql);
        listSql =sql.toString();
        return this.commondao.query(sql.toString(), paramList);
    }

    public List<Map<String,Object>> queryappearanceSaleInfo2(int startNum, int endNum) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(
                " select a.*,b.USER_FOLLOW,B.USER_ISVALID,c.COLL_TYPE,c.user_id userIdColl  FROM c_post_bar_13 a " +
                        " LEFT JOIN f_user_follow b ON a.main_id = b.main_id" +
                        " LEFT JOIN F_USER_COLL_INFO c ON  a.MAIN_ID=c.MAIN_ID" +
                        " WHERE a.BELONG_QF is not NULL" +
                        " AND a.VIEW_NAME is not NULL" +
                        " AND a.POST_CONTENT IS NOT NULL" +
//                        " AND a.PRICE_NUM is NOT null"+
                        " ORDER BY a.REPLY_TIME DESC " +
                        " LIMIT "+startNum+","+endNum);

        System.out.println(sql);
        listSql =sql.toString();
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
        sql.append(" SELECT view_name from c_post_bar_13 group by view_name ");
        System.out.println(sql);
        return this.commondao.query(sql.toString(), paramList);
    }

    public int selectIsvalid(String userId, String mainId) throws Exception {
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

    public String selectUserId(String userName) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" select id from userinfo where username = '"+userName+"' limit 0,1");
        System.out.println(sql);
        return this.commondao.queryOne(sql.toString(), paramList);
    }

    public List<Map<String,Object>> queryCollectCont(String mainId) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" select BELONG_QF,view_name,post_content from C_POST_BAR_13 where main_id ='"+mainId + "'");
        System.out.println(sql);
        return this.commondao.query(sql.toString(), paramList);
    }

    public int insertuserIsvalid(String uuid,String userId, String mainId, String collect_date, int collect_type, int mod_id, int coll_type, String collect_cont, int collect_stusta, String favor_date) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        String createTime =  new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd HH:mm:ss").replace("\\s*","");
        sql.append(" insert into F_USER_COLL_INFO(record_id,createtime,updatetime,user_id,main_id,collect_date,collect_type,mod_id,coll_type,collect_cont,collect_stusta,favor_date) " +
                   " VAlUES('"+uuid+"','"+createTime+"','"+createTime+"',"+userId+",'"+mainId+"','"+collect_date+"',"+collect_type+","+mod_id+","+coll_type+",'"+collect_cont+"','"+collect_stusta+"','"+favor_date+"')");
        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);
    }

    public int edituserIsvalid(String userId, String mainId, int isValid) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" update F_USER_COLL_INFO set " +
                " COLL_TYPE = '"+isValid+"'"+
                " where user_id = '"+userId+"'"+
                " and main_id = '"+mainId+"'");
        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);
    }

    public List<Map<String,Object>> queryappearanceSaleSource(String mainId) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append("select REPLY_TIME,BELONG_QF,POST_CONTENT,PAGE_URL,BELONG_FLOOR from C_POST_BAR_13 where MAIN_ID ='"+mainId+"' AND URL_VALID =1");
        System.out.println(sql);
        return this.commondao.query(sql.toString(), paramList);
    }

    public List<Map<String,Object>> queryappearanceSaleSource2(int userId) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append("select user_qq from f_user_info where USER_ID = "+userId);
        System.out.println(sql);
        return this.commondao.query(sql.toString(), paramList);
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
        if(userName ==null) {
            sql.append(" INSERT into f_user_follow(RECORD_ID,CREATETIME,UPDATETIME,ISVALID,MAIN_ID,USER_FOLLOW,USER_ISVALID) VALUES('','" + dateTime + "','" + dateTime + "','1','" + mainId + "','1',0)");
        }else{
            sql.append(" INSERT into f_user_follow(RECORD_ID,CREATETIME,UPDATETIME,ISVALID,USER_ID,MAIN_ID,USER_FOLLOW,USER_ISVALID) VALUES('','"+dateTime+"','"+dateTime+"','1',(select id from userinfo where username ='"+userName+"'),'"+mainId+"','1',0)");
        }
        System.out.println(sql);
        this.commondao.update(sql.toString(), paramList);
    }

    public List<Map<String,Object>> hasAuth(String userName) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" SELECT a.*, b.mod_name FROM f_sys_mod_3 a,f_sys_mod_1 b WHERE a.MOD_ID = b.MOD_ID AND a.USER_ID = " +
                "( SELECT id FROM userinfo WHERE username = '"+userName+"') AND b.mod_id=24 AND a.SERVER_NUM > 1 ");
        System.out.println(sql);
        return this.commondao.query(sql.toString(), paramList);
    }

    public List<Map<String,Object>> queryappearanceSaleInfo3(String selectTion1, String selectTion2, String selectTion3, Map<String, Set<String>> map, int startNum, int endNum,String shape) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append("SELECT" +
                " a.*,b.USER_FOLLOW,B.USER_ISVALID,c.COLL_TYPE ,c.user_id userIdColl " +
                " FROM" +
                " c_post_bar_13 a " +
                " LEFT JOIN f_user_follow b ON a.main_id = b.main_id " +
                " LEFT JOIN F_USER_COLL_INFO c ON  a.MAIN_ID=c.MAIN_ID " +
                " WHERE" +
                " 1 = 1 ");
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
        if(map.size()>0) {
            for (Map.Entry<String, Set<String>> entry : map.entrySet()) {
                String key = entry.getKey();
               if("waiguan".equals(key)){
                    Object[] objArr = entry.getValue().toArray();
                    String[] arr = new String[objArr.length];
                    for(int i =0;i<objArr.length;i++){
                        arr[i] = objArr[i].toString();
                    }
                    sql.append(" AND (A.WAIGUAN_NAME like '%"+arr[0]+"%'");
                    for(int i=1;i<arr.length;i++){
                        sql.append(" || A.WAIGUAN_NAME like '%"+arr[i]+"%'");
                    }
                    sql.append(" || A.POST_CONTENT like '%"+shape+"%' )");
                }
            }
        }else{
            sql.append(" AND A.POST_CONTENT like '%"+shape+"%'");
        }
        sql.append(
                " AND a.BELONG_QF is not NULL" +
                        " AND a.VIEW_NAME is not NULL" +
                        " AND a.POST_CONTENT IS NOT NULL" +
//                        " AND a.PRICE_NUM is NOT null"+
                        " ORDER BY" +
                        " a.REPLY_TIME DESC" +
                        " LIMIT "+startNum+"," + endNum);
        listSql = sql.toString();
        System.out.println(sql);
        return this.commondao.query(sql.toString(), paramList);
    }

    public int quertLength(int i) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" select COUNT(RECORD_ID) FROM c_post_bar_13 where TRADE_TYPE='"+i+"'");
        System.out.println(sql);
        return Integer.parseInt(this.commondao.queryOne(sql.toString(), paramList));
    }

    public List<Map<String,Object>> queryappearanceSaleInfo22(int tradeType, int startNum) throws Exception {
        String sql = "select * from D_POST_BAR_16 WHERE TRADE_TYPE ='"+tradeType+"' ORDER BY FAVOR_DATE DESC LIMIT "+startNum+",20";
        List<Object> paramList = new ArrayList<Object>();
        listSql =sql;
        System.out.println(sql);
        return this.commondao.query(sql, paramList);
    }

    public List<Map<String,Object>> queryappearanceSaleInfo222(int tradeType, String selectTion1, String selectTion2, String selectTion3, Map<String, Set<String>> map, int startNum, int endNum, String shape) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append("SELECT a.* " +
                " FROM" +
                " D_POST_BAR_16 a " +
                " WHERE a.trade_type = '"+tradeType+"'");
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
        if(map.size()>0) {
            for (Map.Entry<String, Set<String>> entry : map.entrySet()) {
                String key = entry.getKey();
                if("waiguan".equals(key)){
                    Object[] objArr = entry.getValue().toArray();
                    String[] arr = new String[objArr.length];
                    for(int i =0;i<objArr.length;i++){
                        arr[i] = objArr[i].toString();
                    }
                    sql.append(" AND (A.VIEW_NAME like '%"+arr[0]+"%'");
                    for(int i=1;i<arr.length;i++){
                        sql.append(" || A.VIEW_NAME like '%"+arr[i]+"%'");
                    }
                    sql.append(" || A.FAVOR_INFO like '%"+shape+"%' )");
                }
            }
        }else{
            sql.append(" AND A.FAVOR_INFO like '%"+shape+"%'");
        }
        sql.append(
                " AND a.BELONG_QF is not NULL" +
                        " AND a.VIEW_NAME is not NULL" +
                        " AND a.FAVOR_INFO IS NOT NULL" +
                        " ORDER BY" +
                        " a.FAVOR_DATE DESC" +
                        " LIMIT "+startNum+"," + endNum);
        listSql = sql.toString();
        System.out.println(sql);
        return this.commondao.query(sql.toString(), paramList);
    }

    public String queryappearanceSaleSource22(String mainId) throws Exception {
        String sql = " SELECT a.USER_QQ FROM f_user_info a,d_post_bar_16 b where b.FAVOR_ID = '"+mainId+"' and b.USER_ID = a.USER_ID ";
        List<Object> paramList = new ArrayList<Object>();
        System.out.println(sql);
        return this.commondao.queryOne(sql, paramList);
    }

    public List<Map<String,Object>> queryappearanceSaleInfo33(int startNum) throws Exception {
        String sql = "select * from D_POST_BAR_17  ORDER BY TRADE_DATE DESC LIMIT "+startNum+",20";
        List<Object> paramList = new ArrayList<Object>();
        listSql =sql;
        System.out.println(sql);
        return this.commondao.query(sql, paramList);
    }

    public List<Map<String,Object>> queryappearanceSaleInfo333(String selectTion1, String selectTion2, String selectTion3, String shape, int startNum, int endNum) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append("SELECT" +
                " a.* " +
                " FROM" +
                " D_POST_BAR_17 a " +
                " WHERE" +
                " 1 = 1 ");
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
        if(!"".equals(shape)){
            sql.append(" AND a.VIEW_NAME like '%"+shape+"%' || a.VIEW_NAME_1 like '%"+shape+"%'");
        }
        sql.append(
                " AND a.BELONG_QF is not NULL" +
                        " AND a.VIEW_NAME is not NULL" +
                        " ORDER BY" +
                        " a.TRADE_DATE DESC" +
                        " LIMIT "+startNum+"," + endNum);
        listSql = sql.toString();
        System.out.println(sql);
        return this.commondao.query(sql.toString(), paramList);
    }

    public List<Map<String,Object>> queryappearanceSaleInfo4(int startNum) throws Exception {
        String sql = " SELECT" +
                " *" +
                " FROM" +
                " (" +
                " SELECT" +
                " a.FAVOR_ID," +
                " a.USER_ID," +
                " a.BELONG_QF," +
                " a.OTHER_QF," +
                " a.VIEW_NAME," +
                " a.VIEW_NAME_1," +
                " a.VIEW_CONTENT," +
                " a.PRICE_FLOOR," +
                " a.PRICE_CEILING," +
                " a.PRICE_HN," +
                " a.PRICE_HN_HIGH," +
                " a.TRADE_DATE," +
                " b.*" +
                " FROM" +
                " D_POST_BAR_17_1 a" +
                " LEFT JOIN b_post_bar_6 b ON a.VIEW_NAME = b.WAIGUAN_NAME" +
                " WHERE" +
                " 1 = 1" +
                " GROUP BY" +
                " a.BELONG_QF," +
                " a.VIEW_NAME" +
                " ORDER BY" +
                " a.TRADE_DATE DESC" +
                " ) c" +
                " ORDER BY" +
                " c.WAIGUAN_TYPE," +
                " c.WAIGUAN_SERIES LIMIT "+startNum+",20";
        List<Object> paramList = new ArrayList<Object>();
        listSql =sql;
        System.out.println(sql);
        return this.commondao.query(sql, paramList);
    }

    public List<Map<String,Object>> queryappearanceSaleInfo44(String selectTion1, String selectTion2, String selectTion3,String selectTion4, String shape, int startNum, int endNum) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" SELECT * FROM ( SELECT a.FAVOR_ID, a.USER_ID, a.BELONG_QF, a.OTHER_QF, a.VIEW_NAME,"+
                   " a.VIEW_NAME_1, a.VIEW_CONTENT, a.PRICE_FLOOR, a.PRICE_CEILING, a.PRICE_HN,"+
                   " a.PRICE_HN_HIGH, a.TRADE_DATE, b.* FROM D_POST_BAR_17_1 a LEFT JOIN b_post_bar_6 b ON a.VIEW_NAME = b.WAIGUAN_NAME "+
                   " WHERE" +
                   " 1 = 1 ");
        if(!"".equals(selectTion1)||!"".equals(selectTion2)||!"".equals(selectTion3)||!"".equals(selectTion4)) {
            if(!"".equals(selectTion4)){
                sql.append(" AND (REPLACE (" +
                        " REPLACE (a.BELONG_QF, '[', '')," +
                        " ']'," +
                        " ''" +
                        " ) like '%" + selectTion4 + "%'" +
                        " || REPLACE (" +
                        "REPLACE (a.BELONG_QF, '[', '')," +
                        "']'," +
                        "''" +
                        ") like '%" + selectTion3 + "%'" +
                        " || REPLACE (" +
                        "REPLACE (a.BELONG_QF, '[', '')," +
                        "']'," +
                        "''" +
                        ") like '%" + selectTion2 + "%'" +
                        " || REPLACE (" +
                        "REPLACE (a.BELONG_QF, '[', '')," +
                        "']'," +
                        "''" +
                        ") like '%" + selectTion1 + "%'" +
                        " || a.BELONG_QF is null || a.BELONG_QF ='' )");
            }else if(!"".equals(selectTion3)){
                sql.append(" AND (REPLACE (" +
                        " REPLACE (a.BELONG_QF, '[', '')," +
                        " ']'," +
                        " ''" +
                        " ) like '%" + selectTion1 + "%'" +
                        " || REPLACE (" +
                        "REPLACE (a.BELONG_QF, '[', '')," +
                        "']'," +
                        "''" +
                        ") like '%" + selectTion2 + "%'" +
                        " || REPLACE (" +
                        "REPLACE (a.BELONG_QF, '[', '')," +
                        "']'," +
                        "''" +
                        ") like '%" + selectTion3 + "%' " +
                        " || a.BELONG_QF is null || a.BELONG_QF ='' )");
            }else if(!"".equals(selectTion2)){
                sql.append(" AND (REPLACE (" +
                        " REPLACE (a.BELONG_QF, '[', '')," +
                        " ']'," +
                        " ''" +
                        " ) like '%" + selectTion1 + "%' " +
                        " || REPLACE (" +
                        "REPLACE (a.BELONG_QF, '[', '')," +
                        "']'," +
                        "''" +
                        ") like '%" + selectTion2 + "%' " +
                        " || a.BELONG_QF is null || a.BELONG_QF ='' )");
            }else{
                sql.append(" AND (a.BELONG_QF like '%" + selectTion1 + "%' " +
                        " || a.BELONG_QF is null || a.BELONG_QF ='' )");
            }
        }
        if(!"".equals(shape)){
            sql.append(" AND a.VIEW_NAME like '%"+shape+"%' || a.VIEW_NAME_1 like '%"+shape+"%'");
        }
        sql.append(
                " AND a.BELONG_QF is not NULL" +
                        " AND a.VIEW_NAME is not NULL" +
                        " GROUP BY a.BELONG_QF,a.VIEW_NAME " +
                        " ORDER BY" +
                        " a.TRADE_DATE DESC ) C ORDER BY c.WAIGUAN_TYPE,c.WAIGUAN_SERIES" );
        sql.append(" LIMIT "+startNum+"," + endNum );
        listSql = sql.toString();
        System.out.println(sql);
        return this.commondao.query(sql.toString(), paramList);
    }
}

