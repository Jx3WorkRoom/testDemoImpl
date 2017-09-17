package com.dao;

import com.utils.CommonDao;
import com.utils.MD5Util;
import com.utils.MyDateTimeUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class IwantReleaseDao {
    private JdbcTemplate jdbcTemplate;
    private CommonDao commondao;
    public String listSql = "";
    public IwantReleaseDao(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
        if(this.jdbcTemplate !=null)
            this.commondao = new CommonDao(this.jdbcTemplate);
    }
    //--------------------------------------------------------------------------------------
    //select nextval('Sequence_3');
    public int getSequence3() throws Exception {
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

    //--------------------------------------------------------------------------------------
    //黑榜曝光表(C_POST_BAR_11)
    public int saveHbbgfo(String recordId, int favorId, String userId, int cheatType, String belongQf, String tixin, String roleName, String cheatIntro, String cheatInfo, String pageUrl) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        String createTime =  new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd HH:mm:ss").replace("\\s*","");
        String favorDate = createTime;
        StringBuffer sb = new StringBuffer();
        sb.append(cheatIntro);
        String MAIN_ID = MD5Util.getMd5(sb.toString());
        //sql.append(" insert into C_POST_BAR_11(record_id,createtime,updatetime,isvalid,favor_id,user_id,favor_date,cheat_type,belong_qf,tixin,role_name,cheat_intro,cheat_info,page_url) " +
        //        " VAlUES('"+recordId+"','"+createTime+"','"+createTime+"','1','"+favorId+"','"+userId+"','"+favorDate+"','"+cheatType+"','"+belongQf+"','"+tixin+"','"+roleName+"','"+cheatIntro+"','"+cheatInfo+"','"+pageUrl+"')");
        sql.append("INSERT INTO `grab`.`c_post_bar_11` (`RECORD_ID`, `CREATETIME`, `UPDATETIME`, `ISVALID`,`FAVOR_ID`, `USER_ID`, `FAVOR_DATE`, `CHEAT_TYPE`, `BELONG_QF`, " +
                "`TIXIN`, `ROLE_NAME`, `CHEAT_INTRO`, `CHEAT_INFO`, `PAGE_URL`, `MAIN_ID`) " +
                "VALUES ('"+recordId+"','"+createTime+"','"+createTime+"','1','"+favorId+"','"+userId+"','"+favorDate+"','"+cheatType+"','"+belongQf+"','"+tixin+"'," +
                "'"+roleName+"','"+cheatIntro+"','"+cheatInfo+"','"+pageUrl+"','"+MAIN_ID+"')");
        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);
    }
    public int upeditHbbgfo(int favorId, String userId, int cheatType, String belongQf, String tixin, String roleName, String cheatIntro, String cheatInfo, String pageUrl) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        String updatetime =  new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd HH:mm:ss").replace("\\s*","");

        sql.append(" update c_post_bar_11 set updatetime='"+updatetime+"',cheat_type='"+cheatType+"',belong_qf='"+belongQf+"',tixin='"+tixin+"',role_name='"+roleName+"',cheat_intro='"+cheatIntro+"',cheat_info='"+cheatInfo+"',page_url='"+pageUrl+"' " +
                "where favor_id='"+favorId+"'");
        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);
    }

    //账号交易信息表(C_POST_BAR_12)
    public int saveZhjyxxInfo(String recordId, int favorId,int tradeType, String userId, String belongQf,String tixin,String priceNum,String replyContent) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        String createTime =  new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd HH:mm:ss").replace("s*","");
        String favorDate = createTime;
        String REPLY_TIME = createTime;String BELONG_FLOOR = "1";String PAGE_NUM = "1";String PAGE_URL = "";String URL_VALID = "1";String SOURCE_TYPE = "1";String MENPAI_NAME = "";String XINFA_NAME = "";String TITLE_NAME = "";
        String WAIGUAN_NAME = "";String HORSE_NAME = "";String ARM_NAME = "";String STRA_NAME = "";String PEND_NAME = "";String THEME_ID = "";String THEME_NAME = "";String POST_BAR = "";String POST_ID = "";String POST_BAR_CLASS = "0";String BAR_SOUR_TYPE = "0";
        StringBuffer sb = new StringBuffer();
        sb.append(belongQf);
        sb.append(tixin);
        String MAIN_ID = MD5Util.getMd5(sb.toString());
        //sql.append(" insert into C_POST_BAR_12(record_id,createtime,updatetime,isvalid,favor_id,user_id,trade_type,favor_date,belong_qf,tixin,price_num) " +
        //        " VAlUES('"+recordId+"','"+createTime+"','"+createTime+"','1','"+favorId+"','"+userId+"','"+tradeType+"','"+favorDate+"','"+belongQf+"','"+tixin+"','"+priceNum+"')");
        sql.append(" INSERT INTO `grab`.`c_post_bar_12` (`RECORD_ID`, `CREATETIME`, `UPDATETIME`, `ISVALID`, `FAVOR_ID`, `TRADE_TYPE`, `BELONG_QF`, `TIXIN`, `PRICE_NUM`, " +
                "`REPLY_TIME`, `REPLY_CONTENT`, `BELONG_FLOOR`, `PAGE_NUM`, `PAGE_URL`, `URL_VALID`, `MAIN_ID`, `USER_ID`, `SOURCE_TYPE`, `MENPAI_NAME`, `XINFA_NAME`, `TITLE_NAME`, " +
                "`WAIGUAN_NAME`, `HORSE_NAME`, `ARM_NAME`, `STRA_NAME`, `PEND_NAME`, `THEME_ID`, `THEME_NAME`, `POST_BAR`, `POST_ID`, `POST_BAR_CLASS`, `BAR_SOUR_TYPE`) " +
                "VALUES ('"+recordId+"','"+createTime+"','"+createTime+"','1', '"+favorId+"', '"+tradeType+"', '"+belongQf+"', '"+tixin+"', '"+priceNum+"', " +
                "'"+REPLY_TIME+"', '"+replyContent+"', '"+BELONG_FLOOR+"', '"+PAGE_NUM+"', '"+PAGE_URL+"', '"+URL_VALID+"', '"+MAIN_ID+"', '"+userId+"', '"+SOURCE_TYPE+"', '"+MENPAI_NAME+"', '"+XINFA_NAME+"', '"+TITLE_NAME+"'," +
                "'"+WAIGUAN_NAME+"', '"+HORSE_NAME+"', '"+ARM_NAME+"', '"+STRA_NAME+"', '"+PEND_NAME+"', '"+THEME_ID+"', '"+THEME_NAME+"', '"+POST_BAR+"', '"+POST_ID+"', '"+POST_BAR_CLASS+"', '"+BAR_SOUR_TYPE+"')");
        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);
    }
    public int updateZhjyxxInfo(String recordId, int favorId,int tradeType, String userId, String belongQf,String tixin,String priceNum,String replyContent) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        String updatetime =  new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd HH:mm:ss").replace("s*","");
        String favorDate = updatetime;
        String REPLY_TIME = updatetime;String BELONG_FLOOR = "1";String PAGE_NUM = "1";String PAGE_URL = "";String URL_VALID = "1";String SOURCE_TYPE = "1";String MENPAI_NAME = "";String XINFA_NAME = "";String TITLE_NAME = "";
        String WAIGUAN_NAME = "";String HORSE_NAME = "";String ARM_NAME = "";String STRA_NAME = "";String PEND_NAME = "";String THEME_ID = "";String THEME_NAME = "";String POST_BAR = "";String POST_ID = "";String POST_BAR_CLASS = "0";String BAR_SOUR_TYPE = "0";
        StringBuffer sb = new StringBuffer();
        sb.append(belongQf);
        sb.append(tixin);
        String MAIN_ID = MD5Util.getMd5(sb.toString());
        sql.append(" update c_post_bar_12 set updatetime='"+updatetime+"',TRADE_TYPE='"+tradeType+"',belong_qf='"+belongQf+"',tixin='"+tixin+"',PRICE_NUM='"+priceNum+"',REPLY_CONTENT='"+replyContent+"'," +
                " where favor_id='"+favorId+"'");
        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);
    }

    //账号交易唯一信息表(C_POST_BAR_12_1)
