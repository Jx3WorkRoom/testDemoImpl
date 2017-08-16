package com.action;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.service.alarmlogService;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/27 0027.
 */
@RestController
@Api(value = "alarmlogAction", description = "告警记录表基本增删改查通用接口Api", produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("alarmlog")
public class alarmlogAction {
    @Autowired
    alarmlogService alarmlogService;

    @ApiOperation(value="获取告警记录信息", notes="根据ID去获得告警记录信息",produces = "application/json")
    @RequestMapping(value="alarmlogInfo",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> getalarmLogInfo(
            @RequestParam(value="ids",required=false) String[] ids
    ){
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        Object dataList = alarmlogService.queryalarmlog(ids);
        resmap.put("datas", dataList);
            resmap.put("pageTion", dataList);
        resmap.put("success", true);
        long post=System.currentTimeMillis();
        System.out.println("查询告警记录接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }

    @ApiOperation(value="修改告警记录信息", notes="根据ID去修改告警记录信息",produces = "application/json")
    @RequestMapping(value="editalarmlog",method = RequestMethod.GET)
    @GET
    @Produces("application/json")
    public int editalarmlog(
            @RequestParam(value="id") int id,
            @RequestParam(value="AlarmLogID") int AlarmLogID,
            @RequestParam(value="AlarmType") int  AlarmType,
            @RequestParam(value="AlarmText") String  AlarmText,
            @RequestParam(value="OccurrenceTime") String  OccurrenceTime
    ) throws Exception {
        AlarmText = java.net.URLDecoder.decode(AlarmText,"utf-8");
        long pre=System.currentTimeMillis();
        int editSureFlag = alarmlogService.editalarmlog(id,AlarmLogID,AlarmType,AlarmText,OccurrenceTime);
        long post=System.currentTimeMillis();
        System.out.println("修改告警记录接口执行时间（单位：毫秒）："+ (post-pre));
        return editSureFlag;
    }

    @ApiOperation(value="新增告警记录", notes="新增告警记录",produces = "application/json")
    @RequestMapping(value = "addalarmlog",method = RequestMethod.GET)
    @GET
    @Produces("application/json")
    public int addalarmlog(
            @RequestParam(value="AlarmLogID") int AlarmLogID,
            @RequestParam(value="AlarmType") int  AlarmType,
            @RequestParam(value="AlarmText") String  AlarmText,
            @RequestParam(value="OccurrenceTime") String  OccurrenceTime
    ) throws Exception {
        AlarmText = java.net.URLDecoder.decode(AlarmText,"utf-8");
        long pre=System.currentTimeMillis();
        int addSureFlag = alarmlogService.addalarmlog(AlarmLogID,AlarmType,AlarmText,OccurrenceTime);
        long post=System.currentTimeMillis();
        System.out.println("新增告警记录接口执行时间（单位:毫秒）："+ (post-pre));
        return  addSureFlag;
    }

    @ApiOperation(value="删除告警记录", notes="删除告警记录",produces = "application/json")
    @RequestMapping(value = "delalarmlog",method = RequestMethod.GET)
    @GET
    @Produces("application/json")
    public int delalarmlog(
            @RequestParam(value="ids") String[] ids
    ) throws Exception {
        long pre=System.currentTimeMillis();
        int delSureFlag = alarmlogService.delalarmlog(ids);
        long post=System.currentTimeMillis();
        System.out.println("删除告警记录接口执行时间（单位:毫秒）："+ (post-pre));
        return  delSureFlag;
    }
}
