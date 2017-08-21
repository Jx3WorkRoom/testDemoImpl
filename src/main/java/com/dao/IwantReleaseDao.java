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

    public int saveInfo(int cheatType, String belongQf, String tixin, String roleName, String cheatIntro, String cheatInfo, String pageUrl) throws Exception {
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

}