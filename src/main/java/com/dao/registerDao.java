package com.dao;

import com.utils.CommonDao;
import com.utils.MyDateTimeUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public class registerDao {

    private JdbcTemplate jdbcTemplate;
    private CommonDao commondao;
    public registerDao(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
        if(this.jdbcTemplate !=null)
            this.commondao = new CommonDao(this.jdbcTemplate);
    }

    public int addUser(String loginName, String userName, String loginWord, String tel) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        String recordId= UUID.randomUUID().toString();
        String createTime =  new MyDateTimeUtils().DateTimeToStr(new Date(), "yyyy-MM-dd hh:mm:ss").replace("\\s*","");
        sql.append(" INSERT INTO f_user_info (" +
                " record_id," +
                " CREATETIME," +
                " UPDATETIME," +
                " ISVALID," +
                " LOGIN_NAME," +
                " LOGIN_WORD," +
                " USER_NAME," +
                " USER_TEL" +
                " )" +
                " VALUES" +
                " (" +
                "'"+recordId+"'," +
                "'"+createTime+"'," +
                "'"+createTime+"'," +
                "1," +
                "'"+loginName+"'," +
                "'"+loginWord+"'," +
                "'"+userName+"'," +
                "'"+tel+"'" +
                ")");
        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);
    }

    public void addUser2(String loginName, String loginWord) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" insert into userinfo(id,username,password,role) values( (SELECT max(user_id) FROM f_user_info),'"+loginName+"','"+loginWord+"','ROLE_user')");
        System.out.println(sql);
        this.commondao.update(sql.toString(), paramList);
    }

    public int isHas(String loginName) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" select * from userinfo where username = '"+loginName+"'");
        System.out.println(sql);
        return this.commondao.query(sql.toString(), paramList).size();
    }

    public String checkIsEmpty1(String loginName) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" select login_name from f_user_info where login_name = '"+loginName+"' limit 0 ,1");
        System.out.println(sql);
        return this.commondao.queryOne(sql.toString(), paramList);
    }

    public String checkIsEmpty2(String userName) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" select user_name from f_user_info where user_name = '"+userName+"' limit 0 ,1");
        System.out.println(sql);
        return this.commondao.queryOne(sql.toString(), paramList);
    }

    public String checkIsEmpty3(String tel) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" select USER_TEL from f_user_info where USER_TEL = '"+tel+"' limit 0 ,1");
        System.out.println(sql);
        return this.commondao.queryOne(sql.toString(), paramList);
    }
}
