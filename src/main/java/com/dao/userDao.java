package com.dao;

import com.utils.CommonDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/15 0015.
 */
@Repository
public class userDao {
    private JdbcTemplate jdbcTemplate;
    private CommonDao commondao;
    public userDao(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
        if(this.jdbcTemplate !=null)
            this.commondao = new CommonDao(this.jdbcTemplate);
    }

    /**
     * 查询所有用户信息
     * @param username
     */
    public List<Map<String,Object>> queryUser(String username) throws Exception{
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append("select * from userinfo where username = '"+username+"' limit 0 ,1");
        System.out.println(sql);
        return this.commondao.query(sql.toString(), paramList);
    }

    /**
     * 修改用户信息
     * @throws Exception
     */
    public int editUser(String id, String username,Integer usergroup, Integer userAuthority, String password, String employeeNo,String createTime, String role) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append("UPDATE USERINFO SET " +
                   "USERNAME = '"+username+"'," +
                   "USERGROUP = '"+usergroup+"'," +
                   "USERAUTHORITY = '"+userAuthority+"'," +
                   "PASSWORD = '"+password+"'," +
                   "EMPLOYEENO = '"+employeeNo+"'," +
                   "CREATETIME = '"+createTime+"'," +
                   "ROLE = '"+role+"'" +
                   " WHERE ID='"+id+"'");
        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);
    }

    /*
        删除用户
     */
    public int delUser(String[] ids) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        StringBuilder append = sql.append("DELETE FROM USERINFO " +
                    " WHERE ID=" + ids[0] + "");
                for (int i= 1;i<ids.length;i++){
                    sql.append(" || ID="+ids[i]+" ");
                }
        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);
    }

    /*
    添加用户
     */
    public int addUser(String username, String password, Integer userGroup, String employeeNo, Integer userAuthority, String createTime, String role) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        String idSql = "Select max(id) from userinfo";
        String idtemp =this.commondao.queryOne(idSql, paramList);
        int newId = Integer.parseInt(idtemp)+1;
        StringBuilder append = sql.append("INSERT INTO USERINFO VALUES("+newId+",'"+username+"','"+userGroup+"','"+userAuthority+"','"+password+"','"+employeeNo+"','"+role+"','"+createTime+"')");
        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);
    }

    /**
     *
     * @param str 实际执行create table 语句
     * @return
     * @throws Exception
     */
    public int testAddUserTable(String str) throws Exception {
        String sql = "CREATE TABLE `userinfoTest` (  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',  `USERNAME` varchar(50) DEFAULT NULL COMMENT '用户名',  `UserGroup` int(11) DEFAULT NULL COMMENT '用户类型',  `UserAuthority` int(11) DEFAULT NULL COMMENT '用户权限',  `PASSWORD` varchar(50) DEFAULT NULL COMMENT '登录口令',  `EmployeeNo` varchar(50) DEFAULT NULL COMMENT '人员工号',  `role` varchar(50) DEFAULT NULL COMMENT '角色',  `createTime` varchar(50) DEFAULT NULL COMMENT '创建时间',  PRIMARY KEY (`ID`)) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;";
        List<Object> paramList = new ArrayList<Object>();
        int testResult = this.commondao.update(sql.toString(), paramList);
        System.out.println(paramList);
        return testResult;
    }

    public List<Map<String,Object>> queryUserInfo(String username) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append("select * from f_user_info where login_name = '"+username+"' limit 0 ,1");
        System.out.println(sql);
        return this.commondao.query(sql.toString(), paramList);
    }

    public Object getUserId(String userName) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" select id from userinfo where username = '"+userName+"' limit 0,1 ");
        System.out.println(sql);
        return this.commondao.queryOne(sql.toString(), paramList);
    }

    public List<Map<String,Object>> queryUserById(String userId) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append("select * from f_user_info where user_id = '"+userId+"' limit 0 ,1");
        System.out.println(sql);
        return this.commondao.query(sql.toString(), paramList);
    }
}
