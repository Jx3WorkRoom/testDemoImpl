package com.dao;

import com.utils.CommonDao;
import com.utils.MyDateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

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
                " c_post_bar_12_1 a" +
                " LEFT JOIN f_user_follow b on a.main_id = b.main_id " +
                " WHERE" +
                " a.TRADE_TYPE = "+tradeType);
        if(!"".equals(selectTion1)||!"".equals(selectTion2)||!"".equals(selectTion3))
            sql.append(
                    " AND a.BELONG_QF like '%"+selectTion1+"%"+selectTion2+"%"+selectTion3+"%'");
        if(!"".equals(shape)) {
            sql.append(
                    " AND a.TIXIN_1 like '%" + shape + "%'");
        }
        if(!"".equals(info)) {
            sql.append(
                    " AND a.REPLY_CONTENT like'%" + info + "%'");
        }
        sql.append(
                " AND a.BELONG_QF is not NULL" +
                " AND a.TIXIN_1 is not NULL" +
                " AND a.REPLY_CONTENT IS NOT NULL" +
//                " AND a.PRICE_NUM is NOT null"+
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
                " select a.*,b.USER_FOLLOW,B.USER_ISVALID  FROM c_post_bar_12_1 a LEFT JOIN f_user_follow b on a.main_id = b.main_id " +
                        " WHERE " +
                        "  a.TRADE_TYPE = "+tradeType +
                        " AND a.BELONG_QF is not NULL" +
                        " AND a.TIXIN_1 is not NULL" +
                        " AND a.REPLY_CONTENT IS NOT NULL" +
//                        " AND a.PRICE_NUM is NOT null"+
                        " ORDER BY a.REPLY_TIME DESC " +
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

    public String queryPageListNum() throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        int num = listSql.indexOf("LIMIT");
        sql.append(" SELECT COUNT(*) FROM ( ");
        sql.append(listSql.substring(0, num - 1));
        sql.append(" ) allNum ");
        System.out.println(sql);
        return this.commondao.queryOne(sql.toString(), paramList);

    }

    public List<Map<String,Object>> queryTixinListInfo() throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" select menpai_name from b_post_bar_1 ");
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
                " a.*, b.WENJIAN_PATH,b.WENJIAN_SEQ" +
                " FROM" +
                " c_post_bar_12_1 a" +
                " LEFT JOIN A_POST_BAR_JX3_3 b ON a.THEME_ID = b.THEME_ID and  a.BELONG_FLOOR = b.BELONG_FLOOR " +
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
        sql.append(" select REPLY_CONTENT from C_POST_BAR_12 where main_id ='"+mainId + "'");
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

    public List<Map<String,Object>> queryaccountDetailSource(String mainId, int startNum, int endNum) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append("select REPLY_TIME,PAGE_URL,BELONG_FLOOR from C_POST_BAR_12 where MAIN_ID ='"+mainId+"' AND URL_VALID =1 LIMIT "+startNum+","+endNum);
        System.out.println(sql);
        listSql = sql.toString();
        return this.commondao.query(sql.toString(), paramList);
    }

    public List<Map<String,Object>> queryaccountDetailSource2(String userName) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append("select user_qq from f_user_info where LOGIN_NAME = '"+userName+"'");
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

    public void insertUserFollow(int favorId,String userName) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        String dateTime = new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd HH:mm:ss").replace("\\s*","");
        sql.append(" INSERT into f_user_follow(RECORD_ID,CREATETIME,UPDATETIME,ISVALID,USER_ID,MAIN_ID,USER_FOLLOW,USER_ISVALID) VALUES('','"+dateTime+"','"+dateTime+"','1',(select id from userinfo where username ='"+userName+"'),(select main_id from c_post_bar_12 where FAVOR_ID ="+favorId+" ),'1',0)");
        System.out.println(sql);
        this.commondao.update(sql.toString(), paramList);
    }

    public List<Map<String,Object>> queryHasCollected(String mainId, String username) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" select coll_type from f_user_coll_info where main_id = '"+mainId+"' and user_id =(select id from userinfo where username = '"+username+"')");
        System.out.println(sql);
        return this.commondao.query(sql.toString(), paramList);
    }

    public List<Map<String, Object>> hasAuth(String username) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" SELECT a.*, b.mod_name FROM f_sys_mod_3 a,f_sys_mod_1 b WHERE a.MOD_ID = b.MOD_ID AND a.USER_ID = " +
                "( SELECT id FROM userinfo WHERE username = '"+username+"') AND b.mod_id=14 AND a.SERVER_NUM > 1 ");
        System.out.println(sql);
        return this.commondao.query(sql.toString(), paramList);
    }

    public List<Map<String,Object>> queryAccountListInfo3(int tradeType, String selectTion1, String selectTion2, String selectTion3, String shape, Map<String, Set<String>> map, int startNum, int endNum,String info,String lowPrice,String highPrice,String hasChecked) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append("SELECT" +
                " a.*,b.USER_FOLLOW,B.USER_ISVALID " +
                " FROM" +
                " c_post_bar_12_1 a" +
                " LEFT JOIN f_user_follow b on a.main_id = b.main_id " +
                " WHERE" +
                " a.TRADE_TYPE = "+tradeType);
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
        if(!"".equals(shape)) {
            sql.append(
                    " AND a.TIXIN_1 like '%" + shape + "%'");
        }
        if(map.size()>0) {
            sql.append(" AND( ");
            for (Map.Entry<String, Set<String>> entry : map.entrySet()) {
                String key = entry.getKey();
                if("title".equals(key)){
                    Object[] objArr = entry.getValue().toArray();
                    String[] arr = new String[objArr.length];
                    for(int i =0;i<objArr.length;i++){
                        arr[i] = objArr[i].toString();
                    }
                    sql.append(" A.TITLE_NAME like '%"+arr[0]+"%'");
                    for(int i=1;i<arr.length;i++){
                        sql.append(" || A.TITLE_NAME like '%"+arr[i]+"%'");
                    }
                    sql.append("||");
                }else if("waiguan".equals(key)){
                    Object[] objArr = entry.getValue().toArray();
                    String[] arr = new String[objArr.length];
                    for(int i =0;i<objArr.length;i++){
                        arr[i] = objArr[i].toString();
                    }
                    sql.append(" A.WAIGUAN_NAME like '%"+arr[0]+"%'");
                    for(int i=1;i<arr.length;i++){
                        sql.append(" || A.WAIGUAN_NAME like '%"+arr[i]+"%'");
                    }
                    sql.append("||");
                }else if("horse".equals(key)){
                    Object[] objArr = entry.getValue().toArray();
                    String[] arr = new String[objArr.length];
                    for(int i =0;i<objArr.length;i++){
                        arr[i] = objArr[i].toString();
                    }
                    sql.append(" A.HORSE_NAME like '%"+arr[0]+"%'");
                    for(int i=1;i<arr.length;i++){
                        sql.append(" || A.HORSE_NAME like '%"+arr[i]+"%'");
                    }
                    sql.append("||");
                }else if("arm".equals(key)){
                    Object[] objArr = entry.getValue().toArray();
                    String[] arr = new String[objArr.length];
                    for(int i =0;i<objArr.length;i++){
                        arr[i] = objArr[i].toString();
                    }
                    sql.append(" A.ARM_NAME like '%"+arr[0]+"%'");
                    for(int i=1;i<arr.length;i++){
                        sql.append(" || A.ARM_NAME like '%"+arr[i]+"%'");
                    }
                    sql.append("||");
                }else if("stra".equals(key)){
                    Object[] objArr = entry.getValue().toArray();
                    String[] arr = new String[objArr.length];
                    for(int i =0;i<objArr.length;i++){
                        arr[i] = objArr[i].toString();
                    }
                    sql.append(" A.STRA_NAME like '%"+arr[0]+"%'");
                    for(int i=1;i<arr.length;i++){
                        sql.append(" || A.STRA_NAME like '%"+arr[i]+"%'");
                    }
                    sql.append("||");
                }else if("pend".equals(key)){
                    Object[] objArr = entry.getValue().toArray();
                    String[] arr = new String[objArr.length];
                    for(int i =0;i<objArr.length;i++){
                        arr[i] = objArr[i].toString();
                    }
                    sql.append(" A.PEND_NAME like '%"+arr[0]+"%'");
                    for(int i=1;i<arr.length;i++){
                        sql.append(" || A.PEND_NAME like '%"+arr[i]+"%'");
                    }
                    sql.append("||");
                }
            }
            sql.append(" A.REPLY_CONTENT like '%"+info+"%'");
            sql.append(")");
        }else{
            sql.append(" AND A.REPLY_CONTENT like '%"+info+"%'");
        }
        if(!"0".equals(lowPrice)&&!"0".equals(highPrice)) {
            if("true".equals(hasChecked)) {
                sql.append(" AND ( A.PRICE_NUM >='" + lowPrice + "' and A.PRICE_NUM <='" + highPrice + "' ||  A.PRICE_NUM = 0 || A.PRICE_NUM like '%k%' )");
            }else{
                sql.append(" AND ( A.PRICE_NUM >='" + lowPrice + "' and A.PRICE_NUM <='" + highPrice + "' )");
            }
        }else if(!"0".equals(lowPrice)){
            if("true".equals(hasChecked)) {
                sql.append(" AND ( A.PRICE_NUM >='" + lowPrice + "'  ||  A.PRICE_NUM = 0 || A.PRICE_NUM like '%k%' )");
            }else{
                sql.append(" AND ( A.PRICE_NUM >='" + lowPrice + "' )");
            }
        }else if(!"0".equals(highPrice)){
            if("true".equals(hasChecked)) {
                sql.append(" AND ( A.PRICE_NUM <='" + highPrice + "' ||  A.PRICE_NUM = 0 || A.PRICE_NUM like '%k%' )");
            }else{
                sql.append(" AND ( A.PRICE_NUM <='" + highPrice + "' )");
            }
        }
        sql.append(
                " AND a.BELONG_QF is not NULL" +
                        " AND a.TIXIN_1 is not NULL" +
                        " AND a.REPLY_CONTENT IS NOT NULL" +
//                        " AND a.PRICE_NUM is NOT null"+
                        " ORDER BY" +
                        " a.REPLY_TIME DESC" +
                        " LIMIT "+startNum+"," + endNum+
        "" );
        System.out.println(sql);
        listSql = sql.toString();
        return this.commondao.query(sql.toString(), paramList);
    }

    public List<Map<String,Object>> queryAccountDetailInfo2(int favorId) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" SELECT" +
                " a.*, b.*" +
                " FROM" +
                " c_post_bar_12_1 a" +
                " LEFT JOIN d_post_bar_21  b ON a.favor_id = b.favor_id " +
                " WHERE" +
                " a.FAVOR_ID =" +favorId);
        System.out.println(sql);
        return this.commondao.query(sql.toString(), paramList);
    }

    public int quertLength(int i) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" select COUNT(RECORD_ID) FROM c_post_bar_12_1 where TRADE_TYPE='"+i+"'");
        System.out.println(sql);
        return Integer.parseInt(this.commondao.queryOne(sql.toString(), paramList));
    }

    public int appearancePrice1(String qufu, String viewName, int priceNum, String favorDate,String userID) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        String RECORD_ID = UUID.randomUUID().toString();
        String CREATETIME = new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd HH:mm:ss").replace("\\s*","");
        String UPDATETIME = new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd HH:mm:ss").replace("\\s*","");
        int ISVALID = 1;
        int favorId = getSequence3();
        String USER_ID = userID;
        String BELONG_QF = qufu;
        String OTHER_QF = "";
        String sql1 = " select KEYWORD_NAME_G from b_post_bar_big where KEYWORD_NAME = '"+viewName+"' limit 0,1";
        String VIEW_NAME = "--";
        try{
            VIEW_NAME = this.commondao.queryOne(sql1, paramList);
        }catch (Exception e){
            VIEW_NAME = "--";
        }finally {
            String VIEW_NAME_1 = viewName;
            int PRICE_NUM = priceNum;
            String TRADE_DATE = favorDate;
            sql.append(" insert into D_POST_BAR_17 values('"+RECORD_ID+"','"+CREATETIME+"','"+UPDATETIME+"','"+ISVALID+"','"+favorId+"','"+USER_ID+"','"+BELONG_QF+"','"+OTHER_QF+"','"+VIEW_NAME+"','"+VIEW_NAME_1+"','"+PRICE_NUM+"','"+TRADE_DATE+"')");
            System.out.println(sql);
            return this.commondao.update(sql.toString(), paramList);
        }
    }

    public int getSequence3() {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" select nextval('Sequence_3')");
        System.out.println(sql);
        int sequence_3 = -1;
        try {
            sequence_3 = Integer.parseInt(this.commondao.queryOne(sql.toString(), paramList));
        }catch (Exception e){
            return sequence_3;//未查到
        }
        return sequence_3;
    }

    public int appearancePrice3(String qufu, String viewName, String viewContent,int priceLow, int priceHigh,int priceHN,int priceHNHIGH, String favorDate, String userID) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        String RECORD_ID = UUID.randomUUID().toString();
        String CREATETIME = new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd HH:mm:ss").replace("\\s*","");
        String UPDATETIME = new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd HH:mm:ss").replace("\\s*","");
        int ISVALID = 1;
        int favorId = getSequence3();
        String USER_ID = userID;
        String BELONG_QF = qufu;
        String OTHER_QF = "";
        String sql1 = " select KEYWORD_NAME_G from b_post_bar_big where KEYWORD_NAME = '"+viewName+"' limit 0,1";
        String VIEW_NAME = "--";
        try{
            VIEW_NAME = this.commondao.queryOne(sql1, paramList);
        }catch (Exception e){
            VIEW_NAME = "--";
        }finally {
            String VIEW_NAME_1 = viewName;
            String VIEW_CONTENT = viewContent;
            int PRICE_FLOOR = priceLow;
            int PRICE_CEILING = priceHigh;
            int PRICE_HN = priceHN;
            int PRICE_HN_HIGH = priceHNHIGH;
            String TRADE_DATE = favorDate;
            sql.append(" insert into D_POST_BAR_17_1 values('"+RECORD_ID+"','"+CREATETIME+"','"+UPDATETIME+"','"+ISVALID+"','"+favorId+"','"+USER_ID+"','"+BELONG_QF+"','"+OTHER_QF+"','"+VIEW_NAME+"','"+VIEW_NAME_1+"','"+VIEW_CONTENT+"','"+PRICE_FLOOR+"','"+PRICE_CEILING+"','"+PRICE_HN+"','"+PRICE_HN_HIGH+"','"+TRADE_DATE+"')");
            System.out.println(sql);
            return this.commondao.update(sql.toString(), paramList);
        }
    }

    public List<Map<String,Object>> queryFaxinListInfo() throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" Select WAIGUAN_NAME,WAIGUAN_NAME_2 from B_POST_BAR_6 where WAIGUAN_TYPE=1 order by VIEW_ID ");
        System.out.println(sql);
        return this.commondao.query(sql.toString(), paramList);
    }

    public List<Map<String,Object>> queryHeziListInfo() throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" Select WAIGUAN_NAME,WAIGUAN_NAME_2 from B_POST_BAR_6 where WAIGUAN_TYPE=5 order by VIEW_ID ");
        System.out.println(sql);
        return this.commondao.query(sql.toString(), paramList);
    }

    public List<Map<String,Object>> queryPifengListInfo() throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" Select WAIGUAN_NAME,WAIGUAN_NAME_2 from B_POST_BAR_6 where WAIGUAN_TYPE=4 order by VIEW_ID ");
        System.out.println(sql);
        return this.commondao.query(sql.toString(), paramList);
    }

    public List<Map<String,Object>> queryWuxianListInfo() throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" Select WAIGUAN_NAME,WAIGUAN_NAME_2 from B_POST_BAR_6 where WAIGUAN_TYPE in (2,3) and XIANLIANG_TYPE ='五限' order by VIEW_ID ");
        System.out.println(sql);
        return this.commondao.query(sql.toString(), paramList);
    }

    public List<Map<String,Object>> queryliuxianListInfo() throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" Select WAIGUAN_NAME,WAIGUAN_NAME_2 from B_POST_BAR_6 where WAIGUAN_TYPE in (2,3) and XIANLIANG_TYPE ='六限' order by VIEW_ID ");
        System.out.println(sql);
        return this.commondao.query(sql.toString(), paramList);
    }

    public List<Map<String,Object>> querychengyiListInfo() throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" Select WAIGUAN_NAME,WAIGUAN_NAME_2 from B_POST_BAR_6 where WAIGUAN_TYPE in (2,3) and XIANLIANG_TYPE<>'五限' and XIANLIANG_TYPE <>'六限' order by VIEW_ID ");
        return this.commondao.query(sql.toString(), paramList);
    }

    public List<Map<String,Object>> queryqiyuListInfo() throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" select stra_name from B_POST_BAR_13 where stra_type in (1,2,3) order by stra_id ");
        return this.commondao.query(sql.toString(), paramList);
    }

    public List<Map<String,Object>> queryc5ListInfo() throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" select arm_name from B_POST_BAR_8 where arm_type=1 order by arm_id ");
        return this.commondao.query(sql.toString(), paramList);
    }

    public List<Map<String,Object>> queryguajianListInfo() throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" select pend_name from B_POST_BAR_14 where pend_type=1 order by pend_id ");
        return this.commondao.query(sql.toString(), paramList);
    }

    public int keepQuery(String tradeType, String areaSelection, String menpai, String tixin, String faxin, String hezi, String pifeng, String wuxian, String liuxian, String chengyi, String qiyu, String chengwu, String guajia, String lowPrice, String highPrice, String info, String username,String fanganName) throws Exception {
        StringBuilder sql = new StringBuilder();
        String RECORD_ID =UUID.randomUUID().toString();
        String createTime =  new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd HH:mm:ss").replace("s*","");
        List<Object> paramList = new ArrayList<Object>();
        int traType = 1;
        String qu1 = "";
        String qu2 = "";
        String qu3 = "";
        if(!"".equals(areaSelection)) {
            qu1 = areaSelection.split(",")[0];
            qu2 = areaSelection.split(",")[1];
            qu3 = areaSelection.split(",")[2];
        }
        if("出售".equals(tradeType)){
            traType =2;
        }
        sql.append(" insert into F_SYS_INFO_10(" +
                " RECORD_ID,CREATETIME,UPDATETIME,ISVALID,USER_ID,FANG_AN_TYPE," +
                " FANG_AN_NAME,TRADE_TYPE,QF_FACTOR_1,QF_FACTOR_2,QF_FACTOR_3," +
                " MENPAI_FACTOR,TIXIN_FACTOR,PRICE_LOW,PRICE_UP,FAXIN,HEZHI,PIFENG," +
                " WUXIAN,LIUXIAN,CHENYI,QIYU,GUJIAN,CHENWU,SEARCH_FACTOR,FAVOR_TIME)" +
                " values (" +
                " '"+RECORD_ID+"'," +
                " '"+createTime+"'," +
                " '"+createTime+"'," +
                " '"+1+"'," +
                " (select user_id from F_USER_INFO where login_name = '"+username+"' limit 0,1)," +
                " '"+1+"'," +
                " '"+fanganName+"'," +
                " '"+traType+"'," +
                " '"+qu1+"'," +
                " '"+qu2+"'," +
                " '"+qu3+"'," +
                " '"+menpai+"'," +
                " '"+tixin+"'," +
                " '"+lowPrice+"'," +
                " '"+highPrice+"'," +
                " '"+faxin+"'," +
                " '"+hezi+"'," +
                " '"+pifeng+"'," +
                " '"+wuxian+"'," +
                " '"+liuxian+"'," +
                " '"+chengyi+"'," +
                " '"+qiyu+"'," +
                " '"+guajia+"'," +
                " '"+chengwu+"'," +
                " '"+info+"'," +
                " '"+createTime+"'" +
                " ) " );
        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);
    }

    public List<Map<String,Object>> getKeepQuery(String username) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" select * from F_SYS_INFO_10 where user_id = ( select user_id from F_USER_INFO where login_name ='"+username+"') order by favor_time desc ");
        System.out.println(sql);
        return this.commondao.query(sql.toString(), paramList);
    }

    public int delectKeepQuery(String selectId) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" delete from  F_SYS_INFO_10 where user_seq = '"+selectId+"'");
        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);
    }
}
