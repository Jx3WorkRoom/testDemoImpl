package com.action;

import com.service.accountListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.service.userInfoService;

import javax.ws.rs.Produces;
import java.util.HashMap;
import java.util.Map;

@RestController
@Api(value = "userInfoAction", description = "查询用户信息", produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("userInfo")
public class userInfoAction {
    @Autowired
    userInfoService userInfoService;

    @ApiOperation(value="获取用户信息", notes="",produces = "application/json")
    @RequestMapping(value="queryUserInfo",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> queryUserInfo(
            @RequestParam(value="shape",required=false,defaultValue ="") String shape,
            @RequestParam(value="type",required=false,defaultValue ="0") int type,
            @RequestParam(value="num",required=false,defaultValue ="0") int num
    ){
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        Object datas = new Object();
        if(!"".equals(shape)){
            datas = userInfoService.queryUserInfo1(shape,type,num);
        }else {
            datas = userInfoService.queryUserInfo(num);
        }
        Object pageList = userInfoService.queryPageListNum();
        resmap.put("datas", datas);
        resmap.put("pageList", pageList);
        resmap.put("success", true);
        long post=System.currentTimeMillis();
        System.out.println("查询用户信息接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }

    @ApiOperation(value="获取用户信息通过userId", notes="",produces = "application/json")
    @RequestMapping(value="queryUserInfoByUserId",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> queryUserInfoByUserId(
            @RequestParam(value="userId",required=false,defaultValue ="0") String userId
    ){
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        Object datas = userInfoService.queryUserInfoByUserId(userId);
        resmap.put("datas", datas);
        resmap.put("success", true);
        long post=System.currentTimeMillis();
        System.out.println("查询用户信息接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }

    @ApiOperation(value="修改锁定时间通过userId", notes="",produces = "application/json")
    @RequestMapping(value="editLockTime",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> editLockTime(
            @RequestParam(value="userId",required=false,defaultValue ="0") String userId,
            @RequestParam(value="hour",required=false,defaultValue ="0") int hour
    ){
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        Object info = userInfoService.editLockTime(userId,hour);
        resmap.put("info", info);
        resmap.put("success", true);
        long post=System.currentTimeMillis();
        System.out.println("查询用户信息接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }

    @ApiOperation(value="找回密码", notes="",produces = "application/json")
    @RequestMapping(value="recoverPassword",method = RequestMethod.GET)
    @Produces("application/json")
    public Map<String,Object> recoverPassword(
            @RequestParam(value="loginName",required=false,defaultValue ="0") String loginName,
            @RequestParam(value="newPassword",required=false,defaultValue ="0") String newPassword
    ){
        Map<String,Object> resmap=new HashMap<String,Object>();
        long pre=System.currentTimeMillis();
        Object info = userInfoService.recoverPassword(loginName,newPassword);
        resmap.put("info", info);
        resmap.put("success", true);
        long post=System.currentTimeMillis();
        System.out.println("查询用户信息接口执行时间（单位：毫秒）："+ (post-pre));
        return resmap;
    }
}