//    public int saveZhjywyxxInfo(String recordId, int favorId, String userId, String BELONG_QF, String TIXIN, String PRICE_NUM, String FACE_NUM, String BACK_NUM, String WAIST_NUM, String LEFT_NUM, String RIGHT_NUM, String qtxych, String
//            CRED_NUM, String TOP_NUM, String CONSUM_NUM, String INTEG_NUM, String GOLD_NUM, String PET_NUM, String CREATE_ACCO, String CARD_TIME, String CURR_NUM, String TWO_INPUT, String THREE_INPUT, String _95cw, String _90cw, String _80cw, String _70cw, String
//                                        mptx, String XUANJIN_95, String XIAOTIE_95, String XUANJIN_90, String XIAOTIE_90, String XUANJIN_80, String XIAOTIE_80, String XUANJIN_70, String XIAOTIE_70,
//                                String PVP_HPS, String PVE_HPS, String PVP_T, String PVP_IN, String PVE_IN, String PVP_OUT, String PVE_OUT, String OTHER_EXPLAIN) throws Exception {
//        StringBuilder sql = new StringBuilder();
//        List<Object> paramList = new ArrayList<Object>();
//        String createTime =  new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd HH:mm:ss").replace("\\s*","");
//        String favorDate = createTime;
//        String OTHER_TITLE = "1";
//        String RECRUIT_NUM = "1";
//        String WHITE_HAIR_NUM = "1";
//        String BLACK_HAIR_NUM = "1";
//        String ARM_NUM = "1";
//        String SPARE_NUM = "1";
//        String DISG_NUM = "1";
//        String PVE_T = "1";
//        CREATE_ACCO = createTime;
//        CARD_TIME = createTime;
//        sql.append("INSERT INTO `grab`.`C_POST_BAR_12_1` (`RECORD_ID`, `CREATETIME`, `UPDATETIME`, `ISVALID`, `FAVOR_ID`, `USER_ID`, `FAVOR_DATE`," +
//                " `BELONG_QF`, `TIXIN`, `PRICE_NUM`, `FACE_NUM`, `BACK_NUM`, `WAIST_NUM`, `LEFT_NUM`, `RIGHT_NUM`, `OTHER_TITLE`, `CRED_NUM`, `TOP_NUM`, " +
//                "`CONSUM_NUM`, `INTEG_NUM`, `PET_NUM`, `CREATE_ACCO`, `CARD_TIME`, `CURR_NUM`, `GOLD_NUM`, `RECRUIT_NUM`, `TWO_INPUT`, `THREE_INPUT`, `WHITE_HAIR_NUM`," +
//                " `BLACK_HAIR_NUM`, `ARM_NUM`, `SPARE_NUM`, `DISG_NUM`, `XUANJIN_95`, `XUANJIN_90`, `XUANJIN_80`, `XUANJIN_70`, `XIAOTIE_95`, `XIAOTIE_90`, `XIAOTIE_80`," +
//                " `XIAOTIE_70`, `PVP_HPS`, `PVP_T`, `PVP_IN`, `PVP_OUT`, `PVE_HPS`, `PVE_T`, `PVE_IN`, `PVE_OUT`, `OTHER_EXPLAIN`) " +
//                "VALUES ('"+recordId+"', '"+createTime+"', '"+createTime+"', '1', '"+favorId+"', '"+userId+"', '"+favorDate+"', " +
//                "'"+BELONG_QF+"', '"+TIXIN+"', '"+PRICE_NUM+"', '"+FACE_NUM+"', '"+BACK_NUM+"', '"+WAIST_NUM+"', '"+LEFT_NUM+"', '"+RIGHT_NUM+"', '"+OTHER_TITLE+"', '"+CRED_NUM+"', '"+TOP_NUM+"', " +
//                "'"+CONSUM_NUM+"', '"+INTEG_NUM+"', '"+PET_NUM+"', '"+CREATE_ACCO+"', '"+CARD_TIME+"', '"+CURR_NUM+"', '"+GOLD_NUM+"', '"+RECRUIT_NUM+"', '"+TWO_INPUT+"', '"+THREE_INPUT+"', '"+WHITE_HAIR_NUM+"', " +
//                "'"+BLACK_HAIR_NUM+"', '"+ARM_NUM+"', '"+SPARE_NUM+"', '"+DISG_NUM+"', '"+XUANJIN_95+"', '"+XUANJIN_90+"', '"+XUANJIN_80+"', '"+XUANJIN_70+"', '"+XIAOTIE_95+"', '"+XIAOTIE_90+"', '"+XIAOTIE_80+"', " +
//                "'"+XIAOTIE_70+"', '"+PVP_HPS+"', '"+PVP_T+"', '"+PVP_IN+"', '"+PVP_OUT+"', '"+PVE_HPS+"', '"+PVE_T+"', '"+PVE_IN+"', '"+PVE_OUT+"', '"+OTHER_EXPLAIN+"');");
//
//        System.out.println(sql);
//        return this.commondao.update(sql.toString(), paramList);
//    }

    //外观交易信息表(C_POST_BAR_13)
    public int saveWgjyxxInfo(String recordId, int favorId, String userId, int tradeType, String belongQf, String viewName, int priceNum, String favorInfo) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        String createTime =  new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd HH:mm:ss").replace("\\s*","");
        String favorDate = createTime;
        String THEME_ID = ""; String PAGE_URL = ""; String POST_BAR = ""; String POST_ID = ""; String REPLY_TIME = ""; String POST_BAR_CLASS = "1"; String SOURCE_TYPE = "2"; String WAIGUAN_NAME = ""; String BELONG_FLOOR = "1";
        String PAGE_NUM = "1";String THEME_NAME = "";String URL_VALID = "1";
        StringBuffer sb = new StringBuffer();
        sb.append(belongQf);sb.append(",");
        sb.append(viewName);sb.append(",");
        sb.append(priceNum);sb.append(",");
        sb.append(favorInfo);
        String MAIN_ID = MD5Util.getMd5(sb.toString());
        //sql.append(" insert into C_POST_BAR_13(record_id,createtime,updatetime,isvalid,favor_id,user_id,trade_type,belong_qf,view_name,price_num,favor_date,favor_info) " +
        //        " VAlUES('"+recordId+"','"+createTime+"','"+createTime+"','1','"+favorId+"','"+userId+"','"+tradeType+"','"+belongQf+"','"+viewName+"','"+priceNum+"','"+favorDate+"','"+favorInfo+"')");
        sql.append(" INSERT INTO `grab`.`c_post_bar_13` (`RECORD_ID`, `CREATETIME`, `UPDATETIME`, `ISVALID`,FAVOR_ID, `THEME_ID`, `PAGE_URL`, `POST_BAR`, `POST_ID`, " +
                "`POST_BAR_CLASS`, `POST_CONTENT`, `REPLY_TIME`, `SOURCE_TYPE`, `TRADE_TYPE`, `USER_ID`, `BELONG_QF`, `WAIGUAN_NAME`, `VIEW_NAME`, `PRICE_NUM`, `BELONG_FLOOR`, " +
                "`PAGE_NUM`, `THEME_NAME`, `URL_VALID`, `MAIN_ID`) " +
                "VALUES ('"+recordId+"','"+createTime+"','"+createTime+"','1','"+favorId+"', '"+THEME_ID+"', '"+PAGE_URL+"', '"+POST_BAR+"', '"+POST_ID+"', " +
                "null, '"+sb.toString()+"', '"+favorDate+"', '"+SOURCE_TYPE+"', '"+userId+"', '"+userId+"', '["+belongQf+"]', '"+WAIGUAN_NAME+"', '"+viewName+"', '"+priceNum+"', null, " +
                "null, '"+THEME_NAME+"', '"+URL_VALID+"', '"+MAIN_ID+"')");
        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);
    }
    //-编辑
    public int upeditWgjyxxInfo(String recordId, int favorId, String userId, int tradeType, String belongQf, String viewName, int priceNum, String favorInfo) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        String updatetime =  new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd HH:mm:ss").replace("\\s*","");
        String favorDate = updatetime;
        String THEME_ID = ""; String PAGE_URL = ""; String POST_BAR = ""; String POST_ID = ""; String REPLY_TIME = ""; String POST_BAR_CLASS = "1"; String SOURCE_TYPE = "2"; String WAIGUAN_NAME = ""; String BELONG_FLOOR = "1";
        String PAGE_NUM = "1";String THEME_NAME = "";String URL_VALID = "1";
        StringBuffer sb = new StringBuffer();
        sb.append(belongQf);sb.append(",");
        sb.append(viewName);sb.append(",");
        sb.append(priceNum);sb.append(",");
        sb.append(favorInfo);
        String MAIN_ID = MD5Util.getMd5(sb.toString());
        //sql.append(" insert into C_POST_BAR_13(record_id,createtime,updatetime,isvalid,favor_id,user_id,trade_type,belong_qf,view_name,price_num,favor_date,favor_info) " +
        //        " VAlUES('"+recordId+"','"+createTime+"','"+createTime+"','1','"+favorId+"','"+userId+"','"+tradeType+"','"+belongQf+"','"+viewName+"','"+priceNum+"','"+favorDate+"','"+favorInfo+"')");
        sql.append(" update c_post_bar_13 set updatetime='"+updatetime+"',trade_type='"+tradeType+"',belong_qf='"+belongQf+"',view_name='"+viewName+"',price_num='"+priceNum+"' " +
                "where favor_id='"+favorId+"'");
        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);
    }

    //道具交易信息表(C_POST_BAR_15)
    public int saveDjjyxxInfo(String recordId, int favorId, String userId, int tradeType, String belongQf, String propName, int priceNum, String favorInfo) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        String createTime =  new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd HH:mm:ss").replace("\\s*","");
        String favorDate = createTime;
        String THEME_ID = ""; String PAGE_URL = "";String POST_BAR = "";String POST_ID = "";String POST_BAR_CLASS = "1";String OTHER_EXPLAIN = "";String THEME_NAME = "";String BELONG_FLOOR = "1";String PAGE_NUM = "1";
        String SOURCE_TYPE = "2";String URL_VALID = "1";
        StringBuffer sb = new StringBuffer();
        sb.append(belongQf);sb.append(",");
        sb.append(propName);sb.append(",");
        sb.append(priceNum);sb.append(",");
        sb.append(favorInfo);
        String MAIN_ID = MD5Util.getMd5(sb.toString());
        //sql.append(" insert into C_POST_BAR_15(record_id,createtime,updatetime,isvalid,favor_id,user_id,trade_type,belong_qf,prop_name,price_num,favor_info,favor_date) " +
        //        " VAlUES('"+recordId+"','"+createTime+"','"+createTime+"','1','"+favorId+"','"+userId+"','"+tradeType+"','"+belongQf+"','"+propName+"','"+priceNum+"','"+favorInfo+"','"+favorDate+"')");
        sql.append(" INSERT INTO `grab`.`c_post_bar_15` (`RECORD_ID`, `CREATETIME`, `UPDATETIME`, `ISVALID`, FAVOR_ID, `THEME_ID`, `PAGE_URL`, `POST_BAR`, `POST_ID`, " +
                "`POST_BAR_CLASS`, `POST_CONTENT`, `REPLY_TIME`, `BELONG_QF`, `PROP_NAME`, `PRICE_NUM`, `OTHER_EXPLAIN`, `THEME_NAME`, `BELONG_FLOOR`, `PAGE_NUM`, `USER_ID`, " +
                "`TRADE_TYPE`, `SOURCE_TYPE`, `URL_VALID`, `MAIN_ID`) " +
                "VALUES ('"+recordId+"','"+createTime+"','"+createTime+"','1','"+favorId+"', '"+THEME_ID+"', '"+PAGE_URL+"', '"+POST_BAR+"', '"+POST_ID+"', " +
                "null, '"+favorInfo+"', '"+favorDate+"', '["+belongQf+"]', '"+propName+"', '"+priceNum+"', '"+OTHER_EXPLAIN+"', '"+THEME_NAME+"', null, null, '"+userId+"', " +
                "'"+tradeType+"', '"+SOURCE_TYPE+"', '"+URL_VALID+"', '"+MAIN_ID+"')");
        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);
    }
    public int upeditDjjyxxInfo(String recordId, int favorId, String userId, int tradeType, String belongQf, String propName, int priceNum, String favorInfo) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        String updatetime =  new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd HH:mm:ss").replace("\\s*","");
        String favorDate = updatetime;
        String THEME_ID = ""; String PAGE_URL = "";String POST_BAR = "";String POST_ID = "";String POST_BAR_CLASS = "1";String OTHER_EXPLAIN = "";String THEME_NAME = "";String BELONG_FLOOR = "1";String PAGE_NUM = "1";
        String SOURCE_TYPE = "2";String URL_VALID = "1";
        StringBuffer sb = new StringBuffer();
        sb.append(favorInfo);
        String MAIN_ID = MD5Util.getMd5(sb.toString());
        sql.append(" update c_post_bar_15 set updatetime='"+updatetime+"',TRADE_TYPE='"+tradeType+"',belong_qf='"+belongQf+"',PROP_NAME='"+propName+"',PRICE_NUM='"+priceNum+"',POST_CONTENT='"+favorInfo+"' " +
                "where favor_id='"+favorId+"'");
        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);
    }
    //游戏金币交易信息表(C_POST_BAR_19)
    public int saveYxjbjyInfo(String recordId, int favorId, String userId, int tradeType, String belongQf, int goldTotal, int unitPrice, int ifSplit, String favorInfo) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        String createTime =  new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd HH:mm:ss").replace("\\s*","");
        String favorDate = createTime;
        String ifSplitStr = "";
        if(ifSplit==1){     //1-可拆分；0-不可拆分
            ifSplitStr = "可拆分";
        }else{
            ifSplitStr = "不可拆分";
        }
        StringBuffer sb = new StringBuffer();
        sb.append(belongQf);sb.append(",");
        sb.append(goldTotal);sb.append("砖,");sb.append(goldTotal);sb.append("G/R,");
        sb.append(unitPrice);sb.append(",");
        sb.append(ifSplitStr);sb.append(",");
        sb.append(favorDate);sb.append(",");
        sb.append(favorInfo);
        String MAIN_ID = MD5Util.getMd5(sb.toString());
        //sql.append(" insert into C_POST_BAR_19(record_id,createtime,updatetime,isvalid,favor_id,user_id,favor_date,trade_type,belong_qf,gold_total,unit_price,if_split,favor_info) " +
        //        " VAlUES('"+recordId+"','"+createTime+"','"+createTime+"','1','"+favorId+"','"+userId+"','"+favorDate+"','"+tradeType+"','"+belongQf+"','"+goldTotal+"','"+unitPrice+"','"+ifSplit+"','"+favorInfo+"')");
        sql.append(" INSERT INTO `grab`.`c_post_bar_19` (`RECORD_ID`, `CREATETIME`, `UPDATETIME`, `ISVALID`, FAVOR_ID, `USER_ID`, `FAVOR_DATE`, `TRADE_TYPE`, `BELONG_QF`, " +
                "`GOLD_TOTAL`, `UNIT_PRICE`, `IF_SPLIT`, `MAIN_ID`, `POST_CONTENT`) " +
                "VALUES ('"+recordId+"','"+createTime+"','"+createTime+"','1','"+favorId+"','"+userId+"','"+favorDate+"','"+tradeType+"','["+belongQf+"]', " +
                "'"+goldTotal+"', '"+unitPrice+"', '"+ifSplit+"', '"+MAIN_ID+"', '"+sb.toString()+"')");
        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);
    }
    public int updateYxjbjyInfo(String recordId, int favorId, String userId, int tradeType, String belongQf, int goldTotal, int unitPrice, int ifSplit, String favorInfo) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        String updatetime =  new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd HH:mm:ss").replace("\\s*","");
        String favorDate = updatetime;
        String ifSplitStr = "";
        if(ifSplit==1){     //1-可拆分；0-不可拆分
            ifSplitStr = "可拆分";
        }else{
            ifSplitStr = "不可拆分";
        }
        StringBuffer sb = new StringBuffer();
        sb.append(belongQf);sb.append(",");
        sb.append(goldTotal);sb.append("砖,");sb.append(goldTotal);sb.append("G/R,");
        sb.append(unitPrice);sb.append(",");
        sb.append(ifSplitStr);sb.append(",");
        sb.append(favorDate);sb.append(",");
        sb.append(favorInfo);
        String MAIN_ID = MD5Util.getMd5(sb.toString());
        sql.append(" update c_post_bar_19 set updatetime='"+updatetime+"',TRADE_TYPE='"+tradeType+"',belong_qf='"+belongQf+"',GOLD_TOTAL='"+goldTotal+"',UNIT_PRICE='"+unitPrice+"',IF_SPLIT='"+ifSplit+"' " +
                "where favor_id='"+favorId+"'");
        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);
    }

    //游戏代练代打信息表(C_POST_BAR_17)
    public int saveYxddxxInfo(String recordId, int favorId, String userId, int needType, String belongQf, String favorInfo) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        String createTime =  new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd HH:mm:ss").replace("\\s*","");
        String favorDate = createTime;
        String THEME_ID = "";String PAGE_URL = "";String POST_BAR = "";String POST_ID = "";int POST_BAR_CLASS = 0;String BELONG_FLOOR = "1";String PAGE_NUM = "1";String THEME_NAME = "";
        StringBuffer sb = new StringBuffer();
        sb.append(belongQf);
        sb.append(favorInfo);
        sb.append(userId);
        String MAIN_ID = MD5Util.getMd5(sb.toString());
        //sql.append(" insert into C_POST_BAR_17(record_id,createtime,updatetime,isvalid,favor_id,user_id,cheat_type,belong_qf,favor_info,favorDate) " +
        //        " VAlUES('"+recordId+"','"+createTime+"','"+createTime+"','1','"+favorId+"','"+userId+"','"+cheatType+"','"+belongQf+"','"+favorInfo+"','"+favorDate+"')");
        sql.append(" INSERT INTO `grab`.`c_post_bar_17` (`RECORD_ID`, `CREATETIME`, `UPDATETIME`, `ISVALID`, `FAVOR_ID`, `USER_ID`, `NEED_TYPE`, `POST_CONTENT`, " +
                "`REPLY_TIME`, `BELONG_QF`, `THEME_ID`, `PAGE_URL`, `POST_BAR`, `POST_ID`, `POST_BAR_CLASS`, `BELONG_FLOOR`, `PAGE_NUM`, `THEME_NAME`, `MAIN_ID`) " +
                "VALUES ('"+recordId+"','"+createTime+"','"+createTime+"','1','"+favorId+"','"+userId+"', '"+needType+"', '"+favorInfo+"', " +
                "'"+favorDate+"', '"+belongQf+"', '"+THEME_ID+"', '"+PAGE_URL+"', '"+POST_BAR+"', '"+POST_ID+"', null, null, null, '"+THEME_NAME+"', '"+MAIN_ID+"')");
        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);
    }
    public int updateYxddxxInfo(String recordId, int favorId, String userId, int needType, String belongQf, String favorInfo) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        String updatetime =  new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd HH:mm:ss").replace("\\s*","");
        String favorDate = updatetime;
        String THEME_ID = "";String PAGE_URL = "";String POST_BAR = "";String POST_ID = "";int POST_BAR_CLASS = 0;String BELONG_FLOOR = "1";String PAGE_NUM = "1";String THEME_NAME = "";
        StringBuffer sb = new StringBuffer();
        sb.append(belongQf);
        sb.append(favorInfo);
        sb.append(userId);
        String MAIN_ID = MD5Util.getMd5(sb.toString());
        sql.append(" update c_post_bar_15 set updatetime='"+updatetime+"',NEED_TYPE='"+needType+"',belong_qf='"+belongQf+
                "where favor_id='"+favorId+"'");
        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);
    }
