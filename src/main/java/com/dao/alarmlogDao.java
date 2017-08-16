package com.dao;

import com.utils.CommonDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/27 0027.
 */
@Repository
public class alarmlogDao {
    private JdbcTemplate jdbcTemplate;
    private CommonDao commondao;
    public alarmlogDao(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
        if(this.jdbcTemplate !=null)
            this.commondao = new CommonDao(this.jdbcTemplate);
    }

    public List<Map<String,Object>> queryalarmlog(String[] ids) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        if(ids!=null) {
            sql.append("select * from alarmlog " +
                    " WHERE ID='" + ids[0] + "'");
            for (int i = 1; i < ids.length; i++) {
                sql.append(" || ID='" + ids[i] + "' ");
            }
        }else{
            sql.append("select * from alarmlog");
        }
        System.out.println(sql);
        return this.commondao.query(sql.toString(), paramList);
    }

    public int editalarmlog(int id,int alarmLogID, int alarmType, String alarmText, String occurrenceTime) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" update alarmlog set " +
                " alarmLogID = '"+alarmLogID+"',"+
                " alarmType = '"+alarmType+"',"+
                " alarmText = '"+alarmText+"',"+
                " occurrenceTime = '"+occurrenceTime+"'"+
                " where id = '"+id+"'");
        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);
    }

    public int addalarmlog(int alarmLogID, int alarmType, String alarmText, String occurrenceTime) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        String idSql = "Select max(id) from alarmlog";
        String idtemp =this.commondao.queryOne(idSql, paramList);
        int newId = Integer.parseInt(idtemp)+1;
        sql.append(" insert into alarmlog values('"+newId+"','"+alarmLogID+"','"+alarmType+"','"+alarmText+"','"+occurrenceTime+"') " );
        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);
    }

    public int delalarmlog(String[] ids) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<Object>();
        sql.append(" delete from alarmlog "+
                " WHERE ID=" + ids[0] + "");
        for (int i= 1;i<ids.length;i++){
            sql.append(" || ID="+ids[i]+" ");
        }
        System.out.println(sql);
        return this.commondao.update(sql.toString(), paramList);
    }
}
