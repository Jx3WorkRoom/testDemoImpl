package com.dao;

import com.utils.CommonDao;
import com.utils.MyDateTimeUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class userManageDao {
    private JdbcTemplate jdbcTemplate;
    private CommonDao commondao;
    public userManageDao(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
        if(this.jdbcTemplate !=null)
            this.commondao = new CommonDao(this.jdbcTemplate);
    }

    public List<Map<String,Object>> userManageInfo() throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" SELECT a.*,b.START_DATE,b.SERVER_COST,b.SERVER_NUM FROM f_sys_mod_1 a LEFT JOIN f_sys_mod_2 b ON a.mod_id = b.MOD_ID ORDER BY a.MOD_ID ");
        System.out.println(sql);
        return this.commondao.query(sql.toString(), paramList);
    }

    public int delMod(int modId) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" delete from f_sys_mod_1 where mod_id = '"+modId+"'");
        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);
    }

    public int addMod(String belong_web, int modId, String mod_name, int modType, int visitRole, int registRole, String serverCost, String serverNum) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        String createTime = new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd hh:mm:ss").replace("\\s*", "");
        String recordId = UUID.randomUUID().toString();
        if(!"".equals(serverCost)||!"".equals(serverNum)) {
            sql.append(" insert into  f_sys_mod_2(RECORD_ID,ISVALID,CREATETIME,UPDATETIME,MOD_ID,START_DATE,SERVER_COST,SERVER_NUM) values('"+recordId+"','1','"+createTime+"','"+createTime+"','"+modId+"','"+createTime+"','"+serverCost+"','"+serverNum+"')");
            this.commondao.update(sql.toString(),paramList);
            System.out.println(sql);
        }
        String sql2 = " insert into f_sys_mod_1(RECORD_ID,ISVALID,CREATETIME,UPDATETIME,BELONG_WEB,MOD_ID,MOD_NAME,MOD_TYPE,VISIT_ROLE,REGIST_ROLE) values('"+recordId+"','1','"+createTime+"','"+createTime+"','"+belong_web+"','"+modId+"','"+mod_name+"','"+modType+"','"+visitRole+"','"+registRole+"')";
        System.out.println(sql2);
        return this.commondao.update(sql2, paramList);
    }

    public int editMod(String belong_web, int modId, String mod_name, int modType, int visitRole, int registRole, String serverCost, String serverNum) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        if(!"".equals(serverCost)||!"".equals(serverNum)) {
            sql.append(" update f_sys_mod_2 set serverCost='"+serverCost+"',serverNum='"+serverNum+"' where MOD_ID= '"+modId+"'");
            this.commondao.update(sql.toString(),paramList);
            System.out.println(sql);
        }
        String sql2 = " update  f_sys_mod_1 set BELONG_WEB='"+belong_web+"',MOD_NAME='"+mod_name+"',MOD_TYPE='"+modType+"', VISIT_ROLE='"+visitRole+"',REGIST_ROLE='"+registRole+"' where MOD_ID= '"+modId+"'";
        System.out.println(sql2);
        return this.commondao.update(sql2, paramList);
    }
}
