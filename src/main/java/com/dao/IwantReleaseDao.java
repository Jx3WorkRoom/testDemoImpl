package com.dao;

import com.utils.CommonDao;
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

    //我要举报
    public int saveWyjbInfo(int cheatType, String belongQf, String tixin, String roleName, String cheatIntro, String cheatInfo, String pageUrl) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        String createTime =  new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd hh:mm:ss").replace("\\s*","");
        String recordId = UUID.randomUUID().toString().replace("-", "");
        String favorId = "1";
        String userId = "1";
        String favorDate = createTime;
        sql.append(" insert into D_POST_BAR_11(record_id,createtime,updatetime,isvalid,favor_id,user_id,favor_date,cheat_type,belong_qf,tixin,role_name,cheat_intro,cheat_info,page_url) " +
                " VAlUES('"+recordId+"','"+createTime+"','"+createTime+"','1','"+favorId+"','"+userId+"','"+favorDate+"','"+cheatType+"','"+belongQf+"','"+tixin+"','"+roleName+"','"+cheatIntro+"','"+cheatInfo+"','"+pageUrl+"')");
        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);
    }

    //外观交易
    public int saveWgjyInfo(int tradeType, String belongQf, String viewName, int priceNum, String favorInfo) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        String createTime =  new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd hh:mm:ss").replace("\\s*","");
        String recordId = UUID.randomUUID().toString().replace("-", "");
        String favorId = "1";
        String userId = "1";
        String favorDate = createTime;
        sql.append(" insert into D_POST_BAR_16(record_id,createtime,updatetime,isvalid,favor_id,user_id,trade_type,belong_qf,view_name,price_num,favor_date,favor_info) " +
                " VAlUES('"+recordId+"','"+createTime+"','"+createTime+"','1','"+favorId+"','"+userId+"','"+tradeType+"','"+belongQf+"','"+viewName+"','"+priceNum+"','"+favorDate+"','"+favorInfo+"')");
        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);
    }

    //道具交易
    public int saveDjjyInfo(int tradeType, String belongQf, String propName, int priceNum, String favorInfo) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        String createTime =  new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd hh:mm:ss").replace("\\s*","");
        String recordId = UUID.randomUUID().toString().replace("-", "");
        String favorId = "1";
        String userId = "1";
        String favorDate = createTime;
        sql.append(" insert into D_POST_BAR_18(record_id,createtime,updatetime,isvalid,favor_id,user_id,trade_type,belong_qf,prop_name,price_num,favor_info,favor_date) " +
                " VAlUES('"+recordId+"','"+createTime+"','"+createTime+"','1','"+favorId+"','"+userId+"','"+tradeType+"','"+belongQf+"','"+propName+"','"+priceNum+"','"+favorInfo+"','"+favorDate+"')");
        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);
    }

    //金币交易
    public int saveJbjyInfo(int tradeType, String belongQf, int goldTotal, int unitPrice, int ifSplit, String favorInfo) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        String createTime =  new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd hh:mm:ss").replace("\\s*","");
        String recordId = UUID.randomUUID().toString().replace("-", "");
        String favorId = "1";
        String userId = "1";
        String favorDate = createTime;
        sql.append(" insert into D_POST_BAR_19(record_id,createtime,updatetime,isvalid,favor_id,user_id,favor_date,trade_type,belong_qf,gold_total,unit_price,if_split,favor_info) " +
                " VAlUES('"+recordId+"','"+createTime+"','"+createTime+"','1','"+favorId+"','"+userId+"','"+favorDate+"','"+tradeType+"','"+belongQf+"','"+goldTotal+"','"+unitPrice+"','"+ifSplit+"','"+favorInfo+"')");
        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);
    }

    //代练代打
    public int saveDlddInfo(int cheatType, String belongQf, String favorInfo) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        String createTime =  new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd hh:mm:ss").replace("\\s*","");
        String recordId = UUID.randomUUID().toString().replace("-", "");
        String favorId = "1";
        String userId = "1";
        String favorDate = createTime;
        sql.append(" insert into D_POST_BAR_20(record_id,createtime,updatetime,isvalid,favor_id,user_id,cheat_type,belong_qf,favor_info,favorDate) " +
                " VAlUES('"+recordId+"','"+createTime+"','"+createTime+"','1','"+favorId+"','"+userId+"','"+cheatType+"','"+belongQf+"','"+favorInfo+"','"+favorDate+"')");
        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);
    }

    //账号快售快速发布
    public int saveZhssInfo(String belongQf,String tixin,int priceNum,String accoInfo) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        String createTime =  new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd hh:mm:ss").replace("\\s*","");
        String recordId = UUID.randomUUID().toString().replace("-", "");
        String favorId = "1";
        String userId = "1";
        String tradeType = "1";
        String favorDate = createTime;
        sql.append(" insert into D_POST_BAR_13(record_id,createtime,updatetime,isvalid,favor_id,user_id,trade_type,favor_date,belong_qf,tixin,price_num) " +
                " VAlUES('"+recordId+"','"+createTime+"','"+createTime+"','1','"+favorId+"','"+userId+"','"+tradeType+"','"+favorDate+"','"+belongQf+"','"+tixin+"','"+priceNum+"')");
        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);
    }

    //保存用户账号详情发布(D_POST_BAR_14)
    public int saveZhssxxfbInfo(String BELONG_QF, String TIXIN, String PRICE_NUM, String FACE_NUM, String BACK_NUM, String WAIST_NUM, String LEFT_NUM, String RIGHT_NUM, String qtxych, String
            CRED_NUM, String TOP_NUM, String CONSUM_NUM, String INTEG_NUM, String GOLD_NUM, String PET_NUM, String CREATE_ACCO, String CARD_TIME, String CURR_NUM, String TWO_INPUT, String THREE_INPUT, String _95cw, String _90cw, String _80cw, String _70cw, String
                                        mptx, String XUANJIN_95, String XIAOTIE_95, String XUANJIN_90, String XIAOTIE_90, String XUANJIN_80, String XIAOTIE_80, String XUANJIN_70, String XIAOTIE_70,
                                String PVP_HPS, String PVE_HPS, String PVP_T, String PVP_IN, String PVE_IN, String PVP_OUT, String PVE_OUT, String OTHER_EXPLAIN) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        String createTime =  new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd hh:mm:ss").replace("\\s*","");
        String recordId = UUID.randomUUID().toString().replace("-", "");
        String favorId = "1";
        String USER_ID = "1";
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
                "VALUES ('"+recordId+"', '"+createTime+"', '"+createTime+"', '1', '"+favorId+"', '"+USER_ID+"', '"+favorDate+"', " +
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
        sql.append("select * from B_POST_BAR_BIG where KEYWORD_BIG_TYPE=" + type + " and KEYWORD_PAR_NUM=" + parNum + " and KEYWORD_CATE="+cate);

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
}