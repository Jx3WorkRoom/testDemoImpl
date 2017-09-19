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
        sql.append(" SELECT a.* FROM f_sys_mod_1 a  WHERE a.ISVALID=1 ORDER BY a.MOD_ID ");
        System.out.println(sql);
        return this.commondao.query(sql.toString(), paramList);
    }

    public int delMod(int modId) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" update f_sys_mod_1 set ISVALID = 0 where mod_id = '"+modId+"'");
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
        List<Object> paramList = new ArrayList<Object>();
        String sql2 = " update  f_sys_mod_1 set BELONG_WEB='"+belong_web+"',MOD_NAME='"+mod_name+"',MOD_TYPE='"+modType+"', VISIT_ROLE='"+visitRole+"',REGIST_ROLE='"+registRole+"' where MOD_ID= '"+modId+"'";
        System.out.println(sql2);
        return this.commondao.update(sql2, paramList);
    }

    public List<Map<String,Object>> modDetail(int modId) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" SELECT" +
                " a.*,b.MOD_NAME,b.BELONG_WEB " +
                " FROM" +
                " F_SYS_MOD_2 a" +
                " LEFT JOIN f_sys_mod_1 b ON a.MOD_ID = b.MOD_ID " +
                " WHERE" +
                " a.MOD_ID = '"+modId+"' and a.ISVALID=1 ORDER BY UPDATETIME DESC");
        System.out.println(sql);
        return this.commondao.query(sql.toString(), paramList);
    }

    public int delMolDetail(String recordId) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" update f_sys_mod_2 set ISVALID = 0 where record_id = '"+recordId+"'");
        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);
    }

    public int addModDetail( String modId, String costNum, String canNum) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        String createTime = new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd hh:mm:ss").replace("\\s*", "");
        String recordId = UUID.randomUUID().toString();
        sql.append(" insert into  f_sys_mod_2(RECORD_ID,ISVALID,CREATETIME,UPDATETIME,MOD_ID,START_DATE,SERVER_COST,SERVER_NUM) " +
                " values('"+recordId+"','1','"+createTime+"','"+createTime+"','"+modId+"','"+createTime+"','"+costNum+"','"+canNum+"')");
        System.out.println(sql);
        return this.commondao.update(sql.toString(),paramList);
    }

    public int editModDetail(String recordId, String modId, String costNum, String canNum) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" update f_sys_mod_2 set mod_id ='"+modId+"' ,  SERVER_COST = '"+costNum+"' ,  SERVER_NUM = '"+canNum+"' where record_id = '"+recordId+"'");
        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);
    }
}
