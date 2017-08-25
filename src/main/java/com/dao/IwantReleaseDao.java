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

}