//--------------------------------------------------------------------------------------
    //我要举报(D_POST_BAR_11--C_POST_BAR_11)
    public int saveWyjbInfo(String recordId, int favorId, String userId, int cheatType, String belongQf, String tixin, String roleName, String cheatIntro, String cheatInfo, String pageUrl) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        String createTime =  new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd HH:mm:ss").replace("\\s*","");

        String favorDate = createTime;
        sql.append(" insert into D_POST_BAR_11(record_id,createtime,updatetime,isvalid,favor_id,user_id,favor_date,cheat_type,belong_qf,tixin,role_name,cheat_intro,cheat_info,page_url) " +
                " VAlUES('"+recordId+"','"+createTime+"','"+createTime+"','1','"+favorId+"','"+userId+"','"+favorDate+"','"+cheatType+"','"+belongQf+"','"+tixin+"','"+roleName+"','"+cheatIntro+"','"+cheatInfo+"','"+pageUrl+"')");
        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);
    }
    //-编辑
    public int upeditWyjbInfo(int favorId, String userId, int cheatType, String belongQf, String tixin, String roleName, String cheatIntro, String cheatInfo, String pageUrl) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        String updatetime =  new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd HH:mm:ss").replace("\\s*","");

        sql.append(" update D_POST_BAR_11 set updatetime='"+updatetime+"',cheat_type='"+cheatType+"',belong_qf='"+belongQf+"',tixin='"+tixin+"',role_name='"+roleName+"',cheat_intro='"+cheatIntro+"',cheat_info='"+cheatInfo+"',page_url='"+pageUrl+"' " +
                "where favor_id='"+favorId+"'");
        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);
    }

    //外观交易(D_POST_BAR_16--C_POST_BAR_13)
    public int saveWgjyInfo(String recordId, int favorId, String userId, int tradeType, String belongQf, String viewName, int priceNum, String favorInfo) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        String createTime =  new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd HH:mm:ss").replace("\\s*","");
        String favorDate = createTime;
        sql.append(" insert into D_POST_BAR_16(record_id,createtime,updatetime,isvalid,favor_id,user_id,trade_type,belong_qf,view_name,price_num,favor_date,favor_info) " +
                " VAlUES('"+recordId+"','"+createTime+"','"+createTime+"','1','"+favorId+"','"+userId+"','"+tradeType+"','"+belongQf+"','"+viewName+"','"+priceNum+"','"+favorDate+"','"+favorInfo+"')");
        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);
    }
    //-编辑
    public int upeditWgjyInfo(String recordId, int favorId, String userId, int tradeType, String belongQf, String viewName, int priceNum, String favorInfo) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        String updatetime =  new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd HH:mm:ss").replace("\\s*","");
        sql.append(" update D_POST_BAR_16 set updatetime='"+updatetime+"',trade_type='"+tradeType+"',belong_qf='"+belongQf+"',view_name='"+viewName+"',price_num='"+priceNum+"',favor_info='"+favorInfo+"' " +
                "where favor_id='"+favorId+"'");
        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);
    }

    //道具交易(D_POST_BAR_18-C_POST_BAR_15)
    public int saveDjjyInfo(String recordId, int favorId, String userId, int tradeType, String belongQf, String propName, int priceNum, String favorInfo) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        String createTime =  new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd HH:mm:ss").replace("\\s*","");
        String favorDate = createTime;
        sql.append(" insert into D_POST_BAR_18(record_id,createtime,updatetime,isvalid,favor_id,user_id,trade_type,belong_qf,prop_name,price_num,favor_info,favor_date) " +
                " VAlUES('"+recordId+"','"+createTime+"','"+createTime+"','1','"+favorId+"','"+userId+"','"+tradeType+"','"+belongQf+"','"+propName+"','"+priceNum+"','"+favorInfo+"','"+favorDate+"')");
        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);
    }
    public int upeditDjjyInfo(String recordId, int favorId, String userId, int tradeType, String belongQf, String propName, int priceNum, String favorInfo) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        String updatetime =  new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd HH:mm:ss").replace("\\s*","");
        String favorDate = updatetime;
        sql.append(" update D_POST_BAR_18 set updatetime='"+updatetime+"',trade_type='"+tradeType+"',belong_qf='"+belongQf+"',prop_name='"+propName+"',price_num='"+priceNum+"',favor_info='"+favorInfo+"' " +
                "where favor_id='"+favorId+"'");
        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);
    }

    //金币交易(D_POST_BAR_19--C_POST_BAR_19)
    public int saveJbjyInfo(String recordId, int favorId, String userId, int tradeType, String belongQf, int goldTotal, int unitPrice, int ifSplit, String favorInfo) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        String createTime =  new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd HH:mm:ss").replace("\\s*","");
        String favorDate = createTime;
        sql.append(" insert into D_POST_BAR_19(record_id,createtime,updatetime,isvalid,favor_id,user_id,favor_date,trade_type,belong_qf,gold_total,unit_price,if_split,favor_info) " +
                " VAlUES('"+recordId+"','"+createTime+"','"+createTime+"','1','"+favorId+"','"+userId+"','"+favorDate+"','"+tradeType+"','"+belongQf+"','"+goldTotal+"','"+unitPrice+"','"+ifSplit+"','"+favorInfo+"')");
        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);
    }
    public int updateJbjyInfo(String recordId, int favorId, String userId, int tradeType, String belongQf, int goldTotal, int unitPrice, int ifSplit, String favorInfo) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        String updatetime =  new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd HH:mm:ss").replace("\\s*","");
        String favorDate = updatetime;
        sql.append(" update D_POST_BAR_19 set updatetime='"+updatetime+"',trade_type='"+tradeType+"',belong_qf='"+belongQf+"',gold_total='"+goldTotal+"',unit_price='"+unitPrice+"',if_split='"+ifSplit+"' " +
                "where favor_id='"+favorId+"'");
        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);
    }

    //代练代打(D_POST_BAR_20--C_POST_BAR_17)
    public int saveDlddInfo(String recordId, int favorId, String userId, int needType, String belongQf, String favorInfo) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        String createTime =  new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd HH:mm:ss").replace("\\s*","");
        String favorDate = createTime;
        sql.append(" insert into D_POST_BAR_20(record_id,createtime,updatetime,isvalid,favor_id,user_id,need_type,belong_qf,favor_info,favor_Date) " +
                " VAlUES('"+recordId+"','"+createTime+"','"+createTime+"','1','"+favorId+"','"+userId+"','"+needType+"','"+belongQf+"','"+favorInfo+"','"+favorDate+"')");
        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);
    }
    public int updateDlddInfo(String recordId, int favorId, String userId, int needType, String belongQf, String favorInfo) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        String updatetime =  new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd HH:mm:ss").replace("\\s*","");
        String favorDate = updatetime;
        sql.append(" update D_POST_BAR_20 set updatetime='"+updatetime+"',need_type='"+needType+"',belong_qf='"+belongQf+
                "where favor_id='"+favorId+"'");
        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);
    }

    //账号快售快速发布(D_POST_BAR_13--C_POST_BAR_12)
    public int saveZhssInfo(String recordId, int favorId,int tradeType, String userId, String belongQf,String tixin,int priceNum,String accoInfo) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        String createTime =  new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd HH:mm:ss").replace("\\s*","");
        String favorDate = createTime;
        sql.append(" insert into D_POST_BAR_13(record_id,createtime,updatetime,isvalid,favor_id,user_id,trade_type,favor_date,belong_qf,tixin,price_num,acco_info) " +
                " VAlUES('"+recordId+"','"+createTime+"','"+createTime+"','1','"+favorId+"','"+userId+"','"+tradeType+"','"+favorDate+"','"+belongQf+"','"+tixin+"','"+priceNum+"','"+accoInfo+"')");
        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);
    }
    public int updateZhssInfo(String recordId, int favorId,int tradeType, String userId, String belongQf,String tixin,int priceNum,String accoInfo) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        String updatetime =  new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd HH:mm:ss").replace("\\s*","");
        String favorDate = updatetime;
        sql.append(" update D_POST_BAR_13 set updatetime='"+updatetime+"',trade_type='"+tradeType+"',belong_qf='"+belongQf+"',tixin='"+tixin+"',price_num='"+priceNum+"',acco_info='"+accoInfo+"'" +
                " where favor_id='"+favorId+"'");
        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);
    }

    //保存用户账号详情发布(D_POST_BAR_14--C_POST_BAR_12_1)
    public int saveZhssxxfbInfo(String recordId, int favorId, String userId, String BELONG_QF, String TIXIN, String PRICE_NUM, String FACE_NUM, String BACK_NUM, String WAIST_NUM, String LEFT_NUM, String RIGHT_NUM, String qtxych, String
            CRED_NUM, String TOP_NUM, String CONSUM_NUM, String INTEG_NUM, String GOLD_NUM, String PET_NUM, String CREATE_ACCO, String CARD_TIME, String CURR_NUM, String TWO_INPUT, String THREE_INPUT, String _95cw, String _90cw, String _80cw, String _70cw, String
                                        mptx, String XUANJIN_95, String XIAOTIE_95, String XUANJIN_90, String XIAOTIE_90, String XUANJIN_80, String XIAOTIE_80, String XUANJIN_70, String XIAOTIE_70,
                                String PVP_HPS, String PVE_HPS, String PVP_T, String PVP_IN, String PVE_IN, String PVP_OUT, String PVE_OUT, String OTHER_EXPLAIN) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        String createTime =  new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd HH:mm:ss").replace("\\s*","");
        String favorDate = createTime;
        String OTHER_TITLE = "1";
        String RECRUIT_NUM = "1";
        String WHITE_HAIR_NUM = "1";
        String BLACK_HAIR_NUM = "1";
        String ARM_NUM = "1";
        String SPARE_NUM = "1";
        String DISG_NUM = "1";
        String PVE_T = "1";
        CREATE_ACCO = createTime;
        CARD_TIME = createTime;
        sql.append("INSERT INTO `grab`.`d_post_bar_14` (`RECORD_ID`, `CREATETIME`, `UPDATETIME`, `ISVALID`, `FAVOR_ID`, `USER_ID`, `FAVOR_DATE`," +
                " `BELONG_QF`, `TIXIN`, `PRICE_NUM`, `FACE_NUM`, `BACK_NUM`, `WAIST_NUM`, `LEFT_NUM`, `RIGHT_NUM`, `OTHER_TITLE`, `CRED_NUM`, `TOP_NUM`, " +
                "`CONSUM_NUM`, `INTEG_NUM`, `PET_NUM`, `CREATE_ACCO`, `CARD_TIME`, `CURR_NUM`, `GOLD_NUM`, `RECRUIT_NUM`, `TWO_INPUT`, `THREE_INPUT`, `WHITE_HAIR_NUM`," +
                " `BLACK_HAIR_NUM`, `ARM_NUM`, `SPARE_NUM`, `DISG_NUM`, `XUANJIN_95`, `XUANJIN_90`, `XUANJIN_80`, `XUANJIN_70`, `XIAOTIE_95`, `XIAOTIE_90`, `XIAOTIE_80`," +
                " `XIAOTIE_70`, `PVP_HPS`, `PVP_T`, `PVP_IN`, `PVP_OUT`, `PVE_HPS`, `PVE_T`, `PVE_IN`, `PVE_OUT`, `OTHER_EXPLAIN`) " +
                "VALUES ('"+recordId+"', '"+createTime+"', '"+createTime+"', '1', '"+favorId+"', '"+userId+"', '"+favorDate+"', " +
                "'"+BELONG_QF+"', '"+TIXIN+"', '"+PRICE_NUM+"', '"+FACE_NUM+"', '"+BACK_NUM+"', '"+WAIST_NUM+"', '"+LEFT_NUM+"', '"+RIGHT_NUM+"', '"+OTHER_TITLE+"', '"+CRED_NUM+"', '"+TOP_NUM+"', " +
                "'"+CONSUM_NUM+"', '"+INTEG_NUM+"', '"+PET_NUM+"', '"+CREATE_ACCO+"', '"+CARD_TIME+"', '"+CURR_NUM+"', '"+GOLD_NUM+"', '"+RECRUIT_NUM+"', '"+TWO_INPUT+"', '"+THREE_INPUT+"', '"+WHITE_HAIR_NUM+"', " +
                "'"+BLACK_HAIR_NUM+"', '"+ARM_NUM+"', '"+SPARE_NUM+"', '"+DISG_NUM+"', '"+XUANJIN_95+"', '"+XUANJIN_90+"', '"+XUANJIN_80+"', '"+XUANJIN_70+"', '"+XIAOTIE_95+"', '"+XIAOTIE_90+"', '"+XIAOTIE_80+"', " +
                "'"+XIAOTIE_70+"', '"+PVP_HPS+"', '"+PVP_T+"', '"+PVP_IN+"', '"+PVP_OUT+"', '"+PVE_HPS+"', '"+PVE_T+"', '"+PVE_IN+"', '"+PVE_OUT+"', '"+OTHER_EXPLAIN+"');");

        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);
    }

    //获取特征词
    public List<Map<String,Object>> getTzc(String type, String parNum, String cate) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        if(parNum == null || parNum.isEmpty()){
            sql.append("select RECORD_ID as id,KEYWORD_NAME_G as name from B_POST_BAR_BIG where KEYWORD_BIG_TYPE=" + type + " and KEYWORD_CATE="+cate);
        }else{
            sql.append("select RECORD_ID as id,KEYWORD_NAME_G as name from B_POST_BAR_BIG where KEYWORD_BIG_TYPE=" + type + " and KEYWORD_PAR_NUM=" + parNum + " and KEYWORD_CATE="+cate);
        }


        System.out.println(sql);
        listSql = sql.toString();
        return this.commondao.query(sql.toString(), paramList);
    }

    //获取图片地址
    public List<Map<String,Object>> getPicturePath(String favorId, String seqNum) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append("select pic_path from C_POST_BAR_14 where favor_id = " + favorId + " and seq_num="+seqNum);

        System.out.println(sql);
        listSql = sql.toString();
        return this.commondao.query(sql.toString(), paramList);
    }

    public int savePub(String recordId, String createTime, String updateTime, int isvalid, int mainId, String userId, String favorDate, int favorType, String collect_cont, int collect_stusta) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" insert into F_USER_PUB_INFO(RECORD_ID,CREATETIME,UPDATETIME,ISVALID,MAIN_ID,USER_ID,FAVOR_DATE,FAVOR_TYPE,COLLECT_CONT,COLLECT_STUSTA) " +
                   " values('"+recordId+"','"+createTime+"','"+updateTime+"','"+isvalid+"','"+mainId+"','"+userId+"','"+favorDate+"','"+favorType+"','"+collect_cont+"','"+collect_stusta+"') ");
        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);
    }
    public int updatePub(String recordId, String createTime, String updateTime, int isvalid, int mainId, String userId, String favorDate, int favorType, String collect_cont, int collect_stusta) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" update F_USER_PUB_INFO set UPDATETIME='"+updateTime+"',FAVOR_TYPE='"+favorType+"',COLLECT_CONT='"+collect_cont+"',COLLECT_STUSTA='"+collect_stusta+"'"+
                "where MAIN_ID='"+mainId+"'");
        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);
    }

