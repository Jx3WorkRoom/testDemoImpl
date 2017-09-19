package com.dao;

import com.utils.CommonDao;
import com.utils.MyDateTimeUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class userInfoDao {
    private JdbcTemplate jdbcTemplate;
    private CommonDao commondao;
    public String listSql = "";
    public userInfoDao(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
        if(this.jdbcTemplate !=null)
            this.commondao = new CommonDao(this.jdbcTemplate);
    }

    public List<Map<String,Object>> queryUserInfo(int num) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append("  select * from F_USER_INFO ORDER BY REGIST_DATE DESC LIMIT "+num+",20 ");
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

    public List<Map<String,Object>> queryUserInfoByUserId(String userId) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" select * from F_USER_INFO where user_id = '"+userId+"'");
        System.out.println(sql);
        return this.commondao.query(sql.toString(), paramList);
    }

    public List<Map<String,Object>> queryUserInfo1(String shape, int type, int num) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" select * from F_USER_INFO where 1=1");
        if(type==1) {
            sql.append(" and LOGIN_NAME LIKE '%"+shape+"%' ");
        }else if(type==2){
            sql.append(" and USER_NAME LIKE '%"+shape+"%' ");
        }else if(type==3){
            sql.append(" and USER_TEL LIKE '%"+shape+"%' ");
        }
        sql.append(" LIMIT "+num+",20");
        listSql = sql.toString();
        System.out.println(sql);
        return this.commondao.query(sql.toString(), paramList);
    }

    public int editLockTime(String userId, int hour) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        Calendar ca=Calendar.getInstance();
        ca.setTime(new Date());
        ca.add(Calendar.HOUR_OF_DAY, hour);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(ca.getTime()));
        sql.append(" update F_USER_INFO set ADMIN_LOCK = '"+sdf.format(ca.getTime())+"' where user_id = '"+userId+"'");
        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);
    }

    public int recoverPassword(String loginName, String newPassword) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" update f_user_info set LOGIN_WORD ='"+newPassword+"' where LOGIN_NAME = '"+loginName+"'");
        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);
    }

    public int recoverPassword2(String loginName, String newPassword) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" update userinfo set password = '"+newPassword+"' where username ='"+loginName+"'");
        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);

    }

    public String userIsEmpty(String loginName) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" select login_name from f_user_info where login_name = '"+loginName+"'");
        System.out.println(sql);
        return this.commondao.queryOne(sql.toString(), paramList);
    }

    public String userIsEmpty2(String loginName) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" select user_tel from f_user_info where login_name = '"+loginName+"'");
        System.out.println(sql);
        return this.commondao.queryOne(sql.toString(), paramList);
    }
}