//--------------------------------------------------------------------------------------

    //获取道具名下拉框填充信息
    public List<Map<String,Object>> queryTixinListInfo(String keyword) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" select distinct KEYWORD_NAME from b_post_bar_big where keyword_big_name = '"+keyword+"' and keyword_cate=1 ");
        System.out.println(sql);
        return this.commondao.query(sql.toString(), paramList);
    }

    //获取我要举报
    public List<Map<String,Object>> getReport(String mainId) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append("select * from D_post_bar_11 where favor_id = " + mainId);

        System.out.println(sql);
        listSql = sql.toString();
        return this.commondao.query(sql.toString(), paramList);
    }

    //获取外观交易
    public List<Map<String,Object>> getAppearanceTransaction(String mainId) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append("select * from D_post_bar_16 where favor_id = " + mainId);

        System.out.println(sql);
        listSql = sql.toString();
        return this.commondao.query(sql.toString(), paramList);
    }

    //获取道具交易
    public List<Map<String,Object>> getPropTransaction(String mainId) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append("select * from D_post_bar_18 where favor_id = " + mainId);

        System.out.println(sql);
        listSql = sql.toString();
        return this.commondao.query(sql.toString(), paramList);
    }

    //获取金币交易
    public List<Map<String,Object>> getAccountTransaction(String mainId) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append("select * from D_post_bar_19 where favor_id = " + mainId);

        System.out.println(sql);
        listSql = sql.toString();
        return this.commondao.query(sql.toString(), paramList);
    }

    //获取代练交易
    public List<Map<String,Object>> getAccountExchange(String mainId) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append("select * from D_post_bar_20 where favor_id = " + mainId);

        System.out.println(sql);
        listSql = sql.toString();
        return this.commondao.query(sql.toString(), paramList);
    }

    //获取账号收售快速发布
    public List<Map<String,Object>> getQuickRelease(String mainId) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append("select * from D_post_bar_13 where favor_id = " + mainId);

        System.out.println(sql);
        listSql = sql.toString();
        return this.commondao.query(sql.toString(), paramList);
    }

    public String queryCheatType(int cheatType) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" SELECT" +
                    " T2.PAR_NAME" +
                    " FROM" +
                    " G_PUB_PAR_1 T2" +
                    " WHERE" +
                    " T2.PAR_NUM = '" +cheatType+"'"+
                    " AND T2.PAR_SERIES = 1016 ");
        System.out.println(sql);
        return this.commondao.queryOne(sql.toString(), paramList);
    }
}